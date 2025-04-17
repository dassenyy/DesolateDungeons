package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class DesolateWorldKeys {
    public static final RegistryKey<World> DESOLATE_DUNGEON = registryKeyOf("desolate_dungeon");

    private static RegistryKey<World> registryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.WORLD, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
