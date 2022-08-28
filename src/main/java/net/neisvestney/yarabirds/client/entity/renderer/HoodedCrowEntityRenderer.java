package net.neisvestney.yarabirds.client.entity.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.neisvestney.yarabirds.YaraBirds;
import net.neisvestney.yarabirds.client.entity.renderer.model.HoodedCrowEntityModel;
import net.neisvestney.yarabirds.entity.HoodedCrowEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HoodedCrowEntityRenderer extends GeoEntityRenderer<HoodedCrowEntity> {
    public HoodedCrowEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new HoodedCrowEntityModel());
    }

    @Override
    public Identifier getTextureResource(HoodedCrowEntity instance) {
        return new Identifier(YaraBirds.MOD_ID, "textures/entity/hooded_crow/hooded_crow.png");
    }
}
