package com.gempire.tileentities;

import com.gempire.blocks.DrainedBlock;
import com.gempire.blocks.GemSeedBlock;
import com.gempire.blocks.machine.PowerCrystalBlock;
import com.gempire.blocks.machine.TankBlock;
import com.gempire.init.*;
import com.gempire.items.ItemChroma;
import com.gempire.systems.injection.Crux;
import com.gempire.systems.injection.GemConditions;
import com.gempire.systems.injection.GemFormation;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.IFluidBlock;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;


public class GemSeedTE extends BlockEntity {
    boolean spawned = false;
    public int ticks = 0;
    public int stage = 0;
    public int tier;
    public static final int DRAIN_SIZE = 11;
    public ItemChroma chroma;
    boolean weighThisGem = false;

    public Item primer;
    public String essences = "pink-blue-yellow-white";
    public int facing;
    public boolean checked = false;
    public Block drained_sand, drained_soil, drained_stone, drained_stone_2, banded_drained_stone, drained_log, drained_log_cracked, drained_ice;

    public HashMap<Integer, BlockPos> POSITIONS = new HashMap<>();
    public ArrayList<Integer> IDS = new ArrayList<>();
    public int blockNumber;

    //INPUT: List of gems and their cruxes as well as crux temperatures and depth preferences, list of blocks to check
    HashMap<String, GemConditions> GEM_CONDITIONS = new HashMap<>();

    //Create an object to store the gems and their weights once the cruxes have been evaluated
    HashMap<String, Float> WEIGHTS_OF_GEMS = new HashMap<>();
    public ArrayList<ArrayList<Float>> TEMPORARY_WEIGHTS = new ArrayList<>();
    GemConditions conditions;

    //Create an object to store the total weight
    float totalWeight = 0;
    int speed = 8;

    public GemSeedTE(BlockPos pos, BlockState state) {
        super(ModTE.GEM_SEED_TE.get(), pos, state);
            for (int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_2.size(); i++) {
                this.TEMPORARY_WEIGHTS.add(i, new ArrayList<>());
            }
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        GemSeedTE te = (GemSeedTE) be;
        //System.out.println("Gem List is of size: " + GemFormation.POSSIBLE_GEMS.size());
        if (!te.checked) {
            te.ScanPositions(level, te.getBlockPos(), new BlockPos(DRAIN_SIZE, DRAIN_SIZE, DRAIN_SIZE));
            te.checked = true;
        }
        if (te.primer == ModItems.GILDED_LAPIS.get()) {
            te.speed = 2;
        } else {
            te.speed = 8;
        }
        if (te.ticks % te.speed == 0) {
            if (!te.spawned && te.checked) {
                if (te.IDS.size() > 0) {
                    int rando = ThreadLocalRandom.current().nextInt(te.IDS.size());
                    te.DrainBlock(te.POSITIONS.get(te.IDS.get(rando)));
                    te.IDS.remove(rando);
                    te.setChanged();
                } else {
                    te.spawned = true;
                    if (te.tier == 1) {
                        for (int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_1.size(); i++) {
                            //te.TEMPORARY_WEIGHTS.add(i, new ArrayList<Float>());
                            float weight = 0;
                            for (int n = 0; n <= 1; n++) {
                                weight += te.TEMPORARY_WEIGHTS.get(i).get(n);
                            }
                            te.WEIGHTS_OF_GEMS.put(GemFormation.POSSIBLE_GEMS_TIER_1.get(i), weight);
                        }
                    } else if (te.tier == 2) {
                        for (int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_2.size(); i++) {
                            float weight = 0;
                            for (int n = 0; n <= 1; n++) {
                                weight += te.TEMPORARY_WEIGHTS.get(i).get(n);
                            }
                            te.WEIGHTS_OF_GEMS.put(GemFormation.POSSIBLE_GEMS_TIER_2.get(i), weight);
                        }
                    }
                    //System.out.println(te.totalWeight);
                    //System.out.println(te.essences);
                    GemFormation form = new GemFormation(te.level, te.getBlockPos(), new BlockPos(GemSeedTE.DRAIN_SIZE, GemSeedTE.DRAIN_SIZE, GemSeedTE.DRAIN_SIZE), te.chroma, te.primer, te.essences, te.facing, te.WEIGHTS_OF_GEMS, te.totalWeight, te.tier);
                    form.SpawnGem();
                    level.sendBlockUpdated(te.getBlockPos(), te.getBlockState(), te.getBlockState(), 2);
                    te.setChanged();
                }
            }
        }
        te.ticks++;
    }

