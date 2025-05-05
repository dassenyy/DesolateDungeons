package dev.dassen.desolatedungeons.augment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;

import java.util.Optional;

// The alternative would be calling AugmentFunction.CODEC.optionalFieldOf() with a default "empty" AugmentFunction, which itself would need to have a dummy field.
// Also Minecraft's loot functions and predicates work and serialize the same way so I think it should be fine.
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Augment {
    public static final Codec<Augment> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter((augment -> augment.name)),
            AugmentFunction.CODEC.optionalFieldOf("on_grant_function").forGetter((augment -> augment.onGrantFunction))
        )
        .apply(instance, Augment::new)
    );

    public final String name;
    public final Optional<AugmentFunction> onGrantFunction;

    public Augment(String name, Optional<AugmentFunction> onGrantFunction) {
        this.name = name;
        this.onGrantFunction = onGrantFunction;
    }
}
