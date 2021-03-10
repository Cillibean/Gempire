package com.gempire.entities.gems;

import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import com.gempire.util.Color;
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

import java.util.ArrayList;

public class EntitySapphire extends EntityGem {
    public static final int WHITE_SKIN_COLOR_START = 0xFFFFFF;
    public static final int WHITE_SKIN_COLOR_END = 0xE4E6E9;
    public static final int ORANGE_SKIN_COLOR_START = 0xFFE8B4;
    public static final int ORANGE_SKIN_COLOR_MID = 0xF08872;
    public static final int ORANGE_SKIN_COLOR_END = 0xC4414B;
    public static final int MAGENTA_SKIN_COLOR_START = 0xFAABFC;
    public static final int MAGENTA_SKIN_COLOR_MID = 0xDB50B5;
    public static final int MAGENTA_SKIN_COLOR_END = 0x790568;
    public static final int LIGHT_BLUE_SKIN_COLOR_START = 0xCBD9FC;
    public static final int LIGHT_BLUE_SKIN_COLOR_MID = 0x6B84D8;
    public static final int LIGHT_BLUE_SKIN_COLOR_END = 0x2F4496;
    public static final int YELLOW_SKIN_COLOR_START = 0xFCFFB4;
    public static final int YELLOW_SKIN_COLOR_MID = 0xFFF273;
    public static final int YELLOW_SKIN_COLOR_END = 0xDEB644;
    public static final int LIME_SKIN_COLOR_START = 0xD2ED67;
    public static final int LIME_SKIN_COLOR_MID = 0x86BD1D;
    public static final int LIME_SKIN_COLOR_END = 0x2B8200;
    public static final int PINK_SKIN_COLOR_START = 0xFFD9DE;
    public static final int PINK_SKIN_COLOR_MID = 0xFFABD5;
    public static final int PINK_SKIN_COLOR_END = 0xF35AB2;
    public static final int GRAY_SKIN_COLOR_START = 0x888A8C;
    public static final int GRAY_SKIN_COLOR_MID = 0x5D5F61;
    public static final int GRAY_SKIN_COLOR_END = 0x393B3D;
    public static final int LIGHT_GRAY_SKIN_COLOR_START = 0xE4E6E9;
    public static final int LIGHT_GRAY_SKIN_COLOR_MID = 0xB7BABD;
    public static final int LIGHT_GRAY_SKIN_COLOR_END = 0x888A8C;
    public static final int CYAN_SKIN_COLOR_START = 0xADFFFF;
    public static final int CYAN_SKIN_COLOR_MID = 0x49DDFC;
    public static final int CYAN_SKIN_COLOR_END = 0x2B9EEB;
    public static final int PURPLE_SKIN_COLOR_START = 0xFFA5FB;
    public static final int PURPLE_SKIN_COLOR_MID = 0xC147EB;
    public static final int PURPLE_SKIN_COLOR_END = 0x752FCF;
    public static final int BLUE_SKIN_COLOR_START = 0x7F9CFF;
    public static final int BLUE_SKIN_COLOR_MID = 0x4C56D4;
    public static final int BLUE_SKIN_COLOR_END = 0x272982;
    public static final int BROWN_SKIN_COLOR_START = 0xD1A26E;
    public static final int BROWN_SKIN_COLOR_MID = 0x7A533B;
    public static final int BROWN_SKIN_COLOR_END = 0x38201B;
    public static final int GREEN_SKIN_COLOR_START = 0x84E767;
    public static final int GREEN_SKIN_COLOR_MID = 0x117D37;
    public static final int GREEN_SKIN_COLOR_END = 0x00362A;
    public static final int BLACK_SKIN_COLOR_START = 0x393B3D;
    public static final int BLACK_SKIN_COLOR_MID = 0x212226;
    public static final int BLACK_SKIN_COLOR_END = 0x0D0D12;

