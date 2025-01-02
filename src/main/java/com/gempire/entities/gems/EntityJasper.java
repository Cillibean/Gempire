package com.gempire.entities.gems;

import com.gempire.config.GempireServerConfigs;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

import java.util.ArrayList;

public class EntityJasper extends AbstractQuartz {

    public EntityJasper(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new FloatGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new EntityAIFollowAssigned(this, 1.0D));
        this.goalSelector.addGoal(8, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(8, new EntityAIStay(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowGarnet(this, 0.7D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkRebel));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityAbomination.class, 1, false, false, this::checkNotSludged));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityShambler.class, 1, false, false, this::checkNotSludged));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityCrawler.class, 1, false, false, this::checkNotSludged));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 1, false, false, (p_234199_0_) -> p_234199_0_.getClassification(true) == MobCategory.MONSTER));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGemGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGemGoal(this));
        this.goalSelector.addGoal(1, new EntityAISludged(this, 0.6));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkBothSludged));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 1, false, false, this::checkSludged));
    this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EntityGem.class, 6.0F, 1.0D, 1.2D, this::checkElseSludged));
   }
    public boolean getIsCut()
    {
        return false;
    }

    public boolean flocksTo(EntityGem gem) {
        return gem.isPopular() || gem instanceof EntityEmerald;
    }

    @Override
    public boolean hasMarkings() {
        return true;
    }

    @Override
    public int maxMarkings() {
        return (this.getSkinColorVariant() == 5 || this.getSkinColorVariant() == 9) ? 2 : 1;
    }

    @Override
    public int maxMarkings2() {
        return 1;
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public int generateHairVariant() {
        return this.random.nextInt(20);
    }

    @Override
    public int[] NeglectedColors() {
        return null;
    }

    @Override
    public Float baseXScale() {
        return 1.05F;
    }

    @Override
    public Float baseYScale() {
        return 1.15F;
    }

    @Override
    public Float baseZScale() {
        return 1.05F;
    }

    @Override
    public boolean hasMarkings2() {
        return this.getSkinColorVariant() == 2 ||
                this.getSkinColorVariant() == 3 ||
                this.getSkinColorVariant() == 6 ||
                this.getSkinColorVariant() == 8 ||
                this.getSkinColorVariant() == 10 ||
                this.getSkinColorVariant() == 11 ||
                this.getSkinColorVariant() == 12 ||
                this.getSkinColorVariant() == 14 ||
                this.getSkinColorVariant() == 16;
    }

    @Override
    public String NameFromColor(byte i) {
        String name = "";
        switch(i){
            case 1:
                name = "red_striped";
                break;
            case 2:
                name = "ripple";
                break;
            case 3:
                name = "imperial";
                break;
            case 4:
                name = "golden";
                break;
            case 5:
                name = "rainforest";
                break;
            case 6:
                name = "mookaite";
                break;
            case 7:
                name = "matrix";
                break;
            case 8:
                name = "picasso";
                break;
            case 9:
                name = "ocean";
                break;
            case 10:
                name = "royal_plume";
                break;
            case 11:
                name = "blue_snakeskin";
                break;
            case 12:
                name = "biggs";
                break;
            case 13:
                name = "kambaba";
                break;
            case 14:
                name = "flame";
                break;
            case 15:
                name = "black";
                break;
            case 16:
                name = "butterfly";
                break;
            default:
                name = "zebra";
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
    public int generateOutfitVariant() {
        return this.random.nextInt(5);
    }

    @Override
    public int generateInsigniaVariant() {
        return this.getGemPlacement() != 17 ? this.getOutfitVariant() : 0;
    }

    public int generateRebelInsigniaVariant() {
        return this.getGemPlacement() != 17 ? this.getRebelOutfitVariant() : 0;
    }

    @Override
    public int generateVisorVariant() {
        return this.random.nextInt(2);
    }

    @Override
    public boolean hasSkinColorVariant() {
        return true;
    }

    @Override
    public boolean generateIsEmotional() {
        return GempireServerConfigs.OUTBURSTS.get();
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
        return new ArrayList<>();
    }

    @Override
    public int baseFocus() {
        return 3;
    }

    @Override
    public boolean hasOutfitPlacementVariant() {
        return true;
    }

    @Override
    public int[] outfitPlacementVariants() {
        return new int[]{
                17
        };
    }
}
