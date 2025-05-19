package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.augment.Augment;
import dev.dassen.desolatedungeons.registry.key.AugmentKeys;
import dev.dassen.desolatedungeons.registry.key.ModRegistryKeys;
import dev.dassen.desolatedungeons.registry.tag.AugmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AugmentTagProvider extends FabricTagProvider<Augment> {
    public AugmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, ModRegistryKeys.AUGMENT, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(AugmentTags.DESOLATE_DUNGEONS_SET)
            .add(AugmentKeys.DAMAGE)
            .add(AugmentKeys.EMERGENCY_PUFFERFISH)
            .add(AugmentKeys.MINER_MANIA)
            .add(AugmentKeys.SPEED);
    }
}
