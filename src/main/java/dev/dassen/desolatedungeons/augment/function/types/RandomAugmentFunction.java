package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.context.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RandomAugmentFunction extends AugmentFunction {
    public static final MapCodec<RandomAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            Codec.pair(
                AugmentFunction.CODEC.fieldOf("augment_function").codec(),
                Codecs.POSITIVE_INT.fieldOf("weight").codec()
            ).listOf().fieldOf("weighted_augment_functions").forGetter(augmentFunction -> augmentFunction.weightedAugmentFunctions)
        ).apply(instance, RandomAugmentFunction::new)
    );

    private final List<Pair<AugmentFunction, Integer>> weightedAugmentFunctions;
    private final int totalWeight;
    private @Nullable AugmentFunction runningAugmentFunction;

    public RandomAugmentFunction(List<Pair<AugmentFunction, Integer>> weightedAugmentFunctions) {
        this.weightedAugmentFunctions = weightedAugmentFunctions;
        this.totalWeight = weightedAugmentFunctions.stream().mapToInt(Pair::getSecond).sum();
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.RANDOM;
    }

    @Override
    protected State run(AugmentExecutionContext context) {
        if (runningAugmentFunction != null) {
            State nestedFunctionState = runningAugmentFunction.tryStartingOrKeepRunning(context);
            if (nestedFunctionState == State.ENDED) { runningAugmentFunction = null; }
            return state = nestedFunctionState;
        }

        int randomWeight = context.serverWorld().random.nextInt(totalWeight);
        int cumulativeWeight = 0;

        for (Pair<AugmentFunction, Integer> weightedAugment : weightedAugmentFunctions) {
            cumulativeWeight += weightedAugment.getSecond();
            if (cumulativeWeight <= randomWeight) { continue; }

            State nestedFunctionState = weightedAugment.getFirst().tryStartingOrKeepRunning(context);
            if (nestedFunctionState == State.RUNNING) { runningAugmentFunction = weightedAugment.getFirst(); }
            return state = nestedFunctionState;
        }

        return state = State.RUNNING;
    }
}
