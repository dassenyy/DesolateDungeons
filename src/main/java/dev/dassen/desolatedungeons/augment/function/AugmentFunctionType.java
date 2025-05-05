package dev.dassen.desolatedungeons.augment.function;

import com.mojang.serialization.MapCodec;

public record AugmentFunctionType<T extends AugmentFunction>(MapCodec<T> codec) {
}