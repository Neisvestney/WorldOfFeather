package net.neisvestney.yarabirds.entity.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.neisvestney.yarabirds.YaraBirds;
import net.neisvestney.yarabirds.entity.RavenEntity;

public class LandBirdGoal extends Goal {
    private final RavenEntity bird;
    private final int chance;


    public LandBirdGoal(RavenEntity bird, int chance) {
        this.bird = bird;
        this.chance = chance;
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
        HitResult result = this.bird.world.raycast(new RaycastContext(this.bird.getPos(), end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.ANY, this.bird));
        if (result.getType() != HitResult.Type.BLOCK) {
            return false;
        }
        BlockState block = this.bird.world.getBlockState(((BlockHitResult) result).getBlockPos());
        YaraBirds.LOGGER.info("Block: " + block + " " + result.squaredDistanceTo(this.bird));
        if (!block.getMaterial().isSolid() || block.getMaterial().isLiquid()) {
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        this.bird.setMovementControl(new MoveControl(this.bird));
        this.bird.setNoGravity(false);
        this.bird.setFlying(false);
    }
}
