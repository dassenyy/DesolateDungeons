package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.block.ModBlocks;
import dev.dassen.desolatedungeons.item.ModItems;
import dev.dassen.desolatedungeons.registry.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup) {
        configureKhopeshes();

        getOrCreateTagBuilder(ItemTags.STAIRS)
            .add(ModBlocks.SANDSTONE_BRICK_STAIRS.asItem())
            .add(ModBlocks.LATERITE_BRICK_STAIRS.asItem());

        getOrCreateTagBuilder(ItemTags.SLABS)
            .add(ModBlocks.SANDSTONE_BRICK_SLAB.asItem())
            .add(ModBlocks.LATERITE_BRICK_SLAB.asItem());

        getOrCreateTagBuilder(ItemTags.WALLS)
            .add(ModBlocks.SANDSTONE_BRICK_WALL.asItem())
            .add(ModBlocks.LATERITE_BRICK_WALL.asItem());
    }

    private void configureKhopeshes() {
        getOrCreateTagBuilder(ModItemTags.KHOPESHES)
            .add(ModItems.WOODEN_KHOPESH)
            .add(ModItems.STONE_KHOPESH)
            .add(ModItems.IRON_KHOPESH)
            .add(ModItems.GOLDEN_KHOPESH)
            .add(ModItems.DIAMOND_KHOPESH)
            .add(ModItems.NETHERITE_KHOPESH);

        getOrCreateTagBuilder(conventionalTagKeyOf("tools"))
            .addOptionalTag(ModItemTags.KHOPESHES);

        // Vanilla melee weapons are both in #c:tools/melee_weapon and #c:tools/melee_weapons so mine are as well I guess?
        getOrCreateTagBuilder(conventionalTagKeyOf("tools/melee_weapon"))
            .addOptionalTag(ModItemTags.KHOPESHES);

        getOrCreateTagBuilder(conventionalTagKeyOf("tools/melee_weapons"))
            .addOptionalTag(ModItemTags.KHOPESHES);

        getOrCreateTagBuilder(ModItemTags.ENCHANTABLE_KHOPESH)
            .addOptionalTag(ModItemTags.KHOPESHES);

        getOrCreateTagBuilder(conventionalTagKeyOf("enchantables"))
            .addOptionalTag(ModItemTags.KHOPESHES);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
            .addOptionalTag(ModItemTags.KHOPESHES);

        getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
            .addOptionalTag(ModItemTags.KHOPESHES);

        getOrCreateTagBuilder(ItemTags.BREAKS_DECORATED_POTS)
            .addOptionalTag(ModItemTags.KHOPESHES);
    }

    /**
     * Conventional tags are standardized tags in the c namespace. These tags are expected to be used by multiple mods.
     * @param path The general format for conventional tags is plural, with words separated with underscores.
     *             Since they are expected to be used by multiple mods, the name scheme for conventional tags should be consistent between mods.
     *             A flat structure is used rather than a hierarchical structure. For example, c:iron_ores is preferred over c:ores/iron.
     */
    private static TagKey<Item> conventionalTagKeyOf(String path) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", path));
    }
}
