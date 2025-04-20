package dev.dassen.desolatedungeons.world.gen;

import dev.dassen.desolatedungeons.world.biome.ModBiomeModifications;

public class ModWorldGeneration {
    public static void initialize() {
        ModBiomeModifications.addFeatures();
        ModBiomeModifications.addSpawns();
    }
}