    public void ScanPositions(Level domhain, BlockPos position, BlockPos volume) {
        int id = 0;
        float xo = GemFormation.getHalfMiddleOffsetRight(volume.getX());
        float yo = GemFormation.getHalfMiddleOffsetRight(volume.getY());
        float zo = GemFormation.getHalfMiddleOffsetRight(volume.getZ());
        for (int z = GemFormation.getHalfMiddleOffsetLeft(volume.getZ()); z < zo; z++) {
            for (int y = GemFormation.getHalfMiddleOffsetLeft(volume.getY()); y < yo; y++) {
                for (int x = GemFormation.getHalfMiddleOffsetLeft(volume.getX()); x < xo; x++) {
                    BlockPos block = position.offset(new BlockPos(x, y, z));
                    if (domhain.getBlockState(block).getBlock() instanceof IFluidBlock || domhain.getBlockState(block).getBlock() instanceof AirBlock || level.isOutsideBuildHeight(block)) {
                    } else {
                        if (ThreadLocalRandom.current().nextInt(10) > 3) {
                            this.POSITIONS.put(id, block);
                            this.IDS.add(id);
                            id++;
                        }
                    }
                }
            }
        }
    }

    public void DrainBlock(BlockPos blockPos) {
        blockNumber++;
        this.GEM_CONDITIONS = ModEntities.CRUXTOGEM;
        float BLOCK_TEMPERATURE = this.level.getBiome(this.getBlockPos()).get().getBaseTemperature();
        this.SetDrainedStoneColor(BLOCK_TEMPERATURE);
        Block block = this.level.getBlockState(blockPos).getBlock();
        // TODO: fix it
        if (tier == 1) {
            for (int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_1.size(); i++) {
                String gem = GemFormation.POSSIBLE_GEMS_TIER_1.get(i);
                float gemWeight = 0;
                if (GEM_CONDITIONS.get(gem) != null) {
                    this.GEM_CONDITIONS = ModEntities.CRUXTOGEM;
                    this.SetDrainedStoneColor(BLOCK_TEMPERATURE);
                    GemConditions conditions = GEM_CONDITIONS.get(gem);
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
                    int essenceCount = 0;
                    String[] indEssencesInj = this.essences.split("-");
                    String[] indEssencesCond = conditions.essences.split("-");
                    for(int n = 0; n < indEssencesInj.length; n++){
                        String essJ = indEssencesInj[n];
                        for(int j = 0; j < indEssencesCond.length; j++){
                            String essC = indEssencesCond[j];
                            if(essJ.equalsIgnoreCase(essC)){
                                essenceCount++;
                            }
                        }
                    }
                    if (essenceCount == indEssencesCond.length) {
                        weighThisGem = true;
                    }
                    if (weighThisGem) {
                        for (Crux crux : GEM_CONDITIONS.get(gem).cruxes) {
                            //Then for every crux, calculate the total weight of crux that matches every block in the volume for every gem
                            //Example: if there are three stone in the volume, the total weight will be 3 stone times however many gems there are that have stone as a crux, and so forth
                            if (block != crux.block) {
                                if (block instanceof DrainedBlock) {
                                    totalWeight -= 0.3;
                                    gemWeight -= 0.3;
                                    gemWeight *= GEM_CONDITIONS.get(gem).rarity;
                                }
                                if (block.defaultBlockState().is(Tags.Blocks.STONE)) {
                                    totalWeight += 1;
                                    totalWeight += GEM_CONDITIONS.get(gem).rarity;
                                    gemWeight += 1 * (1 - temperatureDifference);
                                    gemWeight += 1 - temperatureDifference;
                                    gemWeight *= GEM_CONDITIONS.get(gem).rarity;
                                    for (int n = 0; n <= 1; n++){
                                        TEMPORARY_WEIGHTS.get(i).add(n, gemWeight);
                                    }
                                } else {
                                    for (int n = 0; n <= 1; n++){
                                        TEMPORARY_WEIGHTS.get(i).add(n, gemWeight - (1 - temperatureDifference));
                                    }
                                }
                            } else {
                                totalWeight += 1;
                                totalWeight += crux.weight * GEM_CONDITIONS.get(gem).rarity;
                                gemWeight += 1 * (1 - temperatureDifference);
                                gemWeight += crux.weight * (1 - temperatureDifference);
                                gemWeight *= GEM_CONDITIONS.get(gem).rarity;
                                for (int n = 0; n <= 1; n++){
                                    TEMPORARY_WEIGHTS.get(i).add(n, gemWeight);
                                }
                            }
                        }
                    }
                    //Once the total weight has been obtained, store the individual weights of every gem in a hashmap.
                    else {
                        TEMPORARY_WEIGHTS.get(i).add(0f);
                    }
                } else {
                    TEMPORARY_WEIGHTS.get(i).add(0f);
                }
            }
        } else if (tier == 2) {
            for (int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_2.size(); i++) {
                String gem = GemFormation.POSSIBLE_GEMS_TIER_2.get(i);
                float gemWeight = 0;
                if (GEM_CONDITIONS.get(gem) != null) {
                    this.GEM_CONDITIONS = ModEntities.CRUXTOGEM;
                    this.SetDrainedStoneColor(BLOCK_TEMPERATURE);
                    GemConditions conditions = GEM_CONDITIONS.get(gem);
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
                    int essenceCount = 0;
                    String[] indEssencesInj = this.essences.split("-");
                    String[] indEssencesCond = conditions.essences.split("-");
                    for(int n = 0; n < indEssencesInj.length; n++){
                        String essJ = indEssencesInj[n];
                        for(int j = 0; j < indEssencesCond.length; j++){
                            String essC = indEssencesCond[j];
                            if(essJ.equalsIgnoreCase(essC)){
                                essenceCount++;
                            }
                        }
                    }
                    System.out.println("essence count "+essenceCount);
                    if (essenceCount == indEssencesCond.length) {
                        System.out.println("weigh");
                        weighThisGem = true;
                    }
                    if (weighThisGem) {
                        for (Crux crux : GEM_CONDITIONS.get(gem).cruxes) {
                            //Then for every crux, calculate the total weight of crux that matches every block in the volume for every gem
                            //Example: if there are three stone in the volume, the total weight will be 3 stone times however many gems there are that have stone as a crux, and so forth
                            if (block != crux.block) {
                                for (int n = 0; n <= 1; n++){
                                    TEMPORARY_WEIGHTS.get(i).add(n, gemWeight - (1 - temperatureDifference));
                                    System.out.println("temp weights from gem weights no crux " +TEMPORARY_WEIGHTS.get(i).get(n));
                                }
                            } else {
                                totalWeight += 1;
                                totalWeight += crux.weight * GEM_CONDITIONS.get(gem).rarity;
                                gemWeight += 1 * (1 - temperatureDifference);
                                gemWeight += crux.weight * (1 - temperatureDifference);
                                gemWeight *= GEM_CONDITIONS.get(gem).rarity;
                                for (int n = 0; n <= 1; n++){
                                    TEMPORARY_WEIGHTS.get(i).add(n, gemWeight);
                                    System.out.println("temp weights from gem weights " +TEMPORARY_WEIGHTS.get(i).get(n));
                                }
                            }
                        }
                    }
                    //Once the total weight has been obtained, store the individual weights of every gem in a hashmap.
                    else {
                        TEMPORARY_WEIGHTS.get(i).add(0f);
                    }
                } else {
                    TEMPORARY_WEIGHTS.get(i).add(0f);
                }
            }
        }

        if (!(block instanceof AirBlock) &&
                !(block instanceof SlabBlock) &&
                !(block instanceof BushBlock) &&
                !(block instanceof SnowLayerBlock) &&
                !(block instanceof LiquidBlock) &&
                !(block instanceof TorchBlock) &&
                !(block instanceof BedBlock) &&
                !(block instanceof BeaconBlock) &&
                !(block instanceof WoolCarpetBlock) &&
                !(block instanceof TargetBlock) &&
                !(block instanceof DoorBlock) &&
                !(block instanceof RailBlock) &&
                !(block instanceof ChestBlock) &&
                !(block instanceof FurnaceBlock) &&
                !(block instanceof FenceBlock) &&
                !(block instanceof FenceGateBlock) &&
                !(block instanceof GlassBlock) &&
                !(block instanceof IronBarsBlock) &&
                !(block instanceof CraftingTableBlock) &&
                !(block instanceof AnvilBlock) &&
                !(block instanceof BlastFurnaceBlock) &&
                !(block instanceof SmokerBlock) &&
                !(block instanceof LoomBlock) &&
                !(block instanceof CartographyTableBlock) &&
                !(block instanceof CactusBlock) &&
                !(block instanceof TankBlock) &&
                !(block instanceof GemSeedBlock) &&
                !(block instanceof PowerCrystalBlock) &&
                !(block == Blocks.BEDROCK) &&
                !(block == ModBlocks.DRILL_BLOCK.get()) &&
                !(block == ModBlocks.DRAINED_ICE.get()) &&
                !(block == ModBlocks.DRAINED_LOG_CRACKED.get()) &&
                !(block == ModBlocks.DRAINED_LOG.get()) &&
                !(block instanceof DrainedBlock)) {
            if (block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT_PATH
                    || block == Blocks.GRAVEL || block == Blocks.MOSS_BLOCK) {
                this.level.setBlockAndUpdate(blockPos, this.drained_soil.defaultBlockState());
            } else if (block == Blocks.SAND || block == Blocks.RED_SAND || block == Blocks.SOUL_SAND) {
                this.level.setBlockAndUpdate(blockPos, this.drained_sand.defaultBlockState());
            } else if (block == Blocks.OAK_LOG || block == Blocks.STRIPPED_OAK_LOG || block == Blocks.STRIPPED_OAK_WOOD || block == Blocks.OAK_WOOD
                    || block == Blocks.SPRUCE_LOG || block == Blocks.STRIPPED_SPRUCE_LOG || block == Blocks.STRIPPED_SPRUCE_WOOD || block == Blocks.SPRUCE_WOOD
                    || block == Blocks.BIRCH_LOG || block == Blocks.STRIPPED_BIRCH_LOG || block == Blocks.STRIPPED_BIRCH_WOOD || block == Blocks.BIRCH_WOOD
                    || block == Blocks.JUNGLE_LOG || block == Blocks.STRIPPED_JUNGLE_LOG || block == Blocks.STRIPPED_JUNGLE_WOOD || block == Blocks.JUNGLE_WOOD
                    || block == Blocks.ACACIA_LOG || block == Blocks.STRIPPED_ACACIA_LOG || block == Blocks.STRIPPED_ACACIA_WOOD || block == Blocks.ACACIA_WOOD
                    || block == Blocks.DARK_OAK_LOG || block == Blocks.STRIPPED_DARK_OAK_LOG || block == Blocks.STRIPPED_DARK_OAK_WOOD || block == Blocks.DARK_OAK_WOOD) {
                this.level.setBlockAndUpdate(blockPos, this.drained_log.withPropertiesOf(this.level.getBlockState(blockPos)));
            } else if (block == Blocks.CRIMSON_STEM || block == Blocks.WARPED_STEM || block == Blocks.STRIPPED_CRIMSON_STEM || block == Blocks.STRIPPED_WARPED_STEM
                    || block == Blocks.CRIMSON_HYPHAE || block == Blocks.WARPED_HYPHAE || block == Blocks.STRIPPED_CRIMSON_HYPHAE || block == Blocks.STRIPPED_WARPED_HYPHAE) {
                this.level.setBlockAndUpdate(blockPos, this.drained_log_cracked.defaultBlockState());
            } else if (block == Blocks.BLUE_ICE || block == Blocks.PACKED_ICE) {
                this.level.setBlockAndUpdate(blockPos, this.drained_ice.defaultBlockState());
            } else {
                if (blockPos.getY() < 80) {
                    this.level.setBlockAndUpdate(blockPos, this.drained_stone.defaultBlockState());
                } else {
                    this.level.setBlockAndUpdate(blockPos, this.drained_stone_2.defaultBlockState());
                    if (blockPos.getY() % 6 == 0) {
                        this.level.setBlockAndUpdate(blockPos, this.banded_drained_stone.defaultBlockState());
                    }
                }
                if (blockPos.getY() == 80) {
                    this.level.setBlockAndUpdate(blockPos, this.banded_drained_stone.defaultBlockState());
                }
            }
        }
    }

