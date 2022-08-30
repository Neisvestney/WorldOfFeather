package net.neisvestney.worldoffeather.client.entity.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.neisvestney.worldoffeather.WorldOfFeather;
import net.neisvestney.worldoffeather.client.entity.renderer.model.RavenEntityModel;
import net.neisvestney.worldoffeather.entity.RavenEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RavenEntityRenderer extends GeoEntityRenderer<RavenEntity> {
    public RavenEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RavenEntityModel());
    }

    @Override
    public Identifier getTextureResource(RavenEntity instance) {
        return new Identifier(WorldOfFeather.MOD_ID, "textures/entity/raven/raven.png");
    }
}
