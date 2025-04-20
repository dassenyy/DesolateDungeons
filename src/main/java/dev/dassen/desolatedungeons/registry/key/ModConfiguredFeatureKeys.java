package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public final class ModConfiguredFeatureKeys {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PERIDOTITE = registryKeyOf("ore_peridotite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_LATERITE = registryKeyOf("ore_laterite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_LIMESTONE = registryKeyOf("ore_limestone");

    private static RegistryKey<ConfiguredFeature<?, ?>> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
