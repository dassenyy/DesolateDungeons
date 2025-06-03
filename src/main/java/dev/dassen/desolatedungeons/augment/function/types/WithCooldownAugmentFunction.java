package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.context.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import net.minecraft.util.math.intprovider.IntProvider;
import org.jetbrains.annotations.NotNull;

public class WithCooldownAugmentFunction extends AugmentFunction {
    public static final MapCodec<WithCooldownAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            AugmentFunction.CODEC.fieldOf("augment_function").forGetter(augmentFunction -> augmentFunction.augmentFunction),
            IntProvider.POSITIVE_CODEC.fieldOf("cooldown_int_provider").forGetter(augmentFunction -> augmentFunction.cooldownIntProvider)
        ).apply(instance, WithCooldownAugmentFunction::new)
    );

    private final AugmentFunction augmentFunction;
    private final IntProvider cooldownIntProvider;
    private long nextExecutionTick;

    public WithCooldownAugmentFunction(AugmentFunction augmentFunction, IntProvider cooldownIntProvider) {
        this.augmentFunction = augmentFunction;
        this.cooldownIntProvider = cooldownIntProvider;
        this.nextExecutionTick = 0;
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.WITH_COOLDOWN;
    }

    @Override
    protected State run(AugmentExecutionContext context) {
        if (context.time() < nextExecutionTick) {
            return state = State.RUNNING;
        } else { // context.time() >= nextExecutionTick
            State nestedFunctionState = augmentFunction.tryStartingOrKeepRunning(context);

            if (nestedFunctionState == State.ENDED) {
                nextExecutionTick = context.time() + cooldownIntProvider.get(context.serverWorld().random);
                return state = State.ENDED;
            }

            return state = State.RUNNING;
        }
    }
}
