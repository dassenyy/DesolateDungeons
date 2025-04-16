package dev.dassen.desolatedungeons;

import dev.dassen.desolatedungeons.client.render.entity.ScarabBeetleEntityRenderer;
import dev.dassen.desolatedungeons.client.render.entity.model.ScarabBeetleEntityModel;
import dev.dassen.desolatedungeons.entity.DesolateEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class DesolateDungeonsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ScarabBeetleEntityModel.SCARAB_BEETLE, ScarabBeetleEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(DesolateEntities.SCARAB_BEETLE, ScarabBeetleEntityRenderer::new);
    }
}
