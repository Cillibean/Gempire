package com.gempire.entities.gems;

import com.gempire.entities.ai.*;
import com.gempire.entities.bases.AbstractQuartz;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class EntityQuartz extends AbstractQuartz {

    public EntityQuartz(EntityType<? extends PathfinderMob> type, Level worldIn) {
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
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkRebel));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 1, false, false, (p_234199_0_) -> p_234199_0_.getClassification(true) == MobCategory.MONSTER));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGemGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGemGoal(this));
    }

    @Override
    public boolean hasMarkings() {
        return this.getSkinColorVariant() == 2 || this.getSkinColorVariant() == 3 || this.getSkinColorVariant() == 6 || this.getSkinColorVariant() == 12 || this.getSkinColorVariant() == 13 || this.getSkinColorVariant() == 17;
    }

    @Override
    public boolean hasMarkings2() {
        return this.getSkinColorVariant() == 17;
    }

    @Override
    public int maxMarkings() {
        return 1;
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
    public String NameFromColor(byte i) {
        String name = "";
        switch(i){
            case 1:
                name = "chert";
                break;
            case 2:
                name = "cherry_quartz";
                break;
            case 3:
                name = "blue_aventurine";
                break;
            case 4:
                name = "citrine";
                break;
            case 5:
                name = "prasiolite";
                break;
            case 6:
                name = "rose_quartz";
                break;
            case 7:
                name = "flint";
                break;
            case 8:
                name = "smoky_quartz";
                break;
            case 9:
                name = "chalcedony";
                break;
            case 10:
                name = "amethyst";
                break;
            case 11:
                name = "dumortierite_quartz";
                break;
            case 12:
                name = "tigers_eye";
                break;
            case 13:
                name = "heliotrope";
                break;
            case 14:
                name = "carnelian";
                break;
            case 15:
                name = "onyx";
                break;
            case 16:
                name = "angel_aura_quartz";
                break;
            case 17:
                name = "lace_amethyst";
                break;
            default:
                name = "milky_quartz";
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
        return this.getGemPlacement() != 17 ? this.getRebelOutfitVariant() : 1;
    }

    @Override
    public boolean hasSkinColorVariant() {
        return true;
    }

    @Override
    public boolean generateIsEmotional() {
        return true;
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
    public Abilities[] definiteAbilities() {
        return new Abilities[0];
    }

    @Override
    public int baseFocus() {
        return 7;
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
