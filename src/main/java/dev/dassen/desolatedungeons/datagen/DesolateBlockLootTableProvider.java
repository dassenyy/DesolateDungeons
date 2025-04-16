package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.block.DesolateBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class DesolateBlockLootTableProvider extends FabricBlockLootTableProvider  {
    public DesolateBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(dataOutput, registryLookupFuture);
    }

    @Override
    public void generate() {
        addDrop(DesolateBlocks.SANDSTONE_BRICKS);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_STAIRS);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_SLAB);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_WALL);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_STAIRS);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_SLAB);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_WALL);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_STAIRS);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_SLAB);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_WALL);
        addDrop(dev.dassen.desolatedungeons.block.DesolateBlocks.PERIDOTITE);
    }
}
