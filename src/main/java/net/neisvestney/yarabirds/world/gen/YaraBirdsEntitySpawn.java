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
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, YaraBirdsEntities.RAVEN, 25, 1, 2);
        SpawnRestriction.register(YaraBirdsEntities.RAVEN, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::canMobSpawn);
    }
}
