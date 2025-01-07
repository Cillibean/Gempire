package com.gempire.tileentities;

import com.gempire.container.BoardContainer;
import com.gempire.container.IncubatorContainer;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.entities.gems.EntitySapphire;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.AddonHandler;
import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import com.gempire.init.ModTE;
import com.gempire.items.ItemChroma;
import com.gempire.items.ItemGem;
import com.gempire.items.ItemGemBase;
import com.gempire.util.Color;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class BoardTE extends BaseContainerBlockEntity implements MenuProvider {
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
    public NonNullList<ItemStack> items = NonNullList.withSize(BoardTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public String gemBase = "";
    public boolean blockConsumed = false;
    public int[] blockAmounts = new int[4];
    public boolean baseConsumed = false;
    public boolean chromaConsumed = false;

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
    public static HashMap<String, Boolean> addon = new HashMap<>();
    public static HashMap<String, Boolean> prismaticVariant = new HashMap<>();
    public HashMap<String, Integer> time = new HashMap<>();
    public HashMap<String, ArrayList<Integer>> essenceRequired = new HashMap<>();

    public HashMap<String, HashMap<Item, Integer>> blocks = new HashMap<>();
    public ArrayList<Item> blockList = new ArrayList<>();

    public BoardTE(BlockPos pos, BlockState state) {
        super(ModTE.INCUBATOR_TE.get(), pos, state);
        setup();
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return BoardTE.this.incubationProgress;
                    case 1: return BoardTE.this.incubationTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: BoardTE.this.incubationProgress = value; break;
                    case 1: BoardTE.this.incubationTime = value; break;
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
        this.chromaColor = nbt.getInt("color");
        this.gemBase = nbt.getString("base");
        this.incubationProgress = nbt.getInt("progress");
        this.incubationTime = nbt.getInt("time");
        this.blockAmounts[0] = nbt.getInt("block1Amount");
        this.blockAmounts[1] = nbt.getInt("block2Amount");
        this.blockAmounts[2] = nbt.getInt("block3Amount");
        this.blockAmounts[3] = nbt.getInt("block4Amount");
        this.weight = nbt.getInt("weight");
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.items);
        gemBase = this.items.get(4).getItem().toString();
        setup();
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putBoolean("block", this.blockConsumed);
        compound.putBoolean("chroma", this.chromaConsumed);
        compound.putBoolean("base", this.baseConsumed);
        compound.putInt("color", this.chromaColor);
        compound.putString("base", this.gemBase);
        compound.putInt("progress", this.incubationProgress);
        compound.putInt("time", this.incubationTime);
        compound.putInt("block1Amount", this.blockAmounts[0]);
        compound.putInt("block2Amount", this.blockAmounts[1]);
        compound.putInt("block3Amount", this.blockAmounts[2]);
        compound.putInt("block4Amount", this.blockAmounts[3]);
        compound.putInt("weight", this.weight);
        ContainerHelper.saveAllItems(compound, this.items);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        BoardTE te = (BoardTE)be;
        if(!level.isClientSide()) {
            /*if (te.incubationProgress != 0) {
                state.setValue(IncubatorBlock.ACTIVE, Boolean.valueOf(true));
            } else {
                state.setValue(IncubatorBlock.ACTIVE, Boolean.valueOf(false));
            }*/
            if (te.getItem(BoardTE.GEM_OUTPUT_SLOT_INDEX).isEmpty()) {
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
            ItemStack stack = this.getItem(BoardTE.CHROMA_INPUT_SLOT_INDEX);
            if (stack.getItem() instanceof ItemChroma chroma) {
                this.chromaConsumed = true;
                this.chromaColor = chroma.color;
            }
        } else {
            ItemStack stack = this.getItem(BoardTE.CHROMA_INPUT_SLOT_INDEX);
            if (!(stack.getItem() instanceof ItemChroma)) {
                this.chromaConsumed = false;
                this.chromaColor = 0;
            }
        }
    }

    public void HandleBaseTick() {
        if (chromaConsumed && !baseConsumed) {
            ItemStack stack = this.getItem(BoardTE.GEM_BASE_INPUT_SLOT_INDEX);
            if (stack.getItem() instanceof ItemGemBase base) {
                this.baseConsumed = true;
                this.gemBase = base.toString();
            }
        } else {
            ItemStack stack = this.getItem(BoardTE.GEM_BASE_INPUT_SLOT_INDEX);
            if (!(stack.getItem() instanceof ItemGemBase)) {
                this.baseConsumed = false;
                this.gemBase = "";
            }
        }
    }

    public void HandleEssenceTick() {
        if (baseConsumed) {
            ItemStack stack = this.getItem(BoardTE.ESSENCE1_INPUT_SLOT_INDEX);
            ItemStack stack2 = this.getItem(BoardTE.ESSENCE2_INPUT_SLOT_INDEX);
            String name = gemBase.toLowerCase().replaceAll("inactive_", "").replaceAll("_base", "");
            int essence1 = essenceRequired.get(name).get(0);
            int essence2 = essenceRequired.get(name).get(1);
            Item bottle1 = essence1 == 1 ? ModItems.PINK_ESSENCE_BUCKET.get() : (essence1 == 2 ? ModItems.YELLOW_ESSENCE_BUCKET.get() : (essence1 == 3 ? ModItems.BLUE_ESSENCE_BUCKET.get() : ModItems.WHITE_ESSENCE_BUCKET.get()));
            Item bottle2 = essence2 == 1 ? ModItems.PINK_ESSENCE_BUCKET.get() : (essence2 == 2 ? ModItems.YELLOW_ESSENCE_BUCKET.get() : (essence2 == 3 ? ModItems.BLUE_ESSENCE_BUCKET.get() : ModItems.WHITE_ESSENCE_BUCKET.get()));
            if (chromaConsumed && !essenceConsumed) {
                if (stack.is(bottle1) && stack2.is(bottle2)) {
                    this.essenceConsumed = true;
                } else if (stack.is(bottle2) && stack2.is(bottle1)) {
                    this.essenceConsumed = true;
                }
            } else {
                if (!stack.is(bottle1) && !stack.is(bottle2)) {
                    this.essenceConsumed = false;
                } else if (!stack2.is(bottle2) && !stack2.is(bottle1)) {
                    this.essenceConsumed = false;
                }
            }
        }
    }

    public void HandleCruxTick() {
        if (baseConsumed && !blockConsumed) {
            weight = 0;
            String name = gemBase.toLowerCase().replaceAll("inactive_", "").replaceAll("_base", "");
            ItemStack stack = this.getItem(BLOCK1_INPUT_SLOT_INDEX);
            ItemStack stack2 = this.getItem(BLOCK2_INPUT_SLOT_INDEX);
            ItemStack stack3 = this.getItem(BLOCK3_INPUT_SLOT_INDEX);
            ItemStack stack4 = this.getItem(BLOCK4_INPUT_SLOT_INDEX);
            if (blocks.get(name).containsKey(stack.getItem())) {
                int value = blocks.get(name).get(stack.getItem());
                weight += stack.getCount() * value;
                blockAmounts[0] = stack.getCount();
            }
            if (blocks.get(name).containsKey(stack2.getItem())) {
                int value = blocks.get(name).get(stack2.getItem());
                weight += stack2.getCount() * value;
                blockAmounts[1] = stack2.getCount();
            }
            if (blocks.get(name).containsKey(stack3.getItem())) {
                int value = blocks.get(name).get(stack3.getItem());
                weight += stack3.getCount() * value;
                blockAmounts[2] = stack3.getCount();
            }
            if (blocks.get(name).containsKey(stack4.getItem())) {
                int value = blocks.get(name).get(stack4.getItem());
                weight += stack4.getCount() * value;
                blockAmounts[3] = stack4.getCount();
            }
            if (primer == 1) weight += 150;
            System.out.println("weight "+weight);
            blockConsumed = blocks.get(name).containsKey(stack.getItem()) &&
                    blocks.get(name).containsKey(stack2.getItem()) &&
                    blocks.get(name).containsKey(stack3.getItem()) &&
                    blocks.get(name).containsKey(stack4.getItem());
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
            ItemStack stack = this.getItem(BoardTE.PRIMER_INPUT_SLOT_INDEX);
            if (stack.is(ModItems.PRIME_BOOST.get())) {
                primer = 1;
            } else if (stack.is(ModItems.SPEED_BOOSTER.get())) {
                primer = 2;
            } else {
                primer = 0;
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

        addon.put("ruby", false);
        addon.put("nephrite", false);
        addon.put("rutile", false);
        addon.put("bismuth", false);
        addon.put("aquamarine", false);
        addon.put("emerald", false);
        addon.put("bixbite", false);
        addon.put("lapis", false);
        addon.put("obsidian", false);
        addon.put("larimar", false);
        addon.put("morganite", false);
        addon.put("peridot", false);
        addon.put("jasper", false);
        addon.put("garnet", false);
        addon.put("quartz", false);
        addon.put("sapphire", false);
        addon.put("agate", false);
        addon.put("spinel", false);
        addon.put("tourmaline", false);
        addon.put("zircon", false);
        addon.put("spodumene", false);
        addon.put("topaz", false);

        //-----------

        prismaticVariant.put("ruby", false);
        prismaticVariant.put("nephrite", false);
        prismaticVariant.put("rutile", false);
        prismaticVariant.put("bismuth", false);
        prismaticVariant.put("aquamarine", false);
        prismaticVariant.put("emerald", false);
        prismaticVariant.put("bixbite", false);
        prismaticVariant.put("lapis", false);
        prismaticVariant.put("obsidian", false);
        prismaticVariant.put("larimar", false);
        prismaticVariant.put("morganite", false);
        prismaticVariant.put("peridot", false);
        prismaticVariant.put("jasper", true);
        prismaticVariant.put("garnet", true);
        prismaticVariant.put("quartz", true);
        prismaticVariant.put("sapphire", false);
        prismaticVariant.put("agate", true);
        prismaticVariant.put("spinel", false);
        prismaticVariant.put("tourmaline", true);
        prismaticVariant.put("zircon", false);
        prismaticVariant.put("spodumene", false);
        prismaticVariant.put("topaz", false);

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
        agate.put(Items.RAW_COPPER_BLOCK, 3);
        agate.put(Items.QUARTZ_BLOCK, 4);

        aquamarine.put(Items.SOUL_SOIL, 1);
        aquamarine.put(Items.PRISMARINE, 3);
        aquamarine.put(Items.GHAST_TEAR, 4);

        bismuth.put(Items.MAGMA_BLOCK, 1);
        bismuth.put(Items.IRON_BLOCK, 3);
        bismuth.put(ModItems.TUNGSTEN_BLOCK.get(), 4);

        bixbite.put(Items.RED_SANDSTONE, 1);
        bixbite.put(Items.BONE_BLOCK, 2);
        bixbite.put(Items.FIRE_CORAL_BLOCK, 3);
        bixbite.put(Items.RAW_GOLD, 4);

        emerald.put(Items.END_STONE, 2);
        emerald.put(Items.EMERALD_BLOCK, 3);
        emerald.put(Items.ANCIENT_DEBRIS, 4);

        garnet.put(Items.NETHERRACK, 1);
        garnet.put(Items.RAW_COPPER, 2);
        garnet.put(Items.BONE_BLOCK, 3);
        garnet.put(Items.SCULK, 4);

        jasper.put(Items.RAW_IRON, 1);
        jasper.put(Items.QUARTZ_BLOCK, 2);
        jasper.put(Items.GILDED_BLACKSTONE, 3);
        jasper.put(Items.BLAZE_ROD, 4);

        lapis.put(Items.CALCITE, 2);
        lapis.put(Items.DARK_PRISMARINE, 3);
        lapis.put(Items.LAPIS_BLOCK, 4);

        larimar.put(Items.SNOW_BLOCK, 1);
        larimar.put(Items.ICE, 2);
        larimar.put(Items.PACKED_ICE, 3);

        morganite.put(Items.SOUL_SAND, 2);
        morganite.put(Items.END_STONE, 3);
        morganite.put(Items.PHANTOM_MEMBRANE, 4);

        nephrite.put(Items.HONEY_BLOCK, 1);
        nephrite.put(Items.SLIME_BLOCK, 2);
        nephrite.put(Items.WITHER_ROSE, 4);

        obsidian.put(Items.OBSIDIAN, 2);
        obsidian.put(Items.CRYING_OBSIDIAN, 4);

        peridot.put(Items.CLAY, 1);
        peridot.put(Items.SMOOTH_BASALT, 2);
        peridot.put(Items.RAW_IRON_BLOCK, 3);
        peridot.put(Items.EXPERIENCE_BOTTLE, 4);

        quartz.put(Items.RAW_IRON, 2);
        quartz.put(Items.QUARTZ_BLOCK, 4);

        ruby.put(Items.NETHERRACK, 1);
        ruby.put(Items.IRON_ORE, 4);

        rutile.put(Items.RAW_COPPER, 1);
        rutile.put(Items.REDSTONE, 2);
        rutile.put(Items.GLOWSTONE_DUST, 3);
        rutile.put(Items.FIRE_CHARGE, 4);

        sapphire.put(Items.BLUE_ICE, 2);
        sapphire.put(Items.ENDER_EYE, 3);
        sapphire.put(Items.DIAMOND_BLOCK, 4);

        spinel.put(Items.SLIME_BLOCK, 1);
        spinel.put(Items.NETHER_WART, 3);
        spinel.put(Items.CHORUS_FLOWER, 4);

        spodumene.put(Items.GLOWSTONE, 2);
        spodumene.put(Items.BONE_BLOCK, 2);
        spodumene.put(Items.BRAIN_CORAL_BLOCK, 4);

        topaz.put(Items.RAW_GOLD_BLOCK, 2);
        topaz.put(Items.GILDED_BLACKSTONE, 4);

        tourmaline.put(Items.MOSS_BLOCK, 1);
        tourmaline.put(Items.HONEYCOMB, 2);
        tourmaline.put(Items.MYCELIUM, 3);

        zircon.put(Items.DEEPSLATE, 1);
        zircon.put(Items.ANCIENT_DEBRIS, 2);
        zircon.put(Items.DIAMOND_BLOCK, 3);
        zircon.put(Items.SCULK, 4);

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

        blockList.add(Items.ANCIENT_DEBRIS);
        blockList.add(Items.BLAZE_ROD);
        blockList.add(Items.BLUE_ICE);
        blockList.add(Items.BONE_BLOCK);
        blockList.add(Items.BRAIN_CORAL_BLOCK);
        blockList.add(Items.CALCITE);
        blockList.add(Items.CHORUS_FLOWER);
        blockList.add(Items.CLAY);
        blockList.add(Items.CRYING_OBSIDIAN);
        blockList.add(Items.DARK_PRISMARINE);
        blockList.add(Items.DEEPSLATE);
        blockList.add(Items.DIAMOND_BLOCK);
        blockList.add(Items.EMERALD_BLOCK);
        blockList.add(Items.ENDER_EYE);
        blockList.add(Items.END_STONE);
        blockList.add(Items.EXPERIENCE_BOTTLE);
        blockList.add(Items.FIRE_CORAL_BLOCK);
        blockList.add(Items.GHAST_TEAR);
        blockList.add(Items.GILDED_BLACKSTONE);
        blockList.add(Items.GLOWSTONE);
        blockList.add(Items.HONEYCOMB);
        blockList.add(Items.HONEY_BLOCK);
        blockList.add(Items.ICE);
        blockList.add(Items.IRON_BLOCK);
        blockList.add(Items.IRON_ORE);
        blockList.add(Items.LAPIS_BLOCK);
        blockList.add(Items.MAGMA_BLOCK);
        blockList.add(Items.MOSS_BLOCK);
        blockList.add(Items.MYCELIUM);
        blockList.add(Items.NETHERRACK);
        blockList.add(Items.NETHER_WART);
        blockList.add(Items.OBSIDIAN);
        blockList.add(Items.PACKED_ICE);
        blockList.add(Items.PHANTOM_MEMBRANE);
        blockList.add(Items.PRISMARINE);
        blockList.add(Items.QUARTZ_BLOCK);
        blockList.add(Items.RAW_COPPER);
        blockList.add(Items.RAW_COPPER_BLOCK);
        blockList.add(Items.RAW_GOLD);
        blockList.add(Items.RAW_GOLD_BLOCK);
        blockList.add(Items.RAW_IRON);
        blockList.add(Items.RAW_IRON_BLOCK);
        blockList.add(Items.RED_SANDSTONE);
        blockList.add(Items.SCULK);
        blockList.add(Items.SLIME_BLOCK);
        blockList.add(Items.SMOOTH_BASALT);
        blockList.add(Items.SNOW_BLOCK);
        blockList.add(Items.SOUL_SAND);
        blockList.add(Items.SOUL_SOIL);
        blockList.add(ModItems.TUNGSTEN_BLOCK.get());
        blockList.add(Items.WITHER_ROSE);
        blockList.add(Items.REDSTONE);
        blockList.add(Items.GLOWSTONE_DUST);
        blockList.add(Items.FIRE_CHARGE);

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

        //------------

        colour.putAll(AddonHandler.colour);
        blockList.addAll(AddonHandler.blockList);
        time.putAll(AddonHandler.time);
        blocks.putAll(AddonHandler.blocks);
        essenceRequired.putAll(AddonHandler.essenceRequired);
        for (String s : AddonHandler.addon.keySet()) {
            addon.put(s, true);
        }
        prismaticVariant.putAll(AddonHandler.prismaticVariant);
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
        ItemStack stack = this.getItem(BoardTE.PRIMER_INPUT_SLOT_INDEX);
        ItemStack bstack = this.getItem(BoardTE.BLOCK1_INPUT_SLOT_INDEX);
        ItemStack bstack2 = this.getItem(BoardTE.BLOCK2_INPUT_SLOT_INDEX);
        ItemStack bstack3 = this.getItem(BoardTE.BLOCK3_INPUT_SLOT_INDEX);
        ItemStack bstack4 = this.getItem(BoardTE.BLOCK4_INPUT_SLOT_INDEX);
        if (baseConsumed && chromaConsumed && essenceConsumed && blockConsumed) {
            if (primer != 0 && stack.isEmpty() ||
                    (bstack.getCount() != blockAmounts[0] ||
                    bstack2.getCount() != blockAmounts[1] ||
                    bstack3.getCount() != blockAmounts[2] ||
                    bstack4.getCount() != blockAmounts[3])) {
                    primer = 0;
                    incubationProgress = 0;
                    incubationTime = 0;
                    blockAmounts[0] = 0;
                    blockAmounts[1] = 0;
                    blockAmounts[2] = 0;
                    blockAmounts[3] = 0;
                    weight = 0;
                    this.blockConsumed = false;
                    this.chromaConsumed = false;
                    this.baseConsumed = false;
                    this.chromaColor = 0;
            }
            incubationTime = time.get(gemBase.toLowerCase().replaceAll("inactive_", "").replaceAll("_base", ""));
            if (primer == 2) incubationTime = incubationTime/2;
            if (incubationProgress < incubationTime) {
                incubationProgress++;
                System.out.println(incubationProgress);
            } else {
                incubationProgress = 0;
                incubationTime = 0;
                if (weight >= 800) {
                    this.formGem(this.chromaColor, 0);
                } else if (weight <= 400) {
                    this.formGem(this.chromaColor, 2);
                } else {
                    this.formGem(this.chromaColor, 1);
                }
            }
        } else {
            incubationProgress = 0;
            incubationTime = 0;
            blockAmounts[0] = 0;
            blockAmounts[1] = 0;
            blockAmounts[2] = 0;
            blockAmounts[3] = 0;
            weight = 0;
            this.blockConsumed = false;
            this.chromaConsumed = false;
            this.primer = 0;
            this.baseConsumed = false;
            this.chromaColor = 0;
        }
    }

    //perfect = 0
    //normal = 1
    //defect = 2
    public void formGem(int chroma, int quality){
        this.getItem(BoardTE.ESSENCE1_INPUT_SLOT_INDEX).shrink(1);
        this.getItem(BoardTE.ESSENCE2_INPUT_SLOT_INDEX).shrink(1);
        this.setItem(BoardTE.ESSENCE1_INPUT_SLOT_INDEX, Items.BUCKET.getDefaultInstance());
        this.setItem(BoardTE.ESSENCE2_INPUT_SLOT_INDEX, Items.BUCKET.getDefaultInstance());
        this.getItem(BoardTE.CHROMA_INPUT_SLOT_INDEX).shrink(1);
        this.getItem(BoardTE.GEM_BASE_INPUT_SLOT_INDEX).shrink(1);
        this.getItem(BoardTE.PRIMER_INPUT_SLOT_INDEX).shrink(1);
        this.getItem(BoardTE.BLOCK1_INPUT_SLOT_INDEX).shrink(blockAmounts[0]);
        this.getItem(BoardTE.BLOCK2_INPUT_SLOT_INDEX).shrink(blockAmounts[1]);
        this.getItem(BoardTE.BLOCK3_INPUT_SLOT_INDEX).shrink(blockAmounts[2]);
        this.getItem(BoardTE.BLOCK4_INPUT_SLOT_INDEX).shrink(blockAmounts[3]);
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
        boolean addonGem = addon.get(baseName.toLowerCase());
        boolean coloured = false;
        boolean special = prismaticVariant.get(baseName.toLowerCase());
        if (colour.get(baseName.toLowerCase())) {
            if (chroma >= 16 && special) {
                name = "SPECIAL_"+baseName+"_GEM";
                coloured = true;
            } else {
                if (chroma >= 16) {
                    Random r = new Random();
                    chroma = r.nextInt(16);
                    chromaColor = chroma;
                    System.out.println("Colour "+Color.getColorName(chroma));
                }
                name = Color.getColorName(chroma).toUpperCase() + "_" + baseName + "_GEM";
                coloured = true;
            }
        } else {
            name = baseName + "_GEM";
        }
        if (baseName.toLowerCase().contains("quartz") && chromaColor == 16) {
            Random r = new Random();
            int o = r.nextInt(2);
            if (o == 0) {
                chromaColor = 17;
            }
            System.out.println(o);
            System.out.println(chromaColor);
        }
        RegistryObject<EntityType<EntityPebble>> egemm = ModEntities.PEBBLE;
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
        if (array.length > 1 && coloured) skinColorVariant = array[0];
        for (String s : array) {
            System.out.println(s);
        }
        if (array.length >= 4 && coloured) skinColorVariant = array[0] + "_" + array[1];
        System.out.println("skin variant string " +skinColorVariant);
        System.out.println("array "+ Arrays.toString(array));
        System.out.println("name "+name);
        try {
            if (!addonGem) {
                egemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(name.toUpperCase().replaceAll("GEM", "").replaceAll(skinColorVariant, "").replaceAll("_", "")).get(null);
            } else {
                egemm = (RegistryObject<EntityType<EntityPebble>>) AddonHandler.ENTITY_ADDON_ENTITY_REGISTRIES.get(baseName.toLowerCase()).getField(baseName).get(null);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        EntityGem egem = egemm.get().create(this.level);
        if (egem instanceof EntityVaryingGem) {
            if (((EntityVaryingGem) egem).UsesUniqueNames() && !(egem instanceof EntitySapphire)) {
                String uniqueColor = Component.translatable("nickname.gempire." + egem.getWholeGemName() + "_" + chromaColor).getString();
                System.out.println(uniqueColor.toLowerCase());
                System.out.println(egem.getWholeGemName());
                //.replaceAll(" "+egem.getWholeGemName(), "")
                //uniqueColor = uniqueColor.replaceAll(" ", "_");
                if (!(uniqueColor.toLowerCase().contains("quartz") || uniqueColor.toLowerCase().contains("blue spodumene")))
                    uniqueColor = uniqueColor.toLowerCase().replaceAll(" "+egem.getWholeGemName(), "").replaceAll(" ", "_").replaceAll("'", "");
                else uniqueColor = uniqueColor.toLowerCase().replaceAll(" ", "_").replaceAll("'", "");
                System.out.println(uniqueColor);
                name = name.replaceAll(skinColorVariant.toUpperCase(), uniqueColor.toUpperCase());
                System.out.println("name "+ name);
            }
        }
        try {
            if (!addonGem) {
                gemm = (RegistryObject<Item>) ModItems.class.getField(name.toUpperCase()).get(null);
                gem = (ItemGem) gemm.get();
            } else {
                gemm = (RegistryObject<Item>) AddonHandler.ENTITY_ADDON_ITEM_REGISTRIES.get(baseName.toLowerCase()).getField(name.toUpperCase()).get(null);
                gem = (ItemGem) gemm.get();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        if (array.length > 1 && coloured) {
            assert gem != null;
            egem.setSkinVariantOnInitialSpawn = false;
            egem.initalSkinVariant = chromaColor;
        }
        System.out.println("skin variant "+egem.getSkinColorVariant());
        //egem.setSkinColorVariant(egem.initalSkinVariant);
        System.out.println(name);
        egem.setUUID(Mth.createInsecureUUID(this.level.random));
        //egem.setSkinColorVariant(chroma);
        CompoundTag tag = new CompoundTag();
        /*if (quality == 0) {
            tag.putBoolean("prime", true);
            egem.setPrimary(true);
            egem.setDefective(false);
            egem.addAdditionalSaveData(tag);
        } else if (quality == 2) {
            tag.putBoolean("defective", true);
            egem.setPrimary(false);
            egem.setDefective(true);
            egem.addAdditionalSaveData(tag);
        }*/
        egem.finalizeSpawn((ServerLevelAccessor) this.level, this.level.getCurrentDifficultyAt(this.worldPosition), MobSpawnType.MOB_SUMMONED, null, null);
        ItemStack stack = new ItemStack(gem);
        ItemGem.saveData(stack, egem);
        egem.remove(Entity.RemovalReason.DISCARDED);
        if (quality == 0) {
            stack.getOrCreateTag().putBoolean("prime", true);
        } else if (quality == 2) {
            stack.getOrCreateTag().putBoolean("defective", true);
        }
        this.setItem(BoardTE.GEM_OUTPUT_SLOT_INDEX, stack);
        primer = 0;
        incubationProgress = 0;
        incubationTime = 0;
        blockAmounts[0] = 0;
        blockAmounts[1] = 0;
        blockAmounts[2] = 0;
        blockAmounts[3] = 0;
        weight = 0;
        this.blockConsumed = false;
        this.chromaConsumed = false;
        this.baseConsumed = false;
        this.chromaColor = 0;
    }

    //CONTAINER STUFF

    /*LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
            net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (int x = 0; x < handlers.length; x++)
            handlers[x].invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }*/

    @Override
    public Component getDisplayName() {
        return Component.translatable("");//TranslationTextComponent("container.gempire.injector");
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.gempire.incubator");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new BoardContainer(id, player, this, this.data);
    }

    @Override
    public int getContainerSize() {
        return BoardTE.NUMBER_OF_SLOTS;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int i) {
        return items.get(i);
    }

    @Override
    public ItemStack removeItem(int i, int u) {
        return ContainerHelper.removeItem(this.items, i, u);
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return ContainerHelper.takeItem(this.items, i);
    }

    @Override
    public void setItem(int i, ItemStack stack) {
        items.set(i, stack);
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    public ItemStack getGemItem(){
        return this.getItem(BoardTE.GEM_OUTPUT_SLOT_INDEX);
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

    /*
    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction == Direction.DOWN) {
            return new int[]{9};
        } else if (direction == Direction.UP){
            return new int[]{0, 1, 2, 3, 5, 4, 6, 7, 8};
        }
        return new int[0];
        //this.getBlockState().getValue(Direction)
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack stack, @Nullable Direction direction) {
        if (direction == Direction.UP) {
            if (i == 4) {
                return stack.getItem() instanceof ItemGemBase;
            }
            if (i == 1) {
                return stack.getItem() == ModItems.SPEED_BOOSTER.get() ||
                        stack.getItem() == ModItems.PRIME_BOOST.get();
            }
            if (i == 6 || i == 8) {
                return stack.getItem() == ModItems.PINK_ESSENCE_BUCKET.get() ||
                        stack.getItem() == ModItems.YELLOW_ESSENCE_BUCKET.get() ||
                        stack.getItem() == ModItems.BLUE_ESSENCE_BUCKET.get() ||
                        stack.getItem() == ModItems.WHITE_ESSENCE_BUCKET.get();
            }
            if (i == 7) {
                return stack.getItem() instanceof ItemChroma;
            }
            if (i == 0 || i == 2 || i == 3 || i == 5) {
                return blockList.contains(stack.getItem());
            }
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack stack, Direction direction) {
        return direction == Direction.DOWN && i == 9;
        //return false;
    }*/

    @Override
    public void clearContent() {
        this.items.clear();
    }
}
