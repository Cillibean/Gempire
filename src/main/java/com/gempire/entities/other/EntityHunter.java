package com.gempire.entities.other;

import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EntityHunter extends PathfinderMob implements RangedAttackMob, GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation ATTACK_ANIMATION = RawAnimation.begin().thenPlay("attack.ranged");

    public EntityHunter(EntityType<? extends PathfinderMob> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        RangedBowAttackGoal<EntityHunter> goal = new RangedBowAttackGoal<>(this, 1.0, 20, 15.0F);
        goal.setMinAttackInterval(60);
        this.goalSelector.addGoal(4, goal);
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EntityGem.class, true));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    @Override
    public HumanoidArm getMainArm() {
        if (random.nextInt(1) == 0) {
            return HumanoidArm.RIGHT;
        } else {
            return HumanoidArm.LEFT;
        }
    }

    public void performRangedAttack(LivingEntity p_32141_, float p_32142_) {
        ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, (item) -> {
            return item instanceof BowItem;
        })));
        AbstractArrow abstractarrow = this.getArrow(itemstack, p_32142_);
        if (this.getMainHandItem().getItem() instanceof BowItem) {
            abstractarrow = ((BowItem)this.getMainHandItem().getItem()).customArrow(abstractarrow);
        }

        double d0 = p_32141_.getX() - this.getX();
        double d1 = p_32141_.getY(0.3333333333333333) - abstractarrow.getY();
        double d2 = p_32141_.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        abstractarrow.shoot(d0, d1 + d3 * 0.20000000298023224, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(abstractarrow);
    }

    protected AbstractArrow getArrow(ItemStack p_32156_, float p_32157_) {
        AbstractArrow $$2 = ProjectileUtil.getMobArrow(this, p_32156_, p_32157_);
        if ($$2 instanceof Arrow) {
            ((Arrow)$$2).addEffect(new MobEffectInstance(ModEffects.ELECTROCUTION.get(), 20));
        }
        return $$2;
    }

    public boolean canFireProjectileWeapon(ProjectileWeaponItem p_32144_) {
        return p_32144_ == Items.BOW;
    }

    public void rideTick() {
        super.rideTick();
        Entity entity = this.getControlledVehicle();
        if (entity instanceof PathfinderMob pathfindermob) {
            this.yBodyRot = pathfindermob.yBodyRot;
        }

    }

    protected float getStandingEyeHeight(Pose p_32154_, EntityDimensions p_32155_) {
        return 1.74F;
    }

    public double getMyRidingOffset() {
        return -0.6;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(DefaultAnimations.genericWalkIdleController(this));
        controllerRegistrar.add(DefaultAnimations.genericAttackAnimation(this, ATTACK_ANIMATION));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
