package com.gempire.entities.gems;

import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityRedSapphire extends EntityGem {
    public boolean defensive = false;

    public EntityRedSapphire(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        compound.putBoolean("defensive", this.defensive);
        super.addAdditionalSaveData(compound);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.defensive = compound.getBoolean("defensive");
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        this.defensive = true;
        return super.hurt(source, amount);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(9, new FloatGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (p_213621_0_) -> {
            return p_213621_0_ instanceof Enemy;
        }));
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.TOP_OF_HEAD, GemPlacements.FOREHEAD, GemPlacements.BACK_OF_HEAD, GemPlacements.LEFT_EYE,
                GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.LEFT_EAR, GemPlacements.RIGHT_EAR,
                GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.BELLY, GemPlacements.LEFT_ARM, GemPlacements.RIGHT_ARM,
        };
    }

    @Override
    public int generateHairVariant() {
        return this.random.nextInt(24);
    }

    public String NameFromColor() {
        return "";
    }

    public int generateOutfitVariant() {
        return this.random.nextInt(31);
    }

    public int generateInsigniaVariant() {
        return this.getOutfitVariant();
    }

    @Override
    public int generateSkinColorVariant() {
        return 14;
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
                Abilities.NO_ABILITY, Abilities.TANK, Abilities.BEEFCAKE, Abilities.UNHINGED
        };
    }

    @Override
    public Abilities[] definiteAbilities() {
        return new Abilities[]{
                Abilities.PYROKINESIS
        };
    }

    @Override
    public boolean generateIsEmotional() {
        return false;
    }

    @Override
    public byte EmotionThreshold() {
        return 30;
    }

    public boolean canChangeUniformColorByDefault() {
        return false;
    }

    public boolean canChangeInsigniaColorByDefault(){
        return false;
    }

    @Override
    public boolean fireImmune(){
        return true;
    }

    public boolean hasSkinColorVariant(){
        return false;
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
