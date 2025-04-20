package dev.dassen.desolatedungeons.registry.tag;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItemTags {
    public static final TagKey<Item> KHOPESHES = tagKeyOf("khopeshes");
    public static final TagKey<Item> ENCHANTABLE_KHOPESH = tagKeyOf("enchantable/khopesh");

    private static TagKey<Item> tagKeyOf(String path) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
