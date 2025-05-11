package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.condition.AugmentCondition;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import org.jetbrains.annotations.NotNull;

public class ConditionAugmentFunction implements AugmentFunction {
    public static final MapCodec<ConditionAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            AugmentCondition.CODEC.fieldOf("augment_condition").forGetter(augmentFunction -> augmentFunction.augmentCondition),
            AugmentFunction.CODEC.fieldOf("augment_function").forGetter(augmentFunction -> augmentFunction.augmentFunction)
        ).apply(instance, ConditionAugmentFunction::new)
    );

    private final AugmentCondition augmentCondition;
    private final AugmentFunction augmentFunction;

    public ConditionAugmentFunction(AugmentCondition augmentCondition, AugmentFunction augmentFunction) {
        this.augmentCondition = augmentCondition;
        this.augmentFunction = augmentFunction;
    }


    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.CONDITION;
    }

    @Override
    public void run(AugmentFunctionContext context) {
        if (augmentCondition.test(context)) {
            augmentFunction.run(context);
        } else {
            augmentFunction.pass(context);
        }
    }

    @Override
    public void pass(AugmentFunctionContext context) {
    }
}
