package dev.dassen.desolatedungeons.client.render.entity;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.client.render.entity.model.ScarabBeetleEntityModel;
import dev.dassen.desolatedungeons.client.render.entity.state.ScarabBeetleEntityRenderState;
import dev.dassen.desolatedungeons.entity.ScarabBeetleEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ScarabBeetleEntityRenderer extends MobEntityRenderer<ScarabBeetleEntity, ScarabBeetleEntityRenderState, ScarabBeetleEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(DesolateDungeons.MOD_ID, "textures/entity/scarab_beetle/scarab_beetle.png");

    public ScarabBeetleEntityRenderer(EntityRendererFactory.Context context) {
        // The float is the entity shadow radius.
        super(context, new ScarabBeetleEntityModel(context.getPart(ScarabBeetleEntityModel.SCARAB_BEETLE)), 0.3f);
    }

    @Override
    public Identifier getTexture(ScarabBeetleEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public ScarabBeetleEntityRenderState createRenderState() {
        return new ScarabBeetleEntityRenderState();
    }

    @Override
    public void updateRenderState(ScarabBeetleEntity entity, ScarabBeetleEntityRenderState renderState, float tickDelta) {
        super.updateRenderState(entity, renderState, tickDelta);
        renderState.controlState = entity.getControlState();
        renderState.changedControlStateTickDelta = entity.getChangedControlStateTickDelta();
        renderState.idlingAnimationState.copyFrom(entity.idlingAnimationState);
        renderState.walkingAnimationState.copyFrom(entity.walkingAnimationState);
        renderState.ascendingAnimationState.copyFrom(entity.ascendingAnimationState);
        renderState.flyingAnimationState.copyFrom(entity.flyingAnimationState);
        renderState.descendingAnimationState.copyFrom(entity.descendingAnimationState);
    }
}
