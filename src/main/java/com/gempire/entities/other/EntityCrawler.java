package com.gempire.entities.other;

import com.gempire.entities.ai.EntityAICrawlerAttackGoal;
import com.gempire.entities.bases.EntityGem;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidType;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class EntityCrawler extends Monster implements IAnimatable {
    private static final AnimationBuilder ATTACK_ANIMATION = new AnimationBuilder().addAnimation("animation.crawler.attack", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    private static final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("animation.crawler.idle", ILoopType.EDefaultLoopTypes.LOOP);
    private static final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("animation.crawler.walk", ILoopType.EDefaultLoopTypes.LOOP);
    private static final AnimationBuilder HURT_ANIMATION = new AnimationBuilder().addAnimation("animation.crawler.hurt", ILoopType.EDefaultLoopTypes.LOOP);
    private final AnimationFactory FACTORY = GeckoLibUtil.createFactory(this);
    public EntityCrawler(EntityType<? extends Monster> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.1D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(EntityCrawler.class, EntityDataSerializers.BOOLEAN);

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new FloatGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        //this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        //this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, true));
        //this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
        this.goalSelector.addGoal(1, new EntityAICrawlerAttackGoal(this, 1.2D, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, event -> {
            /*if (this.hurtMarked && !event.isMoving() && !this.swinging) {
                event.getController().setAnimation(HURT_ANIMATION);
                return PlayState.CONTINUE;
            } else*/ if (event.isMoving() && !this.swinging){
                event.getController().setAnimation(WALK_ANIMATION);
                return PlayState.CONTINUE;
            } else if (!event.isMoving() && !this.swinging) {
                event.getController().setAnimation(IDLE_ANIMATION);
                return PlayState.CONTINUE;
            }
            return PlayState.STOP;
        }));

        data.addAnimationController(new AnimationController(this, "attackController", 0, event -> {
            if (this.swinging) {
                event.getController().setAnimation(ATTACK_ANIMATION);
                return PlayState.CONTINUE;
            }
            event.getController().markNeedsReload();
            return PlayState.STOP;
        }));
    }

    @Override
    public AnimationFactory getFactory() {
        return FACTORY;
    }
    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    @Override
    public int getCurrentSwingDuration() {
        return 5;
    }
}
