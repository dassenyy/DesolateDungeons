package dev.dassen.desolatedungeons;

import dev.dassen.desolatedungeons.client.render.dimension.ModDimensionEffects;
import dev.dassen.desolatedungeons.client.render.entity.ModEntityRendering;
import dev.dassen.desolatedungeons.networking.ModPackets;
import net.fabricmc.api.ClientModInitializer;

@SuppressWarnings("unused")
public class DesolateDungeonsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntityRendering.register();

        ModDimensionEffects.register();

        ModPackets.registerS2C();
    }
}
