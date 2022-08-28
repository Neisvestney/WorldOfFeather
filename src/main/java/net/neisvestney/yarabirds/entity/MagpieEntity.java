package net.neisvestney.yarabirds.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class MagpieEntity extends AbstractBirdEntity {
    public MagpieEntity(EntityType<? extends AbstractBirdEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected String getAnimationsPostfix() {
        return "magpie";
    }
}
