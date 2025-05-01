package dev.dassen.desolatedungeons.registry;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.Augment;
import dev.dassen.desolatedungeons.registry.key.ModDynamicRegistryKeys;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class ModDynamicRegistries {
    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Dynamic Registries for " + DesolateDungeons.MOD_ID);

        DynamicRegistries.register(ModDynamicRegistryKeys.AUGMENT, Augment.CODEC);
    }
}
