package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.augment.Augments;
import dev.dassen.desolatedungeons.registry.key.AugmentKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AugmentProvider extends FabricDynamicRegistryProvider {
    public AugmentProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.add(AugmentKeys.EMPTY, Augments.EMPTY);
        entries.add(AugmentKeys.EMERGENCY_PUFFERFISH, Augments.EMERGENCY_PUFFERFISH);
        entries.add(AugmentKeys.DAMAGE, Augments.DAMAGE);
        entries.add(AugmentKeys.SPEED, Augments.SPEED);
    }

    @Override
    public String getName() {
        return "AugmentProvider";
    }
}
