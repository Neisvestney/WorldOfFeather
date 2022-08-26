package net.neisvestney.yarabirds;

import net.fabricmc.api.ModInitializer;
import net.neisvestney.yarabirds.registry.YaraBirdsEntities;
import net.neisvestney.yarabirds.registry.YaraBirdsItems;
import net.neisvestney.yarabirds.world.gen.YaraBirdsEntitySpawn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class YaraBirds implements ModInitializer {
	public static final String MOD_ID = "yarabirds";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("YaraBirds start initializing");

		YaraBirdsEntities.register();
		YaraBirdsItems.register();
		YaraBirdsEntitySpawn.register();

		GeckoLib.initialize();
	}
}
