package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;

public final class ModDimensionTypeKeys {
    public static final RegistryKey<DimensionType> DESOLATE_DUNGEON = registryKeyOf("desolate_dungeon");

    private static RegistryKey<DimensionType> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.DIMENSION_TYPE, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
