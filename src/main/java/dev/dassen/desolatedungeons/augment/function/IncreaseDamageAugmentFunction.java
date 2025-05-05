package dev.dassen.desolatedungeons.augment.function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;

public class IncreaseDamageAugmentFunction implements AugmentFunction {
    public static final MapCodec<IncreaseDamageAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            Codec.INT.fieldOf("amount").forGetter(thisAugmentFunction -> thisAugmentFunction.amount)
        ).apply(instance, IncreaseDamageAugmentFunction::new)
    );

    private final int amount;

    public IncreaseDamageAugmentFunction(int amount) {
        this.amount = amount;
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.INCREASE_DAMAGE;
    }
}