    public static final int WHITE_HAIR_COLOR_START = 0xC7C9CC;
    public static final int WHITE_HAIR_COLOR_END = 0xA2A4A6;
    public static final int ORANGE_HAIR_COLOR_START = 0xFCD9CC;
    public static final int ORANGE_HAIR_COLOR_MID = 0xF0A773;
    public static final int ORANGE_HAIR_COLOR_MID2 = 0xF26262;
    public static final int ORANGE_HAIR_COLOR_END = 0xC23551;
    public static final int MAGENTA_HAIR_COLOR_START = 0xF9C7FF;
    public static final int MAGENTA_HAIR_COLOR_MID = 0xB2339E;
    public static final int MAGENTA_HAIR_COLOR_END = 0x51144F;
    public static final int LIGHT_BLUE_HAIR_COLOR_START = 0xEAEAFF;
    public static final int LIGHT_BLUE_HAIR_COLOR_MID = 0xB0D1FF;
    public static final int LIGHT_BLUE_HAIR_COLOR_END = 0x556CC0;
    public static final int YELLOW_HAIR_COLOR_START = 0xFFFFD5;
    public static final int YELLOW_HAIR_COLOR_MID = 0xEDD852;
    public static final int YELLOW_HAIR_COLOR_END = 0xB48127;
    public static final int LIME_HAIR_COLOR_START = 0xEEFF82;
    public static final int LIME_HAIR_COLOR_MID = 0x88C42B;
    public static final int LIME_HAIR_COLOR_END = 0x106B00;
    public static final int PINK_HAIR_COLOR_START = 0xFFA9BE;
    public static final int PINK_HAIR_COLOR_MID = 0xED64A8;
    public static final int PINK_HAIR_COLOR_END = 0xA125A3;
    public static final int GRAY_HAIR_COLOR_START = 0x48494A;
    public static final int GRAY_HAIR_COLOR_MID = 0x313233;
    public static final int GRAY_HAIR_COLOR_END = 0x232426;
    public static final int LIGHT_GRAY_HAIR_COLOR_START = 0x969799;
    public static final int LIGHT_GRAY_HAIR_COLOR_MID = 0x636566;
    public static final int LIGHT_GRAY_HAIR_COLOR_END = 0x48494A;
    public static final int CYAN_HAIR_COLOR_START = 0xDBFEFF;
    public static final int CYAN_HAIR_COLOR_MID = 0x3CB5ED;
    public static final int CYAN_HAIR_COLOR_END = 0x0777BF;
    public static final int PURPLE_HAIR_COLOR_START = 0xF8B7FF;
    public static final int PURPLE_HAIR_COLOR_MID = 0xA633EB;
    public static final int PURPLE_HAIR_COLOR_END = 0x4A139D;
    public static final int BLUE_HAIR_COLOR_START = 0xB4DCFE;
    public static final int BLUE_HAIR_COLOR_MID = 0x496AF3;
    public static final int BLUE_HAIR_COLOR_END = 0x1928A5;
    public static final int BROWN_HAIR_COLOR_START = 0xF9DFBE;
    public static final int BROWN_HAIR_COLOR_MID = 0x5C3018;
    public static final int BROWN_HAIR_COLOR_END = 0x260F0A;
    public static final int GREEN_HAIR_COLOR_START = 0xD2F38F;
    public static final int GREEN_HAIR_COLOR_MID = 0x236750;
    public static final int GREEN_HAIR_COLOR_END = 0x032636;
    public static final int BLACK_HAIR_COLOR_START = 0x17171C;
    public static final int BLACK_HAIR_COLOR_MID = 0x0A0A0D;
    public static final int BLACK_HAIR_COLOR_END = 0x050508;

