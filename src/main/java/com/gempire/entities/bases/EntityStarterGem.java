package com.gempire.entities.bases;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

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
}
