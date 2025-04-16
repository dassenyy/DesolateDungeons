package dev.dassen.desolatedungeons.client.render.entity.state;

import dev.dassen.desolatedungeons.entity.ScarabBeetleEntity;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class ScarabBeetleEntityRenderState extends LivingEntityRenderState {
    public ScarabBeetleEntity.ControlState controlState;
    public long changedControlStateTickDelta;
    public final AnimationState idlingAnimationState = new AnimationState();
    public final AnimationState walkingAnimationState = new AnimationState();
    public final AnimationState ascendingAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();
    public final AnimationState descendingAnimationState = new AnimationState();
}
