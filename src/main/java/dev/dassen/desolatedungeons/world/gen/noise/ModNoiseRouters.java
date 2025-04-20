package dev.dassen.desolatedungeons.world.gen.noise;

import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.noise.NoiseRouter;

public class ModNoiseRouters {
    public static NoiseRouter createDesolateDungeon(
        RegistryEntryLookup<DensityFunction> densityFunctionLookup,
        RegistryEntryLookup<DoublePerlinNoiseSampler.NoiseParameters> noiseParametersLookup
    ) {
        DensityFunction finalDensity = DensityFunctionTypes.rangeChoice(
            new DensityFunctionTypes.RegistryEntryHolder(
                densityFunctionLookup.getOrThrow(RegistryKey.of(RegistryKeys.DENSITY_FUNCTION, Identifier.ofVanilla("y")))
            ),
            0d,
            128d,
            DensityFunctionTypes.add(
                DensityFunctionTypes.yClampedGradient(0, 255, 1.5d, -1.5d),
                DensityFunctionTypes.noise(noiseParametersLookup.getOrThrow(NoiseParametersKeys.GRAVEL))
            ),
            DensityFunctionTypes.rangeChoice(
                new DensityFunctionTypes.RegistryEntryHolder(
                    densityFunctionLookup.getOrThrow(RegistryKey.of(RegistryKeys.DENSITY_FUNCTION, Identifier.ofVanilla("y")))
                ),
                128d,
                256d,
                DensityFunctionTypes.add(
                    DensityFunctionTypes.yClampedGradient(0, 255, -1.5d, 1.5d),
                    DensityFunctionTypes.noise(noiseParametersLookup.getOrThrow(NoiseParametersKeys.GRAVEL))
                ),
                DensityFunctionTypes.add(
                    DensityFunctionTypes.yClampedGradient(256, 383, 1d, -1d),
                    DensityFunctionTypes.noise(noiseParametersLookup.getOrThrow(NoiseParametersKeys.GRAVEL))
                )
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
            DensityFunctionTypes.zero(),            /* 13: veinToggle                       (Ore vein control) */
            DensityFunctionTypes.zero(),            /* 14: veinRidged                       (Ore vein control) */
            DensityFunctionTypes.zero()             /* 15: veinGap                          (Ore vein control) */
        );
    }
}
