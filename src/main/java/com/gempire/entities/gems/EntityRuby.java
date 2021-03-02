package com.gempire.entities.gems;

import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.util.Abilities;
import com.gempire.util.Color;
import com.gempire.util.GemPlacements;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityRuby extends EntityGem {
    public static final int SKIN_COLOR_START = 0xF4705C;
    public static final int SKIN_COLOR_MID = 0xED454A;
    public static final int SKIN_COLOR_MID2 = 0xD9234C;
    public static final int SKIN_COLOR_MID3 = 0x9E0333;
    public static final int SKIN_COLOR_END = 0x4F1434;

    public static final int HAIR_COLOR_START = 0x140001;
    public static final int HAIR_COLOR_MID = 0x210004;
    public static final int HAIR_COLOR_MID2 = 0x480A1B;
    public static final int HAIR_COLOR_END = 0x600C27;

    public EntityRuby(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(9, new SwimGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MobEntity.class, 1, false, false, (p_234199_0_) -> {
            return p_234199_0_ instanceof IMob;
        }));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.1D, false));
    }

    @Override
    public int generateSkinColor(){
        ArrayList<Integer> skins = new ArrayList<>();
        skins.add(EntityRuby.SKIN_COLOR_START);
        skins.add(EntityRuby.SKIN_COLOR_MID);
        skins.add(EntityRuby.SKIN_COLOR_MID2);
        skins.add(EntityRuby.SKIN_COLOR_MID3);
        skins.add(EntityRuby.SKIN_COLOR_END);
        return Color.lerpHex(skins);
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.TOP_OF_HEAD, GemPlacements.FOREHEAD, GemPlacements.BACK_OF_HEAD, GemPlacements.LEFT_EYE, GemPlacements.RIGHT_EYE, GemPlacements.NOSE,
                GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.LEFT_EAR, GemPlacements.RIGHT_EAR, GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.BELLY,
                GemPlacements.LEFT_SHOULDER, GemPlacements.RIGHT_SHOULDER, GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.LEFT_PALM, GemPlacements.RIGHT_PALM,
                GemPlacements.LEFT_THIGH, GemPlacements.RIGHT_THIGH, GemPlacements.LEFT_ANKLE, GemPlacements.RIGHT_ANKLE
        };
    }

    @Override
    public int generateHairColor() {
        ArrayList<Integer> skins = new ArrayList<>();
        skins.add(EntityRuby.HAIR_COLOR_START);
        skins.add(EntityRuby.HAIR_COLOR_MID);
        skins.add(EntityRuby.HAIR_COLOR_MID2);
        skins.add(EntityRuby.HAIR_COLOR_END);
        return Color.lerpHex(skins);
    }

    @Override
    public int generateHairVariant() {
        return this.rand.nextInt(3);
    }

    @Override
    public int generateGemColor() {
        return 0xAA0019;
    }

    @Override
    public int getOutfitVariant() {
        return 0;
    }

    @Override
    public int generateInsigniaColor() {
        return 0xFFCE2E;
    }

    @Override
    public int generateAbilitySlots(){
        //TODO: Temporary
        return 3;
    }

    public Abilities[] possibleAbilities(){
        return new Abilities[]{
                Abilities.KNOCKBACK, Abilities.PARALYSIS, Abilities.NO_ABILITY
        };
    }
    public Abilities[] definiteAbilities(){
        return new Abilities[]{
                Abilities.PYROKINESIS
        };
    }

    public int generateSkinColorVariant() {
        return 0;
    }

    @Override
    public boolean generateIsEmotional() {
        return true;
    }

    public boolean canChangeUniformColorByDefault() {
        return false;
    }

    public boolean canChangeInsigniaColorByDefault(){
        return true;
    }

    @Override
    public boolean isImmuneToFire(){
        return true;
    }
}
