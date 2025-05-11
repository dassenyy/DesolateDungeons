package dev.dassen.desolatedungeons.augment.condition;

import com.mojang.serialization.MapCodec;

public record AugmentConditionType<T extends AugmentCondition>(MapCodec<T> codec) {
}
