package dev.dassen.desolatedungeons.world.gen.feature;

import dev.dassen.desolatedungeons.registry.key.DesolateConfiguredFeatureKeys;
import dev.dassen.desolatedungeons.registry.key.DesolatePlacedFeatureKeys;
import dev.dassen.desolatedungeons.world.gen.placementmodifier.PlacementModifiersBuilder;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class DesolatePlacedFeatures {
    public static void bootstrap(Registerable<PlacedFeature> placedFeatureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredRegistryEntry = placedFeatureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(
            placedFeatureRegisterable,
            DesolatePlacedFeatureKeys.ORE_PERIDOTITE_UPPER,
            configuredRegistryEntry.getOrThrow(DesolateConfiguredFeatureKeys.ORE_PERIDOTITE),
            PlacementModifiersBuilder
                .createFromOrePlacementModifiersWithRarity(6, UniformHeightProvider.create(YOffset.fixed(64), YOffset.fixed(128)))
                .build()
        );
        register(
            placedFeatureRegisterable,
            DesolatePlacedFeatureKeys.ORE_PERIDOTITE_LOWER,
            configuredRegistryEntry.getOrThrow(DesolateConfiguredFeatureKeys.ORE_PERIDOTITE),
            PlacementModifiersBuilder
                .createFromOrePlacementModifiersWithCount(1, UniformHeightProvider.create(YOffset.fixed(0), YOffset.fixed(128)))
                .build()
        );

        register(
            placedFeatureRegisterable,
            DesolatePlacedFeatureKeys.ORE_LATERITE,
            configuredRegistryEntry.getOrThrow(DesolateConfiguredFeatureKeys.ORE_LATERITE),
            PlacementModifiersBuilder
                .createFromOrePlacementModifiersWithCount(4, UniformHeightProvider.create(YOffset.fixed(64), YOffset.fixed(192)))
                .addSurfaceThreshold(Heightmap.Type.WORLD_SURFACE_WG, -10, -3)
                .build()
        );

        register(
            placedFeatureRegisterable,
            DesolatePlacedFeatureKeys.ORE_LIMESTONE,
            configuredRegistryEntry.getOrThrow(DesolateConfiguredFeatureKeys.ORE_LIMESTONE),
            PlacementModifiersBuilder
                .createFromOrePlacementModifiersWithCount(2, UniformHeightProvider.create(YOffset.fixed(64), YOffset.fixed(192)))
                .addModDirectionlessEnvironmentScan(BlockPredicate.IS_AIR, 1)
                .build()
        );
    }

    private static void register(
        Registerable<PlacedFeature> placedFeatureRegisterable,
        RegistryKey<PlacedFeature> key,
        RegistryEntry<ConfiguredFeature<?, ?>> configuredFeatureEntry,
        List<PlacementModifier> modifiers
    ) {
        placedFeatureRegisterable.register(key, new PlacedFeature(configuredFeatureEntry, List.copyOf(modifiers)));
    }
}
