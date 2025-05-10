package dev.dassen.desolatedungeons.augment.function;

import dev.dassen.desolatedungeons.augment.Augment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public record AugmentFunctionContext(
    Augment augment,
    PlayerEntity player,
    World world
) { }