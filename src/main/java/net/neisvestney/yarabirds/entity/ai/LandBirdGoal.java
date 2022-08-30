package net.neisvestney.yarabirds.entity.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.neisvestney.yarabirds.entity.AbstractBirdEntity;

import java.util.EnumSet;

public class LandBirdGoal extends Goal {
    private final AbstractBirdEntity bird;
    private final int chance;

    private BlockPos pos;


    public LandBirdGoal(AbstractBirdEntity bird, int chance) {
        this.bird = bird;
        this.chance = chance;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (!this.bird.getFlying()) {
            return false;
        }

        if (this.bird.getRandom().nextInt(toGoalTicks(this.chance)) != 0) {
            return false;
        }

        Vec3d end = this.bird.getPos();
        end = new Vec3d(end.x, 0, end.z);
        BlockHitResult result = this.bird.world.raycast(new RaycastContext(this.bird.getPos(), end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.ANY, this.bird));
        if (result.getType() != HitResult.Type.BLOCK) {
            return false;
        }
        BlockState block = this.bird.world.getBlockState(result.getBlockPos());
        // YaraBirds.LOGGER.info("Block: " + block + " " + result.squaredDistanceTo(this.bird));
        if (!block.getMaterial().isSolid() || block.getMaterial().isLiquid()) {
            return false;
        }

        pos = result.getBlockPos();
        return true;
    }

    @Override
    public void start() {
        this.bird.getMoveControl().moveTo(pos.getX(), pos.getY() + 1, pos.getZ(), 1.0f);
    }

    @Override
    public boolean shouldContinue() {
        MoveControl control = this.bird.getMoveControl();
        double d = Math.pow(control.getTargetX() - this.bird.getX(), 2) + Math.pow(control.getTargetY() - this.bird.getY(), 2) + Math.pow(control.getTargetZ() - this.bird.getZ(), 2);
        return d > 16 && d < 36000;
    }

    @Override
    public void stop() {
        this.bird.setMovementControl(new MoveControl(this.bird));
        this.bird.setNoGravity(false);
        this.bird.setFlying(false);
    }
}
