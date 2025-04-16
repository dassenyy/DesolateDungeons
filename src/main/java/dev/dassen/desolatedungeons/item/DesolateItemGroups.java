package dev.dassen.desolatedungeons.item;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.block.DesolateBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DesolateItemGroups {
    public static final ItemGroup DESOLATED_DUNGEONS_GROUP =
        Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(DesolateDungeons.MOD_ID, "desolate_dungeons"),
            FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.desolate_dungeons"))
                .icon(() -> new ItemStack(DesolateItems.ANCIENT_TOTEM))
                .entries(((displayContext, entries) -> {
                    entries.add(DesolateItems.SCARAB_BEETLE);
                    entries.add(DesolateItems.ANCIENT_TOTEM);
                    entries.add(DesolateItems.KHOPESH_HANDLE);
                    entries.add(DesolateItems.WOODEN_KHOPESH);
                    entries.add(DesolateItems.STONE_KHOPESH);
                    entries.add(DesolateItems.IRON_KHOPESH);
                    entries.add(DesolateItems.GOLDEN_KHOPESH);
                    entries.add(DesolateItems.DIAMOND_KHOPESH);
                    entries.add(DesolateItems.NETHERITE_KHOPESH);
                    entries.add(DesolateItems.SCARAB_BEETLE_SPAWN_EGG);

                    entries.add(DesolateBlocks.SANDSTONE_BRICKS);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_STAIRS);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_SLAB);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_WALL);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_STAIRS);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_SLAB);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_WALL);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_STAIRS);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_SLAB);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_WALL);
                    entries.add(dev.dassen.desolatedungeons.block.DesolateBlocks.PERIDOTITE);
                }))
            .build()
        );

    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Item Groups for " + DesolateDungeons.MOD_ID);
    }
}
