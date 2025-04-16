package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.block.DesolateBlocks;
import dev.dassen.desolatedungeons.item.DesolateItems;
import dev.dassen.desolatedungeons.registry.tag.DesolateItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class DesolateItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public DesolateItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup) {
        configureKhopeshes();

        getOrCreateTagBuilder(ItemTags.STAIRS)
            .add(DesolateBlocks.SANDSTONE_BRICK_STAIRS.asItem())
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_STAIRS.asItem());

        getOrCreateTagBuilder(ItemTags.SLABS)
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_SLAB.asItem())
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_SLAB.asItem());

        getOrCreateTagBuilder(ItemTags.WALLS)
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_WALL.asItem())
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_WALL.asItem());
    }

    private void configureKhopeshes() {
        getOrCreateTagBuilder(DesolateItemTags.KHOPESHES)
            .add(DesolateItems.WOODEN_KHOPESH)
            .add(DesolateItems.STONE_KHOPESH)
            .add(DesolateItems.IRON_KHOPESH)
            .add(DesolateItems.GOLDEN_KHOPESH)
            .add(DesolateItems.DIAMOND_KHOPESH)
            .add(DesolateItems.NETHERITE_KHOPESH);

        getOrCreateTagBuilder(conventionalTagKeyOf("tools"))
            .addOptionalTag(DesolateItemTags.KHOPESHES);

        // Vanilla melee weapons are both in #c:tools/melee_weapon and #c:tools/melee_weapons so mine are as well I guess?
        getOrCreateTagBuilder(conventionalTagKeyOf("tools/melee_weapon"))
            .addOptionalTag(DesolateItemTags.KHOPESHES);

        getOrCreateTagBuilder(conventionalTagKeyOf("tools/melee_weapons"))
            .addOptionalTag(DesolateItemTags.KHOPESHES);

        getOrCreateTagBuilder(DesolateItemTags.ENCHANTABLE_KHOPESH)
            .addOptionalTag(DesolateItemTags.KHOPESHES);

        getOrCreateTagBuilder(conventionalTagKeyOf("enchantables"))
            .addOptionalTag(DesolateItemTags.KHOPESHES);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
            .addOptionalTag(DesolateItemTags.KHOPESHES);

        getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
            .addOptionalTag(DesolateItemTags.KHOPESHES);

        getOrCreateTagBuilder(ItemTags.BREAKS_DECORATED_POTS)
            .addOptionalTag(DesolateItemTags.KHOPESHES);
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
