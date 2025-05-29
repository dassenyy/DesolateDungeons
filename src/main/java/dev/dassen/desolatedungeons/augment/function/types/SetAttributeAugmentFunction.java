package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.AugmentState;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class SetAttributeAugmentFunction implements AugmentFunction {
    public static final MapCodec<SetAttributeAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            EntityAttribute.CODEC.fieldOf("attribute").forGetter(augmentFunction -> augmentFunction.attribute),
            Identifier.CODEC.fieldOf("custom_identifier").forGetter(augmentFunction -> augmentFunction.customIdentifier),
            EntityAttributeModifier.Operation.CODEC.fieldOf("operation").forGetter(augmentFunction -> augmentFunction.operation),
            Codec.DOUBLE.fieldOf("amount").forGetter(augmentFunction -> augmentFunction.amount)
        ).apply(instance, SetAttributeAugmentFunction::new)
    );

    private final RegistryEntry<EntityAttribute> attribute;
    private final Identifier customIdentifier;
    private final EntityAttributeModifier.Operation operation;
    private final double amount;


    public SetAttributeAugmentFunction(RegistryEntry<EntityAttribute> attribute, Identifier customIdentifier, EntityAttributeModifier.Operation operation, double amount) {
        this.attribute = attribute;
        this.customIdentifier = customIdentifier;
        this.operation = operation;
        this.amount = amount;
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.SET_ATTRIBUTE;
    }

    @Override
    public AugmentState run(AugmentExecutionContext context) {
        EntityAttributeInstance attributeInstance = context.serverPlayer().getAttributeInstance(attribute);

        if (attributeInstance != null && attributeInstance.getModifier(customIdentifier) == null) {
            attributeInstance.addTemporaryModifier(
                new EntityAttributeModifier(
                    customIdentifier,
                    amount,
                    operation
                )
            );
        }

        return AugmentState.ENDED;
    }
}
