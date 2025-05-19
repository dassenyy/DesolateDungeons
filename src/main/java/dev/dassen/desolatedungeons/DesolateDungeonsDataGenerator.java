package dev.dassen.desolatedungeons;

import dev.dassen.desolatedungeons.augment.Augments;
import dev.dassen.desolatedungeons.datagen.*;
import dev.dassen.desolatedungeons.registry.key.ModRegistryKeys;
import dev.dassen.desolatedungeons.world.biome.ModBiomes;
import dev.dassen.desolatedungeons.world.dimension.ModDimensionTypes;
import dev.dassen.desolatedungeons.world.gen.chunk.ModChunkGeneratorSettings;
import dev.dassen.desolatedungeons.world.gen.feature.DesolateConfiguredFeatures;
import dev.dassen.desolatedungeons.world.gen.feature.DesolatePlacedFeatures;
import dev.dassen.desolatedungeons.world.gen.structure.ModStructurePools;
import dev.dassen.desolatedungeons.world.gen.structure.ModStructureSets;
import dev.dassen.desolatedungeons.world.gen.structure.ModStructures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.dimension.DimensionOptions;

@SuppressWarnings("unused")
public class DesolateDungeonsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);

		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModChestLootTableProvider::new);

		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModBiomeTagProvider::new);
		pack.addProvider(AugmentTagProvider::new);

		pack.addProvider(ModRecipeProvider::new);

		pack.addProvider(ModWorldGenerationProvider::new);

		pack.addProvider(ModAdvancementProvider::new);

		pack.addProvider(
			(output, registryLookupFuture)
				-> new ModDimensionOptionsProvider(output, registryLookupFuture, RegistryKeys.DIMENSION, DimensionOptions.CODEC)
		);

		pack.addProvider(AugmentProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, DesolateConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, DesolatePlacedFeatures::bootstrap);

		registryBuilder.addRegistry(RegistryKeys.STRUCTURE, ModStructures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.STRUCTURE_SET, ModStructureSets::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.TEMPLATE_POOL, ModStructurePools::bootstrap);

		registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);

		registryBuilder.addRegistry(RegistryKeys.CHUNK_GENERATOR_SETTINGS, ModChunkGeneratorSettings::bootstrap);

		registryBuilder.addRegistry(RegistryKeys.DIMENSION_TYPE, ModDimensionTypes::bootstrap);

		registryBuilder.addRegistry(ModRegistryKeys.AUGMENT, Augments::bootstrap);

		DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
	}
}
