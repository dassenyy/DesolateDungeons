package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public final class ModBiomeKeys {
    public static final RegistryKey<Biome> DESOLATE_DUNGEON = registryKeyOf("desolate_dungeon");

    private static RegistryKey<Biome> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
