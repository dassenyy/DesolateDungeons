package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.Augment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class AugmentKeys {
    public static final RegistryKey<Augment> EMERGENCY_PUFFERFISH = registryKeyOf("emergency_pufferfish");

    private static RegistryKey<Augment> registryKeyOf(String path) {
        return RegistryKey.of(ModDynamicRegistryKeys.AUGMENT, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
