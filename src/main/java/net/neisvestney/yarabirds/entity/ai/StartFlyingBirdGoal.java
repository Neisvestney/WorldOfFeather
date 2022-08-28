package net.neisvestney.yarabirds.entity.ai;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.neisvestney.yarabirds.entity.AbstractBirdEntity;
import net.neisvestney.yarabirds.entity.ai.control.FreeFlightMoveControl;

public class StartFlyingBirdGoal extends Goal {
    private final AbstractBirdEntity bird;
    private final int chance;


    public StartFlyingBirdGoal(AbstractBirdEntity bird, int chance) {
        this.bird = bird;
        this.chance = chance;
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

        Box box = this.bird.getBoundingBox();
        if (!this.bird.world.isSpaceEmpty(this.bird, box.expand(0, 10, 0).offset(0, 10, 0))) {
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        this.bird.setMovementControl(new FreeFlightMoveControl(this.bird));
        this.bird.setFlying(true);
        Random random = (Random) this.bird.getRandom();
        double d = this.bird.getX();
        double e = this.bird.getY() + 10f;
        double f = this.bird.getZ();
        this.bird.getMoveControl().moveTo(d, e, f, 0.15);
    }

    @Override
    public boolean shouldContinue() {
        return super.shouldContinue();
    }
}
