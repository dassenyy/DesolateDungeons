package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.Identifier;

public final class DesolateStructureSetKeys {
    public static final RegistryKey<StructureSet> SANDSWEPT_RUINS = registryKeyOf("sandswept_ruins");

    public static RegistryKey<StructureSet> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.STRUCTURE_SET, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
