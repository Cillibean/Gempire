package com.gempire.entities.gems;

import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityVaryingGem;
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

public class EntityTourmaline extends EntityVaryingGem {
    //TO-DO: IMPLEMENT TOURMALINE. Tourmaline will take care of one crop variety per Tourmaline.
    // Perfect Tourmalines will take care of several. Much like Zircon and her enchantments.
    // Tourmaline will have aerokinesis (wind manipulation) as an additional ability.
    //TO-DO: IMPLEMENT COLOR VARIANTS
    public EntityTourmaline(EntityType<? extends CreatureEntity> type, World worldIn) {
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
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MobEntity.class, 1, false, false, (p_234199_0_) -> {
            return p_234199_0_ instanceof IMob;
        }));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
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
    public boolean hasMarkings() {
        return this.getSkinColorVariant() == 16;
    }

    @Override
    public boolean hasMarkings2() {
        return false;
    }

    @Override
    public int maxMarkings() {
        return 1;
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
    public boolean UsesUniqueNames() {
        return true;
    }

    @Override
    public String NameFromColor(byte i) {
        String name = "";
        switch(i){
            case 1:
                name = "pumpkin";
                break;
            case 2:
                name = "mushroom";
                break;
            case 3:
                name = "olenite";
                break;
            case 4:
                name = "canary";
                break;
            case 5:
                name = "verdelite";
                break;
            case 6:
                name = "rossmanite";
                break;
            case 7:
                name = "adachiite";
                break;
            case 8:
                name = "povondraite";
                break;
            case 9:
                name = "paraiba";
                break;
            case 10:
                name = "siberite";
                break;
            case 11:
                name = "indicolite";
                break;
            case 12:
                name = "dravite";
                break;
            case 13:
                name = "congo";
                break;
            case 14:
                name = "rubellite";
                break;
            case 15:
                name = "schorl";
                break;
            case 16:
                name = "watermelon";
                break;
            default:
                name = "tourmaline";
                break;
        }
        return name;
    }

    public String NameFromColor() {
        return "tourmaline";
    }

    @Override
    public int generateInsigniaColor() {
        return 5;
    }

    @Override
    public int generateOutfitColor() {
        return 13;
    }

    @Override
    public boolean hasOutfitPlacementVariant() {
        return true;
    }

    @Override
    public int[] outfitPlacementVariants() {
        return new int[]{
                11, 17
        };
    }

    public Abilities[] possibleAbilities(){
        return new Abilities[]{
                Abilities.NO_ABILITY, Abilities.TANK, Abilities.BEEFCAKE, Abilities.POWERHOUSE, Abilities.UNHINGED
        };
    }
    public Abilities[] definiteAbilities(){
        return new Abilities[]{
                Abilities.FARMER, Abilities.AEROKINESIS
        };
    }

    @Override
    public boolean generateIsEmotional() {
        return true;
    }

    @Override
    public byte EmotionThreshold() {
        return 15;
    }

    public boolean canChangeUniformColorByDefault() {
        return true;
    }

    public boolean canChangeInsigniaColorByDefault(){
        return true;
    }

    @Override
    public boolean isImmuneToFire(){
        return true;
    }

    public boolean hasSkinColorVariant(){
        return true;
    }

    public int[] NeglectedColors() {
        return new int[]{
                17
        };
    }

    public int generateOutfitVariant(){
        return this.rand.nextInt(4);
    }

    public int generateInsigniaVariant(){
        if (this.getGemPlacement() == 11) {
            return this.getGemPlacement() != 11 ? this.getOutfitVariant() : 4;
        } else if (this.getGemPlacement() == 17) {
            return this.getGemPlacement() != 17 ? this.getOutfitVariant() : 5;
        } else {
            return this.getOutfitVariant();
        }
    }

    @Override
    public int baseFocus() {
        return 7;
    }

}