    public EntitySapphire(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D);
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
    }

    @Override
    public int generateSkinColor(){
        ArrayList<Integer> skins = new ArrayList<>();
        switch (this.getSkinColorVariant()){
            case 0:
                skins.add(EntitySapphire.WHITE_SKIN_COLOR_START);
                skins.add(EntitySapphire.WHITE_SKIN_COLOR_END);
                break;
            case 1:
                skins.add(EntitySapphire.ORANGE_SKIN_COLOR_START);
                skins.add(EntitySapphire.ORANGE_SKIN_COLOR_MID);
                skins.add(EntitySapphire.ORANGE_SKIN_COLOR_END);
                break;
            case 2:
                skins.add(EntitySapphire.MAGENTA_SKIN_COLOR_START);
                skins.add(EntitySapphire.MAGENTA_SKIN_COLOR_MID);
                skins.add(EntitySapphire.MAGENTA_SKIN_COLOR_END);
                break;
            case 3:
                skins.add(EntitySapphire.LIGHT_BLUE_SKIN_COLOR_START);
                skins.add(EntitySapphire.LIGHT_BLUE_SKIN_COLOR_MID);
                skins.add(EntitySapphire.LIGHT_BLUE_SKIN_COLOR_END);
                break;
            case 4:
                skins.add(EntitySapphire.YELLOW_SKIN_COLOR_START);
                skins.add(EntitySapphire.YELLOW_SKIN_COLOR_MID);
                skins.add(EntitySapphire.YELLOW_SKIN_COLOR_END);
                break;
            case 5:
                skins.add(EntitySapphire.LIME_SKIN_COLOR_START);
                skins.add(EntitySapphire.LIME_SKIN_COLOR_MID);
                skins.add(EntitySapphire.LIME_SKIN_COLOR_END);
                break;
            case 6:
                skins.add(EntitySapphire.PINK_SKIN_COLOR_START);
                skins.add(EntitySapphire.PINK_SKIN_COLOR_MID);
                skins.add(EntitySapphire.PINK_SKIN_COLOR_END);
                break;
            case 7:
                skins.add(EntitySapphire.GRAY_SKIN_COLOR_START);
                skins.add(EntitySapphire.GRAY_SKIN_COLOR_MID);
                skins.add(EntitySapphire.GRAY_SKIN_COLOR_END);
                break;
            case 8:
                skins.add(EntitySapphire.LIGHT_GRAY_SKIN_COLOR_START);
                skins.add(EntitySapphire.LIGHT_GRAY_SKIN_COLOR_MID);
                skins.add(EntitySapphire.LIGHT_GRAY_SKIN_COLOR_END);
                break;
            case 9:
                skins.add(EntitySapphire.CYAN_SKIN_COLOR_START);
                skins.add(EntitySapphire.CYAN_SKIN_COLOR_MID);
                skins.add(EntitySapphire.CYAN_SKIN_COLOR_END);
                break;
            case 10:
                skins.add(EntitySapphire.PURPLE_SKIN_COLOR_START);
                skins.add(EntitySapphire.PURPLE_SKIN_COLOR_MID);
                skins.add(EntitySapphire.PURPLE_SKIN_COLOR_END);
                break;
            case 11:
                skins.add(EntitySapphire.BLUE_SKIN_COLOR_START);
                skins.add(EntitySapphire.BLUE_SKIN_COLOR_MID);
                skins.add(EntitySapphire.BLUE_SKIN_COLOR_END);
                break;
            case 12:
                skins.add(EntitySapphire.BROWN_SKIN_COLOR_START);
                skins.add(EntitySapphire.BROWN_SKIN_COLOR_MID);
                skins.add(EntitySapphire.BROWN_SKIN_COLOR_END);
                break;
            case 13:
                skins.add(EntitySapphire.GREEN_SKIN_COLOR_START);
                skins.add(EntitySapphire.GREEN_SKIN_COLOR_MID);
                skins.add(EntitySapphire.GREEN_SKIN_COLOR_END);
                break;
            case 15:
                skins.add(EntitySapphire.BLACK_SKIN_COLOR_START);
                skins.add(EntitySapphire.BLACK_SKIN_COLOR_MID);
                skins.add(EntitySapphire.BLACK_SKIN_COLOR_END);
                break;
            default:
                skins.add(EntityRuby.SKIN_COLOR_START);
                skins.add(EntityRuby.SKIN_COLOR_MID);
                skins.add(EntityRuby.SKIN_COLOR_MID2);
                skins.add(EntityRuby.SKIN_COLOR_MID3);
                skins.add(EntityRuby.SKIN_COLOR_END);
                break;
        }
        return Color.lerpHex(skins);
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
                GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.LEFT_PALM, GemPlacements.RIGHT_PALM
        };
    }

    @Override
    public int generateHairColor() {
        ArrayList<Integer> skins = new ArrayList<>();
        switch (this.getSkinColorVariant()){
            case 0:
                skins.add(EntitySapphire.WHITE_HAIR_COLOR_START);
                skins.add(EntitySapphire.WHITE_HAIR_COLOR_END);
                break;
            case 1:
                skins.add(EntitySapphire.ORANGE_HAIR_COLOR_START);
                skins.add(EntitySapphire.ORANGE_HAIR_COLOR_MID);
                skins.add(EntitySapphire.ORANGE_HAIR_COLOR_MID2);
                skins.add(EntitySapphire.ORANGE_HAIR_COLOR_END);
                break;
            case 2:
                skins.add(EntitySapphire.MAGENTA_HAIR_COLOR_START);
                skins.add(EntitySapphire.MAGENTA_HAIR_COLOR_MID);
                skins.add(EntitySapphire.MAGENTA_HAIR_COLOR_END);
                break;
            case 3:
                skins.add(EntitySapphire.LIGHT_BLUE_HAIR_COLOR_START);
                skins.add(EntitySapphire.LIGHT_BLUE_HAIR_COLOR_MID);
                skins.add(EntitySapphire.LIGHT_BLUE_HAIR_COLOR_END);
                break;
            case 4:
                skins.add(EntitySapphire.YELLOW_HAIR_COLOR_START);
                skins.add(EntitySapphire.YELLOW_HAIR_COLOR_MID);
                skins.add(EntitySapphire.YELLOW_HAIR_COLOR_END);
                break;
            case 5:
                skins.add(EntitySapphire.LIME_HAIR_COLOR_START);
                skins.add(EntitySapphire.LIME_HAIR_COLOR_MID);
                skins.add(EntitySapphire.LIME_HAIR_COLOR_END);
                break;
            case 6:
                skins.add(EntitySapphire.PINK_HAIR_COLOR_START);
                skins.add(EntitySapphire.PINK_HAIR_COLOR_MID);
                skins.add(EntitySapphire.PINK_HAIR_COLOR_END);
                break;
            case 7:
                skins.add(EntitySapphire.GRAY_HAIR_COLOR_START);
                skins.add(EntitySapphire.GRAY_HAIR_COLOR_MID);
                skins.add(EntitySapphire.GRAY_HAIR_COLOR_END);
                break;
            case 8:
                skins.add(EntitySapphire.LIGHT_GRAY_HAIR_COLOR_START);
                skins.add(EntitySapphire.LIGHT_GRAY_HAIR_COLOR_MID);
                skins.add(EntitySapphire.LIGHT_GRAY_HAIR_COLOR_END);
                break;
            case 9:
                skins.add(EntitySapphire.CYAN_HAIR_COLOR_START);
                skins.add(EntitySapphire.CYAN_HAIR_COLOR_MID);
                skins.add(EntitySapphire.CYAN_HAIR_COLOR_END);
                break;
            case 10:
                skins.add(EntitySapphire.PURPLE_HAIR_COLOR_START);
                skins.add(EntitySapphire.PURPLE_HAIR_COLOR_MID);
                skins.add(EntitySapphire.PURPLE_HAIR_COLOR_END);
                break;
            case 11:
                skins.add(EntitySapphire.BLUE_HAIR_COLOR_START);
                skins.add(EntitySapphire.BLUE_HAIR_COLOR_MID);
                skins.add(EntitySapphire.BLUE_HAIR_COLOR_END);
                break;
            case 12:
                skins.add(EntitySapphire.BROWN_HAIR_COLOR_START);
                skins.add(EntitySapphire.BROWN_HAIR_COLOR_MID);
                skins.add(EntitySapphire.BROWN_HAIR_COLOR_END);
                break;
            case 13:
                skins.add(EntitySapphire.GREEN_HAIR_COLOR_START);
                skins.add(EntitySapphire.GREEN_HAIR_COLOR_MID);
                skins.add(EntitySapphire.GREEN_HAIR_COLOR_END);
                break;
            case 14:
                skins.add(EntityRuby.HAIR_COLOR_START);
                skins.add(EntityRuby.HAIR_COLOR_MID);
                skins.add(EntityRuby.HAIR_COLOR_MID2);
                skins.add(EntityRuby.HAIR_COLOR_END);
                break;
            case 15:
                skins.add(EntitySapphire.BLACK_HAIR_COLOR_START);
                skins.add(EntitySapphire.BLACK_HAIR_COLOR_MID);
                skins.add(EntitySapphire.BLACK_HAIR_COLOR_END);
                break;
        }
        return Color.lerpHex(skins);
    }

    @Override
    public int generateHairVariant() {
        return 0;
    }

    @Override
    public int generateGemColor() {
        switch (this.getSkinColorVariant()) {
            case 0:
                return EntitySapphire.WHITE_HAIR_COLOR_END;
            case 1:
                return EntitySapphire.ORANGE_HAIR_COLOR_END;
            case 2:
                return EntitySapphire.MAGENTA_HAIR_COLOR_END;
            case 3:
                return EntitySapphire.LIGHT_BLUE_HAIR_COLOR_END;
            case 4:
                return EntitySapphire.YELLOW_HAIR_COLOR_END;
            case 5:
                return EntitySapphire.LIME_HAIR_COLOR_END;
            case 6:
                return EntitySapphire.PINK_HAIR_COLOR_END;
            case 7:
                return EntitySapphire.GRAY_HAIR_COLOR_END;
            case 8:
                return EntitySapphire.LIGHT_GRAY_HAIR_COLOR_END;
            case 9:
                return EntitySapphire.CYAN_HAIR_COLOR_END;
            case 10:
                return EntitySapphire.PURPLE_HAIR_COLOR_END;
            case 11:
                return EntitySapphire.BLUE_HAIR_COLOR_END;
            case 12:
                return EntitySapphire.BROWN_HAIR_COLOR_END;
            case 13:
                return EntitySapphire.GREEN_HAIR_COLOR_END;
            case 14:
                return EntityRuby.HAIR_COLOR_END;
            case 15:
                return EntitySapphire.BLACK_HAIR_COLOR_END;
            default:
                return EntitySapphire.WHITE_HAIR_COLOR_END;
        }
    }

    @Override
    public int getOutfitVariant() {
        return 0;
    }

    @Override
    public int getOutfitColor() {
        switch (this.getSkinColorVariant()) {
            case 0:
                return EntitySapphire.WHITE_SKIN_COLOR_START;
            case 1:
                return EntitySapphire.ORANGE_SKIN_COLOR_START;
            case 2:
                return EntitySapphire.MAGENTA_SKIN_COLOR_START;
            case 3:
                return EntitySapphire.LIGHT_BLUE_SKIN_COLOR_START;
            case 4:
                return EntitySapphire.YELLOW_SKIN_COLOR_START;
            case 5:
                return EntitySapphire.LIME_SKIN_COLOR_START;
            case 6:
                return EntitySapphire.PINK_SKIN_COLOR_START;
            case 7:
                return EntitySapphire.GRAY_SKIN_COLOR_START;
            case 8:
                return EntitySapphire.LIGHT_GRAY_SKIN_COLOR_START;
            case 9:
                return EntitySapphire.CYAN_SKIN_COLOR_START;
            case 10:
                return EntitySapphire.PURPLE_SKIN_COLOR_START;
            case 11:
                return EntitySapphire.BLUE_SKIN_COLOR_START;
            case 12:
                return EntitySapphire.BROWN_SKIN_COLOR_START;
            case 13:
                return EntitySapphire.GREEN_SKIN_COLOR_START;
            case 14:
                return EntityRuby.HAIR_COLOR_START;
            case 15:
                return EntitySapphire.BLACK_SKIN_COLOR_START;
            default:
                return EntitySapphire.WHITE_SKIN_COLOR_START;
        }
    }

    @Override
    public int generateInsigniaColor() {
        switch (this.getSkinColorVariant()) {
            case 0:
                return EntitySapphire.WHITE_HAIR_COLOR_START;
            case 1:
                return EntitySapphire.ORANGE_HAIR_COLOR_START;
            case 2:
                return EntitySapphire.MAGENTA_HAIR_COLOR_START;
            case 3:
                return EntitySapphire.LIGHT_BLUE_HAIR_COLOR_START;
            case 4:
                return EntitySapphire.YELLOW_HAIR_COLOR_START;
            case 5:
                return EntitySapphire.LIME_HAIR_COLOR_START;
            case 6:
                return EntitySapphire.PINK_HAIR_COLOR_START;
            case 7:
                return EntitySapphire.GRAY_HAIR_COLOR_START;
            case 8:
                return EntitySapphire.LIGHT_GRAY_HAIR_COLOR_START;
            case 9:
                return EntitySapphire.CYAN_HAIR_COLOR_START;
            case 10:
                return EntitySapphire.PURPLE_HAIR_COLOR_START;
            case 11:
                return EntitySapphire.BLUE_HAIR_COLOR_START;
            case 12:
                return EntitySapphire.BROWN_HAIR_COLOR_START;
            case 13:
                return EntitySapphire.GREEN_HAIR_COLOR_START;
            case 14:
                return EntityRuby.HAIR_COLOR_START;
            case 15:
                return EntitySapphire.BLACK_HAIR_COLOR_START;
            default:
                return EntitySapphire.WHITE_HAIR_COLOR_START;
        }
    }

    @Override
    public int generateAbilitySlots(){
        //TODO: Temporary
        return 1;
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
                Abilities.NO_ABILITY
        };
    }

    @Override
    public Abilities[] definiteAbilities() {
        return new Abilities[]{
                Abilities.NO_ABILITY
        };
    }

    public int generateSkinColorVariant() {
        return this.initalSkinVariant;
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
        return false;
    }

    public boolean hasSkinColorVariant(){
        return true;
    }
}
