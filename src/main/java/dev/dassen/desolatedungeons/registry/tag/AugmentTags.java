package dev.dassen.desolatedungeons.registry.tag;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.Augment;
import dev.dassen.desolatedungeons.registry.key.ModRegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class AugmentTags {
    public static final TagKey<Augment> DESOLATE_DUNGEONS_SET = tagKeyOf("sets/desolate_dungeons");

    private static TagKey<Augment> tagKeyOf(String path) {
        return TagKey.of(ModRegistryKeys.AUGMENT, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
