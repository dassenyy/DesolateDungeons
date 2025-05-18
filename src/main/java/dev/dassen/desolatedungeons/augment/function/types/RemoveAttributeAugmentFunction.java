package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.AugmentState;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class RemoveAttributeAugmentFunction implements AugmentFunction {
    public static final MapCodec<RemoveAttributeAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            EntityAttribute.CODEC.fieldOf("attribute").forGetter(augmentFunction -> augmentFunction.attribute),
            Identifier.CODEC.fieldOf("custom_identifier").forGetter(augmentFunction -> augmentFunction.customIdentifier)
        ).apply(instance, RemoveAttributeAugmentFunction::new)
    );

    private final RegistryEntry<EntityAttribute> attribute;
    private final Identifier customIdentifier;

    public RemoveAttributeAugmentFunction(RegistryEntry<EntityAttribute> attribute, Identifier customIdentifier) {
        this.attribute = attribute;
        this.customIdentifier = customIdentifier;
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.REMOVE_ATTRIBUTE;
    }

    @Override
    public AugmentState run(AugmentFunctionContext context) {
        EntityAttributeInstance attributeInstance = context.player().getAttributeInstance(attribute);

        if (attributeInstance != null && attributeInstance.getModifier(customIdentifier) != null) {
            attributeInstance.removeModifier(customIdentifier);
        }

        return AugmentState.ENDED;
    }
}
