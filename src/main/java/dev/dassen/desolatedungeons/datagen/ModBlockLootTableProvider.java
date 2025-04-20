package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider  {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(dataOutput, registryLookupFuture);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.SANDSTONE_BRICKS);
        addDrop(ModBlocks.SANDSTONE_BRICK_STAIRS);
        addDrop(ModBlocks.SANDSTONE_BRICK_SLAB);
        addDrop(ModBlocks.SANDSTONE_BRICK_WALL);
        addDrop(ModBlocks.LATERITE);
        addDrop(ModBlocks.LATERITE_BRICKS);
        addDrop(ModBlocks.LATERITE_BRICK_STAIRS);
        addDrop(ModBlocks.LATERITE_BRICK_SLAB);
        addDrop(ModBlocks.LATERITE_BRICK_WALL);
        addDrop(ModBlocks.LIMESTONE);
        addDrop(ModBlocks.POLISHED_LIMESTONE);
        addDrop(ModBlocks.POLISHED_LIMESTONE_STAIRS);
        addDrop(ModBlocks.POLISHED_LIMESTONE_SLAB);
        addDrop(ModBlocks.POLISHED_LIMESTONE_WALL);
        addDrop(ModBlocks.PERIDOTITE);
    }
}
