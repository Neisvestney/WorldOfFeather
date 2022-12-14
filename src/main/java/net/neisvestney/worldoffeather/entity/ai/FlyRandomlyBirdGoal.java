package net.neisvestney.worldoffeather.entity.ai;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.RaycastContext;
import net.neisvestney.worldoffeather.entity.AbstractBirdEntity;
import net.neisvestney.worldoffeather.entity.ai.control.FreeFlightMoveControl;

import java.util.EnumSet;

public class FlyRandomlyBirdGoal extends Goal {
    private final AbstractBirdEntity bird;

    public FlyRandomlyBirdGoal(AbstractBirdEntity bird) {
        this.bird = bird;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (!this.bird.getFlying()) {
            return false;
        }

        double f;
        double e;
        MoveControl moveControl = this.bird.getMoveControl();
        if (!moveControl.isMoving()) {
            return true;
        }
        double d = moveControl.getTargetX() - this.bird.getX();
        double g = d * d + (e = moveControl.getTargetY() - this.bird.getY()) * e + (f = moveControl.getTargetZ() - this.bird.getZ()) * f;
        return g < 1.0 || g > 3600.0;
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }

    @Override
    public void start() {
        this.bird.setMovementControl(new FreeFlightMoveControl(this.bird));

        double currentHeight = -1;
        Vec3d end = this.bird.getPos();
        end = new Vec3d(end.x, 0, end.z);
        HitResult result = this.bird.world.raycast(new RaycastContext(this.bird.getPos(), end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.ANY, this.bird));
        if (result.getType() == HitResult.Type.BLOCK) {
            currentHeight = this.bird.getY() - ((BlockHitResult) result).getBlockPos().getY();
        }

        Random random = (Random) this.bird.getRandom();
        double d = this.bird.getX() + (double) ((random.nextFloat() * 2.0f - 1.0f) * 50.0f);
        double e = this.bird.getY() + (double) ((random.nextFloat() * 2.0f - 1.0f) * 2 + currentHeight != -1 && currentHeight < 6 ? 6 : 0);
        double f = this.bird.getZ() + (double) ((random.nextFloat() * 2.0f - 1.0f) * 50.0f);
        this.bird.getMoveControl().moveTo(d, e, f, 1.0);
    }
}