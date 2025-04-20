package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.block.ModBlocks;
import dev.dassen.desolatedungeons.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LATERITE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LIMESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PERIDOTITE);

        BlockStateModelGenerator.BlockTexturePool sandstoneBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SANDSTONE_BRICKS);
        sandstoneBricksPool.stairs(ModBlocks.SANDSTONE_BRICK_STAIRS);
        sandstoneBricksPool.slab(ModBlocks.SANDSTONE_BRICK_SLAB);
        sandstoneBricksPool.wall(ModBlocks.SANDSTONE_BRICK_WALL);
        BlockStateModelGenerator.BlockTexturePool lateriteBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LATERITE_BRICKS);
        lateriteBricksPool.stairs(ModBlocks.LATERITE_BRICK_STAIRS);
        lateriteBricksPool.slab(ModBlocks.LATERITE_BRICK_SLAB);
        lateriteBricksPool.wall(ModBlocks.LATERITE_BRICK_WALL);
        BlockStateModelGenerator.BlockTexturePool polishedLimestonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_LIMESTONE);
        polishedLimestonePool.stairs(ModBlocks.POLISHED_LIMESTONE_STAIRS);
        polishedLimestonePool.slab(ModBlocks.POLISHED_LIMESTONE_SLAB);
        polishedLimestonePool.wall(ModBlocks.POLISHED_LIMESTONE_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SCARAB_BEETLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANCIENT_TOTEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.KHOPESH_HANDLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOODEN_KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_KHOPESH, Models.HANDHELD);
        itemModelGenerator.registerSpawnEgg(ModItems.SCARAB_BEETLE_SPAWN_EGG, 0xFAD54A, 0xB16310);
    }
}
