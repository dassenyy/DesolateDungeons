package dev.dassen.desolatedungeons.augment.function;

import com.mojang.serialization.Codec;
import dev.dassen.desolatedungeons.registry.ModRegistries;
import org.jetbrains.annotations.NotNull;

public interface AugmentFunction {
    Codec<AugmentFunction> CODEC = ModRegistries.AUGMENT_FUNCTION_TYPE.getCodec()
        .dispatch("type", AugmentFunction::getType, AugmentFunctionType::codec);

    @NotNull AugmentFunctionType<?> getType();

    void run(AugmentFunctionContext context);

    void pass(AugmentFunctionContext context);
}