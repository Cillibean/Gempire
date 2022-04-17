package com.gempire.entities.gems;

import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class EntitySpodumene extends EntityVaryingGem {

    public EntitySpodumene(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.ATTACK_SPEED, .5D)
                .add(net.minecraftforge.common.ForgeMod.SWIM_SPEED.get(), 1D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new FloatGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 1, false, false, (p_234199_0_) -> {
            return p_234199_0_ instanceof Enemy;
        }));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public int generateHairVariant() {
        return 0;
    }

    @Override
    public boolean UsesUniqueNames() {
        return true;
    }

    @Override
    public int[] NeglectedColors() {
        return new int[]{
                0,2,3,5,6,7,8,9,12,14,15,16,17
        };
    }

    @Override
    public String NameFromColor(byte i) {
        switch(i){
            case 4:
                return "triphane";
            case 10:
                return "kunzite";
            case 11:
                return "blue_spodumene";
            case 13:
                return "hiddenite";
            default:
                return "spodumene";
        }
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.BACK, GemPlacements.BACK_OF_HEAD, GemPlacements.CHEST, GemPlacements.FOREHEAD, GemPlacements.LEFT_EAR,
                GemPlacements.BELLY, GemPlacements.RIGHT_EAR, GemPlacements.TOP_OF_HEAD
        };
    }

    @Override
    public int generateOutfitVariant() {
        return this.random.nextInt(2);
    }

    @Override
    public int generateInsigniaVariant() {
        return this.getOutfitVariant();
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
        return 20;
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
    public Abilities[] possibleAbilities(){
        return new Abilities[]{
                Abilities.PARALYSIS, Abilities.NO_ABILITY, Abilities.TANK, Abilities.BEEFCAKE, Abilities.POWERHOUSE
        };
    }

    @Override
    public Abilities[] definiteAbilities() {
        return new Abilities[]{
                Abilities.AMPHIBIAN, Abilities.ANGLER
        };
    }

    @Override
    public int baseFocus() {
        return 8;
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

    @Override
    public int getLuck() {
        return 3;
    }
}
