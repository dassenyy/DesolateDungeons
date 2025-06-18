package dev.dassen.desolatedungeons.world.gen.noise;

import com.mojang.datafixers.util.Pair;
import dev.dassen.desolatedungeons.world.gen.densityfunction.RangeChoiceDensityFunctionBuilder;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.noise.NoiseRouter;

import java.util.List;

public class ModNoiseRouters {
    public static NoiseRouter createDesolateDungeon(
        RegistryEntryLookup<DensityFunction> densityFunctionLookup,
        RegistryEntryLookup<DoublePerlinNoiseSampler.NoiseParameters> noiseParametersLookup
    ) {
        DensityFunction finalDensity = RangeChoiceDensityFunctionBuilder.buildLayersFromBottom(
            densityFunctionLookup,
            0,
            List.of(
                // 0 - 63 -> Layer 7 Floor
                Pair.of(createLayer7Floor(noiseParametersLookup), 64),
                // 64 - 127 -> Layer 7 Roof
                Pair.of(createLayer7Roof(noiseParametersLookup), 64),
                // 128 - 255 -> Layer 1 Floor
                Pair.of(createLayer1Floor(noiseParametersLookup), 64)
            )
        );

        return new NoiseRouter(
            DensityFunctionTypes.zero(),            /* 01: barrierNoise                     (Aquifer control) */
            DensityFunctionTypes.zero(),            /* 02: fluidLevelFloodednessNoise       (Aquifer control) */
            DensityFunctionTypes.zero(),            /* 03: fluidLevelSpreadNoise            (Aquifer control) */
            DensityFunctionTypes.zero(),            /* 04: lavaNoise                        (Aquifer control) */
            DensityFunctionTypes.zero(),            /* 05: temperature                      (Biome placement only) */
            DensityFunctionTypes.zero(),            /* 06: vegetation                       (Biome placement only) */
            DensityFunctionTypes.zero(),            /* 07: continents                       (Biome placement only) */
            DensityFunctionTypes.zero(),            /* 08: erosion                          (Biome placement only) */
            DensityFunctionTypes.zero(),            /* 09: depth                            (Biome placement only) */
            DensityFunctionTypes.zero(),            /* 10: ridges                           (Biome placement only) */
            DensityFunctionTypes.zero(),            /* 11: initialDensityWithoutJaggedness  (Surface rules / Aquifers) */
            finalDensity,                           /* 12: finalDensity                     (Main density function) */
            DensityFunctionTypes.zero(),            /* 13: veinToggle                       (Large ore vein control) */
            DensityFunctionTypes.zero(),            /* 14: veinRidged                       (Large ore vein control) */
            DensityFunctionTypes.zero()             /* 15: veinGap                          (Large ore vein control) */
        );
    }

    private static DensityFunction createLayer7Floor(
        RegistryEntryLookup<DoublePerlinNoiseSampler.NoiseParameters> noiseParametersLookup
    ) {
        return DensityFunctionTypes.add(
            DensityFunctionTypes.yClampedGradient(0, 127, 1.5d, -1.5d),
            DensityFunctionTypes.noise(noiseParametersLookup.getOrThrow(NoiseParametersKeys.GRAVEL))
        );
    }

    private static DensityFunction createLayer7Roof(
        RegistryEntryLookup<DoublePerlinNoiseSampler.NoiseParameters> noiseParametersLookup
    ) {
        return DensityFunctionTypes.add(
            DensityFunctionTypes.yClampedGradient(0, 127, -1.5d, 1.5d),
            DensityFunctionTypes.noise(noiseParametersLookup.getOrThrow(NoiseParametersKeys.GRAVEL))
        );
    }

    private static DensityFunction createLayer1Floor(
        RegistryEntryLookup<DoublePerlinNoiseSampler.NoiseParameters> noiseParametersLookup
    ) {
        return DensityFunctionTypes.add(
            DensityFunctionTypes.yClampedGradient(128, 191, 1d, -1d),
            DensityFunctionTypes.noise(noiseParametersLookup.getOrThrow(NoiseParametersKeys.BADLANDS_SURFACE), 0.5d, 0.5d).clamp(-1f, 1f)
        );
    }
}
