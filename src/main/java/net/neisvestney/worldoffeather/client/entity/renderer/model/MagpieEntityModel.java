package net.neisvestney.worldoffeather.client.entity.renderer.model;

import net.minecraft.util.Identifier;
import net.neisvestney.worldoffeather.WorldOfFeather;
import net.neisvestney.worldoffeather.entity.MagpieEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class MagpieEntityModel extends AnimatedGeoModel<MagpieEntity> {
    @Override
    public Identifier getModelResource(MagpieEntity object) {
        return new Identifier(WorldOfFeather.MOD_ID, "geo/magpie.geo.json");
    }

    @Override
    public Identifier getTextureResource(MagpieEntity object) {
        return new Identifier(WorldOfFeather.MOD_ID, "textures/entity/magpie/magpie.png");
    }

    @Override
    public Identifier getAnimationResource(MagpieEntity animatable) {
        return new Identifier(WorldOfFeather.MOD_ID, "animations/magpie.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(MagpieEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("neck");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            int pitch = entity.getFlying() ? 30 : 0;
            head.setRotationX((extraData.headPitch - pitch) * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
