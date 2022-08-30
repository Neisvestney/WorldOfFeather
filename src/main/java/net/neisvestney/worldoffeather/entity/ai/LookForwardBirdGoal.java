package net.neisvestney.worldoffeather.entity.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.neisvestney.worldoffeather.entity.AbstractBirdEntity;

import java.util.EnumSet;

public class LookForwardBirdGoal extends Goal {
    private final AbstractBirdEntity bird;

    public LookForwardBirdGoal(AbstractBirdEntity bird) {
        this.bird = bird;
        this.setControls(EnumSet.of(Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (!this.bird.getFlying()) return false;

        return true;
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        Vec3d vec3d = this.bird.getVelocity();
        this.bird.setYaw(-((float) MathHelper.atan2(vec3d.x, vec3d.z)) * 57.295776f);
        this.bird.bodyYaw = this.bird.getYaw();
        this.bird.setPitch(40);
    }
}
