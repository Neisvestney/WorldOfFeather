package net.neisvestney.worldoffeather.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.neisvestney.worldoffeather.client.entity.renderer.HoodedCrowEntityRenderer;
import net.neisvestney.worldoffeather.client.entity.renderer.MagpieEntityRenderer;
import net.neisvestney.worldoffeather.client.entity.renderer.RavenEntityRenderer;

@Environment(EnvType.CLIENT)
public class WorldOfFeatherEntityRenderers {

    public static void register() {
        EntityRendererRegistry.register(WorldOfFeatherEntities.HOODED_CROW, HoodedCrowEntityRenderer::new);
        EntityRendererRegistry.register(WorldOfFeatherEntities.MAGPIE, MagpieEntityRenderer::new);
        EntityRendererRegistry.register(WorldOfFeatherEntities.RAVEN, RavenEntityRenderer::new);
    }
}
