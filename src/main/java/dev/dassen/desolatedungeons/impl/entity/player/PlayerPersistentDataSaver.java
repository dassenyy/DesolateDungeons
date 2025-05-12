package dev.dassen.desolatedungeons.impl.entity.player;

import net.minecraft.nbt.NbtCompound;

public interface PlayerPersistentDataSaver {
    NbtCompound getStashedPlayerData();
}