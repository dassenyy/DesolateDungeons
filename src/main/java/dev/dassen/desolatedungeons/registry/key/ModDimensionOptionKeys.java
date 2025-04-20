package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionOptions;

public final class ModDimensionOptionKeys {
    public static final RegistryKey<DimensionOptions> DESOLATE_DUNGEON = registryKeyOf("desolate_dungeon");

    private static RegistryKey<DimensionOptions> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.DIMENSION, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
