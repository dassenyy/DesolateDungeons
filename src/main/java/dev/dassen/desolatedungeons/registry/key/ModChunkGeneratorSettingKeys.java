package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

public final class ModChunkGeneratorSettingKeys {
    public static final RegistryKey<ChunkGeneratorSettings> DESOLATE_DUNGEON = registryKeyOf("desolate_dungeon");

    private static RegistryKey<ChunkGeneratorSettings> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.CHUNK_GENERATOR_SETTINGS, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
