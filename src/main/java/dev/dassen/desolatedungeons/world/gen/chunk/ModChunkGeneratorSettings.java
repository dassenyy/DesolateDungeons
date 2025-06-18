package dev.dassen.desolatedungeons.world.gen.chunk;

import dev.dassen.desolatedungeons.registry.key.ModChunkGeneratorSettingKeys;
import dev.dassen.desolatedungeons.world.biome.surface.ModMaterialRules;
import dev.dassen.desolatedungeons.world.gen.noise.ModNoiseRouters;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.GenerationShapeConfig;
import net.minecraft.world.gen.noise.NoiseRouter;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import java.util.List;

public class ModChunkGeneratorSettings {
    public static void bootstrap(Registerable<ChunkGeneratorSettings> chunkGeneratorSettingsRegisterable) {
        chunkGeneratorSettingsRegisterable.register(
            ModChunkGeneratorSettingKeys.DESOLATE_DUNGEON,
            createDesolateDungeon(
                ModNoiseRouters.createDesolateDungeon(
                    chunkGeneratorSettingsRegisterable.getRegistryLookup(RegistryKeys.DENSITY_FUNCTION),
                    chunkGeneratorSettingsRegisterable.getRegistryLookup(RegistryKeys.NOISE_PARAMETERS)
                ),
                ModMaterialRules.createDesolateDungeon()
            )
        );
    }

    private static ChunkGeneratorSettings createDesolateDungeon(NoiseRouter noiseRouter, MaterialRules.MaterialRule materialRule) {
        return new ChunkGeneratorSettings(
            new GenerationShapeConfig(0, 512, 1, 2),
            Blocks.STONE.getDefaultState(),
            Blocks.WATER.getDefaultState(),
            noiseRouter,
            materialRule,
            List.of(),
            -1,
            false,
            false,
            false,
            false
        );
    }
}
