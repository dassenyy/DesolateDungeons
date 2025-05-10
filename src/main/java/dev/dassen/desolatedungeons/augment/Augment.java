package dev.dassen.desolatedungeons.augment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;

public class Augment {
    public static final Codec<Augment> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter((augment -> augment.name)),
            AugmentFunction.CODEC.fieldOf("augment_function").forGetter((augment -> augment.augmentFunction))
        )
        .apply(instance, Augment::new)
    );

    public final String name;
    public final AugmentFunction augmentFunction;

    public Augment(String name, AugmentFunction augmentFunction) {
        this.name = name;
        this.augmentFunction = augmentFunction;
    }
}
