package com.gempire.entities.gems;

import com.gempire.entities.abilities.AbilityStern;
import com.gempire.entities.abilities.AbilityTank;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.ai.*;
import com.gempire.entities.bases.AbstractQuartz;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import com.gempire.util.GemPlacements;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class EntityAgate extends AbstractQuartz {

    public EntityAgate(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new FloatGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new EntityAIFollowAssigned(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIStay(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityAbomination.class, 1, false, false, this::checkNotSludged));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityShambler.class, 1, false, false, this::checkNotSludged));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityCrawler.class, 1, false, false, this::checkNotSludged));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 1, false, false, (p_234199_0_) -> p_234199_0_.getClassification(true) == MobCategory.MONSTER));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkRebel));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.1D, false));
        this.targetSelector.addGoal(2, new OwnerHurtByTargetGemGoal(this));
        this.targetSelector.addGoal(3, new OwnerHurtTargetGemGoal(this));
        this.goalSelector.addGoal(1, new EntityAISludged(this, 0.6));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkSludged));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 1, false, false, this::checkSludged));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityGem.class, 6.0F, 1.0D, 1.2D, this::checkElseSludged));

    }

    @Override
    public Float baseXScale() {
        return 1.1F;
    }

    @Override
    public Float baseYScale() {
        return 1.15F;
    }

    @Override
    public Float baseZScale() {
        return 1.1F;
    }

    @Override
    public boolean hasMarkings() {
        return this.getSkinColorVariant() == 0 || this.getSkinColorVariant() == 2 || this.getSkinColorVariant() == 4 || this.getSkinColorVariant() == 5 || this.getSkinColorVariant() == 6 || this.getSkinColorVariant() == 8 || this.getSkinColorVariant() == 9 || this.getSkinColorVariant() == 10 || this.getSkinColorVariant() == 12 || this.getSkinColorVariant() == 14 || this.getSkinColorVariant() == 16;
    }

    @Override
    public int maxMarkings() {
        return 3;
    }

    @Override
    public int maxMarkings2() {
        return 0;
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public int generateHairVariant() {
        return this.random.nextInt(6);
    }

    @Override
    public int[] NeglectedColors() {
        return null;
    }

    @Override
    public boolean hasMarkings2() {
        return false;
    }

    @Override
    public String NameFromColor(byte i) {
        String name = "";
        switch(i){
            case 1:
                name = "wingate_pass_plume";
                break;
            case 2:
                name = "rose";
                break;
            case 3:
                name = "holly_blue";
                break;
            case 4:
                name = "tawny";
                break;
            case 5:
                name = "dragons_vein";
                break;
            case 6:
                name = "sakura";
                break;
            case 7:
                name = "botswana";
                break;
            case 8:
                name = "water";
                break;
            case 9:
                name = "blue_lace";
                break;
            case 10:
                name = "grape";
                break;
            case 11:
                name = "ellensburg_blue";
                break;
            case 12:
                name = "turritella";
                break;
            case 13:
                name = "tree";
                break;
            case 14:
                name = "lake_superior";
                break;
            case 15:
                name = "orca";
                break;
            case 16:
                name = "iris";
                break;
            default:
                name = "dendritic";
                break;
        }
        return name;
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.BACK, GemPlacements.BACK_OF_HEAD, GemPlacements.CHEST, GemPlacements.FOREHEAD, GemPlacements.LEFT_ANKLE, GemPlacements.LEFT_CHEEK,
                GemPlacements.LEFT_EAR, GemPlacements.LEFT_EYE, GemPlacements.LEFT_HAND, GemPlacements.LEFT_PALM, GemPlacements.LEFT_SHOULDER, GemPlacements.LEFT_THIGH,
                GemPlacements.BELLY, GemPlacements.NOSE, GemPlacements.RIGHT_ANKLE, GemPlacements.RIGHT_CHEEK, GemPlacements.RIGHT_EAR, GemPlacements.RIGHT_EYE,
                GemPlacements.RIGHT_HAND, GemPlacements.RIGHT_PALM, GemPlacements.RIGHT_SHOULDER, GemPlacements.RIGHT_THIGH, GemPlacements.TOP_OF_HEAD
        };
    }

    @Override
    public int generateOutfitVariant(){
        return this.random.nextInt(4);
    }

    @Override
    public int generateInsigniaVariant() {
        return this.getOutfitVariant();
    }

    @Override
    public int generateRebelInsigniaVariant() {
        return this.getRebelOutfitVariant();
    }


    @Override
    public boolean hasSkinColorVariant() {
        return true;
    }

    @Override
    public boolean generateIsEmotional() {
        return false;
    }

    @Override
    public byte EmotionThreshold() {
        return 10;
    }

    @Override
    public boolean canChangeUniformColorByDefault() {
        return true;
    }

    @Override
    public boolean canChangeInsigniaColorByDefault() {
        return true;
    }

    @Override
    public ArrayList<Ability> definiteAbilities() {
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new AbilityStern());
        return arrayList;
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public int baseFocus() {
        return 1;
    }

    @Override
    public int generateVisorVariant() {
        return this.random.nextInt(2);
    }
}
