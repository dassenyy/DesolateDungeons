package dev.dassen.desolatedungeons.client.render.entity.model;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.client.render.entity.animation.ScarabBeetleEntityAnimations;
import dev.dassen.desolatedungeons.client.render.entity.state.ScarabBeetleEntityRenderState;
import dev.dassen.desolatedungeons.entity.ScarabBeetleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ScarabBeetleEntityModel extends EntityModel<ScarabBeetleEntityRenderState> {
    public static final EntityModelLayer SCARAB_BEETLE = new EntityModelLayer(Identifier.of(DesolateDungeons.MOD_ID, "scarab_beetle"), "main");

    private final ModelPart head;

    public ScarabBeetleEntityModel(ModelPart part) {
        super(part);

        this.head = part.getChild("root").getChild("scarab_beetle").getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData scarab_beetle = root.addChild("scarab_beetle", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData head = scarab_beetle.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -1.5F, -2.0F, 4.0F, 3.0F, 2.0F, new Dilation(0.0F))
            .uv(14, 2).cuboid(-1.0F, -2.5F, -3.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
            .uv(12, 2).cuboid(1.0F, -2.5F, -3.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -0.5F, -5.0F));

        ModelPartData thorax = scarab_beetle.addChild("thorax", ModelPartBuilder.create().uv(0, 5).cuboid(-4.0F, -2.0F, 0.0F, 8.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -5.0F));

        ModelPartData left_front_leg = thorax.addChild("left_front_leg", ModelPartBuilder.create().uv(4, 39).cuboid(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.1745F));

        ModelPartData left_front_foot = left_front_leg.addChild("left_front_foot", ModelPartBuilder.create(), ModelTransform.of(3.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

        ModelPartData left_front_foot_r1 = left_front_foot.addChild("left_front_foot_r1", ModelPartBuilder.create().uv(0, 36).cuboid(0.0F, -1.0F, -1.5F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, -0.5F, 0.0F, 3.1416F, 0.0F));

        ModelPartData right_front_leg = thorax.addChild("right_front_leg", ModelPartBuilder.create().uv(10, 39).cuboid(-3.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 2.0F, 1.0F, 0.0F, 0.0F, -0.1745F));

        ModelPartData right_front_foot = right_front_leg.addChild("right_front_foot", ModelPartBuilder.create().uv(18, 36).cuboid(0.0F, 0.0F, -2.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

        ModelPartData abdomen = scarab_beetle.addChild("abdomen", ModelPartBuilder.create().uv(0, 12).cuboid(-3.0F, -1.0F, 0.0F, 6.0F, 2.0F, 5.0F, new Dilation(0.0F))
            .uv(0, 19).cuboid(-4.0F, 1.0F, 0.0F, 8.0F, 1.0F, 6.0F, new Dilation(0.0F))
            .uv(17, 15).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -2.0F));

        ModelPartData left_elytron = abdomen.addChild("left_elytron", ModelPartBuilder.create().uv(0, 49).cuboid(-1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
            .uv(0, 57).cuboid(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 6.0F, new Dilation(0.0F))
            .uv(6, 49).cuboid(2.0F, 1.0F, 0.0F, 1.0F, 2.0F, 6.0F, new Dilation(0.0F))
            .uv(14, 52).cuboid(-1.0F, 1.0F, 5.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -2.0F, 0.0F));

        ModelPartData left_wing = abdomen.addChild("left_wing", ModelPartBuilder.create().uv(2, 29).cuboid(-1.0F, 0.0F, -1.0F, 3.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -1.0F, 2.0F));

        ModelPartData left_wing_middle = left_wing.addChild("left_wing_middle", ModelPartBuilder.create().uv(2, 33).cuboid(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, 3.0F, 3.1416F, 0.0F, 0.0F));

        ModelPartData left_wing_tip = left_wing_middle.addChild("left_wing_tip", ModelPartBuilder.create().uv(4, 37).cuboid(-2.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 4.0F, 3.1416F, 0.0F, 0.0F));

        ModelPartData right_elytron = abdomen.addChild("right_elytron", ModelPartBuilder.create().uv(32, 49).cuboid(0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
            .uv(26, 57).cuboid(-3.0F, 0.0F, 0.0F, 3.0F, 1.0F, 6.0F, new Dilation(0.0F))
            .uv(24, 49).cuboid(-3.0F, 1.0F, 0.0F, 1.0F, 2.0F, 6.0F, new Dilation(0.0F))
            .uv(22, 52).cuboid(-2.0F, 1.0F, 5.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -2.0F, 0.0F));

        ModelPartData right_wing = abdomen.addChild("right_wing", ModelPartBuilder.create().uv(8, 29).cuboid(-2.0F, 0.0F, -1.0F, 3.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -1.0F, 2.0F));

        ModelPartData right_wing_middle = right_wing.addChild("right_wing_middle", ModelPartBuilder.create().uv(8, 33).cuboid(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, 3.0F, 3.1416F, 0.0F, 0.0F));

        ModelPartData right_wing_tip = right_wing_middle.addChild("right_wing_tip", ModelPartBuilder.create().uv(10, 37).cuboid(-1.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 4.0F, 3.1416F, 0.0F, 0.0F));

        ModelPartData left_middle_leg = abdomen.addChild("left_middle_leg", ModelPartBuilder.create().uv(4, 41).cuboid(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.1745F));

        ModelPartData left_middle_foot = left_middle_leg.addChild("left_middle_foot", ModelPartBuilder.create(), ModelTransform.of(3.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

        ModelPartData left_middle_foot_r1 = left_middle_foot.addChild("left_middle_foot_r1", ModelPartBuilder.create().uv(0, 38).cuboid(0.0F, -1.0F, -1.5F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.5F, 0.0F, 3.1416F, 0.0F));

        ModelPartData right_middle_leg = abdomen.addChild("right_middle_leg", ModelPartBuilder.create().uv(10, 41).cuboid(-3.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 2.0F, 1.0F, 0.0F, 0.0F, -0.1745F));

        ModelPartData right_middle_foot = right_middle_leg.addChild("right_middle_foot", ModelPartBuilder.create().uv(18, 38).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

        ModelPartData left_hind_leg = abdomen.addChild("left_hind_leg", ModelPartBuilder.create().uv(4, 43).cuboid(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.1745F));

        ModelPartData left_hind_foot = left_hind_leg.addChild("left_hind_foot", ModelPartBuilder.create(), ModelTransform.of(3.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

        ModelPartData left_hind_foot_r1 = left_hind_foot.addChild("left_hind_foot_r1", ModelPartBuilder.create().uv(0, 40).cuboid(0.0F, -1.0F, -1.5F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.5F, 0.0F, 3.1416F, 0.0F));

        ModelPartData right_hind_leg = abdomen.addChild("right_hind_leg", ModelPartBuilder.create().uv(10, 43).cuboid(-3.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 2.0F, 5.0F, 0.0F, 0.0F, -0.1745F));

        ModelPartData right_hind_foot = right_hind_leg.addChild("right_hind_foot", ModelPartBuilder.create().uv(18, 40).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(ScarabBeetleEntityRenderState renderState) {
        super.setAngles(renderState);
        this.setHeadAngles(renderState, renderState.yawDegrees, renderState.pitch);

        this.animate(renderState.idlingAnimationState, ScarabBeetleEntityAnimations.IDLING, renderState.age, 1.0F);
        this.animate(renderState.ascendingAnimationState, ScarabBeetleEntityAnimations.ASCENDING, renderState.age, 1.0F);
        this.animate(renderState.descendingAnimationState, ScarabBeetleEntityAnimations.DESCENDING, renderState.age, 1.0F);
        if (renderState.controlState == ScarabBeetleEntity.ControlState.WALKING) {
            this.animateWalking(ScarabBeetleEntityAnimations.WALKING, renderState.limbFrequency, renderState.limbAmplitudeMultiplier, 10.0f, 10.0f);
        } else if (renderState.changedControlStateTickDelta > 11L && renderState.controlState == ScarabBeetleEntity.ControlState.FLYING) {
            this.animateWalking(ScarabBeetleEntityAnimations.FLYING, renderState.limbFrequency, renderState.limbAmplitudeMultiplier, 4.0f, 4.0f);
        }
    }

    private void setHeadAngles(ScarabBeetleEntityRenderState renderState, float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * ((float)Math.PI / 180F);
        this.head.pitch = headPitch * ((float)Math.PI / 180F);
    }
}