    public void SetDrainedStoneColor(float temperature){
        if(temperature > .1f && temperature <= .5F){
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_GREY_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_GREY_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_GREY_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_GREY_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
        else if(temperature > .5f && temperature <= .9f){
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_PURPLE_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_PURPLE_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_PURPLE_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_PURPLE_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
        else if(temperature > .9f && temperature <= 1.2f || Objects.requireNonNull(this.level).getBiome(getBlockPos()).is(Biomes.DESERT)){
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_YELLOW_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_YELLOW_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_YELLOW_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_YELLOW_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
        else if(temperature > 1.2f && temperature <= 2f){
            this.drained_sand = ModBlocks.DRAINED_RED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_RED_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_RED_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_RED_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_RED_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
        else{
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_BLUE_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_BLUE_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_BLUE_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_BLUE_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
    }


    public void SetChroma(ItemChroma chroma){
        this.chroma = chroma;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public ItemChroma getChroma(){
        return this.chroma;
    }

    public void SetPrimer(Item primer){
        this.primer = primer;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
        System.out.println("primer set");
    }

    public Item getPrimer(){
        return this.primer;
    }

    public void setEssences(String essec){
        this.essences = essec;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public String getEssences(){
        return this.essences;
    }

    public void setTier(int tier){
        this.tier = tier;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public int getTier(){
        return this.tier;
    }


    public void setFacing(int facing){
        this.facing = facing;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public int getFacing(){
        return this.facing;
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("stage", this.stage);
        compound.putBoolean("spawned", this.spawned);
        compound.put("chroma", new ItemStack(this.chroma).save(new CompoundTag()));
        compound.put("primer", new ItemStack(this.primer).save(new CompoundTag()));
        compound.putString("essences", this.essences);
        compound.putInt("facing", this.facing);
        compound.putBoolean("checked", this.checked);
        compound.putInt("tier", this.tier);

        //SAVING CRUX STUFF
        compound.putFloat("totalWeight", this.totalWeight);

        //IDS
        compound.putIntArray("IDS", this.IDS);

        //TEMPORARY_WEIGHTS
        if (tier == 1) {
            if(this.TEMPORARY_WEIGHTS.size() > 0)for(int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_1.size(); i++){
                float weight = 0;
                System.out.println("Gem List Cycle: " + i);
                for(int n = 0; n < this.TEMPORARY_WEIGHTS.get(i).size(); n++){
                    weight += this.TEMPORARY_WEIGHTS.get(i).get(n);
                }
                compound.putFloat(GemFormation.POSSIBLE_GEMS_TIER_1.get(i) + "_weight", weight);
            }
        } else if (tier == 2) {
            System.out.println(TEMPORARY_WEIGHTS.size());
            if(this.TEMPORARY_WEIGHTS.size() > 0)for(int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_2.size(); i++){
                float weight = 0;
                System.out.println("Gem List Cycle: " + i);
                for(int n = 0; n < this.TEMPORARY_WEIGHTS.get(i).size(); n++){
                    weight += this.TEMPORARY_WEIGHTS.get(i).get(n);
                }
                compound.putFloat(GemFormation.POSSIBLE_GEMS_TIER_2.get(i) + "_weight", weight);
            }
        }

        //POSITIONS
        ArrayList<Integer> xs = new ArrayList<>();
        ArrayList<Integer> ys = new ArrayList<>();
        ArrayList<Integer> zs = new ArrayList<>();
        ArrayList<BlockPos> positions = new ArrayList<>();
        for (int i : this.POSITIONS.keySet()){
            BlockPos pos = this.POSITIONS.get(i);
            positions.add(pos);
        }
        for(BlockPos pos : positions){
            xs.add(pos.getX());ys.add(pos.getY());zs.add(pos.getZ());
        }
        compound.putIntArray("xs", xs);
        compound.putIntArray("ys", ys);
        compound.putIntArray("zs", zs);
    }


    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.GEM_CONDITIONS = ModEntities.CRUXTOGEM;
        this.stage = nbt.getInt("stage");
        this.spawned = nbt.getBoolean("spawned");
        ItemStack chroma = ItemStack.of(nbt.getCompound("chroma"));
        this.chroma = (ItemChroma)chroma.getItem();
        ItemStack primer = ItemStack.of(nbt.getCompound("primer"));
        this.primer = primer.getItem();
        String fluids = nbt.getString("essences");
        this.essences = fluids;
        this.facing = nbt.getInt("facing");
        this.checked = nbt.getBoolean("checked");
        this.tier = nbt.getInt("tier");

        //LOADING CRUX STUFF
        this.totalWeight = nbt.getFloat("totalWeight");
        this.setIDS(nbt);
        this.setTEMPORARY_WEIGHTS(nbt);
        this.setPOSITIONS(nbt);
    }

    public void setIDS(CompoundTag nbt){
        int[] TEMPIDS = nbt.getIntArray("IDS");
        for(int i = 0; i < TEMPIDS.length; i++){
            this.IDS.add(i, TEMPIDS[i]);
        }
    }

    public void setTEMPORARY_WEIGHTS(CompoundTag nbt){
        if (tier == 1) {
            if(this.TEMPORARY_WEIGHTS.size() == GemFormation.POSSIBLE_GEMS_TIER_1.size())for(int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_1.size(); i++){
                float weight = nbt.getFloat(GemFormation.POSSIBLE_GEMS_TIER_1.get(i) + "_weight");
                this.TEMPORARY_WEIGHTS.get(i).add(weight);
            }
        } else if (tier == 2) {
            if(this.TEMPORARY_WEIGHTS.size() == GemFormation.POSSIBLE_GEMS_TIER_2.size())for(int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_2.size(); i++){
                float weight = nbt.getFloat(GemFormation.POSSIBLE_GEMS_TIER_2.get(i) + "_weight");
                this.TEMPORARY_WEIGHTS.get(i).add(weight);
            }
        }
    }

    public void setPOSITIONS(CompoundTag nbt){
        for(int i = 0; i < nbt.getIntArray("xs").length; i++){
            int[] xs = nbt.getIntArray("xs");
            int[] ys = nbt.getIntArray("ys");
            int[] zs = nbt.getIntArray("zs");
            this.POSITIONS.put(i, new BlockPos(xs[i], ys[i], zs[i]));
        }
    }

    public static String StringFromFluid(Fluid fluid){
        if(fluid == ModFluids.PINK_ESSENCE.get()){
            return "pink";
        }
        else if(fluid == ModFluids.BLUE_ESSENCE.get()){
            return "blue";
        }
        else if(fluid == ModFluids.YELLOW_ESSENCE.get()){
            return "yellow";
        }
        else if(fluid == ModFluids.WHITE_ESSENCE.get()){
            return "white";
        }
        else{
            return "";
        }
    }

    public static Fluid FluidFromString(String fluid){
        if(Objects.equals(fluid, "pink")){
            return ModFluids.PINK_ESSENCE.get();
        }
        else if(Objects.equals(fluid, "blue")){
            return ModFluids.BLUE_ESSENCE.get();
        }
        else if(Objects.equals(fluid, "yellow")){
            return ModFluids.YELLOW_ESSENCE.get();
        }
        else if(Objects.equals(fluid, "white")){
            return ModFluids.WHITE_ESSENCE.get();
        }
        else {
            return Fluids.EMPTY;
        }
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.load(nbt);
        this.GEM_CONDITIONS = ModEntities.CRUXTOGEM;
        this.stage = nbt.getInt("stage");
        this.spawned = nbt.getBoolean("spawned");
        ItemStack chroma = ItemStack.of(nbt.getCompound("chroma"));
        this.chroma = (ItemChroma)chroma.getItem();
        ItemStack primer = ItemStack.of(nbt.getCompound("primer"));
        this.primer = primer.getItem();
        String fluids = nbt.getString("essences");
        this.essences = fluids;
        this.facing = nbt.getInt("facing");
        this.tier = nbt.getInt("tier");
        this.checked = nbt.getBoolean("checked");

        //LOADING CRUX STUFF
        this.totalWeight = nbt.getFloat("totalWeight");
        this.setIDS(nbt);
        this.setTEMPORARY_WEIGHTS(nbt);
        this.setPOSITIONS(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        super.serializeNBT();
        compound.putInt("stage", this.stage);
        compound.putBoolean("spawned", this.spawned);
        compound.put("chroma", new ItemStack(this.chroma).save(new CompoundTag()));
        compound.put("primer", new ItemStack(this.primer).save(new CompoundTag()));
        compound.putString("essences", this.essences);
        compound.putInt("facing", this.facing);
        compound.putBoolean("checked", this.checked);
        compound.putInt("tier", this.tier);

        //SAVING CRUX STUFF
        compound.putFloat("totalWeight", this.totalWeight);

        //IDS
        compound.putIntArray("IDS", this.IDS);

        //TEMPORARY_WEIGHTS
        if (tier == 1) {
            if(this.TEMPORARY_WEIGHTS.size() > 0)for(int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_1.size(); i++){
                float weight = 0;
                System.out.println("Gem List Cycle: " + i);
                for(int n = 0; n < this.TEMPORARY_WEIGHTS.get(i).size(); n++){
                    weight += this.TEMPORARY_WEIGHTS.get(i).get(n);
                }
                compound.putFloat(GemFormation.POSSIBLE_GEMS_TIER_1.get(i) + "_weight", weight);
            }
        } else if (tier == 2) {
            if(this.TEMPORARY_WEIGHTS.size() > 0)for(int i = 0; i < GemFormation.POSSIBLE_GEMS_TIER_2.size(); i++){
                float weight = 0;
                System.out.println("Gem List Cycle: " + i);
                for(int n = 0; n < this.TEMPORARY_WEIGHTS.get(i).size(); n++){
                    weight += this.TEMPORARY_WEIGHTS.get(i).get(n);
                }
                compound.putFloat(GemFormation.POSSIBLE_GEMS_TIER_2.get(i) + "_weight", weight);
            }
        }

        //POSITIONS
        ArrayList<Integer> xs = new ArrayList<>();
        ArrayList<Integer> ys = new ArrayList<>();
        ArrayList<Integer> zs = new ArrayList<>();
        ArrayList<BlockPos> positions = new ArrayList<>();
        for (int i : this.POSITIONS.keySet()){
            BlockPos pos = this.POSITIONS.get(i);
            positions.add(pos);
        }
        for(BlockPos pos : positions){
            xs.add(pos.getX());ys.add(pos.getY());zs.add(pos.getZ());
        }
        compound.putIntArray("xs", xs);
        compound.putIntArray("ys", ys);
        compound.putIntArray("zs", zs);

        return compound;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        //Debug
        System.out.println("[DEBUG]:Client recived tile sync packet");
        this.load(Objects.requireNonNull(pkt.getTag()));
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        System.out.println("[DEBUG]:Handling tag on chunk load");
        this.load(tag);
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compound = new CompoundTag();
        this.saveAdditional(compound);
        return compound;
    }
    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        //Debug
        System.out.println("[DEBUG]:Server sent tile sync packet");
        return ClientboundBlockEntityDataPacket.create(this);
    }
}