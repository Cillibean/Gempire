package com.gempire.systems.injection;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModEntities;
import com.gempire.items.ItemChroma;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GemFormation {
    public World world;
    public BlockPos pos;
    public BlockPos volumeToCheck;
    public static ArrayList<String> POSSIBLE_GEMS = new ArrayList<>();
    public Block drained_sand, drained_soil, drained_stone, drained_stone_2, banded_drained_stone;
    public ItemChroma chroma;
    public Item primer;
    public Fluid[] essences;

    public GemFormation(World world, BlockPos pos, BlockPos volumeToCheck, ItemChroma chroma, Item primer, Fluid[] essences){
        this.world = world;
        this.pos = pos;
        this.volumeToCheck = volumeToCheck;
        this.chroma = chroma;
        this.primer = primer;
        this.essences = essences;
    }

    public void SpawnGem(){
        RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
        EntityGem gem = gemm.get().create(this.world);
        float BIOME_TEMPERATURE = this.world.getBiome(this.pos).getTemperature();
        this.SetDrainedStoneColor(BIOME_TEMPERATURE);
        String gemtoform = this.EvaluateCruxes();
        if (gemtoform == "") {
            this.Drain(GemFormation.getBlockPosInVolume(this.world, this.pos, this.volumeToCheck));
            return;
        }
        try {
            gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(gemtoform.toUpperCase()).get(null);
            gem = gemm.get().create(this.world);
            if(gem instanceof EntityVaryingGem){
                EntityVaryingGem varyingGem = (EntityVaryingGem)gem;
                varyingGem.setSkinVariantOnInitialSpawn = false;
                int variant = this.getColorFromChroma();
                varyingGem.initalSkinVariant = variant;
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
        HashMap<String, GemConditions> GEM_CONDITIONS = ModEntities.CRUXTOGEM;
        ArrayList<Block> BLOCKS_TO_CHECK = GemFormation.getBlocksInVolume(this.world, this.pos, this.volumeToCheck);
        ArrayList<String> GEMS = this.POSSIBLE_GEMS;
        float BLOCK_TEMPERATURE = this.world.getBiome(this.pos).getTemperature(this.pos);

        //Create an object to store the gems and their weights once the cruxes have been evaluated
        HashMap<String, Float> WEIGHTS_OF_GEMS = new HashMap<>();

        //Create an object to store the total weight
        float totalWeight = 0;

        //Loop through every gem
        for (String gem : GEMS) {
            float gemWeight = 0;
            if (GEM_CONDITIONS.get(gem) != null) {
                GemConditions conditions = GEM_CONDITIONS.get(gem);
                boolean weightThisGem = true;
                //Do some math to multiply the gem weight by the inverse of the difference in biome temperature to preferred temperature
                float temperatureDifference = 0;
                if (BLOCK_TEMPERATURE >= conditions.temperatureMin) {
                    if (BLOCK_TEMPERATURE <= conditions.temperatureMax) {
                        temperatureDifference = 0;
                    } else {
                        temperatureDifference = conditions.temperatureMax - BLOCK_TEMPERATURE == 0 ? 1 : Math.abs(conditions.temperatureMax - BLOCK_TEMPERATURE);
                    }
                } else {
                    temperatureDifference = conditions.temperatureMin - BLOCK_TEMPERATURE == 0 ? 1 : Math.abs(conditions.temperatureMin - BLOCK_TEMPERATURE);
                }
                if(this.primer == conditions.primer && conditions.primer != Items.AIR && this.primer != Items.AIR){
                    temperatureDifference = 0;
                }
                //int essenceCount = 0;
                for (Fluid essence : this.essences){
                    System.out.println(essence.getRegistryName().toString());
                    for(Fluid essenceGem : conditions.essences){
                        if(essence == essenceGem){
                            //essenceCount++;
                        }
                    }
                }
                /*if(essenceCount != this.essences.length){
                    System.out.println("Essences Equal: " + essenceCount);
                    weightThisGem = false;
                }*/
                if(weightThisGem) {
                    for (Crux crux : GEM_CONDITIONS.get(gem).cruxes) {
                        for (Block block : BLOCKS_TO_CHECK) {
                            //Then for every crux, calculate the total weight of crux that matches every block in the volume for every gem
                            //Example: if there are three stone in the volume, the total weight will be 3 stone times however many gems there are that have stone as a crux, and so forth
                            if (block != crux.block) {
                                continue;
                            } else {
                                totalWeight += 1;
                                totalWeight += crux.weight;
                                gemWeight += 1 * (1 - temperatureDifference);
                                gemWeight += crux.weight * (1 - temperatureDifference);
                            }
                        }
                    }
                }
                if(this.primer == conditions.primer && conditions.primer != Items.AIR && this.primer != Items.AIR){
                    gemWeight *= 3;
                }
                //Once the total weight has been obtained, store the individual weights of every gem in a hashmap.
                if(weightThisGem) {
                    WEIGHTS_OF_GEMS.put(gem, gemWeight);
                }else WEIGHTS_OF_GEMS.put(gem, 0f);
                //DEBUG FEATURES
                System.out.println(gem + " weight: " + gemWeight);
            }
        }
        //DEBUG FEATURES
        System.out.println("Total Weight: " + totalWeight);
        //Finally, do a weighted chance selection using the total weight and the individual weights.
        String returnGem = "";
        double lowestR = 100000000;
        String lowestRGem = "";
        for (String gem : this.POSSIBLE_GEMS) {
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
            if (r > 0 && gem == this.POSSIBLE_GEMS.get(this.POSSIBLE_GEMS.size() - 1)){
                returnGem = lowestRGem;
                break;
            }
            if (r <= 0) {
                returnGem = gem;
                break;
            }
        }
        //OUTPUT: A gem
        return returnGem;
    }

    public int getColorFromChroma(){
        return this.chroma.color;
    }

    public void Drain(ArrayList<BlockPos> blockPosList){
        for (BlockPos pos : blockPosList){
            BlockState block = this.world.getBlockState(pos);
            if(block == Blocks.DIRT.getDefaultState() || block == Blocks.GRASS_BLOCK.getDefaultState() || block == Blocks.GRASS_PATH.getDefaultState()){
                this.world.setBlockState(pos, this.drained_soil.getDefaultState());
            }
            else if(block == Blocks.SAND.getDefaultState() || block == Blocks.RED_SAND.getDefaultState() || block == Blocks.SOUL_SAND.getDefaultState()
            || block == Blocks.GRAVEL.getDefaultState()){
                this.world.setBlockState(pos, this.drained_sand.getDefaultState());
            }
            else{
                if(pos.getY() < 80) {
                    this.world.setBlockState(pos, this.drained_stone.getDefaultState());
                }
                else{
                    this.world.setBlockState(pos, this.drained_stone_2.getDefaultState());
                    if(pos.getY() % 6 == 0){
                        this.world.setBlockState(pos, this.banded_drained_stone.getDefaultState());
                    }
                }
                if(pos.getY() == 80){
                    this.world.setBlockState(pos, this.banded_drained_stone.getDefaultState());
                }
            }
        }
    }

    public void SetDrainedStoneColor(float temperature){
        if(temperature > .1f && temperature <= .5F){
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_GREY_SOIL.get();
            this.drained_stone = ModBlocks.DRAINED_GREY_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_GREY_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_GREY_STONE.get();
        }
        else if(temperature > .5f && temperature <= .9f){
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_PURPLE_SOIL.get();
            this.drained_stone = ModBlocks.DRAINED_PURPLE_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_PURPLE_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_PURPLE_STONE.get();
        }
        else if(temperature > .9f && temperature <= 1.2f){
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_SAND.get();
            this.drained_stone = ModBlocks.DRAINED_YELLOW_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_YELLOW_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_YELLOW_STONE.get();
        }
        else if(temperature > 1.2f && temperature <= 2f){
            this.drained_sand = ModBlocks.DRAINED_RED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_RED_SAND.get();
            this.drained_stone = ModBlocks.DRAINED_RED_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_RED_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_RED_STONE.get();
        }
        else{
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_BLUE_SOIL.get();
            this.drained_stone = ModBlocks.DRAINED_BLUE_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_BLUE_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_BLUE_STONE.get();
        }
    }
}
