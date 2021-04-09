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

import javax.annotation.Nullable;
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
    public String essences;
    public int facing = 0;

    public GemFormation(World world, BlockPos pos, BlockPos volumeToCheck, ItemChroma chroma, Item primer, String essences, int facing){
        this.world = world;
        this.pos = pos;
        this.volumeToCheck = volumeToCheck;
        this.chroma = chroma;
        this.primer = primer;
        this.essences = essences;
        this.facing = facing;
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
        gem.setPosition(this.pos.getX() + .5f, this.pos.getY(), this.pos.getZ() + .5f);
        gem.setHealth(gem.getMaxHealth());
        this.world.addEntity(gem);
        this.Drain(GemFormation.getBlockPosInVolume(this.world, this.pos, this.volumeToCheck));
        this.GenerateFacingExitHole();
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
                boolean weightThisGem = false;
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
                int essenceCount = 0;
                String[] indEssencesInj = this.essences.split("-");
                String[] indEssencesCond = conditions.essences.split("-");
                for(int i = 0; i < indEssencesInj.length; i++){
                    String essJ = indEssencesInj[i];
                    for(int j = 0; j < indEssencesCond.length; j++){
                        String essC = indEssencesCond[j];
                        if(essJ.equalsIgnoreCase(essC)){
                            essenceCount++;
                        }
                    }
                }
                if(essenceCount == indEssencesCond.length){
                    weightThisGem = true;
                }
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

    public void GenerateFacingExitHole(){
        if(this.facing <= 3 && this.facing >= 0) {
            boolean found = false;
            ArrayList<BlockPos> blocks = new ArrayList<>();
            ArrayList<BlockPos> blocksToDrain = new ArrayList<>();
            BlockPos dir = BlockPos.ZERO;
            for (int i = 0; i < 16; i++) {
                if(this.facing == 0){
                    dir = new BlockPos(i, 0 ,0);
                }
                else if(this.facing == 1){
                    dir = new BlockPos(0, 0 ,i);
                }
                else if(this.facing == 2){
                    dir = new BlockPos(-i, 0 ,0);
                }
                else if(this.facing == 3){
                    dir = new BlockPos(0, 0 ,-i);
                }
                if (this.world.getBlockState(this.pos.add(dir)).getBlock() != Blocks.AIR) {
                    blocks.add(this.pos.add(dir));
                    blocks.add(this.pos.add(dir.up()));
                    blocks.add(this.pos.add(dir.up().up()));

                    blocksToDrain.add(this.pos.add(i, 0, 0).down());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().up().up());
                    blocksToDrain.add(this.pos.add(i, 0, 0).north());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().north());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().up().north());
                    blocksToDrain.add(this.pos.add(i, 0, 0).south());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().south());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().up().south());
                } else {
                    found = true;
                    break;
                }
            }
            if (found) {
                for (BlockPos pos : blocks) {
                    this.world.destroyBlock(pos, false);
                }
                this.Drain(blocksToDrain);
            } else {
                this.GenerateExitHole();
            }
        }
        else{
            this.GenerateExitHole();
        }
    }

    public void GenerateExitHole(){
        boolean found = false;
        if(!found) {
            ArrayList<BlockPos> blocks = new ArrayList<>();
            ArrayList<BlockPos> blocksToDrain = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                if (this.world.getBlockState(this.pos.add(i, 0, 0)).getBlock() != Blocks.AIR) {
                    blocks.add(this.pos.add(i, 0, 0));
                    blocks.add(this.pos.add(i, 1, 0));
                    blocks.add(this.pos.add(i, 2, 0));

                    blocksToDrain.add(this.pos.add(i, 0, 0).down());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().up().up());
                    blocksToDrain.add(this.pos.add(i, 0, 0).north());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().north());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().up().north());
                    blocksToDrain.add(this.pos.add(i, 0, 0).south());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().south());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().up().south());
                } else {
                    found = true;
                    break;
                }
            }
            if(found){
                for(BlockPos pos : blocks){
                    this.world.destroyBlock(pos, false);
                }
                this.Drain(blocksToDrain);
            }
        }
        if(!found) {
            ArrayList<BlockPos> blocks = new ArrayList<>();
            ArrayList<BlockPos> blocksToDrain = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                if (this.world.getBlockState(this.pos.add(-i, 0, 0)).getBlock() != Blocks.AIR) {
                    blocks.add(this.pos.add(-i, 0, 0));
                    blocks.add(this.pos.add(-i, 1, 0));
                    blocks.add(this.pos.add(-i, 2, 0));

                    blocksToDrain.add(this.pos.add(-i, 0, 0).down());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).up().up().up());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).north());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).up().north());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).up().up().north());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).south());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).up().south());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).up().up().south());
                } else {
                    found = true;
                    break;
                }
            }
            if(found){
                for(BlockPos pos : blocks){
                    this.world.destroyBlock(pos, false);
                }
                this.Drain(blocksToDrain);
            }
        }
        if(!found) {
            ArrayList<BlockPos> blocks = new ArrayList<>();
            ArrayList<BlockPos> blocksToDrain = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                if (this.world.getBlockState(this.pos.add(0, 0, i)).getBlock() != Blocks.AIR) {
                    blocks.add(this.pos.add(0, 0, i));
                    blocks.add(this.pos.add(0, 1, i));
                    blocks.add(this.pos.add(0, 2, i));

                    blocksToDrain.add(this.pos.add(0, 0, i).down());
                    blocksToDrain.add(this.pos.add(0, 0, i).up().up().up());
                    blocksToDrain.add(this.pos.add(0, 0, i).west());
                    blocksToDrain.add(this.pos.add(0, 0, i).up().west());
                    blocksToDrain.add(this.pos.add(0, 0, i).up().up().west());
                    blocksToDrain.add(this.pos.add(0, 0, i).east());
                    blocksToDrain.add(this.pos.add(0, 0, i).up().east());
                    blocksToDrain.add(this.pos.add(0, 0, i).up().up().east());
                } else {
                    found = true;
                    break;
                }
            }
            if(found){
                for(BlockPos pos : blocks){
                    this.world.destroyBlock(pos, false);
                }
                this.Drain(blocksToDrain);
            }
        }
        if(!found) {
            ArrayList<BlockPos> blocksToDrain = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                this.world.destroyBlock(this.pos.add(0, 0, -i), false);
                this.world.destroyBlock(this.pos.add(0, 1, -i), false);
                this.world.destroyBlock(this.pos.add(0, 2, -i), false);

                if(this.world.getBlockState(this.pos.add(0, 0, -i)) != Blocks.AIR.getDefaultState()) {
                    blocksToDrain.add(this.pos.add(0, 0, -i).down());
                    blocksToDrain.add(this.pos.add(0, 0, -i).up().up().up());
                    blocksToDrain.add(this.pos.add(0, 0, -i).west());
                    blocksToDrain.add(this.pos.add(0, 0, -i).up().west());
                    blocksToDrain.add(this.pos.add(0, 0, -i).up().up().west());
                    blocksToDrain.add(this.pos.add(0, 0, -i).east());
                    blocksToDrain.add(this.pos.add(0, 0, -i).up().east());
                    blocksToDrain.add(this.pos.add(0, 0, -i).up().up().east());
                }
            }
            this.Drain(blocksToDrain);
        }
    }

    public int getColorFromChroma(){
        return this.chroma.color;
    }

    public void Drain(ArrayList<BlockPos> blockPosList){
        for (BlockPos pos : blockPosList){
            BlockState block = this.world.getBlockState(pos);
            if(block.getBlock() == ModBlocks.GEM_SEED_BLOCK.get() ||
                    block.getBlock() == ModBlocks.DRILL_BLOCK.get() || block.getBlock() == ModBlocks.TANK_BLOCK.get() ||
                    block.getBlock() == ModBlocks.POWER_CRYSTAL_BLOCK.get()){
                continue;
            }
            if(block == Blocks.DIRT.getDefaultState() || block == Blocks.GRASS_BLOCK.getDefaultState() || block == Blocks.GRASS_PATH.getDefaultState()
                    || block == Blocks.GRAVEL.getDefaultState()){
                this.world.setBlockState(pos, this.drained_soil.getDefaultState());
            }
            else if(block == Blocks.SAND.getDefaultState() || block == Blocks.RED_SAND.getDefaultState() || block == Blocks.SOUL_SAND.getDefaultState()){
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
            this.drained_soil = ModBlocks.DRAINED_PURPLE_SOIL.get();
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
