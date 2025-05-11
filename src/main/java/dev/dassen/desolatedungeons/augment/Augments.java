package dev.dassen.desolatedungeons.augment;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.condition.types.YCoordinateInBetweenAugmentCondition;
import dev.dassen.desolatedungeons.augment.function.types.AttributeModificationAugmentFunction;
import dev.dassen.desolatedungeons.augment.function.types.ConditionAugmentFunction;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class Augments {
    public static final Augment DAMAGE = new Augment(
        "Damage",
        new AttributeModificationAugmentFunction(
            EntityAttributes.ATTACK_DAMAGE,
            Identifier.of(DesolateDungeons.MOD_ID, "damage_augment_function"),
            EntityAttributeModifier.Operation.ADD_VALUE,
            2d
        )
    );
    public static final Augment SPEED = new Augment(
        "Speed",
        new AttributeModificationAugmentFunction(
            EntityAttributes.MOVEMENT_SPEED,
            Identifier.of(DesolateDungeons.MOD_ID, "speed_augment_function"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE,
            0.5d
        )
    );
    public static final Augment EMERGENCY_PUFFERFISH = new Augment(
        "Emergency Pufferfish",
        new AttributeModificationAugmentFunction(
            EntityAttributes.ATTACK_DAMAGE,
            Identifier.of(DesolateDungeons.MOD_ID, "emergency_pufferfish_augment_function"),
            EntityAttributeModifier.Operation.ADD_VALUE,
            5d
        )
    );
    public static final Augment MINER_MANIA = new Augment(
        "Miner Mania",
        new ConditionAugmentFunction(
            new YCoordinateInBetweenAugmentCondition(UniformIntProvider.create(0, 192)),
            new AttributeModificationAugmentFunction(
                EntityAttributes.BLOCK_BREAK_SPEED,
                Identifier.of(DesolateDungeons.MOD_ID, "miner_mania_augment_function"),
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE,
                0.5d
            )
        )
    );
}
