package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.context.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SequenceAugmentFunction extends AugmentFunction {
    public static final MapCodec<SequenceAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            AugmentFunction.CODEC.listOf().fieldOf("sequence").forGetter(augmentFunction -> augmentFunction.sequence)
        ).apply(instance, SequenceAugmentFunction::new)
    );

    private final List<AugmentFunction> sequence;
    private boolean anyStillRunning = false;

    public SequenceAugmentFunction(List<AugmentFunction> sequence) {
        if (sequence.isEmpty()) {
            throw new IllegalArgumentException("Need at least 1 augment function for the sequence");
        } else {
            this.sequence = sequence;
        }
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.SEQUENCE;
    }

    @Override
    protected State run(AugmentExecutionContext context) {
        if (!anyStillRunning) {
            for (AugmentFunction augmentFunction : sequence) {
                augmentFunction.tryStartingOrKeepRunning(context);
            }
        } else {
            anyStillRunning = false;
            for (AugmentFunction augmentFunction : sequence) {
                if (augmentFunction.getState() == State.RUNNING) {
                    State nestedFunctionState = augmentFunction.tryStartingOrKeepRunning(context);
                    if (nestedFunctionState == State.RUNNING) { anyStillRunning = true; }
                }
            }
        }

        if (anyStillRunning) {
            return state = State.RUNNING;
        } else {
            return state = State.ENDED;
        }
    }
}
