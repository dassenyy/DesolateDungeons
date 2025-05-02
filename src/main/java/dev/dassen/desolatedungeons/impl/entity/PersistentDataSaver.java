package dev.dassen.desolatedungeons.impl.entity;

import net.minecraft.nbt.NbtCompound;

public interface PersistentDataSaver {
    NbtCompound getPersistentData();
    NbtCompound getOrCreateNbtCompound(NbtCompound parentNbt, String key);
}