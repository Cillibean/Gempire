package com.gempire.systems.injection;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.EntitySapphire;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.ModEntities;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GemFormation {
    public World world;
    public BlockPos pos;
    public BlockPos volumeToCheck;
    public static String[] POSSIBLE_GEMS = new String[]{
        "ruby", "sapphire", "pebble", "mica", "shale"
    };

    public GemFormation(World world, BlockPos pos, BlockPos volumeToCheck){
        this.world = world;
        this.pos = pos;
        this.volumeToCheck = volumeToCheck;
    }

    public void SpawnGem(){
        RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
        EntityGem gem = gemm.get().create(this.world);
        String gemtoform = this.EvaluateCruxes();
        int variant = 0;
        if(gemtoform == "sapphire"){
            variant = this.world.rand.nextInt(16);
            while(variant == 14){
                variant = this.world.rand.nextInt(16);
            }
        }
        try {
            gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(gemtoform.toUpperCase()).get(null);
            gem = gemm.get().create(this.world);
            if(gemtoform == "sapphire"){
                gem.setSkinVariantOnInitialSpawn = false;
                gem.initalSkinVariant = variant;
            }
            gem.setUniqueId(MathHelper.getRandomUUID(this.world.rand));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            gem.onInitialSpawn((IServerWorld) this.world, this.world.getDifficultyForLocation(this.pos), SpawnReason.TRIGGERED, null, null);
            gem.setOwned(false, UUID.randomUUID());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        gem.setPosition(this.pos.getX(), this.pos.getY(), this.pos.getZ());
        gem.setHealth(gem.getMaxHealth());
        this.world.addEntity(gem);
    }

    public String EvaluateCruxes(){
        float biomeTemperature = this.world.getBiome(this.pos).getTemperature(this.pos);
        ArrayList<Block> blocksInVolume = new ArrayList<>();
        for(int z = 0; z < this.volumeToCheck.getZ(); z++){
            for(int y = 0; y < this.volumeToCheck.getY(); y++){
                for(int x = 0; x < this.volumeToCheck.getX(); x++){
                    Block block = this.world.getBlockState(new BlockPos(-(int)Math.floor(x/2), -(int)Math.floor(y/2), -(int)Math.floor(z/2)).add(this.pos)).getBlock();
                    if(block instanceof AirBlock){
                        continue;
                    }
                    blocksInVolume.add(block);
                }
            }
        }
        HashMap<String, Float> weights = new HashMap<>();
        float totalWeight = 0;
        for(String gem : GemFormation.POSSIBLE_GEMS){
            float gemWeight = 0;
            if(ModEntities.CRUXTOGEM.containsKey(gem)) {
                for (int i = 0; i < ModEntities.CRUXTOGEM.get(gem).size(); i++) {
                    Crux crux = null;
                    if (ModEntities.CRUXTOGEM.get(gem).get(i) != null) {
                        crux = ModEntities.CRUXTOGEM.get(gem).get(i);
                    }
                    totalWeight += crux.weight;
                    for (Block block : blocksInVolume) {
                        if (block == crux.block) {
                            gemWeight += crux.weight;
                        }
                    }
                }
            }
            System.out.println(gem + ": " + gemWeight);
            weights.put(gem, gemWeight);
        }
        System.out.println("Total Weight: " + totalWeight);
        String returnGem = "";
        for(String gem : GemFormation.POSSIBLE_GEMS){
            double r = Math.random() * totalWeight;
            r -= weights.get(gem);
            returnGem = gem;
            if(r<=0) break;
        }
        return returnGem;
    }
}
