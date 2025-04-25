package dev.dassen.desolatedungeons.entity.player;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

public final class CrossDimensionalPlayerStateManager {
    public static void saveAndClearVanillaData(PlayerEntity player, NbtCompound nbt) {
        saveInventory(player, nbt);
        saveEnderItems(player, nbt);
        saveHealth(player, nbt);
        saveHunger(player, nbt);
        saveExperience(player, nbt);
        saveEffects(player, nbt);

        clearInventory(player);
        clearEnderChest(player);
        clearHealth(player);
        clearHunger(player);
        clearExperience(player);
        clearEffects(player);
    }

    public static void loadVanillaData(PlayerEntity player, NbtCompound nbt) {
        loadInventory(player, nbt);
        loadEnderItems(player, nbt);
        loadHealth(player, nbt);
        loadExperience(player, nbt);
        loadHunger(player, nbt);
        loadEffects(player, nbt);
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

    private static void saveHealth(PlayerEntity player, NbtCompound nbt) {
        nbt.putFloat("Health", player.getHealth());
        nbt.putFloat("AbsorptionAmount", player.getAbsorptionAmount());
    }

    private static void saveHunger(PlayerEntity player, NbtCompound nbt) {
        nbt.putInt("foodLevel", player.getHungerManager().getFoodLevel());
        nbt.putFloat("foodSaturationLevel", player.getHungerManager().getSaturationLevel());
    }

    private static void saveExperience(PlayerEntity player, NbtCompound nbt) {
        nbt.putInt("XpLevel", player.experienceLevel);
        nbt.putFloat("XpP", player.experienceProgress);
        nbt.putInt("XpTotal", player.totalExperience);
    }

    private static void saveEffects(PlayerEntity player, NbtCompound nbt) {
        NbtList effectsNbt = new NbtList();
        for (StatusEffectInstance statusEffectInstance : player.getStatusEffects()) {
            effectsNbt.add(statusEffectInstance.writeNbt());
        }
        nbt.put("active_effects", effectsNbt);
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

    private static void clearHealth(PlayerEntity player) {
        player.setHealth(20);
        player.setAbsorptionAmount(0);
    }

    private static void clearHunger(PlayerEntity player) {
        player.getHungerManager().setFoodLevel(20);
        player.getHungerManager().setSaturationLevel(5f);
    }

    private static void clearExperience(PlayerEntity player) {
        player.experienceLevel = 0;
        player.totalExperience = 0;
        player.experienceProgress = 0f;
    }

    private static void clearEffects(PlayerEntity player) {
        player.clearStatusEffects();
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

    private static void loadHealth(PlayerEntity player, NbtCompound nbt) {
        player.setHealth(nbt.getFloat("Health"));
        player.setAbsorptionAmount(nbt.getFloat("AbsorptionAmount"));
    }

    private static void loadHunger(PlayerEntity player, NbtCompound nbt) {
        player.getHungerManager().setFoodLevel(nbt.getInt("foodLevel"));
        player.getHungerManager().setSaturationLevel(nbt.getFloat("foodSaturationLevel"));
    }

    private static void loadExperience(PlayerEntity player, NbtCompound nbt) {
        player.experienceLevel = nbt.getInt("XpLevel");
        player.experienceProgress = nbt.getFloat("XpP");
        player.totalExperience = nbt.getInt("XpTotal");
    }

    private static void loadEffects(PlayerEntity player, NbtCompound nbt) {
        player.clearStatusEffects();

        if (nbt.contains("active_effects", NbtElement.LIST_TYPE)) {
            NbtList effectsNbt = nbt.getList("active_effects", NbtElement.COMPOUND_TYPE);

            for (int i = 0; i < effectsNbt.size(); i++) {
                NbtCompound effectNbt = effectsNbt.getCompound(i);
                StatusEffectInstance statusEffect = StatusEffectInstance.fromNbt(effectNbt);
                if (statusEffect != null) {
                    player.setStatusEffect(statusEffect, null);
                }
            }
        }
    }
}