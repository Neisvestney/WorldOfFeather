package net.neisvestney.worldoffeather;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.neisvestney.worldoffeather.registry.WorldOfFeatherEntityRenderers;

@Environment(EnvType.CLIENT)
public class WorldOfFeatherClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        WorldOfFeather.LOGGER.info("worldoffeather start initializing client");

        WorldOfFeatherEntityRenderers.register();
    }
}
