package com.gempire.entities.gems;

import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.AbstractQuartz;
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

public class EntityAgate extends AbstractQuartz {

    public EntityAgate(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 60.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.35D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D)
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
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MobEntity.class, 1, false, false, (p_234199_0_) -> {
            return p_234199_0_ instanceof IMob;
        }));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.1D, false));
    }

    @Override
    public boolean hasMarkings() {
        return this.getSkinColorVariant() == 0 || this.getSkinColorVariant() == 2 || this.getSkinColorVariant() == 4 || this.getSkinColorVariant() == 6 || this.getSkinColorVariant() == 8 || this.getSkinColorVariant() == 9 || this.getSkinColorVariant() == 10 || this.getSkinColorVariant() == 12 || this.getSkinColorVariant() == 14 || this.getSkinColorVariant() == 16;
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
        return this.rand.nextInt(3);
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
    public int generateOutfitVariant() {
        return 0;
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
        return false;
    }

    @Override
    public byte EmotionThreshold() {
        return 30;
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
        return new Abilities[]{
                Abilities.STERN
        };
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
