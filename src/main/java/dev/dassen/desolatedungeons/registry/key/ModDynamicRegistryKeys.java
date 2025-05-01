package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.Augment;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public final class ModDynamicRegistryKeys {
    public static final RegistryKey<Registry<Augment>> AUGMENT = registryKeyOf("augment");

    private static RegistryKey<Registry<Augment>> registryKeyOf(String path) {
        return RegistryKey.ofRegistry(Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
