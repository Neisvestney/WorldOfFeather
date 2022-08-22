package net.neisvestney.yarabirds.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.neisvestney.yarabirds.YaraBirds;
import net.neisvestney.yarabirds.entity.RavenEntity;

public class YaraBirdsEntities {
    public static final EntityType<RavenEntity> RAVEN = registerEntity(
            "raven", FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RavenEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    public static <T extends Entity> EntityType<T> registerEntity(String name, EntityType<T> entry) {
        return Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(YaraBirds.MOD_ID, name),
                entry
        );
    }

    public static void register() {
        FabricDefaultAttributeRegistry.register(RAVEN, RavenEntity.createMobAttributes());
    }
}