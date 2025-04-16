package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;

public final class DesolateStructurePoolKeys {
    public static final RegistryKey<StructurePool> SANDSWEPT_RUINS_KEY = registryKeyOf("sandswept_ruins/start_pool");

    public static RegistryKey<StructurePool> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.TEMPLATE_POOL, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
