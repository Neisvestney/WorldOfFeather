package net.neisvestney.yarabirds.entity.ai;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class FreeFlightMoveControl extends MoveControl {
    private final MobEntity entity;
    private int collisionCheckCooldown;

    public FreeFlightMoveControl(MobEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void tick() {
        if (this.state != MoveControl.State.MOVE_TO) {
            return;
        }
        if (this.collisionCheckCooldown-- <= 0) {
            this.collisionCheckCooldown += this.entity.getRandom().nextInt(5) + 2;
            Vec3d vec3d = new Vec3d(this.targetX - this.entity.getX(), this.targetY - this.entity.getY(), this.targetZ - this.entity.getZ());
            double d = vec3d.length();
            if (this.willCollide(vec3d = vec3d.normalize(), MathHelper.ceil(d))) {
                this.entity.setVelocity(this.entity.getVelocity().add(vec3d.multiply(0.1)));
            } else {
                this.state = MoveControl.State.WAIT;
            }
        }
    }

    private boolean willCollide(Vec3d direction, int steps) {
        Box box = this.entity.getBoundingBox();
        for (int i = 1; i < steps; ++i) {
            if (this.entity.world.isSpaceEmpty(this.entity, box = box.offset(direction))) continue;
            return false;
        }
        return true;
    }

}
