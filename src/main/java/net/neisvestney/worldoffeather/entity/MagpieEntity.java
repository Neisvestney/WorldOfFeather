package net.neisvestney.worldoffeather.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.world.World;

public class MagpieEntity extends AbstractBirdEntity {
    public MagpieEntity(EntityType<? extends AbstractBirdEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected String getAnimationsPostfix() {
        return "magpie";
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        // this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
    }
}
