package dev.dassen.desolatedungeons.world.dimension;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.registry.key.DesolateDimensionTypeKeys;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.dimension.DimensionType;

import java.util.OptionalLong;

public class DesolateDimensions {
    public static void bootstrapType(Registerable<DimensionType> registerable) {
        registerable.register(
            DesolateDimensionTypeKeys.DESOLATE_DUNGEON,
            new DimensionType(
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
                Identifier.of(DesolateDungeons.MOD_ID, "desolate_dungeon"),
                0.2f,
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 7), 0)
            )
        );
    }
}
