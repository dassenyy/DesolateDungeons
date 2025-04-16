package dev.dassen.desolatedungeons.entity.ai.control;

import dev.dassen.desolatedungeons.entity.ScarabBeetleEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ScarabBeetleMoveControl extends MoveControl {
    private final ScarabBeetleEntity entity;

    public ScarabBeetleMoveControl(ScarabBeetleEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void tick() {
        if (this.entity.getControlState() == ScarabBeetleEntity.ControlState.WALKING) {
            this.entity.setNoGravity(false);
            this.entity.setUpwardSpeed(0f);
            super.tick();
        } else {
            if (this.state == MoveControl.State.MOVE_TO) {
                this.state = MoveControl.State.WAIT;
                this.entity.setNoGravity(true);

                Vec3d direction = new Vec3d(this.targetX - entity.getX(), this.targetY - entity.getY(), this.targetZ - entity.getZ());
                if (Math.sqrt(direction.lengthSquared()) < 0.0005d) {
                    this.entity.setForwardSpeed(0f);
                    this.entity.setUpwardSpeed(0f);
                    return;
                }

                float movementSpeed;
                if (this.entity.isOnGround()) {
                    movementSpeed = (float) (this.speed * this.entity.getAttributeValue(EntityAttributes.MOVEMENT_SPEED));
                } else {
                    movementSpeed = (float) (this.speed * this.entity.getAttributeValue(EntityAttributes.FLYING_SPEED));
                }
                this.entity.setMovementSpeed(movementSpeed);
                if (Math.abs(direction.y) > 0.00001f) {
                    this.entity.setUpwardSpeed(direction.y > 0d ? movementSpeed : -movementSpeed);
                }

                float entityYaw = (float) (MathHelper.atan2(direction.z, direction.x) * 180f / (float)Math.PI) - 90f;
                this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), entityYaw, 90f));
            } else {
                this.entity.setUpwardSpeed(0f);
                this.entity.setForwardSpeed(0f);
            }
        }
    }
}
