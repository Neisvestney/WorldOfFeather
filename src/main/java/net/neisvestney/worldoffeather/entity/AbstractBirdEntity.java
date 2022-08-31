package net.neisvestney.worldoffeather.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.neisvestney.worldoffeather.entity.ai.*;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class AbstractBirdEntity extends AnimalEntity implements IAnimatable {
    protected final AnimationFactory factory = new AnimationFactory(this);
    protected static final TrackedData<Boolean> FLYING = DataTracker.registerData(AbstractBirdEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public AbstractBirdEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.3f);
    }

    @Override
    protected float getJumpVelocity() {
        return 0.55F * this.getJumpVelocityMultiplier();
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        Vec3d vec3d = this.getVelocity();
        if (!this.getFlying() && !this.onGround && vec3d.y < 0.0) {
            this.setVelocity(new Vec3d(vec3d.x, -0.1, vec3d.z));
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        //this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(2, new StartFlyingBirdGoal(this, 120));
        this.goalSelector.add(3, new LandBirdGoal(this, 240));
        this.goalSelector.add(4, new LookForwardBirdGoal(this));
        this.goalSelector.add(5, new FlyRandomlyBirdGoal(this));
        this.goalSelector.add(6, new WanderAroundBirdGoal(this, 1.0));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    public void setMovementControl(MoveControl moveControl) {
        this.moveControl = moveControl;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FLYING, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Flying", this.getFlying());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setFlying(nbt.getBoolean("Flying"));
    }

    public void setFlying(boolean flying) {
        this.dataTracker.set(FLYING, flying);
    }

    public boolean getFlying() {
        return (boolean)this.dataTracker.get(FLYING);
    }

    protected abstract String getAnimationsPostfix();

    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        Vec3d vec3d = this.getVelocity();
        if (!this.onGround && vec3d.y < -0.01) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("glide." + getAnimationsPostfix(), true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving() || vec3d.y > 0.03f) {
            if (this.getFlying()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("fly." + getAnimationsPostfix(), true));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walk." + getAnimationsPostfix(), true));
            }
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle." + getAnimationsPostfix(), true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
