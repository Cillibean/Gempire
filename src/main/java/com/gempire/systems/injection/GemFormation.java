package com.gempire.systems.injection;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.EntitySapphire;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

import java.util.UUID;

public class GemFormation {
    public String[] possibleGems = new String[]{
        "ruby", "sapphire", "pebble", "mica", "shale"
    };

    public void SpawnGem(World world, BlockPos pos){
        RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
        EntityGem gem = gemm.get().create(world);
        String gemtoform = this.gemToForm(world, pos);
        int variant = 0;
        if(gemtoform == "sapphire"){
            variant = world.rand.nextInt(16);
            while(variant == 14){
                variant = world.rand.nextInt(16);
            }
        }
        try {
            gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(gemtoform.toUpperCase()).get(null);
            gem = gemm.get().create(world);
            if(gemtoform == "sapphire"){
                gem.setSkinVariantOnInitialSpawn = false;
                gem.initalSkinVariant = variant;
            }
            gem.setUniqueId(MathHelper.getRandomUUID(world.rand));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            gem.onInitialSpawn((IServerWorld) world, world.getDifficultyForLocation(pos), SpawnReason.TRIGGERED, null, null);
            gem.setOwned(false, UUID.randomUUID());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        gem.setPosition(pos.getX(), pos.getY(), pos.getZ());
        gem.setHealth(gem.getMaxHealth());
        world.addEntity(gem);
    }

    public String gemToForm(World world, BlockPos pos){
        float temp = world.getBiome(pos).getTemperature(pos);
        String gem;
        if(temp >= 1.5f){
            gem = "ruby";
        }
        else if(temp <= .5f){
            gem = "sapphire";
        }
        else{
            gem = this.possibleGems[world.rand.nextInt(this.possibleGems.length)];
            while(gem == "ruby" || gem == "sapphire"){
                gem = this.possibleGems[world.rand.nextInt(this.possibleGems.length)];
            }
        }
        return gem;
    }
}
