package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.Augment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public final class AugmentKeys {
    public static final RegistryKey<Augment> EMERGENCY_PUFFERFISH = registryKeyOf("emergency_pufferfish");
    public static final RegistryKey<Augment> DAMAGE = registryKeyOf("damage");
    public static final RegistryKey<Augment> SPEED = registryKeyOf("speed");

    private static RegistryKey<Augment> registryKeyOf(String path) {
        return RegistryKey.of(ModRegistryKeys.AUGMENT, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
