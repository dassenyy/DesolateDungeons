package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public final class ModLootTableKeys {
    public static RegistryKey<LootTable> SANDSWEPT_RUINS_COMMON = registryKeyOf("chests/sandswept_ruins_common");
    public static RegistryKey<LootTable> SANDSWEPT_RUINS_RARE = registryKeyOf("chests/sandswept_ruins_rare");

    public static RegistryKey<LootTable> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
