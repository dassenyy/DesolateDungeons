package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.NotNull;

public class StatusEffectAugmentFunction implements AugmentFunction {
    public static final MapCodec<StatusEffectAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            StatusEffectInstance.CODEC.fieldOf("status_effect_instance").forGetter(augmentFunction -> augmentFunction.statusEffectInstance)
        ).apply(instance, StatusEffectAugmentFunction::new)
    );

    private final StatusEffectInstance statusEffectInstance;

    public StatusEffectAugmentFunction(StatusEffectInstance statusEffectInstance) {
        this.statusEffectInstance = statusEffectInstance;
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.STATUS_EFFECT;
    }

    @Override
    public void run(AugmentFunctionContext context) {
        context.player().setStatusEffect(statusEffectInstance, null);
    }

    @Override
    public void pass(AugmentFunctionContext context) {
        context.player().removeStatusEffect(statusEffectInstance.getEffectType());
    }
}
