package net.neisvestney.worldoffeather.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.neisvestney.worldoffeather.WorldOfFeather;
import net.neisvestney.worldoffeather.entity.HoodedCrowEntity;
import net.neisvestney.worldoffeather.entity.MagpieEntity;
import net.neisvestney.worldoffeather.entity.RavenEntity;

public class WorldOfFeatherEntities {
    public static final EntityType<HoodedCrowEntity> HOODED_CROW = registerEntity(
            "hooded_crow", FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HoodedCrowEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    public static final EntityType<MagpieEntity> MAGPIE = registerEntity(
            "magpie", FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MagpieEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    public static final EntityType<RavenEntity> RAVEN = registerEntity(
            "raven", FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RavenEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    public static <T extends Entity> EntityType<T> registerEntity(String name, EntityType<T> entry) {
        return Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(WorldOfFeather.MOD_ID, name),
                entry
        );
    }

    public static void register() {
        FabricDefaultAttributeRegistry.register(HOODED_CROW, HoodedCrowEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(MAGPIE, MagpieEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(RAVEN, RavenEntity.createMobAttributes());
    }
}
