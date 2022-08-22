package net.neisvestney.yarabirds;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.neisvestney.yarabirds.registry.YaraBirdsEntityRenderers;

@Environment(EnvType.CLIENT)
public class YaraBirdsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        YaraBirds.LOGGER.info("YaraBirds start initializing client");

        YaraBirdsEntityRenderers.register();
    }
}
