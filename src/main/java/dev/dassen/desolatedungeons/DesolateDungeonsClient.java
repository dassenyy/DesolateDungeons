package dev.dassen.desolatedungeons;

import dev.dassen.desolatedungeons.client.render.dimension.ModDimensionEffects;
import dev.dassen.desolatedungeons.client.render.entity.ModEntityRendering;
import net.fabricmc.api.ClientModInitializer;

public class DesolateDungeonsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntityRendering.register();

        ModDimensionEffects.register();
    }
}
