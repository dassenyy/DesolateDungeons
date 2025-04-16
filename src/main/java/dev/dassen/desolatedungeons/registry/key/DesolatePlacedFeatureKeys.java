package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public final class DesolatePlacedFeatureKeys {
    public static final RegistryKey<PlacedFeature> ORE_PERIDOTITE_UPPER = registryKeyOf("ore_peridotite_upper");
    public static final RegistryKey<PlacedFeature> ORE_PERIDOTITE_LOWER = registryKeyOf("ore_peridotite_lower");
    public static final RegistryKey<PlacedFeature> ORE_LATERITE = registryKeyOf("ore_laterite");
    public static final RegistryKey<PlacedFeature> ORE_LIMESTONE = registryKeyOf("ore_limestone");

    private static RegistryKey<PlacedFeature> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
