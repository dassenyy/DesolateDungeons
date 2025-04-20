package dev.dassen.desolatedungeons.world.gen.structure;

import dev.dassen.desolatedungeons.entity.ModEntities;
import dev.dassen.desolatedungeons.registry.key.ModStructureKeys;
import dev.dassen.desolatedungeons.registry.key.ModStructurePoolKeys;
import dev.dassen.desolatedungeons.registry.tag.ModBiomeTags;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureLiquidSettings;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureSpawns;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureTerrainAdaptation;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider;
import net.minecraft.world.gen.structure.DimensionPadding;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModStructures {
    public static void bootstrap(Registerable<Structure> structureRegisterable) {
        RegistryEntryLookup<Biome> biomeRegistryEntryLookup = structureRegisterable.getRegistryLookup(RegistryKeys.BIOME);
        RegistryEntryLookup<StructurePool> structurePoolRegistryEntryLookup = structureRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);

        structureRegisterable.register(
            ModStructureKeys.SANDSWEPT_RUINS,
            new JigsawStructure(
                new Structure.Config.Builder(biomeRegistryEntryLookup.getOrThrow(ModBiomeTags.SANDSWEPT_RUINS_HAS_STRUCTURE))
                    .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                    .terrainAdaptation(StructureTerrainAdaptation.NONE)
                    .spawnOverrides(
                        Map.of(
                            SpawnGroup.MONSTER, new StructureSpawns(StructureSpawns.BoundingBox.STRUCTURE, Pool.of()),
                            SpawnGroup.CREATURE, new StructureSpawns(StructureSpawns.BoundingBox.STRUCTURE, Pool.of(
                                new SpawnSettings.SpawnEntry(ModEntities.SCARAB_BEETLE, 1, 1, 1)
                            ))
                        )
                    )
                    .build(),
                structurePoolRegistryEntryLookup.getOrThrow(ModStructurePoolKeys.SANDSWEPT_RUINS),
                Optional.empty(),
                2,
                ConstantHeightProvider.create(YOffset.fixed(-12)),
                false,
                Optional.of(Heightmap.Type.WORLD_SURFACE_WG),
                80,
                List.of(),
                DimensionPadding.NONE,
                StructureLiquidSettings.APPLY_WATERLOGGING
            )
        );
    }
}
