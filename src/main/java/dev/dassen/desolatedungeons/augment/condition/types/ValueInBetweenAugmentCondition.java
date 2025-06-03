package dev.dassen.desolatedungeons.augment.condition.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.condition.AugmentCondition;
import dev.dassen.desolatedungeons.augment.condition.AugmentConditionType;
import dev.dassen.desolatedungeons.augment.condition.AugmentConditionTypes;
import dev.dassen.desolatedungeons.augment.context.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.context.ContextValue;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import org.jetbrains.annotations.NotNull;

public class ValueInBetweenAugmentCondition implements AugmentCondition {
    public static final MapCodec<ValueInBetweenAugmentCondition> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            ContextValue.CODEC.fieldOf("value").forGetter(augmentCondition -> augmentCondition.value),
            UniformFloatProvider.CODEC.fieldOf("uniform_float_provider").forGetter(augmentCondition -> augmentCondition.uniformFloatProvider)
        ).apply(instance, ValueInBetweenAugmentCondition::new)
    );

    private final ContextValue value;
    private final UniformFloatProvider uniformFloatProvider;

    public ValueInBetweenAugmentCondition(ContextValue value, UniformFloatProvider uniformFloatProvider) {
        this.value = value;
        this.uniformFloatProvider = uniformFloatProvider;
    }


    @Override
    public @NotNull AugmentConditionType<?> getType() {
        return AugmentConditionTypes.VALUE_IN_BETWEEN;
    }

    @Override
    public boolean test(AugmentExecutionContext context) {
        return uniformFloatProvider.getMin() <= value.getValue(context) && value.getValue(context) <= uniformFloatProvider.getMax();
    }
}
