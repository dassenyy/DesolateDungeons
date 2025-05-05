package dev.dassen.desolatedungeons.augment;

import dev.dassen.desolatedungeons.augment.function.IncreaseDamageAugmentFunction;

import java.util.Optional;

public class Augments {
    public static final Augment EMPTY = new Augment("Empty", Optional.empty());
    public static final Augment DAMAGE = new Augment("Damage", Optional.of(new IncreaseDamageAugmentFunction(2)));
    public static final Augment SPEED = new Augment("Speed",  Optional.of(new IncreaseDamageAugmentFunction(2)));
    public static final Augment EMERGENCY_PUFFERFISH = new Augment("Emergency Pufferfish", Optional.of(new IncreaseDamageAugmentFunction(2)));
}
