package com.gempire.entities.bases;

import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class EntityVaryingGem extends EntityGem{

    public EntityVaryingGem(EntityType<? extends CreatureEntity> type, World worldIn) {
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
        int rando = this.rand.nextInt(16);
        while(!this.isColorValid(rando)){
            rando = this.rand.nextInt(16);
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
