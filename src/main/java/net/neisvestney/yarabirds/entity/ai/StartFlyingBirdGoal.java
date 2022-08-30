package net.neisvestney.yarabirds.entity.ai;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.neisvestney.yarabirds.YaraBirds;
import net.neisvestney.yarabirds.entity.AbstractBirdEntity;
import net.neisvestney.yarabirds.entity.ai.control.FreeFlightMoveControl;

import java.util.EnumSet;

public class StartFlyingBirdGoal extends Goal {
    private final AbstractBirdEntity bird;
    private final int chance;

    private Vec3d target;


    public StartFlyingBirdGoal(AbstractBirdEntity bird, int chance) {
        this.bird = bird;
        this.chance = chance;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (this.bird.getFlying()) {
            return false;
        }

        MoveControl moveControl = this.bird.getMoveControl();
        if (moveControl.isMoving()) {
            return false;
        }

        if (this.bird.getRandom().nextInt(toGoalTicks(this.chance)) != 0) {
            return false;
        }

        Vec3d end = this.bird.getPos();
        for (int i = 0; i <= 360; i += 45) {
            YaraBirds.LOGGER.info("Try " + i);
            double yaw = i * 0.0174533;
            end = new Vec3d(end.x + Math.cos(yaw) * 15f , end.y + 8f,  end.z + Math.sin(yaw) * 15f);
            BlockHitResult result = this.bird.world.raycast(new RaycastContext(this.bird.getPos(), end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.ANY, this.bird));
            if (result.getType() != HitResult.Type.MISS) continue;

            target = end;
            YaraBirds.LOGGER.info("Yes!");
            return true;
        }

        YaraBirds.LOGGER.info("Cant fly!");
        return false;
    }

    @Override
    public void start() {
        this.bird.setMovementControl(new FreeFlightMoveControl(this.bird));
        this.bird.setFlying(true);
        this.bird.getMoveControl().moveTo(target.x, target.y, target.z, 0.15);
    }

    @Override
    public boolean shouldContinue() {
        MoveControl control = this.bird.getMoveControl();
        double d = Math.pow(control.getTargetX() - this.bird.getX(), 2) + Math.pow(control.getTargetY() - this.bird.getY(), 2) + Math.pow(control.getTargetZ() - this.bird.getZ(), 2);
        return d > 1 && d < 36000 && !this.bird.isOnGround();
    }
}
