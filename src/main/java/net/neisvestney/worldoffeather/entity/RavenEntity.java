package net.neisvestney.worldoffeather.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class RavenEntity extends AbstractBirdEntity {
    public RavenEntity(EntityType<? extends AbstractBirdEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected String getAnimationsPostfix() {
        return "raven";
    }
}
