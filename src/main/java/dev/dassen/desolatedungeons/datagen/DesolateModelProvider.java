package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.block.DesolateBlocks;
import dev.dassen.desolatedungeons.item.DesolateItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class DesolateModelProvider extends FabricModelProvider {
    public DesolateModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(DesolateBlocks.LATERITE);
        blockStateModelGenerator.registerSimpleCubeAll(dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(dev.dassen.desolatedungeons.block.DesolateBlocks.PERIDOTITE);

        BlockStateModelGenerator.BlockTexturePool sandstoneBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS);
        sandstoneBricksPool.stairs(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_STAIRS);
        sandstoneBricksPool.slab(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_SLAB);
        sandstoneBricksPool.wall(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_WALL);
        BlockStateModelGenerator.BlockTexturePool lateriteBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS);
        lateriteBricksPool.stairs(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_STAIRS);
        lateriteBricksPool.slab(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_SLAB);
        lateriteBricksPool.wall(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_WALL);
        BlockStateModelGenerator.BlockTexturePool polishedLimestonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE);
        polishedLimestonePool.stairs(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_STAIRS);
        polishedLimestonePool.slab(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_SLAB);
        polishedLimestonePool.wall(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(DesolateItems.SCARAB_BEETLE, Models.GENERATED);
        itemModelGenerator.register(DesolateItems.ANCIENT_TOTEM, Models.GENERATED);
        itemModelGenerator.register(DesolateItems.KHOPESH_HANDLE, Models.GENERATED);
        itemModelGenerator.register(DesolateItems.WOODEN_KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(DesolateItems.STONE_KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(DesolateItems.IRON_KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(DesolateItems.GOLDEN_KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(DesolateItems.DIAMOND_KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(DesolateItems.NETHERITE_KHOPESH, Models.HANDHELD);
        itemModelGenerator.registerSpawnEgg(DesolateItems.SCARAB_BEETLE_SPAWN_EGG, 0xFAD54A, 0xB16310);
    }
}
