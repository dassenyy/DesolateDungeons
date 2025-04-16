package dev.dassen.desolatedungeons.world.gen.feature;

import dev.dassen.desolatedungeons.block.DesolateBlocks;
import dev.dassen.desolatedungeons.registry.key.DesolateConfiguredFeatureKeys;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class DesolateConfiguredFeatures {

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> configuredFeatureRegisterable) {
        RuleTest baseStoneOverworldTest = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);

        register(
            configuredFeatureRegisterable,
            DesolateConfiguredFeatureKeys.ORE_PERIDOTITE,
            Feature.ORE,
            new OreFeatureConfig(baseStoneOverworldTest, DesolateBlocks.PERIDOTITE.getDefaultState(), 64)
        );

        register(
            configuredFeatureRegisterable,
            DesolateConfiguredFeatureKeys.ORE_LATERITE,
            Feature.ORE,
            new OreFeatureConfig(baseStoneOverworldTest, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE.getDefaultState(), 64)
        );

        register(
            configuredFeatureRegisterable,
            DesolateConfiguredFeatureKeys.ORE_LIMESTONE,
            Feature.ORE,
            new OreFeatureConfig(baseStoneOverworldTest, dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE.getDefaultState(), 64)
        );
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
        Registerable<ConfiguredFeature<?, ?>> configuredFeatureRegisterable,
        RegistryKey<ConfiguredFeature<?, ?>> key,
        F feature,
        FC configuration
    ) {
        configuredFeatureRegisterable.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}