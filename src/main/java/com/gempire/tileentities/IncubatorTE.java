package com.gempire.tileentities;

import com.gempire.container.IncubatorContainer;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.*;
import com.gempire.items.ItemChroma;
import com.gempire.items.ItemGem;
import com.gempire.items.ItemGemBase;
import com.gempire.util.Color;
import it.unimi.dsi.fastutil.Hash;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;
import org.checkerframework.checker.units.qual.A;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class IncubatorTE extends RandomizableContainerBlockEntity implements MenuProvider {
    public static final int NUMBER_OF_SLOTS = 10;
    public static final int BLOCK1_INPUT_SLOT_INDEX = 0;
    public static final int PRIMER_INPUT_SLOT_INDEX = 1;
    public static final int BLOCK2_INPUT_SLOT_INDEX = 2;
    public static final int BLOCK3_INPUT_SLOT_INDEX = 3;

    public static final int GEM_BASE_INPUT_SLOT_INDEX = 4;
    public static final int BLOCK4_INPUT_SLOT_INDEX = 5;
    public static final int ESSENCE1_INPUT_SLOT_INDEX = 6;
    public static final int CHROMA_INPUT_SLOT_INDEX = 7;
    public static final int ESSENCE2_INPUT_SLOT_INDEX = 8;
    public static final int GEM_OUTPUT_SLOT_INDEX = 9;
    public NonNullList<ItemStack> items = NonNullList.withSize(IncubatorTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public static final int MAX_BLOCK1 = 64;
    public static final int MAX_BLOCK2 = 64;
    public static final int MAX_BLOCK3 = 64;
    public static final int MAX_BLOCK4 = 64;
    public String gemBase = "";
    public boolean blockConsumed = false;
    public int[] blockAmounts = new int[4];
    public boolean baseConsumed = false;
    public boolean chromaConsumed = false;
    public int essence1Consumed = 0;
    public int essence2Consumed = 0;

    public boolean essenceConsumed = false;

    //essence numbering
    // 0 = not consumed
    // 1 = pink
    // 2 = yellow
    // 3 = blue
    // 4 = white
    public int chromaColor = 0;
    public int ticks = 0;
    public int primer = 0;
    public int weight = 0;

    //primer numbering
    // 0 = not consumed
    // 1 = prime
    // 2 = speed

    public int incubationTime = 0;
    public int incubationProgress = 0;
    protected final ContainerData data;

    public HashMap<String, Boolean> colour = new HashMap<>();

    public HashMap<String, Integer> time = new HashMap<>();
    public HashMap<String, ArrayList<Integer>> essenceRequired = new HashMap<>();

    public HashMap<String, HashMap<Item, Integer>> blocks = new HashMap<>();
    public ArrayList<Item> blockList = new ArrayList<>();

    public IncubatorTE(BlockPos pos, BlockState state) {
        super(ModTE.INCUBATOR_TE.get(), pos, state);
        setup();
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return IncubatorTE.this.incubationProgress;
                    case 1: return IncubatorTE.this.incubationTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: IncubatorTE.this.incubationProgress = value; break;
                    case 1: IncubatorTE.this.incubationTime = value; break;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.blockConsumed = nbt.getBoolean("block");
        this.chromaConsumed = nbt.getBoolean("chroma");
        this.baseConsumed = nbt.getBoolean("base");
        this.essence1Consumed = nbt.getInt("essence1");
        this.essence2Consumed = nbt.getInt("essence2");
        this.chromaColor = nbt.getInt("color");
        this.gemBase = nbt.getString("base");
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if(!this.tryLoadLootTable(nbt)){
            ContainerHelper.loadAllItems(nbt, this.items);
        }
        gemBase = this.items.get(4).getItem().toString();
        setup();
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putBoolean("block", this.blockConsumed);
        compound.putBoolean("chroma", this.chromaConsumed);
        compound.putBoolean("base", this.baseConsumed);
        compound.putInt("essence1", this.essence1Consumed);
        compound.putInt("essence2", this.essence2Consumed);
        compound.putInt("color", this.chromaColor);
        compound.putString("base", this.gemBase);
        if(!this.trySaveLootTable(compound)){
            ContainerHelper.saveAllItems(compound, this.items);
        }
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        IncubatorTE te = (IncubatorTE)be;
        if(!level.isClientSide()) {
            if (te.getItem(IncubatorTE.GEM_OUTPUT_SLOT_INDEX) == ItemStack.EMPTY) {
                if (te.ticks % 50 == 0) {
                    te.HandleChromaTick();
                    te.HandleBaseTick();
                    te.HandleEssenceTick();
                    te.HandlePrimerTick();
                    te.HandleCruxTick();
                    //te.HandleGravelTick();
                    //te.HandleSandTick();
                    //te.HandleClayTick();
                    te.HandleFormGemTick();
                }
                if (te.ticks > 25) {
                    te.ticks = 0;
                } else {
                    te.ticks++;
                }
            }
        }
    }

    /*public void HandleGravelTick(){
        ItemStack stack = this.getItem(IncubatorTE.BLOCK1_INPUT_SLOT_INDEX);
        int count = stack.getCount();
        if (count + block1Consumed >= 64) {
            if (block1Consumed < 64) {
                if (stack.getItem() == Blocks.BLOCK1.asItem()) {
                    stack.shrink(1);
                    this.block1Consumed++;
                }
            }
        } else if (count + block1Consumed >= 32) {
            if (block1Consumed < 32) {
                if (stack.getItem() == Blocks.BLOCK1.asItem()) {
                    stack.shrink(1);
                    this.block1Consumed++;
                }
            }
        } else if (count + block1Consumed >= 16) {
            if (block1Consumed < 16) {
                if (stack.getItem() == Blocks.BLOCK1.asItem()) {
                    stack.shrink(1);
                    this.block1Consumed++;
                }
            }
        }
    }

    public void HandleSandTick(){
        if(this.block1Consumed == IncubatorTE.MAX_BLOCK1 && this.block2Consumed < IncubatorTE.MAX_BLOCK2){
            System.out.println(64);
            ItemStack stack = this.getItem(IncubatorTE.BLOCK2_INPUT_SLOT_INDEX);
            if(stack.getItem() == Blocks.BLOCK2.asItem()){
                stack.shrink(1);
                this.block2Consumed++;
            }
        } else if(this.block1Consumed == 32 && this.block2Consumed < 32){
            System.out.println(32);
            ItemStack stack = this.getItem(IncubatorTE.BLOCK2_INPUT_SLOT_INDEX);
            if(stack.getItem() == Blocks.BLOCK2.asItem()){
                stack.shrink(1);
                this.block2Consumed++;
            }
        } else if(this.block1Consumed == 16 && this.block2Consumed < 16){
            System.out.println(16);
            ItemStack stack = this.getItem(IncubatorTE.BLOCK2_INPUT_SLOT_INDEX);
            if(stack.getItem() == Blocks.BLOCK2.asItem()){
                stack.shrink(1);
                this.block2Consumed++;
            }
        }
    }

    public void HandleClayTick(){
        if(this.block1Consumed == IncubatorTE.MAX_BLOCK1 && this.block2Consumed == IncubatorTE.MAX_BLOCK2 && this.block3Consumed < IncubatorTE.MAX_BLOCK3){
            ItemStack stack = this.getItem(IncubatorTE.BLOCK3_INPUT_SLOT_INDEX);
            if(stack.getItem() == Items.BLOCK3_BALL){
                stack.shrink(1);
                this.block3Consumed++;
            }
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1 / 2 && this.block2Consumed == IncubatorTE.MAX_BLOCK2/ 2 && this.block3Consumed < IncubatorTE.MAX_BLOCK3/ 2){
            ItemStack stack = this.getItem(IncubatorTE.BLOCK3_INPUT_SLOT_INDEX);
            if(stack.getItem() == Items.BLOCK3_BALL){
                stack.shrink(1);
                this.block3Consumed++;
            }
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1 / 4 && this.block2Consumed == IncubatorTE.MAX_BLOCK2/ 4 && this.block3Consumed < IncubatorTE.MAX_BLOCK3/ 4){
            ItemStack stack = this.getItem(IncubatorTE.BLOCK3_INPUT_SLOT_INDEX);
            if(stack.getItem() == Items.BLOCK3_BALL){
                stack.shrink(1);
                this.block3Consumed++;
            }
        }
    }
*/
    public void HandleChromaTick() {
        if (!chromaConsumed) {
            ItemStack stack = this.getItem(IncubatorTE.CHROMA_INPUT_SLOT_INDEX);
            if (stack.getItem() instanceof ItemChroma chroma) {
                this.chromaConsumed = true;
                this.chromaColor = chroma.color;
            }
        }
    }

    public void HandleBaseTick() {
        if (chromaConsumed) {
            ItemStack stack = this.getItem(IncubatorTE.GEM_BASE_INPUT_SLOT_INDEX);
            if (stack.getItem() instanceof ItemGemBase base) {
                this.baseConsumed = true;
                this.gemBase = base.toString();
            }
        }
    }

    public void HandleEssenceTick() {
        if (baseConsumed && chromaConsumed) {
            ItemStack stack = this.getItem(IncubatorTE.ESSENCE1_INPUT_SLOT_INDEX);
            ItemStack stack2 = this.getItem(IncubatorTE.ESSENCE2_INPUT_SLOT_INDEX);
            String name = gemBase.toLowerCase().replaceAll("inactive_", "").replaceAll("_base", "");
            int essence1 = essenceRequired.get(name).get(0);
            int essence2 = essenceRequired.get(name).get(1);
            Item bottle1 = essence1 == 1 ? ModItems.PINK_ESSENCE_BUCKET.get() : (essence1 == 2 ? ModItems.YELLOW_ESSENCE_BUCKET.get() : (essence1 == 3 ? ModItems.BLUE_ESSENCE_BUCKET.get() : ModItems.WHITE_ESSENCE_BUCKET.get()));
            Item bottle2 = essence2 == 1 ? ModItems.PINK_ESSENCE_BUCKET.get() : (essence2 == 2 ? ModItems.YELLOW_ESSENCE_BUCKET.get() : (essence2 == 3 ? ModItems.BLUE_ESSENCE_BUCKET.get() : ModItems.WHITE_ESSENCE_BUCKET.get()));
            if (stack.is(bottle1) && stack2.is(bottle2)) {
                this.essenceConsumed = true;
            } else if (stack.is(bottle2) && stack2.is(bottle1)) {
                this.essenceConsumed = true;
            }
        }
    }

    public void HandleCruxTick() {
        if (baseConsumed && !blockConsumed) {
            String name = gemBase.toLowerCase().replaceAll("inactive_", "").replaceAll("_base", "");
            ItemStack stack = this.getItem(BLOCK1_INPUT_SLOT_INDEX);
            ItemStack stack2 = this.getItem(BLOCK2_INPUT_SLOT_INDEX);
            ItemStack stack3 = this.getItem(BLOCK3_INPUT_SLOT_INDEX);
            ItemStack stack4 = this.getItem(BLOCK4_INPUT_SLOT_INDEX);
            if (blocks.get(name).containsKey(stack.getItem())) {
                int value = blocks.get(name).get(stack.getItem());
                weight += stack.getCount() * value;
                System.out.println("stack 1");
                blockAmounts[0] = stack.getCount();
            }
            if (blocks.get(name).containsKey(stack2.getItem())) {
                int value = blocks.get(name).get(stack2.getItem());
                weight += stack2.getCount() * value;
                System.out.println("stack 2");
                blockAmounts[1] = stack2.getCount();
            }
            if (blocks.get(name).containsKey(stack3.getItem())) {
                int value = blocks.get(name).get(stack3.getItem());
                weight += stack3.getCount() * value;
                System.out.println("stack 3");
                blockAmounts[2] = stack3.getCount();
            }
            if (blocks.get(name).containsKey(stack4.getItem())) {
                int value = blocks.get(name).get(stack4.getItem());
                weight += stack4.getCount() * value;
                System.out.println("stack 4");
                blockAmounts[3] = stack4.getCount();
            }
            blockConsumed = true;
        } else {
            ItemStack stack = this.getItem(BLOCK1_INPUT_SLOT_INDEX);
            ItemStack stack2 = this.getItem(BLOCK2_INPUT_SLOT_INDEX);
            ItemStack stack3 = this.getItem(BLOCK3_INPUT_SLOT_INDEX);
            ItemStack stack4 = this.getItem(BLOCK4_INPUT_SLOT_INDEX);
            if (stack.isEmpty() || stack2.isEmpty() || stack3.isEmpty() || stack4.isEmpty()) {
                blockConsumed = false;
            }
        }
    }

    public void HandlePrimerTick() {
        if (baseConsumed && chromaConsumed) {
            ItemStack stack = this.getItem(IncubatorTE.PRIMER_INPUT_SLOT_INDEX);
            if (stack.is(ModItems.PRIME_BOOST.get())) {
                primer = 1;
            } else if (stack.is(ModItems.GILDED_LAPIS.get())) {
                primer = 2;
            }
        }
    }
/*
    public void HandleEssenceTick() {
        if (this.block1Consumed == IncubatorTE.MAX_BLOCK1 && this.block2Consumed == IncubatorTE.MAX_BLOCK2 && this.block3Consumed == IncubatorTE.MAX_BLOCK3 && this.chromaConsumed) {
            //ESSENCE CHECK
                if (this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock() == ModBlocks.WHITE_ESSENCE_BLOCK.get()) {
                    LiquidBlock block = (LiquidBlock) this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock();
                    if (block.getFluid() == ModFluids.WHITE_ESSENCE.get()) {
                        this.level.setBlockAndUpdate(this.worldPosition.offset(IncubatorTE.direction(4)), Blocks.AIR.defaultBlockState());
                        this.essenceConsumed = true;
                    }
                }
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1/2 && this.block2Consumed == IncubatorTE.MAX_BLOCK2/2 && this.block3Consumed == IncubatorTE.MAX_BLOCK3/2 && this.chromaConsumed) {
            //ESSENCE CHECK
            if (this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock() == ModBlocks.WHITE_ESSENCE_BLOCK.get()) {
                LiquidBlock block = (LiquidBlock) this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock();
                if (block.getFluid() == ModFluids.WHITE_ESSENCE.get()) {
                    this.level.setBlockAndUpdate(this.worldPosition.offset(IncubatorTE.direction(4)), Blocks.AIR.defaultBlockState());
                    this.essenceConsumed = true;
                }
            }
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1/4 && this.block2Consumed == IncubatorTE.MAX_BLOCK2/4 && this.block3Consumed == IncubatorTE.MAX_BLOCK3/4 && this.chromaConsumed) {
            //ESSENCE CHECK
            if (this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock() == ModBlocks.WHITE_ESSENCE_BLOCK.get()) {
                LiquidBlock block = (LiquidBlock) this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock();
                if (block.getFluid() == ModFluids.WHITE_ESSENCE.get()) {
                    this.level.setBlockAndUpdate(this.worldPosition.offset(IncubatorTE.direction(4)), Blocks.AIR.defaultBlockState());
                    this.essenceConsumed = true;
                }
            }
        }
    }*/

    public void setup() {
        colour.put("ruby", false);
        colour.put("nephrite", false);
        colour.put("rutile", false);
        colour.put("bismuth", false);
        colour.put("aquamarine", false);
        colour.put("emerald", false);
        colour.put("bixbite", false);
        colour.put("lapis", false);
        colour.put("obsidian", false);
        colour.put("larimar", false);
        colour.put("morganite", false);
        colour.put("peridot", false);
        colour.put("jasper", true);
        colour.put("garnet", true);
        colour.put("quartz", true);
        colour.put("sapphire", true);
        colour.put("agate", true);
        colour.put("spinel", true);
        colour.put("tourmaline", true);
        colour.put("zircon", true);
        colour.put("spodumene", true);
        colour.put("topaz", true);

        //-----------

        HashMap<Item, Integer> agate = new HashMap<>();
        HashMap<Item, Integer> aquamarine = new HashMap<>();
        HashMap<Item, Integer> bismuth = new HashMap<>();
        HashMap<Item, Integer> bixbite = new HashMap<>();
        HashMap<Item, Integer> emerald = new HashMap<>();
        HashMap<Item, Integer> garnet = new HashMap<>();
        HashMap<Item, Integer> jasper = new HashMap<>();
        HashMap<Item, Integer> lapis = new HashMap<>();
        HashMap<Item, Integer> larimar = new HashMap<>();
        HashMap<Item, Integer> morganite = new HashMap<>();
        HashMap<Item, Integer> nephrite = new HashMap<>();
        HashMap<Item, Integer> obsidian = new HashMap<>();
        HashMap<Item, Integer> peridot = new HashMap<>();
        HashMap<Item, Integer> quartz = new HashMap<>();
        HashMap<Item, Integer> ruby = new HashMap<>();
        HashMap<Item, Integer> rutile = new HashMap<>();
        HashMap<Item, Integer> sapphire = new HashMap<>();
        HashMap<Item, Integer> spinel = new HashMap<>();
        HashMap<Item, Integer> spodumene = new HashMap<>();
        HashMap<Item, Integer> topaz = new HashMap<>();
        HashMap<Item, Integer> tourmaline = new HashMap<>();
        HashMap<Item, Integer> zircon = new HashMap<>();

        agate.put(Items.CALCITE, 1);
        agate.put(Items.RAW_COPPER_BLOCK, 2);
        agate.put(Items.QUARTZ_BLOCK, 4);

        aquamarine.put(Items.SOUL_SOIL, 1);
        aquamarine.put(Items.PRISMARINE, 2);
        aquamarine.put(Items.GHAST_TEAR, 4);

        ruby.put(Items.NETHERRACK, 1);
        ruby.put(Items.IRON_ORE, 4);

        blocks.put("agate", agate);
        blocks.put("aquamarine", aquamarine);
        blocks.put("bismuth", bismuth);
        blocks.put("bixbite", bixbite);
        blocks.put("emerald", emerald);
        blocks.put("garnet", garnet);
        blocks.put("jasper", jasper);
        blocks.put("lapis", lapis);
        blocks.put("larimar", larimar);
        blocks.put("morganite", morganite);
        blocks.put("nephrite", nephrite);
        blocks.put("obsidian", obsidian);
        blocks.put("peridot", peridot);
        blocks.put("quartz", quartz);
        blocks.put("ruby", ruby);
        blocks.put("rutile", rutile);
        blocks.put("sapphire", sapphire);
        blocks.put("spinel", spinel);
        blocks.put("spodumene", spodumene);
        blocks.put("topaz", topaz);
        blocks.put("tourmaline", tourmaline);
        blocks.put("zircon", zircon);

        blockList.add(Items.CALCITE);
        blockList.add(Items.RAW_COPPER_BLOCK);
        blockList.add(Items.QUARTZ_BLOCK);
        blockList.add(Items.NETHERRACK);

        //------------

        time.put("ruby", 50);
        time.put("nephrite", 50);
        time.put("rutile", 50);
        time.put("bismuth", 50);
        time.put("aquamarine", 50);
        time.put("emerald", 50);
        time.put("bixbite", 50);
        time.put("lapis", 50);
        time.put("obsidian", 50);
        time.put("larimar", 50);
        time.put("morganite", 50);
        time.put("peridot", 50);
        time.put("jasper", 50);
        time.put("garnet", 50);
        time.put("quartz", 50);
        time.put("sapphire", 50);
        time.put("agate", 50);
        time.put("spinel", 50);
        time.put("tourmaline", 50);
        time.put("zircon", 50);
        time.put("spodumene", 50);
        time.put("topaz", 50);

        //-------------

        ArrayList<Integer> allPink = new ArrayList<>();
        allPink.add(1);
        allPink.add(1);
        ArrayList<Integer> allYellow = new ArrayList<>();
        allYellow.add(2);
        allYellow.add(2);
        ArrayList<Integer> allBlue = new ArrayList<>();
        allBlue.add(3);
        allBlue.add(3);
        ArrayList<Integer> allWhite = new ArrayList<>();
        allWhite.add(4);
        allWhite.add(4);
        ArrayList<Integer> pinkYellow = new ArrayList<>();
        pinkYellow.add(1);
        pinkYellow.add(2);
        ArrayList<Integer> pinkBlue = new ArrayList<>();
        pinkBlue.add(1);
        pinkBlue.add(3);
        ArrayList<Integer> pinkWhite = new ArrayList<>();
        pinkWhite.add(1);
        pinkWhite.add(4);
        ArrayList<Integer> yellowBlue = new ArrayList<>();
        yellowBlue.add(2);
        yellowBlue.add(3);
        ArrayList<Integer> yellowWhite = new ArrayList<>();
        yellowWhite.add(2);
        yellowWhite.add(4);
        ArrayList<Integer> blueWhite = new ArrayList<>();
        blueWhite.add(3);
        blueWhite.add(4);
        essenceRequired.put("ruby", allPink);
        essenceRequired.put("nephrite", yellowBlue);
        essenceRequired.put("rutile", allPink);
        essenceRequired.put("bismuth", pinkBlue);
        essenceRequired.put("aquamarine", blueWhite);
        essenceRequired.put("emerald", yellowBlue);
        essenceRequired.put("bixbite", allPink);
        essenceRequired.put("lapis", allBlue);
        essenceRequired.put("obsidian", allWhite);
        essenceRequired.put("larimar", allBlue);
        essenceRequired.put("morganite", allPink);
        essenceRequired.put("peridot", yellowBlue);
        essenceRequired.put("jasper", yellowWhite);
        essenceRequired.put("garnet", yellowWhite);
        essenceRequired.put("quartz", pinkWhite);
        essenceRequired.put("sapphire", blueWhite);
        essenceRequired.put("agate", blueWhite);
        essenceRequired.put("spinel", allPink);
        essenceRequired.put("tourmaline", allWhite);
        essenceRequired.put("zircon", blueWhite);
        essenceRequired.put("spodumene", allPink);
        essenceRequired.put("topaz", allYellow);
    }


    public static BlockPos direction(int i){
        switch (i){
            case 1:
                return BlockPos.ZERO.south();
            case 2:
                return BlockPos.ZERO.above();
            case 3:
                return BlockPos.ZERO.west();
            case 4:
                return BlockPos.ZERO.below();
            case 5:
                return BlockPos.ZERO.east();
            default:
                return BlockPos.ZERO.north();
        }
    }


    public void HandleFormGemTick(){
        System.out.println(weight);
        /*if(this.block1Consumed == IncubatorTE.MAX_BLOCK1 && this.block2Consumed == IncubatorTE.MAX_BLOCK2 && this.block3Consumed == IncubatorTE.MAX_BLOCK3 && this.chromaConsumed
                && this.essenceConsumed) {
            this.formGem(this.chromaColor, 0);
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1 / 2 && this.block2Consumed == IncubatorTE.MAX_BLOCK2 / 2 && this.block3Consumed == IncubatorTE.MAX_BLOCK3 / 2 && this.chromaConsumed
                && this.essenceConsumed) {
            this.formGem(this.chromaColor, 1);
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1 / 4 && this.block2Consumed == IncubatorTE.MAX_BLOCK2 / 4 && this.block3Consumed == IncubatorTE.MAX_BLOCK3 / 4 && this.chromaConsumed
                && this.essenceConsumed) {
            this.formGem(this.chromaColor, 2);
        }*/
        if (baseConsumed && chromaConsumed && essenceConsumed && blockConsumed) {
            incubationTime = time.get(gemBase.toLowerCase().replaceAll("inactive_", "").replaceAll("_base", ""));
            if (primer == 2) incubationTime = incubationTime/2;
            if (incubationProgress < incubationTime) {
                incubationProgress++;
                System.out.println(incubationProgress);
            } else {
                incubationProgress = 0;
                incubationTime = 0;
                this.formGem(this.chromaColor, 1);
            }
        }
    }

    //perfect = 0
    //normal = 1
    //defect = 2
    public void formGem(int chroma, int quality){
        this.getItem(IncubatorTE.ESSENCE1_INPUT_SLOT_INDEX).shrink(1);
        this.getItem(IncubatorTE.ESSENCE2_INPUT_SLOT_INDEX).shrink(1);
        this.setItem(IncubatorTE.ESSENCE1_INPUT_SLOT_INDEX, Items.BUCKET.getDefaultInstance());
        this.setItem(IncubatorTE.ESSENCE2_INPUT_SLOT_INDEX, Items.BUCKET.getDefaultInstance());
        this.getItem(IncubatorTE.CHROMA_INPUT_SLOT_INDEX).shrink(1);
        this.getItem(IncubatorTE.GEM_BASE_INPUT_SLOT_INDEX).shrink(1);
        this.getItem(IncubatorTE.PRIMER_INPUT_SLOT_INDEX).shrink(1);
        this.getItem(IncubatorTE.BLOCK1_INPUT_SLOT_INDEX).shrink(blockAmounts[0]);
        this.getItem(IncubatorTE.BLOCK2_INPUT_SLOT_INDEX).shrink(blockAmounts[1]);
        this.getItem(IncubatorTE.BLOCK3_INPUT_SLOT_INDEX).shrink(blockAmounts[2]);
        this.getItem(IncubatorTE.BLOCK4_INPUT_SLOT_INDEX).shrink(blockAmounts[3]);
        /*RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
        String skinColorVariant = "";
        EntityGem gem = gemm.get().create(world);
        String namee = "";
        boolean dying = false;
        List<EntityGem> list;
            if (Objects.equals(this.ID, Gempire.MODID)) {
                namee = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this)).toString().replaceAll("gempire", "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
            } else {
                namee = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this)).toString().replaceAll(this.ID, "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
            }
            //This whole section here checks for variations in color so it can spawn the correct type of gem
            String[] ainmneacha = namee.split("_");
            boolean nullFlag = false;
            int idx = 0;
            for (int i = 0; i < ainmneacha.length; i++) {
                if (ainmneacha[i].isEmpty()) {
                    nullFlag = true;
                    idx = i;
                }
            }
            if (nullFlag) ainmneacha = ArrayUtils.remove(ainmneacha, idx);
            namee = ainmneacha[0];
            if (ainmneacha.length > 1) skinColorVariant = ainmneacha[1];
            for (String s : ainmneacha) {
                System.out.println(s);
            }
            //End of check and set
            try {
                if (Objects.equals(this.ID, Gempire.MODID)) {
                    gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(namee.toUpperCase()).get(null);
                } else {
                    gemm = (RegistryObject<EntityType<EntityPebble>>) AddonHandler.ADDON_ENTITY_REGISTRIES.get(this.ID).getField(namee.toUpperCase()).get(null);
                }
                gem = gemm.get().create(world);
                System.out.println("gem " + gem);
                assert gem != null;
                gem.setUUID(Mth.createInsecureUUID(world.random));
                System.out.println(gem.getUUID());
            } catch (Exception e) {
                e.printStackTrace();
            }
                if (ainmneacha.length > 1) {
                    assert gem != null;
                    gem.setSkinVariantOnInitialSpawn = false;
                    gem.initalSkinVariant = Integer.parseInt(skinColorVariant);
                }
            GemFormEvent event = new GemFormEvent(gem, gem.blockPosition());
            */
        RegistryObject<Item> gemm = ModItems.PEBBLE_GEM;
        ItemGem gem = null;
        String baseName = gemBase.toUpperCase().replaceAll("INACTIVE_", "").replaceAll("_BASE", "");
        String name = "";
        if (colour.get(baseName.toLowerCase())) {
            name = Color.getColorName(chroma).toUpperCase() +"_"+baseName+"_GEM";
        } else {
            name = baseName+"_GEM";
        }
        System.out.println("name "+name);
        RegistryObject<EntityType<EntityPebble>> egemm = ModEntities.PEBBLE;
        String namee = "";
        String skinColorVariant = "";
        System.out.println("name "+name);
        String[] array = name.split("_");
        boolean nullFlag = false;
        int idx = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].isEmpty()) {
                nullFlag = true;
                idx = i;
            }
        }
        if (nullFlag) array = ArrayUtils.remove(array, idx);
        namee = array[0];
        if (array.length > 1) skinColorVariant = array[0];
        for (String s : array) {
            System.out.println(s);
        }
        if (array.length >= 4) skinColorVariant = array[0] + "_" + array[1];
        System.out.println("skin variant string " +skinColorVariant);
        System.out.println("array "+ Arrays.toString(array));
        System.out.println("name "+name);
        if (!colour.get(baseName.toLowerCase())) skinColorVariant = "";
        try {
            egemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(name.toUpperCase().replaceAll("GEM", "").replaceAll(skinColorVariant, "").replaceAll("_", "")).get(null);
        } catch(Exception e){
            e.printStackTrace();
        }
        EntityGem egem = egemm.get().create(this.level);
        if (egem instanceof EntityVaryingGem) {
            if (((EntityVaryingGem) egem).UsesUniqueNames()) {
                String uniqueColor = Component.translatable("nickname.gempire." + egem.getWholeGemName() + "_" + chromaColor).getString();
                System.out.println(uniqueColor.toLowerCase());
                System.out.println(egem.getWholeGemName());
                uniqueColor = uniqueColor.toLowerCase().replaceAll(" "+egem.getWholeGemName(), "").replaceAll(" ", "_");
                System.out.println(uniqueColor);
                name = name.replaceAll(skinColorVariant.toUpperCase(), uniqueColor.toUpperCase());
                System.out.println("name "+ name);
            }
        } else {
            egem.setSkinColorVariant(egem.initalSkinVariant);
        }
        try {
            gemm = (RegistryObject<Item>) ModItems.class.getField(name.toUpperCase()).get(null);
            gem = (ItemGem) gemm.get();
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(name);
        egem.setUUID(Mth.createInsecureUUID(this.level.random));
        /*if (array.length > 1) {
            assert gem != null;
            egem.setSkinVariantOnInitialSpawn = false;
            egem.initalSkinVariant = chromaColor;
        }*/
        egem.setSkinColorVariant(chroma);
        egem.finalizeSpawn((ServerLevelAccessor) this.level, this.level.getCurrentDifficultyAt(this.worldPosition), MobSpawnType.MOB_SUMMONED, null, null);
        ItemStack stack = new ItemStack(gem);
        ItemGem.saveData(stack, egem);
        egem.remove(Entity.RemovalReason.DISCARDED);
        if (quality == 0) {
            stack.getOrCreateTag().putBoolean("prime", true);
        } else if (quality == 2) {
            stack.getOrCreateTag().putBoolean("defective", true);
        }
        this.setItem(IncubatorTE.GEM_OUTPUT_SLOT_INDEX, stack);
        blockAmounts[0] = 0;
        blockAmounts[1] = 0;
        blockAmounts[2] = 0;
        blockAmounts[3] = 0;
        this.chromaConsumed = false;
        this.primer = 0;
        this.baseConsumed = false;
        this.essence1Consumed = 0;
        this.essence2Consumed = 0;
        this.chromaColor = 0;
    }

    //CONTAINER STUFF

    @Override
    public Component getDisplayName() {
        return Component.translatable("");//TranslationTextComponent("container.gempire.injector");
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.gempire.incubator");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new IncubatorContainer(id, player, this, this.data);
    }

    @Override
    public int getContainerSize() {
        return IncubatorTE.NUMBER_OF_SLOTS;
    }

    public ItemStack getGemItem(){
        return this.getItem(IncubatorTE.GEM_OUTPUT_SLOT_INDEX);
    }

    //NETWORKING STUFF

    //NETWORKING STUFF

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        //Debug
        System.out.println("[DEBUG]:Client recived tile sync packet");
        this.load(Objects.requireNonNull(pkt.getTag()));
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        //Debug
        System.out.println("[DEBUG]:Server sent tile sync packet");
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compound = new CompoundTag();
        this.saveAdditional(compound);
        return compound;
    }
    @Override
    public void handleUpdateTag(CompoundTag tag) {
        System.out.println("[DEBUG]:Handling tag on chunk load");
        this.load(tag);
    }
}
