package dev.dassen.desolatedungeons.augment.function;

import com.mojang.serialization.Codec;
import dev.dassen.desolatedungeons.augment.context.AugmentExecutionContext;
import dev.dassen.desolatedungeons.registry.ModRegistries;
import org.jetbrains.annotations.NotNull;

public abstract class AugmentFunction {
    public static final Codec<AugmentFunction> CODEC = ModRegistries.AUGMENT_FUNCTION_TYPE.getCodec()
        .dispatch("type", AugmentFunction::getType, AugmentFunctionType::codec);
    protected State state = State.ENDED;

    public abstract @NotNull AugmentFunctionType<?> getType();

    public State tryStartingOrKeepRunning(AugmentExecutionContext context) {
        return run(context);
    }

    protected abstract State run(AugmentExecutionContext context);

    public State getState() {
        return state;
    }

    public enum State {
        ENDED,
        RUNNING
    }
}