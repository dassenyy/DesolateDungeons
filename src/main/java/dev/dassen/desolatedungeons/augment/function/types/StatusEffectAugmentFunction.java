package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.AugmentState;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.NotNull;

public class StatusEffectAugmentFunction implements AugmentFunction {
    public static final MapCodec<StatusEffectAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            StatusEffect.ENTRY_CODEC.fieldOf("status_effect").forGetter(augmentFunction -> augmentFunction.statusEffect),
            Codec.INT.optionalFieldOf("duration", 0).forGetter(augmentFunction -> augmentFunction.duration),
            Codecs.UNSIGNED_BYTE.optionalFieldOf("amplifier", 0).forGetter(augmentFunction -> augmentFunction.amplifier)
        ).apply(instance, StatusEffectAugmentFunction::new)
    );

    private final RegistryEntry<StatusEffect> statusEffect;
    private final int duration;
    private final int amplifier;

    public StatusEffectAugmentFunction(RegistryEntry<StatusEffect> statusEffect, int duration) {
        this(statusEffect, duration, 0);
    }

    public StatusEffectAugmentFunction(RegistryEntry<StatusEffect> statusEffect, int duration, int amplifier) {
        this.statusEffect = statusEffect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.STATUS_EFFECT;
    }

    @Override
    public AugmentState run(AugmentExecutionContext context) {
        context.serverPlayer().setStatusEffect(new StatusEffectInstance(statusEffect, duration, amplifier), null);

        return AugmentState.ENDED;
    }
}
