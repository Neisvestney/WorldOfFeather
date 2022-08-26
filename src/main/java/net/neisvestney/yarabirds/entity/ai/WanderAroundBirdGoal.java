package net.neisvestney.yarabirds.entity.ai;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.neisvestney.yarabirds.entity.RavenEntity;

public class WanderAroundBirdGoal extends WanderAroundFarGoal {
    protected final RavenEntity mob;

    public WanderAroundBirdGoal(RavenEntity bird, double d) {
        super(bird, d);
        this.mob = bird;
    }

    public WanderAroundBirdGoal(RavenEntity bird, double speed, float probability) {
        super(bird, speed, probability);
        this.mob = bird;
    }

    @Override
    public boolean canStart() {
        if (mob.getFlying()) {
            return false;
        }

        return super.canStart();
    }

    @Override
    public void start() {
        this.mob.setMovementControl(new MoveControl(this.mob));
        super.start();
    }
}
