package dev.dassen.desolatedungeons.entity.data;

import net.minecraft.nbt.NbtCompound;

public interface PersistentDataSaver {
    NbtCompound getPersistentData();
    NbtCompound getOrCreateNbtCompound(NbtCompound parentNbt, String key);
}