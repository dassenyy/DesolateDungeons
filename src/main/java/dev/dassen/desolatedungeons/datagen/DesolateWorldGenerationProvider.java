package dev.dassen.desolatedungeons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class DesolateWorldGenerationProvider extends FabricDynamicRegistryProvider {
    public DesolateWorldGenerationProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup, Entries entries) {
        entries.addAll(registryLookup.getOrThrow(RegistryKeys.CONFIGURED_FEATURE));
        entries.addAll(registryLookup.getOrThrow(RegistryKeys.PLACED_FEATURE));
        entries.addAll(registryLookup.getOrThrow(RegistryKeys.STRUCTURE));
        entries.addAll(registryLookup.getOrThrow(RegistryKeys.STRUCTURE_SET));
        entries.addAll(registryLookup.getOrThrow(RegistryKeys.TEMPLATE_POOL));
    }

    @Override
    public String getName() {
        return "DesolateWorldGenerationProvider";
    }
}
