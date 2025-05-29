package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.AugmentState;
import dev.dassen.desolatedungeons.augment.condition.AugmentCondition;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ConditionAugmentFunction implements AugmentFunction {
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


    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.CONDITION;
    }

    @Override
    public AugmentState run(AugmentExecutionContext context) {
        if (augmentCondition.test(context)) {
            ifTrueAugmentFunction.run(context);
        } else if (ifFalseAugmentFunction.isPresent()) {
            ifFalseAugmentFunction.get().run(context);
        }

        return AugmentState.ENDED;
    }
}
