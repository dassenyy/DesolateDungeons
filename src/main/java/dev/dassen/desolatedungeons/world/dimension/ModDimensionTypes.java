package dev.dassen.desolatedungeons.world.dimension;

import dev.dassen.desolatedungeons.client.render.dimension.DesolateDungeonDimensionEffect;
import dev.dassen.desolatedungeons.registry.key.ModDimensionTypeKeys;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.dimension.DimensionType;

import java.util.OptionalLong;

public class ModDimensionTypes {
    public static void bootstrap(Registerable<DimensionType> dimensionTypeRegisterable) {
        dimensionTypeRegisterable.register(
            ModDimensionTypeKeys.DESOLATE_DUNGEON,
            createDesolateDungeon()
        );
    }

    private static DimensionType createDesolateDungeon() {
        return new DimensionType(
            OptionalLong.of(18000L),
            false,
            false,
            false,
            false,
            1.0,
            false,
            false,
            0,
            512,
            512,
            BlockTags.INFINIBURN_OVERWORLD,
            DesolateDungeonDimensionEffect.IDENTIFIER,
            0.25f,
            new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 7), 0)
        );
    }
}
