package com.gempire.entities.bases;

import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.Level;

public abstract class EntityVaryingGem extends EntityGem{

    public EntityVaryingGem(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }


    public abstract int[] NeglectedColors();

    public boolean isColorValid(int color){
        if(this.NeglectedColors() != null) for(int i = 0; i < this.NeglectedColors().length; i++){
            if(this.NeglectedColors()[i] == color){
                return false;
            }
        }
        return true;
    }

    public int generateRandomInitialSkin(){
        int rando = this.random.nextInt(16);
        while(!this.isColorValid(rando)){
            rando = this.random.nextInt(16);
        }
        return rando;
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
    public int generateSkinColorVariant() {
        return this.initalSkinVariant;
    }

    public abstract boolean UsesUniqueNames();

    public abstract String NameFromColor(byte i);
}
