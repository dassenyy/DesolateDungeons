package dev.dassen.desolatedungeons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModWorldGenerationProvider extends FabricDynamicRegistryProvider {
    public ModWorldGenerationProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup, Entries entries) {
        entries.addAll(registryLookup.getOrThrow(RegistryKeys.CONFIGURED_FEATURE));
        entries.addAll(registryLookup.getOrThrow(RegistryKeys.PLACED_FEATURE));

        entries.addAll(registryLookup.getOrThrow(RegistryKeys.STRUCTURE));
        entries.addAll(registryLookup.getOrThrow(RegistryKeys.STRUCTURE_SET));
        entries.addAll(registryLookup.getOrThrow(RegistryKeys.TEMPLATE_POOL));

        entries.addAll(registryLookup.getOrThrow(RegistryKeys.BIOME));

        entries.addAll(registryLookup.getOrThrow(RegistryKeys.CHUNK_GENERATOR_SETTINGS));

        entries.addAll(registryLookup.getOrThrow(RegistryKeys.DIMENSION_TYPE));
    }

    @Override
    public String getName() {
        return "ModWorldGenerationProvider";
    }
}
