package dev.dassen.desolatedungeons.registry.tag;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModBlockTags {
    public static final TagKey<Block> SCARAB_BEETLES_SPAWNABLE_ON = tagKeyOf("scarab_beetles_spawnable_on");

    private static TagKey<Block> tagKeyOf(String path) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
