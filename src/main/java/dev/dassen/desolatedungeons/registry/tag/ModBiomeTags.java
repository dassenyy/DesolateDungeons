package dev.dassen.desolatedungeons.registry.tag;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public final class ModBiomeTags {
    public static final TagKey<Biome> SANDSWEPT_RUINS_HAS_STRUCTURE = tagKeyOf("has_structure/sandswept_ruins");

    private static TagKey<Biome> tagKeyOf(String path) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
