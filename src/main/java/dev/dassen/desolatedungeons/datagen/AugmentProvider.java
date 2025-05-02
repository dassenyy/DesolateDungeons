package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.augment.Augment;
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
        entries.add(AugmentKeys.EMERGENCY_PUFFERFISH, new Augment("Emergency Pufferfish"));
        entries.add(AugmentKeys.DAMAGE, new Augment("Damage"));
        entries.add(AugmentKeys.SPEED, new Augment("Speed"));
    }

    @Override
    public String getName() {
        return "AugmentProvider";
    }
}
