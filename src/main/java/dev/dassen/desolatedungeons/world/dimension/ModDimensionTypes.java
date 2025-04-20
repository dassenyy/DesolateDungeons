package dev.dassen.desolatedungeons.world.dimension;

import dev.dassen.desolatedungeons.registry.key.ModDimensionTypeKeys;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
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
            true,
            false,
            false,
            1.0,
            false,
            false,
            0,
            512,
            512,
            BlockTags.INFINIBURN_OVERWORLD,
            Identifier.ofVanilla("the_nether"),
            0.2f,
            new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 7), 0)
        );
    }
}
