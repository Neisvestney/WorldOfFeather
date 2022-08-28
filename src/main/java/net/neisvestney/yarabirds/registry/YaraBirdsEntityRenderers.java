package net.neisvestney.yarabirds.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.neisvestney.yarabirds.client.entity.renderer.HoodedCrowEntityRenderer;
import net.neisvestney.yarabirds.client.entity.renderer.MagpieEntityRenderer;
import net.neisvestney.yarabirds.client.entity.renderer.RavenEntityRenderer;

@Environment(EnvType.CLIENT)
public class YaraBirdsEntityRenderers {

    public static void register() {
        EntityRendererRegistry.register(YaraBirdsEntities.HOODED_CROW, HoodedCrowEntityRenderer::new);
        EntityRendererRegistry.register(YaraBirdsEntities.MAGPIE, MagpieEntityRenderer::new);
        EntityRendererRegistry.register(YaraBirdsEntities.RAVEN, RavenEntityRenderer::new);
    }
}
