package dev.dassen.desolatedungeons.util;

import net.minecraft.nbt.NbtCompound;

public interface PersistentDataSaver {
    NbtCompound getPersistentData();
    NbtCompound getOrCreateNbtCompound(NbtCompound parentNbt, String key);
}