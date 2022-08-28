package net.neisvestney.yarabirds.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.neisvestney.yarabirds.registry.YaraBirdsEntities;

public class YaraBirdsEntitySpawn {
    public static void register() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, YaraBirdsEntities.HOODED_CROW, 25, 1, 2);
        SpawnRestriction.register(YaraBirdsEntities.HOODED_CROW, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::canMobSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, YaraBirdsEntities.MAGPIE, 25, 1, 2);
        SpawnRestriction.register(YaraBirdsEntities.MAGPIE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::canMobSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, YaraBirdsEntities.RAVEN, 25, 1, 2);
        SpawnRestriction.register(YaraBirdsEntities.RAVEN, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::canMobSpawn);
    }
}
