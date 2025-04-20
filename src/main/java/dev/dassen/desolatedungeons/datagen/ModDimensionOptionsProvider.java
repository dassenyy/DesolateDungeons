package dev.dassen.desolatedungeons.datagen;

import com.mojang.serialization.Codec;
import dev.dassen.desolatedungeons.registry.key.ModBiomeKeys;
import dev.dassen.desolatedungeons.registry.key.ModChunkGeneratorSettingKeys;
import dev.dassen.desolatedungeons.registry.key.ModDimensionOptionKeys;
import dev.dassen.desolatedungeons.registry.key.ModDimensionTypeKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModDimensionOptionsProvider extends FabricCodecDataProvider<DimensionOptions> {
    public ModDimensionOptionsProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, RegistryKey<? extends Registry<DimensionOptions>> key, Codec<DimensionOptions> codec) {
        super(dataOutput, registriesFuture, key, codec);
    }

    @Override
    protected void configure(BiConsumer<Identifier, DimensionOptions> dimensionOptionsBiConsumer, RegistryWrapper.WrapperLookup registryLookup) {
        dimensionOptionsBiConsumer.accept(
            ModDimensionOptionKeys.DESOLATE_DUNGEON.getValue(),
            createDesolateDungeon(registryLookup)
        );
    }

    private static DimensionOptions createDesolateDungeon(RegistryWrapper.WrapperLookup registryLookup) {
        return new DimensionOptions(
            registryLookup.getOrThrow(RegistryKeys.DIMENSION_TYPE).getOrThrow(ModDimensionTypeKeys.DESOLATE_DUNGEON),
            new NoiseChunkGenerator(
                new FixedBiomeSource(registryLookup.getOrThrow(RegistryKeys.BIOME).getOrThrow(ModBiomeKeys.DESOLATE_DUNGEON)),
                registryLookup.getOrThrow(RegistryKeys.CHUNK_GENERATOR_SETTINGS).getOrThrow(ModChunkGeneratorSettingKeys.DESOLATE_DUNGEON)
            )
        );
    }

    @Override
    public String getName() {
        return "ModDimensionOptionsProvider";
    }
}
