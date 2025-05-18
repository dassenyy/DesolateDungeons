package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.AugmentState;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SequenceAugmentFunction implements AugmentFunction {
    public static final MapCodec<SequenceAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            AugmentFunction.CODEC.listOf().fieldOf("sequence").forGetter(augmentFunction -> augmentFunction.sequence)
        ).apply(instance, SequenceAugmentFunction::new)
    );

    private final List<AugmentFunction> sequence;

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
    public AugmentState run(AugmentFunctionContext context) {
        for (AugmentFunction augmentFunction : sequence) {
            augmentFunction.run(context);
        }

        return AugmentState.ENDED;
    }
}
