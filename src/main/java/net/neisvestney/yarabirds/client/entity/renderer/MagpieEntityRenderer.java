package net.neisvestney.yarabirds.client.entity.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.neisvestney.yarabirds.YaraBirds;
import net.neisvestney.yarabirds.client.entity.renderer.model.MagpieEntityModel;
import net.neisvestney.yarabirds.entity.MagpieEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MagpieEntityRenderer extends GeoEntityRenderer<MagpieEntity> {
    public MagpieEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new MagpieEntityModel());
    }

    @Override
    public Identifier getTextureResource(MagpieEntity instance) {
        return new Identifier(YaraBirds.MOD_ID, "textures/entity/magpie/magpie.png");
    }
}
