package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.AugmentFunctionState;
import dev.dassen.desolatedungeons.augment.condition.AugmentCondition;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConditionAugmentFunction extends AugmentFunction {
    public static final MapCodec<ConditionAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            AugmentCondition.CODEC.fieldOf("augment_condition").forGetter(augmentFunction -> augmentFunction.augmentCondition),
            AugmentFunction.CODEC.fieldOf("if_true_augment_function").forGetter(augmentFunction -> augmentFunction.ifTrueAugmentFunction),
            AugmentFunction.CODEC.optionalFieldOf("if_false_augment_function", null).forGetter(augmentFunction -> augmentFunction.ifFalseAugmentFunction)
        ).apply(instance, ConditionAugmentFunction::new)
    );

    private final AugmentCondition augmentCondition;
    private final AugmentFunction ifTrueAugmentFunction;
    private final @Nullable AugmentFunction ifFalseAugmentFunction;

    public ConditionAugmentFunction(AugmentCondition augmentCondition, AugmentFunction ifTrueAugmentFunction, @Nullable AugmentFunction ifFalseAugmentFunction) {
        this.augmentCondition = augmentCondition;
        this.ifTrueAugmentFunction = ifTrueAugmentFunction;
        this.ifFalseAugmentFunction = ifFalseAugmentFunction;
    }

    public ConditionAugmentFunction(AugmentCondition augmentCondition, AugmentFunction ifTrueAugmentFunction) {
        this(augmentCondition, ifTrueAugmentFunction, null);
    }


    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.CONDITION;
    }

    @Override
    protected AugmentFunctionState run(AugmentExecutionContext context) {
        if (ifTrueAugmentFunction.getState() == AugmentFunctionState.RUNNING) {
            AugmentFunctionState nestedFunctionState = ifTrueAugmentFunction.tryStartingOrKeepRunning(context);
            return state = nestedFunctionState;
        } else if (ifFalseAugmentFunction != null && ifFalseAugmentFunction.getState() == AugmentFunctionState.RUNNING) {
            AugmentFunctionState nestedFunctionState = ifFalseAugmentFunction.tryStartingOrKeepRunning(context);
            return state = nestedFunctionState;
        }

        if (augmentCondition.test(context)) {
            AugmentFunctionState nestedFunctionState = ifTrueAugmentFunction.tryStartingOrKeepRunning(context);
            return state = nestedFunctionState;
        } else if (ifFalseAugmentFunction != null) {
            AugmentFunctionState nestedFunctionState = ifFalseAugmentFunction.tryStartingOrKeepRunning(context);
            return state = nestedFunctionState;
        }

        return state = AugmentFunctionState.RUNNING;
    }
}
