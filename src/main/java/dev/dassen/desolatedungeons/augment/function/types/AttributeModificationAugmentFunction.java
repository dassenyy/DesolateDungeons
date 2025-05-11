package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class AttributeModificationAugmentFunction implements AugmentFunction {
    public static final MapCodec<AttributeModificationAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            EntityAttribute.CODEC.fieldOf("attribute").forGetter(augmentFunction -> augmentFunction.attribute),
            Identifier.CODEC.fieldOf("attributeModifierIdentifier").forGetter(augmentFunction -> augmentFunction.attributeModifierIdentifier),
            EntityAttributeModifier.Operation.CODEC.fieldOf("operation").forGetter(augmentFunction -> augmentFunction.operation),
            Codec.DOUBLE.fieldOf("amount").forGetter(augmentFunction -> augmentFunction.amount)
        ).apply(instance, AttributeModificationAugmentFunction::new)
    );

    private final RegistryEntry<EntityAttribute> attribute;
    private final Identifier attributeModifierIdentifier;
    private final EntityAttributeModifier.Operation operation;
    private final double amount;


    public AttributeModificationAugmentFunction(RegistryEntry<EntityAttribute> attribute, Identifier attributeModifierIdentifier, EntityAttributeModifier.Operation operation, double amount) {
        this.attribute = attribute;
        this.attributeModifierIdentifier = attributeModifierIdentifier;
        this.operation = operation;
        this.amount = amount;
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.ATTRIBUTE_MODIFICATION;
    }

    @Override
    public void run(AugmentFunctionContext context) {
        EntityAttributeInstance attributeInstance = context.player().getAttributeInstance(attribute);

        if (attributeInstance != null && attributeInstance.getModifier(attributeModifierIdentifier) == null) {
            attributeInstance.addTemporaryModifier(
                new EntityAttributeModifier(
                    attributeModifierIdentifier,
                    amount,
                    operation
                )
            );
        }
    }

    @Override
    public void pass(AugmentFunctionContext context) {
        EntityAttributeInstance attributeInstance = context.player().getAttributeInstance(attribute);

        if (attributeInstance != null && attributeInstance.getModifier(attributeModifierIdentifier) != null) {
            attributeInstance.removeModifier(attributeModifierIdentifier);
        }
    }
}
