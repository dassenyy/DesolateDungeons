package dev.dassen.desolatedungeons.world.gen.densityfunction;


import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;

import java.util.List;

public class RangeChoiceDensityFunctionBuilder {
    private RangeChoiceDensityFunctionBuilder() {}

    public static DensityFunction buildLayersFromBottom(
        RegistryEntryLookup<DensityFunction> densityFunctionLookup,
        int bottomStartHeight,
        List<Pair<DensityFunction, Integer>> densityFunctionLayers
    ) {
        DensityFunction yDensityFunction = new DensityFunctionTypes.RegistryEntryHolder(
            densityFunctionLookup.getOrThrow(RegistryKey.of(RegistryKeys.DENSITY_FUNCTION, Identifier.ofVanilla("y")))
        );

        DensityFunction currentDensityFunction = DensityFunctionTypes.constant(0d);
        int currentHeight = bottomStartHeight + densityFunctionLayers.stream().mapToInt(Pair::getSecond).sum();

        for (int i = densityFunctionLayers.size() - 1; i >= 0; i--) {
            DensityFunction layerDensityFunction = densityFunctionLayers.get(i).getFirst();
            int layerHeight = densityFunctionLayers.get(i).getSecond();
            int minInclusive = currentHeight - layerHeight;
            int maxExclusive = currentHeight;

            currentDensityFunction = DensityFunctionTypes.rangeChoice(
                yDensityFunction,
                minInclusive,
                maxExclusive,
                layerDensityFunction,
                currentDensityFunction
            );

            currentHeight -= layerHeight;
        }

        return currentDensityFunction;
    }
}
