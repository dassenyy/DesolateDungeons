package dev.dassen.desolatedungeons.world.gen.densityfunction.types;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.densityfunction.DensityFunction;

import java.util.List;
import java.util.Optional;

public record LayerChoiceDensityFunction(int startingHeight, List<Pair<DensityFunction, Integer>> densityFunctionLayers) implements DensityFunction {
    public static final MapCodec<LayerChoiceDensityFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            Codec.INT.fieldOf("starting_height").forGetter(LayerChoiceDensityFunction::startingHeight),
            Codec.pair(
                DensityFunction.FUNCTION_CODEC.fieldOf("density_function").codec(),
                Codecs.POSITIVE_INT.fieldOf("height").codec()
            ).listOf().fieldOf("density_function_layers").forGetter(LayerChoiceDensityFunction::densityFunctionLayers)
        ).apply(instance, LayerChoiceDensityFunction::new)
    );
    public static final CodecHolder<LayerChoiceDensityFunction> CODEC_HOLDER = CodecHolder.of(CODEC);

    @Override
    public double sample(NoisePos pos) {
        return getDensityFunctionForHeightValue(pos.blockY())
            .map(densityFunction -> densityFunction.sample(pos))
            .orElse(0d);
    }

    @Override
    public void fill(double[] densities, EachApplier applier) {
        this.densityFunctionLayers.forEach(pair -> pair.getFirst().fill(densities, applier));

        for (int i = 0; i < densities.length; i++) {
            final int index = i;
            getDensityFunctionForHeightValue(densities[i])
                .ifPresent(densityFunction -> densities[index] = densityFunction.sample(applier.at(index)));
        }
    }

    @Override
    public DensityFunction apply(DensityFunctionVisitor visitor) {
        return visitor.apply(
            new LayerChoiceDensityFunction(
                this.startingHeight,
                this.densityFunctionLayers
                    .stream()
                    .map(pair -> Pair.of(pair.getFirst().apply(visitor), pair.getSecond()))
                    .toList()
            )
        );
    }

    @Override
    public double minValue() {
        return densityFunctionLayers.stream().mapToDouble(pair -> pair.getFirst().minValue()).min().orElse(0d);
    }

    @Override
    public double maxValue() {
        return densityFunctionLayers.stream().mapToDouble(pair -> pair.getFirst().maxValue()).max().orElse(0d);
    }

    @Override
    public CodecHolder<? extends DensityFunction> getCodecHolder() {
        return CODEC_HOLDER;
    }

    private Optional<DensityFunction> getDensityFunctionForHeightValue(double heightValue) {
        int currentMinY = startingHeight;

        for (Pair<DensityFunction, Integer> pair : densityFunctionLayers) {
            int currentMaxY = currentMinY + pair.getSecond();

            if (currentMinY <= heightValue && heightValue < currentMaxY) {
                return Optional.of(pair.getFirst());
            }

            currentMinY = currentMaxY;
        }

        return Optional.empty();
    }
}
