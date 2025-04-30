package dev.dassen.desolatedungeons.client.render.dimension;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;

public class ModDimensionEffects {
    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Dimension Effects for " + DesolateDungeons.MOD_ID);

        DimensionRenderingRegistry.registerDimensionEffects(DesolateDungeonDimensionEffect.IDENTIFIER, new DesolateDungeonDimensionEffect());
    }
}
