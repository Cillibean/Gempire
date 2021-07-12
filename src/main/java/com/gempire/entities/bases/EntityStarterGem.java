package com.gempire.entities.bases;

import com.gempire.systems.injection.Crux;
import com.gempire.util.Abilities;
import com.gempire.util.CruxType;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class EntityStarterGem extends EntityGem {

    public EntityStarterGem(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public int generateHairColor(){
        return this.getSkinColor();
    }

    public int generateHairVariant(){
        return this.rand.nextInt(3);
    }

    public int generateOutfitColor(){
        return this.rand.nextInt(16);
    }

    public int generateInsigniaColor(){
        return this.rand.nextInt(16);
    }

    public int generateAbilitySlots() {
        return 1;
    }

    public Abilities[] definiteAbilities(){
        return new Abilities[]{
                Abilities.NO_ABILITY
        };
    }

    public boolean canChangeInsigniaColorByDefault(){
        return true;
    }

    @Override
    public int generateSkinColorVariant() {
        return 0;
    }

    public boolean canChangeUniformColorByDefault() {
        return true;
    }

    public boolean hasSkinColorVariant(){
        return false;
    }

    @Override
    public byte EmotionThreshold() {
        return 10;
    }

    public int generateOutfitVariant(){
        return this.rand.nextInt(4);
    }
    public int generateInsigniaVariant(){
        return this.getOutfitVariant();
    }

    @Override
    public boolean generateIsEmotional() {
        return false;
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }
}
