package com.gempire.systems.injection;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.EntitySapphire;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModEntities;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.objectweb.asm.tree.ModuleExportNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.CRC32;

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
        this.Drain(GemFormation.getBlockPosInVolume(this.world, this.pos, this.volumeToCheck));
    }

    public static ArrayList<Block> getBlocksInVolume(World domhain, BlockPos position, BlockPos volume){
        ArrayList<Block> blocksInVolume = new ArrayList<>();
        float xo = GemFormation.getHalfMiddleOffsetRight(volume.getX());
        float yo = GemFormation.getHalfMiddleOffsetRight(volume.getY());
        float zo = GemFormation.getHalfMiddleOffsetRight(volume.getZ());
        for(int z = GemFormation.getHalfMiddleOffsetLeft(volume.getZ()); z < xo; z++){
            for(int y = GemFormation.getHalfMiddleOffsetLeft(volume.getY()); y < yo; y++){
                for(int x = GemFormation.getHalfMiddleOffsetLeft(volume.getX()); x < zo; x++){
                    Block block = domhain.getBlockState(position.add(new BlockPos(x, y, z))).getBlock();
                    if(block.getBlock() instanceof AirBlock){
                        continue;
                    }
                    else{
                        blocksInVolume.add(block);
                    }
                }
            }
        }
        return blocksInVolume;
    }
    public static ArrayList<BlockPos> getBlockPosInVolume(World domhain, BlockPos position, BlockPos volume){
        ArrayList<BlockPos> blocksInVolume = new ArrayList<>();
        float xo = GemFormation.getHalfMiddleOffsetRight(volume.getX());
        float yo = GemFormation.getHalfMiddleOffsetRight(volume.getY());
        float zo = GemFormation.getHalfMiddleOffsetRight(volume.getZ());
        for(int z = GemFormation.getHalfMiddleOffsetLeft(volume.getZ()); z < xo; z++){
            for(int y = GemFormation.getHalfMiddleOffsetLeft(volume.getY()); y < yo; y++){
                for(int x = GemFormation.getHalfMiddleOffsetLeft(volume.getX()); x < zo; x++){
                    BlockPos block = position.add(new BlockPos(x, y, z));
                    if(domhain.getBlockState(block).getBlock() instanceof AirBlock){
                        continue;
                    }
                    else{
                        blocksInVolume.add(block);
                    }
                }
            }
        }
        return blocksInVolume;
    }

    public static int getHalfMiddleOffsetLeft(float value){
        return -(int)Math.floor(value / 2);
    }

    public static int getHalfMiddleOffsetRight(float value){
        return (int)Math.ceil(value / 2);
    }

    public String EvaluateCruxes() {
        //INPUT: List of gems and their cruxes as well as crux temperatures and depth preferences, list of blocks to check
        HashMap<String, ArrayList<Crux>> GEM_CRUXES = ModEntities.CRUXTOGEM;
        ArrayList<Block> BLOCKS_TO_CHECK = GemFormation.getBlocksInVolume(this.world, this.pos, this.volumeToCheck);
        String[] GEMS = GemFormation.POSSIBLE_GEMS;
        float BLOCK_TEMPERATURE = this.world.getBiome(this.pos).getTemperature(this.pos);

        //Create an object to store the gems and their weights once the cruxes have been evaluated
        HashMap<String, Float> WEIGHTS_OF_GEMS = new HashMap<>();

        //Create an object to store the total weight
        float totalWeight = 0;

        //Loop through every gem
        for (String gem : GEMS) {
            float gemWeight = 0;
            //GEM_CRUXES.get(gem) is an ArrayList of Cruxes
            if (GEM_CRUXES.get(gem) != null) for (Crux crux : GEM_CRUXES.get(gem)) {
                //Do some math to multiply the gem weight by the inverse of the difference in biome temperature to preferred temperature
                float temperatureDifference = 0; //crux.temperature - BLOCK_TEMPERATURE == 0 ? 1 : Math.abs(crux.temperature - BLOCK_TEMPERATURE);
                if(BLOCK_TEMPERATURE >= crux.temperatureMin){
                    if(BLOCK_TEMPERATURE <= crux.temperatureMax) {
                        temperatureDifference = 0;
                    }
                    else{
                        temperatureDifference = crux.temperatureMax - BLOCK_TEMPERATURE == 0 ? 1 : Math.abs(crux.temperatureMax - BLOCK_TEMPERATURE);
                    }
                }
                else{
                    temperatureDifference = crux.temperatureMin - BLOCK_TEMPERATURE == 0 ? 1 : Math.abs(crux.temperatureMin - BLOCK_TEMPERATURE);
                }
                for (Block block : BLOCKS_TO_CHECK) {
                    //Then for every crux, calculate the total weight of crux that matches every block in the volume for every gem
                    //Example: if there are three stone in the volume, the total weight will be 3 stone times however many gems there are that have stone as a crux, and so forth
                    if (block != crux.block) {
                        continue;
                    } else {
                        totalWeight += crux.weight;
                        gemWeight += crux.weight * (1 - temperatureDifference);
                    }
                }
            }
            //Once the total weight has been obtained, store the individual weights of every gem in a hashmap.
            WEIGHTS_OF_GEMS.put(gem, gemWeight);
            //DEBUG FEATURES
            System.out.println(gem + " weight: " + gemWeight);
        }
        //DEBUG FEATURES
        System.out.println("Total Weight: " + totalWeight);
        //Finally, do a weighted chance selection using the total weight and the individual weights.
        String returnGem = "";
        double lowestR = 100000000;
        String lowestRGem = "";
        for (String gem : GemFormation.POSSIBLE_GEMS) {
            double r = Math.random() * totalWeight;
            r -= WEIGHTS_OF_GEMS.get(gem);
            if (WEIGHTS_OF_GEMS.get(gem) < 12) {
                r = 1000000;
            }
            if (r < lowestR) {
                lowestR = r;
                lowestRGem = gem;
            }
            returnGem = gem;
            //DEBUG FEATURES
            System.out.println("R for " + gem + " equals: " + r);
            if (r > 0 && gem == GemFormation.POSSIBLE_GEMS[GemFormation.POSSIBLE_GEMS.length - 1]) {
                returnGem = lowestRGem;
                break;
            }
            if (r <= 0) break;
        }
        //OUTPUT: A gem
        return returnGem;
    }

    public void Drain(ArrayList<BlockPos> blockPosList){
        for (BlockPos pos : blockPosList){
            BlockState block = this.world.getBlockState(pos);
            if(block == Blocks.DIRT.getDefaultState() || block == Blocks.GRASS_BLOCK.getDefaultState() || block == Blocks.GRASS_PATH.getDefaultState()){
                this.world.setBlockState(pos, ModBlocks.DRAINED_PURPLE_SOIL.get().getDefaultState());
            }
            else{
                this.world.setBlockState(pos, ModBlocks.DRAINED_PURPLE_STONE.get().getDefaultState());
            }
        }
    }
}
