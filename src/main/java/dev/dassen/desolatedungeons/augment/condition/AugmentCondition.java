package dev.dassen.desolatedungeons.augment.condition;

import com.mojang.serialization.Codec;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionContext;
import dev.dassen.desolatedungeons.registry.ModRegistries;
import org.jetbrains.annotations.NotNull;

public interface AugmentCondition {
    Codec<AugmentCondition> CODEC = ModRegistries.AUGMENT_CONDITION_TYPE.getCodec()
        .dispatch("type", AugmentCondition::getType, AugmentConditionType::codec);

    @NotNull AugmentConditionType<?> getType();

    boolean test(AugmentFunctionContext context);
}
