package dev.dassen.desolatedungeons.augment;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.condition.types.YCoordinateInBetweenAugmentCondition;
import dev.dassen.desolatedungeons.augment.function.types.*;
import dev.dassen.desolatedungeons.registry.key.AugmentKeys;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registerable;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.List;

public class Augments {
    public static final Augment DAMAGE = new Augment(
        "Damage",
        new SetAttributeAugmentFunction(
            EntityAttributes.ATTACK_DAMAGE,
            Identifier.of(DesolateDungeons.MOD_ID, "augment_function_damage"),
            EntityAttributeModifier.Operation.ADD_VALUE,
            2d
        )
    );
    public static final Augment EMERGENCY_PUFFERFISH = new Augment(
        "Emergency Pufferfish",
        new ConditionAugmentFunction(
            new YCoordinateInBetweenAugmentCondition(UniformIntProvider.create(333, 333)),
            new WithCooldownAugmentFunction(
                new SummonEntityAugmentFunction(Identifier.of("minecraft", "pufferfish"), new NbtCompound()),
                ConstantIntProvider.create(50)
            )
        )
    );
    public static final Augment MINER_MANIA = new Augment(
        "Miner Mania",
        new ConditionAugmentFunction(
            new YCoordinateInBetweenAugmentCondition(UniformIntProvider.create(0, 192)),
            new SequenceAugmentFunction(List.of(
                new SetAttributeAugmentFunction(
                    EntityAttributes.BLOCK_BREAK_SPEED,
                    Identifier.of(DesolateDungeons.MOD_ID, "augment_function_miner_mania_block_break_speed"),
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE,
                    0.5d
                ),
                new SetAttributeAugmentFunction(
                    EntityAttributes.BLOCK_INTERACTION_RANGE,
                    Identifier.of(DesolateDungeons.MOD_ID, "augment_function_miner_mania_block_interaction_range"),
                    EntityAttributeModifier.Operation.ADD_VALUE,
                    1d
                ),
                new StatusEffectAugmentFunction(
                    StatusEffects.NIGHT_VISION,
                    20
                )
            )),
            new SequenceAugmentFunction(List.of(
                new RemoveAttributeAugmentFunction(
                    EntityAttributes.BLOCK_BREAK_SPEED,
                    Identifier.of(DesolateDungeons.MOD_ID, "augment_function_miner_mania_block_break_speed")
                ),
                new RemoveAttributeAugmentFunction(
                    EntityAttributes.BLOCK_INTERACTION_RANGE,
                    Identifier.of(DesolateDungeons.MOD_ID, "augment_function_miner_mania_block_interaction_range")
                )
            ))
        )
    );
    public static final Augment SPEED = new Augment(
        "Speed",
        new SetAttributeAugmentFunction(
            EntityAttributes.MOVEMENT_SPEED,
            Identifier.of(DesolateDungeons.MOD_ID, "augment_function_speed"),
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE,
            0.5d
        )
    );

    public static void bootstrap(Registerable<Augment> augmentRegisterable) {
        augmentRegisterable.register(
            AugmentKeys.DAMAGE,
            DAMAGE
        );
        augmentRegisterable.register(
            AugmentKeys.EMERGENCY_PUFFERFISH,
            EMERGENCY_PUFFERFISH
        );
        augmentRegisterable.register(
            AugmentKeys.MINER_MANIA,
            MINER_MANIA
        );
        augmentRegisterable.register(
            AugmentKeys.SPEED,
            SPEED
        );
    }
}
