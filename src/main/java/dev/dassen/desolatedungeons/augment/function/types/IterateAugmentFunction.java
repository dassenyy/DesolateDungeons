package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.context.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import org.jetbrains.annotations.NotNull;

public class IterateAugmentFunction extends AugmentFunction {
    public static final MapCodec<IterateAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            AugmentFunction.CODEC.fieldOf("augment_function")
                .forGetter(augmentFunction -> augmentFunction.augmentFunction),
            IntProvider.POSITIVE_CODEC.fieldOf("iteration_int_provider").
                forGetter(augmentFunction -> augmentFunction.iterationIntProvider),
            IntProvider.NON_NEGATIVE_CODEC.optionalFieldOf("interval_ticks_int_provider", ConstantIntProvider.ZERO)
                .forGetter(augmentFunction -> augmentFunction.intervalTicksIntProvider),
            Codec.BOOL.optionalFieldOf("start_with_interval", false)
                .forGetter(augmentFunction -> augmentFunction.startWithInterval)
        ).apply(instance, IterateAugmentFunction::new)
    );

    private final AugmentFunction augmentFunction;
    private final IntProvider iterationIntProvider;
    private final IntProvider intervalTicksIntProvider;
    private final boolean startWithInterval;
    private int iterations;
    private int currentIteration;
    private long nextExecutionTick;

    public IterateAugmentFunction(
        AugmentFunction augmentFunction,
        IntProvider iterationIntProvider,
        IntProvider intervalTicksIntProvider,
        boolean startWithInterval
    ) {
        this.augmentFunction = augmentFunction;
        this.iterationIntProvider = iterationIntProvider;
        this.intervalTicksIntProvider = intervalTicksIntProvider;
        this.startWithInterval = startWithInterval;
    }

    public IterateAugmentFunction(AugmentFunction augmentFunction, IntProvider iterationIntProvider) {
        this(augmentFunction, iterationIntProvider, ConstantIntProvider.ZERO, false);
    }

    public IterateAugmentFunction(AugmentFunction augmentFunction, IntProvider iterationIntProvider, IntProvider intervalTicksIntProvider) {
        this(augmentFunction, iterationIntProvider, intervalTicksIntProvider, false);
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.ITERATE;
    }

    @Override
    protected State run(AugmentExecutionContext context) {
        if (state == State.ENDED) {
            iterations = iterationIntProvider.get(context.serverWorld().random);
            currentIteration = 0;
            nextExecutionTick = startWithInterval ? context.time() + intervalTicksIntProvider.get(context.serverWorld().random) : context.time();
        }

        if (context.time() >= nextExecutionTick) {
            State nestedFunctionState = augmentFunction.tryStartingOrKeepRunning(context);

            if (nestedFunctionState == State.ENDED) {
                currentIteration++;
                if (currentIteration >= iterations) { return state = State.ENDED; }
                nextExecutionTick = context.time() + intervalTicksIntProvider.get(context.serverWorld().random);
            }
        }

        return state = State.RUNNING;
    }
}
