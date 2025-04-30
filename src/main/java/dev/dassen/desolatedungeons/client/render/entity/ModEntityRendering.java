package dev.dassen.desolatedungeons.client.render.entity;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.client.render.entity.model.ScarabBeetleEntityModel;
import dev.dassen.desolatedungeons.entity.ModEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ModEntityRendering {
    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Entity Model Layers and Entity Renderers for " + DesolateDungeons.MOD_ID);

        EntityModelLayerRegistry.registerModelLayer(ScarabBeetleEntityModel.SCARAB_BEETLE, ScarabBeetleEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SCARAB_BEETLE, ScarabBeetleEntityRenderer::new);
    }
}
