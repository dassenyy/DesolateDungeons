package dev.dassen.desolatedungeons.augment;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.function.EntityAttributeModificationAugmentFunction;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;

public class Augments {
    public static final Augment DAMAGE = new Augment(
        "Damage",
        new EntityAttributeModificationAugmentFunction(
            EntityAttributes.ATTACK_DAMAGE,
            Identifier.of(DesolateDungeons.MOD_ID, "damage_augment_function"),
            EntityAttributeModifier.Operation.ADD_VALUE,
            2d
        )
    );
    public static final Augment SPEED = new Augment(
        "Speed",
        new EntityAttributeModificationAugmentFunction(
            EntityAttributes.MOVEMENT_SPEED,
            Identifier.of(DesolateDungeons.MOD_ID, "speed_augment_function"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE,
            0.5d
        )
    );
    public static final Augment EMERGENCY_PUFFERFISH = new Augment(
        "Emergency Pufferfish",
        new EntityAttributeModificationAugmentFunction(
            EntityAttributes.ATTACK_DAMAGE,
            Identifier.of(DesolateDungeons.MOD_ID, "emergency_pufferfish_augment_function"),
            EntityAttributeModifier.Operation.ADD_VALUE,
            5d
        )
    );
}
