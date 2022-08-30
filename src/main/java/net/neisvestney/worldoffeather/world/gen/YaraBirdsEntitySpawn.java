package net.neisvestney.worldoffeather.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.neisvestney.worldoffeather.registry.WorldOfFeatherEntities;

public class YaraBirdsEntitySpawn {
    public static void register() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, WorldOfFeatherEntities.HOODED_CROW, 25, 1, 2);
        SpawnRestriction.register(WorldOfFeatherEntities.HOODED_CROW, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::canMobSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, WorldOfFeatherEntities.MAGPIE, 25, 1, 2);
        SpawnRestriction.register(WorldOfFeatherEntities.MAGPIE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::canMobSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, WorldOfFeatherEntities.RAVEN, 25, 1, 2);
        SpawnRestriction.register(WorldOfFeatherEntities.RAVEN, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::canMobSpawn);
    }
}
