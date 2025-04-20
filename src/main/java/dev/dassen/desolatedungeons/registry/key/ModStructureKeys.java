package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

public final class ModStructureKeys {
    public static final RegistryKey<Structure> SANDSWEPT_RUINS = registryKeyOf("sandswept_ruins");

    private static RegistryKey<Structure> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.STRUCTURE, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
