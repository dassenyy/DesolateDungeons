package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.condition.AugmentCondition;
import dev.dassen.desolatedungeons.augment.context.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

// Making ifFalseAugmentFunction Nullable is not an option because null can't be used as default value for an optional field
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class ConditionAugmentFunction extends AugmentFunction {
    public static final MapCodec<ConditionAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            AugmentCondition.CODEC.fieldOf("augment_condition").forGetter(augmentFunction -> augmentFunction.augmentCondition),
            AugmentFunction.CODEC.fieldOf("if_true_augment_function").forGetter(augmentFunction -> augmentFunction.ifTrueAugmentFunction),
            AugmentFunction.CODEC.optionalFieldOf("if_false_augment_function").forGetter(augmentFunction -> augmentFunction.ifFalseAugmentFunction)
        ).apply(instance, ConditionAugmentFunction::new)
    );

    private final AugmentCondition augmentCondition;
    private final AugmentFunction ifTrueAugmentFunction;
    private final Optional<AugmentFunction> ifFalseAugmentFunction;

    public ConditionAugmentFunction(AugmentCondition augmentCondition, AugmentFunction ifTrueAugmentFunction, Optional<AugmentFunction> ifFalseAugmentFunction) {
        this.augmentCondition = augmentCondition;
        this.ifTrueAugmentFunction = ifTrueAugmentFunction;
        this.ifFalseAugmentFunction = ifFalseAugmentFunction;
    }

    public ConditionAugmentFunction(AugmentCondition augmentCondition, AugmentFunction ifTrueAugmentFunction) {
        this(augmentCondition, ifTrueAugmentFunction, Optional.empty());
    }

    public ConditionAugmentFunction(AugmentCondition augmentCondition, AugmentFunction ifTrueAugmentFunction, AugmentFunction ifFalseAugmentFunction) {
        this(augmentCondition, ifTrueAugmentFunction, Optional.of(ifFalseAugmentFunction));
    }


    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.CONDITION;
    }

    @Override
    protected State run(AugmentExecutionContext context) {
        if (ifTrueAugmentFunction.getState() == State.RUNNING) {
            State nestedFunctionState = ifTrueAugmentFunction.tryStartingOrKeepRunning(context);
            return state = nestedFunctionState;
        } else if (ifFalseAugmentFunction.isPresent() && ifFalseAugmentFunction.get().getState() == State.RUNNING) {
            State nestedFunctionState = ifFalseAugmentFunction.get().tryStartingOrKeepRunning(context);
            return state = nestedFunctionState;
        }

        if (augmentCondition.test(context)) {
            State nestedFunctionState = ifTrueAugmentFunction.tryStartingOrKeepRunning(context);
            return state = nestedFunctionState;
        } else if (ifFalseAugmentFunction.isPresent()) {
            State nestedFunctionState = ifFalseAugmentFunction.get().tryStartingOrKeepRunning(context);
            return state = nestedFunctionState;
        }

        return state = State.RUNNING;
    }
}
