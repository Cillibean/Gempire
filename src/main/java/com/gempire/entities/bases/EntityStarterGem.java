package com.gempire.entities.bases;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class EntityStarterGem extends EntityGem {


    public EntityStarterGem(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
