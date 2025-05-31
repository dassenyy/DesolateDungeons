package dev.dassen.desolatedungeons.augment.function;

import com.mojang.serialization.Codec;
import dev.dassen.desolatedungeons.augment.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.AugmentFunctionState;
import dev.dassen.desolatedungeons.registry.ModRegistries;
import org.jetbrains.annotations.NotNull;

public abstract class AugmentFunction {
    public static final Codec<AugmentFunction> CODEC = ModRegistries.AUGMENT_FUNCTION_TYPE.getCodec()
        .dispatch("type", AugmentFunction::getType, AugmentFunctionType::codec);
    protected AugmentFunctionState state = AugmentFunctionState.ENDED;

    public abstract @NotNull AugmentFunctionType<?> getType();

    public AugmentFunctionState tryStartingOrKeepRunning(AugmentExecutionContext context) {
        return run(context);
    }

    protected abstract AugmentFunctionState run(AugmentExecutionContext context);

    public AugmentFunctionState getState() {
        return state;
    }
}