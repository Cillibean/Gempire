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
        for(int i = 0; i < this.NeglectedColors().length; i++){
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
    public GemPlacements[] getPlacements() {
        return new GemPlacements[0];
    }

    @Override
    public int generateHairVariant() {
        return 0;
    }

    @Override
    public int generateOutfitVariant() {
        return 0;
    }

    @Override
    public int generateInsigniaVariant() {
        return 0;
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[0];
    }

    @Override
    public Abilities[] definiteAbilities() {
        return new Abilities[0];
    }

    @Override
    public int generateSkinColorVariant() {
        return this.initalSkinVariant;
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
    public boolean canChangeUniformColorByDefault() {
        return false;
    }

    @Override
    public boolean canChangeInsigniaColorByDefault() {
        return false;
    }
}
