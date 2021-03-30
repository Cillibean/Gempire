package com.gempire.entities.bases;

import com.gempire.util.Abilities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class EntityStarterGem extends EntityGem {

    public EntityStarterGem(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public int generateHairColor(){
        return 0;
    }

    public int generateHairVariant(){
        return 0;
    }

    public int generateGemColor(){
        return 0;
    }

    public int generateOutfitColor(){
        return 0;
    }

    public int getOutfitVariant(){
        return 0;
    }

    public int generateInsigniaColor(){
        return 0;
    }

    public int generateAbilitySlots() {
        return 1;
    }

    public boolean generateIsEmotional(){
        return false;
    }

    //TODO : FIX THIS PIECE OF HELL ON EARTH
    @Override
    public String generateAbilities(){
        return "0";
    }

    public Abilities[] definiteAbilities(){
        return new Abilities[]{
                Abilities.NO_ABILITY
        };
    }

    public boolean canChangeInsigniaColorByDefault(){
        return false;
    }

    @Override
    public int generateSkinColorVariant() {
        return 0;
    }

    @Override
    public boolean canChangeUniformColorByDefault() {
        return true;
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
    public float formingTemperature(){
        return 1;
    }
}
