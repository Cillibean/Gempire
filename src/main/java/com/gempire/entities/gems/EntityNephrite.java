package com.gempire.entities.gems;

import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class EntityNephrite extends EntityGem {

    public EntityNephrite(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new SwimGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, MobEntity.class, 1, false, false, (p_234199_0_) -> {
            return p_234199_0_ instanceof IMob;
        }));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.1D, false));
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.FOREHEAD, GemPlacements.LEFT_EYE, GemPlacements.LEFT_EAR, GemPlacements.RIGHT_EAR,
                GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.LEFT_SHOULDER, GemPlacements.RIGHT_SHOULDER,
                GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.BELLY,
                GemPlacements.LEFT_KNEE, GemPlacements.RIGHT_KNEE, GemPlacements.BACK_OF_HEAD, GemPlacements.TOP_OF_HEAD, GemPlacements.LEFT_ANKLE,
                GemPlacements.RIGHT_ANKLE, GemPlacements.LEFT_PALM, GemPlacements.RIGHT_PALM, GemPlacements.LEFT_THIGH, GemPlacements.RIGHT_THIGH,
                GemPlacements.LEFT_ARM, GemPlacements.RIGHT_ARM
        };
    }

    @Override
    public int generateHairVariant() {
        return this.rand.nextInt(10);
    }

    @Override
    public int generateInsigniaColor() {
        return 6;
    }

    @Override
    public int generateOutfitColor() {
        return 15;
    }

    @Override
    public boolean hasOutfitPlacementVariant() {
        return false;
    }

    @Override
    public int[] outfitPlacementVariants() {
        return new int[]{

        };
    }

    public Abilities[] possibleAbilities(){
        return new Abilities[]{
                Abilities.KNOCKBACK, Abilities.NO_ABILITY, Abilities.TANK, Abilities.BEEFCAKE, Abilities.POWERHOUSE, Abilities.UNHINGED
        };
    }
    public Abilities[] definiteAbilities(){
        return new Abilities[]{
                Abilities.PARALYSIS, Abilities.SCOUT
        };
    }

    public int generateSkinColorVariant() {
        return 0;
    }

    @Override
    public boolean generateIsEmotional() {
        return true;
    }

    @Override
    public byte EmotionThreshold() {
        return 20;
    }

    public boolean canChangeUniformColorByDefault() {
        return true;
    }

    public boolean canChangeInsigniaColorByDefault(){
        return true;
    }

    @Override
    public boolean isImmuneToFire(){
        return false;
    }

    public boolean hasSkinColorVariant(){
        return false;
    }

    public int generateOutfitVariant(){
        return 0;
    }

    public int generateInsigniaVariant(){
        return 0;
    }

    @Override
    public int baseFocus() {
        return 7;
    }

    @Override
    public boolean canLocateStructures() {
        return true;
    }

    @Override
    public boolean isOnStructureCooldown() {
        return false;
    }

    @Override
    public boolean canOpenInventoryByDefault() {
        return true;
    }
}
