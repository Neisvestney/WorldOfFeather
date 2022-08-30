package net.neisvestney.worldoffeather;

import net.fabricmc.api.ModInitializer;
import net.neisvestney.worldoffeather.registry.WorldOfFeatherEntities;
import net.neisvestney.worldoffeather.registry.WorldOfFeatherItems;
import net.neisvestney.worldoffeather.world.gen.YaraBirdsEntitySpawn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class WorldOfFeather implements ModInitializer {
	public static final String MOD_ID = "worldoffeather";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("worldoffeather start initializing");

		WorldOfFeatherEntities.register();
		WorldOfFeatherItems.register();
		YaraBirdsEntitySpawn.register();

		GeckoLib.initialize();
	}
}
