package dev.dassen.desolatedungeons.world.gen.placementmodifier;

import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;

public class PlacementModifiersBuilder {
    private final List<PlacementModifier> modifiers = new ArrayList<>();

    private PlacementModifiersBuilder() {}

    public static PlacementModifiersBuilder create() {
        return new PlacementModifiersBuilder();
    }

    public static PlacementModifiersBuilder createFromOrePlacementModifiersWithCount(int count, HeightProvider height) {
        return create()
            .addCount(count)
            .addSquare()
            .addHeightRange(height)
            .addBiome();
    }

    public static PlacementModifiersBuilder createFromOrePlacementModifiersWithRarity(int chance, HeightProvider height) {
        return create()
            .addRarityFilter(chance)
            .addSquare()
            .addHeightRange(height)
            .addBiome();
    }

    public PlacementModifiersBuilder addCount(int count) {
        modifiers.add(CountPlacementModifier.of(count));
        return this;
    }

    public PlacementModifiersBuilder addRarityFilter(int chance) {
        modifiers.add(RarityFilterPlacementModifier.of(chance));
        return this;
    }

    public PlacementModifiersBuilder addSquare() {
        modifiers.add(SquarePlacementModifier.of());
        return this;
    }

    public PlacementModifiersBuilder addHeightRange(HeightProvider height) {
        modifiers.add(HeightRangePlacementModifier.of(height));
        return this;
    }

    public PlacementModifiersBuilder addBiome() {
        modifiers.add(BiomePlacementModifier.of());
        return this;
    }

    public PlacementModifiersBuilder addSurfaceThreshold(Heightmap.Type heightMapType, int min, int max) {
        modifiers.add(SurfaceThresholdFilterPlacementModifier.of(heightMapType, min, max));
        return this;
    }

    public PlacementModifiersBuilder addModDirectionlessEnvironmentScan(BlockPredicate blockPredicate, int maxTaxicabDistance) {
        modifiers.add(DirectionlessEnvironmentScanPlacementModifier.of(blockPredicate, maxTaxicabDistance));
        return this;
    }

    public List<PlacementModifier> build() {
        return modifiers;
    }
}
