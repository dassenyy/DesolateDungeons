package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.AugmentState;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import org.jetbrains.annotations.NotNull;

public class WithCooldownAugmentFunction implements AugmentFunction {
    public static final MapCodec<WithCooldownAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            AugmentFunction.CODEC.fieldOf("augment_function").forGetter(augmentFunction -> augmentFunction.augmentFunction),
            Codec.INT.fieldOf("cooldown").forGetter(augmentFunction -> augmentFunction.cooldown)
        ).apply(instance, WithCooldownAugmentFunction::new)
    );

    private final AugmentFunction augmentFunction;
    private final int cooldown;
    private long nextExecutionTick;

    public WithCooldownAugmentFunction(AugmentFunction augmentFunction, int cooldown) {
        this.augmentFunction = augmentFunction;
        this.cooldown = cooldown;
        this.nextExecutionTick = 0;
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.WITH_COOLDOWN;
    }

    @Override
    public AugmentState run(AugmentExecutionContext context) {
        if (context.serverWorld().getTime() >= nextExecutionTick) {
            AugmentState augmentState = augmentFunction.run(context);

            if (augmentState == AugmentState.ENDED) {
                nextExecutionTick = context.serverWorld().getTime() + cooldown;
            }
        }

        return AugmentState.ENDED;
    }
}
