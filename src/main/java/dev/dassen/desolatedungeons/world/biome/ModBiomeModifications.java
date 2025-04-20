package dev.dassen.desolatedungeons.world.biome;

import dev.dassen.desolatedungeons.entity.ModEntities;
import dev.dassen.desolatedungeons.entity.ScarabBeetleEntity;
import dev.dassen.desolatedungeons.registry.key.ModPlacedFeatureKeys;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModBiomeModifications {
    public static void addFeatures() {
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatureKeys.ORE_PERIDOTITE_UPPER
        );
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatureKeys.ORE_PERIDOTITE_LOWER
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(
                BiomeKeys.JUNGLE,
                BiomeKeys.SPARSE_JUNGLE,
                BiomeKeys.BAMBOO_JUNGLE,
                BiomeKeys.SAVANNA,
                BiomeKeys.SAVANNA_PLATEAU,
                BiomeKeys.WINDSWEPT_SAVANNA
            ),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatureKeys.ORE_LATERITE
        );

        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatureKeys.ORE_LIMESTONE
        );
    }

    public static void addSpawns() {
        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(
                BiomeKeys.DESERT,
                BiomeKeys.BADLANDS,
                BiomeKeys.SAVANNA,
                BiomeKeys.SAVANNA_PLATEAU,
                BiomeKeys.WINDSWEPT_SAVANNA
            ),
            SpawnGroup.CREATURE,
            ModEntities.SCARAB_BEETLE,
            5,
            1,
            2
        );
        SpawnRestriction.register(
            ModEntities.SCARAB_BEETLE,
            SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            ScarabBeetleEntity::canSpawn
        );
    }
}
