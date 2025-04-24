package dev.dassen.desolatedungeons.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

public final class CrossDimensionalPlayerStateManager {
    public static void saveAndClearVanillaData(PlayerEntity player, NbtCompound nbt) {
        saveInventory(player, nbt);
        saveEnderItems(player, nbt);
        saveExperience(player, nbt);

        clearInventory(player);
        clearEnderChest(player);
        clearExperience(player);
    }

    private static void saveInventory(PlayerEntity player, NbtCompound nbt) {
        NbtList inventoryNbt = new NbtList();
        player.getInventory().writeNbt(inventoryNbt);
        nbt.put("Inventory", inventoryNbt);
    }

    private static void saveEnderItems(PlayerEntity player, NbtCompound nbt) {
        NbtList enderItemsNbt = player.getEnderChestInventory().toNbtList(player.getRegistryManager());
        nbt.put("EnderItems", enderItemsNbt);
    }

    private static void saveExperience(PlayerEntity player, NbtCompound nbt) {
        nbt.putInt("XpLevel", player.experienceLevel);
        nbt.putFloat("XpP", player.experienceProgress);
        nbt.putInt("XpTotal", player.totalExperience);
    }

    private static void clearInventory(PlayerEntity player) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            player.getInventory().removeStack(i);
        }
    }

    private static void clearEnderChest(PlayerEntity player) {
        for (int i = 0; i < player.getEnderChestInventory().size(); i++) {
            player.getEnderChestInventory().removeStack(i);
        }
    }

    private static void clearExperience(PlayerEntity player) {
        player.experienceLevel = 0;
        player.totalExperience = 0;
        player.experienceProgress = 0f;
    }

    public static void loadVanillaData(PlayerEntity player, NbtCompound nbt) {
        loadInventory(player, nbt);
        loadEnderItems(player, nbt);
        loadExperience(player, nbt);
    }

    private static void loadInventory(PlayerEntity player, NbtCompound nbt) {
        if (nbt.contains("Inventory", NbtElement.LIST_TYPE)) {
            player.getInventory().readNbt(nbt.getList("Inventory", NbtElement.COMPOUND_TYPE));
        }
    }

    private static void loadEnderItems(PlayerEntity player, NbtCompound nbt) {
        if (nbt.contains("EnderItems", NbtElement.LIST_TYPE)) {
            player.getEnderChestInventory().readNbtList(nbt.getList("EnderItems", NbtElement.COMPOUND_TYPE), player.getRegistryManager());
        }
    }

    private static void loadExperience(PlayerEntity player, NbtCompound nbt) {
        player.experienceLevel = nbt.getInt("XpLevel");
        player.experienceProgress = nbt.getFloat("XpP");
        player.totalExperience = nbt.getInt("XpTotal");
    }
}