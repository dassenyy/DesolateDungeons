package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.registry.tag.DesolateBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class DesolateBiomeTagProvider extends FabricTagProvider<Biome> {
    public DesolateBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, RegistryKeys.BIOME, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup) {
        getOrCreateTagBuilder(DesolateBiomeTags.SANDSWEPT_RUINS_HAS_STRUCTURE)
            .add(BiomeKeys.DESERT)
            .setReplace(false);
    }
}
