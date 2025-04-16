package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.block.DesolateBlocks;
import dev.dassen.desolatedungeons.registry.tag.DesolateBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class DesolateBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public DesolateBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(
                DesolateBlocks.SANDSTONE_BRICKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_WALL,
                dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_WALL,
                dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE,
                dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_WALL,
                dev.dassen.desolatedungeons.block.DesolateBlocks.PERIDOTITE
            );

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE);

        // Needs at least Wooden Level Tool to be mined
        getOrCreateTagBuilder(fabricTagKeyOf("needs_tool_level_0"))
            .add(
                dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_WALL,
                dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_WALL,
                dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE,
                dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_WALL,
                dev.dassen.desolatedungeons.block.DesolateBlocks.PERIDOTITE
            );

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_SLAB);

        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_WALL, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_WALL, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_WALL);

        getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD)
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE, dev.dassen.desolatedungeons.block.DesolateBlocks.PERIDOTITE);

        getOrCreateTagBuilder(BlockTags.STONE_ORE_REPLACEABLES)
            .add(dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE, dev.dassen.desolatedungeons.block.DesolateBlocks.PERIDOTITE);

        getOrCreateTagBuilder(DesolateBlockTags.SCARAB_BEETLES_SPAWNABLE_ON)
            .add(Blocks.GRASS_BLOCK, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS)
            .addOptionalTag(BlockTags.SAND)
            .addOptionalTag(BlockTags.TERRACOTTA);
    }

    private static TagKey<Block> fabricTagKeyOf(String path) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of("fabric", path));
    }
}
