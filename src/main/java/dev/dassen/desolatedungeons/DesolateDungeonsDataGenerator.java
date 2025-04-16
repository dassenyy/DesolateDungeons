package dev.dassen.desolatedungeons;

import dev.dassen.desolatedungeons.datagen.*;
import dev.dassen.desolatedungeons.world.gen.feature.DesolateConfiguredFeatures;
import dev.dassen.desolatedungeons.world.gen.feature.DesolatePlacedFeatures;
import dev.dassen.desolatedungeons.world.gen.structure.DesolateStructurePools;
import dev.dassen.desolatedungeons.world.gen.structure.DesolateStructureSets;
import dev.dassen.desolatedungeons.world.gen.structure.DesolateStructures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class DesolateDungeonsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack  = fabricDataGenerator.createPack();

		pack.addProvider(DesolateModelProvider::new);

		pack.addProvider(DesolateBlockLootTableProvider::new);
		pack.addProvider(DesolateChestLootTableProvider::new);

		pack.addProvider(DesolateItemTagProvider::new);
		pack.addProvider(DesolateBlockTagProvider::new);
		pack.addProvider(DesolateBiomeTagProvider::new);

		pack.addProvider(DesolateRecipeProvider::new);

		pack.addProvider(DesolateWorldGenerationProvider::new);

		pack.addProvider(DesolateAdvancementProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, DesolateConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, DesolatePlacedFeatures::bootstrap);

		registryBuilder.addRegistry(RegistryKeys.STRUCTURE, DesolateStructures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.STRUCTURE_SET, DesolateStructureSets::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.TEMPLATE_POOL, DesolateStructurePools::bootstrap);

		DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
	}
}
