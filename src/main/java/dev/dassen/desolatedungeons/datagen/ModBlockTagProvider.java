package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.block.ModBlocks;
import dev.dassen.desolatedungeons.registry.tag.ModBlockTags;
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

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(
                ModBlocks.SANDSTONE_BRICKS, ModBlocks.SANDSTONE_BRICK_STAIRS, ModBlocks.SANDSTONE_BRICK_SLAB, ModBlocks.SANDSTONE_BRICK_WALL,
                ModBlocks.LATERITE_BRICKS, ModBlocks.LATERITE_BRICK_STAIRS, ModBlocks.LATERITE_BRICK_SLAB, ModBlocks.LATERITE_BRICK_WALL,
                ModBlocks.LIMESTONE,
                ModBlocks.POLISHED_LIMESTONE, ModBlocks.POLISHED_LIMESTONE_STAIRS, ModBlocks.POLISHED_LIMESTONE_SLAB, ModBlocks.POLISHED_LIMESTONE_WALL,
                ModBlocks.PERIDOTITE
            );

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
            .add(ModBlocks.LATERITE);

        // Needs at least Wooden Level Tool to be mined
        getOrCreateTagBuilder(fabricTagKeyOf("needs_tool_level_0"))
            .add(
                ModBlocks.SANDSTONE_BRICKS, ModBlocks.SANDSTONE_BRICK_STAIRS, ModBlocks.SANDSTONE_BRICK_SLAB, ModBlocks.SANDSTONE_BRICK_WALL,
                ModBlocks.LATERITE_BRICKS, ModBlocks.LATERITE_BRICK_STAIRS, ModBlocks.LATERITE_BRICK_SLAB, ModBlocks.LATERITE_BRICK_WALL,
                ModBlocks.LIMESTONE,
                ModBlocks.POLISHED_LIMESTONE, ModBlocks.POLISHED_LIMESTONE_STAIRS, ModBlocks.POLISHED_LIMESTONE_SLAB, ModBlocks.POLISHED_LIMESTONE_WALL,
                ModBlocks.PERIDOTITE
            );

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add(ModBlocks.SANDSTONE_BRICK_STAIRS, ModBlocks.LATERITE_BRICK_STAIRS, ModBlocks.POLISHED_LIMESTONE_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
            .add(ModBlocks.SANDSTONE_BRICK_SLAB, ModBlocks.LATERITE_BRICK_SLAB, ModBlocks.POLISHED_LIMESTONE_SLAB);

        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(ModBlocks.SANDSTONE_BRICK_WALL, ModBlocks.LATERITE_BRICK_WALL, ModBlocks.POLISHED_LIMESTONE_WALL);

        getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD)
            .add(ModBlocks.LIMESTONE, ModBlocks.PERIDOTITE);

        getOrCreateTagBuilder(BlockTags.STONE_ORE_REPLACEABLES)
            .add(ModBlocks.LIMESTONE, ModBlocks.PERIDOTITE);

        getOrCreateTagBuilder(ModBlockTags.SCARAB_BEETLES_SPAWNABLE_ON)
            .add(Blocks.GRASS_BLOCK, ModBlocks.POLISHED_LIMESTONE, ModBlocks.LATERITE_BRICKS)
            .addOptionalTag(BlockTags.SAND)
            .addOptionalTag(BlockTags.TERRACOTTA);
    }

    private static TagKey<Block> fabricTagKeyOf(String path) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of("fabric", path));
    }
}
