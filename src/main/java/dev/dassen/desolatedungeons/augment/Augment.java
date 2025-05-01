package dev.dassen.desolatedungeons.augment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class Augment {
    public static final Codec<Augment> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter((augment -> augment.name))
        )
        .apply(instance, Augment::new)
    );

    public final String name;

    public Augment(String name) {
        this.name = name;
    }
}
