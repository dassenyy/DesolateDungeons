package dev.dassen.desolatedungeons.augment;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.condition.types.YCoordinateInBetweenAugmentCondition;
import dev.dassen.desolatedungeons.augment.function.types.AttributeModificationAugmentFunction;
import dev.dassen.desolatedungeons.augment.function.types.ConditionAugmentFunction;
import dev.dassen.desolatedungeons.augment.function.types.SequenceAugmentFunction;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.List;

public class Augments {
    public static final Augment DAMAGE = new Augment(
        "Damage",
        new AttributeModificationAugmentFunction(
            EntityAttributes.ATTACK_DAMAGE,
            Identifier.of(DesolateDungeons.MOD_ID, "augment_function_damage"),
            EntityAttributeModifier.Operation.ADD_VALUE,
            2d
        )
    );
    public static final Augment SPEED = new Augment(
        "Speed",
        new AttributeModificationAugmentFunction(
            EntityAttributes.MOVEMENT_SPEED,
            Identifier.of(DesolateDungeons.MOD_ID, "augment_function_speed"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE,
            0.5d
        )
    );
    public static final Augment EMERGENCY_PUFFERFISH = new Augment(
        "Emergency Pufferfish",
        new AttributeModificationAugmentFunction(
            EntityAttributes.ATTACK_DAMAGE,
            Identifier.of(DesolateDungeons.MOD_ID, "augment_function_emergency_pufferfish"),
            EntityAttributeModifier.Operation.ADD_VALUE,
            5d
        )
    );
    public static final Augment MINER_MANIA = new Augment(
        "Miner Mania",
        new ConditionAugmentFunction(
            new YCoordinateInBetweenAugmentCondition(UniformIntProvider.create(0, 192)),
            new SequenceAugmentFunction(List.of(
                new AttributeModificationAugmentFunction(
                    EntityAttributes.BLOCK_BREAK_SPEED,
                    Identifier.of(DesolateDungeons.MOD_ID, "augment_function_miner_mania_block_break_speed"),
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE,
                    0.5d
                ),
                new AttributeModificationAugmentFunction(
                    EntityAttributes.BLOCK_INTERACTION_RANGE,
                    Identifier.of(DesolateDungeons.MOD_ID, "augment_function_miner_mania_block_interaction_range"),
                    EntityAttributeModifier.Operation.ADD_VALUE,
                    1d
                )
            ))
        )
    );
}
