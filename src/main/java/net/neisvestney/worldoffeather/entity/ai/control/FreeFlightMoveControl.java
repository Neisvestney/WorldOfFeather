package net.neisvestney.worldoffeather.entity.ai.control;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.neisvestney.worldoffeather.entity.AbstractBirdEntity;

public class FreeFlightMoveControl extends MoveControl {
    private final AbstractBirdEntity bird;
    private int collisionCheckCooldown;

    public FreeFlightMoveControl(AbstractBirdEntity bird) {
        super(bird);
        this.bird = bird;
    }

    @Override
    public void tick() {
        if (this.state != MoveControl.State.MOVE_TO) {
            this.bird.setNoGravity(false);
            return;
        }
        this.bird.setNoGravity(true);
        if (this.collisionCheckCooldown-- <= 0) {
            this.collisionCheckCooldown += this.bird.getRandom().nextInt(5) + 2;
            Vec3d vec3d = new Vec3d(this.targetX - this.bird.getX(), this.targetY - this.bird.getY(), this.targetZ - this.bird.getZ());
            double d = vec3d.length();
            if (this.willCollide(vec3d = vec3d.normalize(), MathHelper.ceil(d))) {
                this.bird.setVelocity(this.bird.getVelocity().add(vec3d.multiply(0.1)));
            } else {
                this.state = MoveControl.State.WAIT;
            }
        }
    }

    private boolean willCollide(Vec3d direction, int steps) {
        Box box = this.bird.getBoundingBox();
        for (int i = 1; i < steps; ++i) {
            if (this.bird.world.isSpaceEmpty(this.bird, box = box.offset(direction))) continue;
            return false;
        }
        return true;
    }

}
