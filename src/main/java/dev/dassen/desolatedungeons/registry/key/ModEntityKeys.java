package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public final class ModEntityKeys {
    public static final RegistryKey<EntityType<?>> SCARAB_BEETLE = registryKeyOf("scarab_beetle");

    private static RegistryKey<EntityType<?>> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
