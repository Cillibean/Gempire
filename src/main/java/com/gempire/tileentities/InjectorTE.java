package com.gempire.tileentities;

import com.gempire.blocks.GemSeedBlock;
import com.gempire.blocks.machine.PowerCrystalBlock;
import com.gempire.blocks.machine.TankBlock;
import com.gempire.container.InjectorContainer;
import com.gempire.events.InjectEvent;
import com.gempire.init.*;
import com.gempire.items.ItemChroma;
import com.gempire.util.GemInfo;
import com.gempire.util.InjectionRegistry;
import com.gempire.util.GemSeedInfo;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class InjectorTE extends RandomizableContainerBlockEntity implements IFluidTank, MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    public static final int NUMBER_OF_SLOTS = 6;
    public static final int PINK_INPUT_SLOT_INDEX = 0;
    public static final int BLUE_INPUT_SLOT_INDEX = 1;
    public static final int YELLOW_INPUT_SLOT_INDEX = 2;
    public static final int WHITE_INPUT_SLOT_INDEX = 3;
    public static final int CHROMA_INPUT_SLOT_INDEX = 4;
    public static final int PRIME_INPUT_SLOT_INDEX = 5;

    public int TANK_CAPACITY() {
        return 1000;
    }

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;

    public boolean tick = false;

    public NonNullList<ItemStack> items = NonNullList.withSize(InjectorTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public FluidTank pinkTank, blueTank, yellowTank, whiteTank;

    public Block drained_sand, drained_soil, drained_stone, drained_stone_2, banded_drained_stone, drained_log, drained_log_cracked, drained_ice;

    public boolean pinkOpen, blueOpen, yellowOpen, whiteOpen, invalid = false;
    public int ticks = 0;

    public static GemSeedInfo info;
    public HashMap<Block, ArrayList<Integer>> resMap = new HashMap<>();
    public HashMap<Block, Float> qualityMap = new HashMap<>();
    public ArrayList<GemInfo> gemInfoList = new ArrayList<>();

    public InjectorTE(BlockPos pos, BlockState state) {
        super(ModTE.INJECTOR_TE.get(), pos, state);
        this.pinkTank = new FluidTank(this.TANK_CAPACITY());
        this.blueTank = new FluidTank(this.TANK_CAPACITY());
        this.yellowTank = new FluidTank(this.TANK_CAPACITY());
        this.whiteTank = new FluidTank(this.TANK_CAPACITY());
        this.data = new ContainerData() {
            @Override
            public int get(int p_39284_) {
                return 0;
            }

            @Override
            public void set(int p_39285_, int p_39286_) {

            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        this.pinkTank.readFromNBT(nbt.getCompound("pinkTank"));
        this.blueTank.readFromNBT(nbt.getCompound("blueTank"));
        this.yellowTank.readFromNBT(nbt.getCompound("yellowTank"));
        this.whiteTank.readFromNBT(nbt.getCompound("whiteTank"));
        this.pinkOpen = nbt.getBoolean("pinkOpen");
        this.blueOpen = nbt.getBoolean("blueOpen");
        this.yellowOpen = nbt.getBoolean("yellowOpen");
        this.whiteOpen = nbt.getBoolean("whiteOpen");
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        InjectionRegistry.setMap();
        gemInfoList = InjectionRegistry.list;
        super.load(nbt);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        compound.put("pinkTank", this.pinkTank.writeToNBT(new CompoundTag()));
        compound.put("blueTank", this.blueTank.writeToNBT(new CompoundTag()));
        compound.put("yellowTank", this.yellowTank.writeToNBT(new CompoundTag()));
        compound.put("whiteTank", this.whiteTank.writeToNBT(new CompoundTag()));
        compound.putBoolean("pinkOpen", this.pinkOpen);
        compound.putBoolean("blueOpen", this.blueOpen);
        compound.putBoolean("yellowOpen", this.yellowOpen);
        compound.putBoolean("whiteOpen", this.whiteOpen);
        compound.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(compound);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        InjectorTE te = (InjectorTE) be;
        if (!level.isClientSide()) {
            te.HandleSlotUpdates();
        }
        te.ticks++;
        if (te.ticks > 15) {
            te.ticks = 0;
        }
    }

    public void HandleSlotUpdates() {
        for (int i = 0; i < 4; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (this.shouldPullFluidFromStack(i)) {
                if (stack.getItem() != Items.AIR) {
                    if (stack.getItem() instanceof BucketItem bucket) {
                        if (this.isValidForSlot(i, bucket)) {
                            this.FillFluidTanks(i, 1000);
                            itemHandler.setStackInSlot(i, new ItemStack(Items.BUCKET.asItem()));
                            assert this.level != null;
                            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
                            this.setChanged();
                        }
                    }
                }
            }
        }
    }

    //INJECTOR FUNCTIONALITY

    //INJECTOR FUNCTIONALITY

    public void inject() {
        info = new GemSeedInfo(new int[6], 0, 0, 0);
        for (int i = 0; i < 4; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (stack.getItem() != Items.AIR) {
                if (stack.getItem() instanceof BucketItem bucket) {
                    if (this.isValidForSlot(i, bucket)) {
                        info.resources[i] += 10;
                    }
                }
            }
        }
        ItemStack chromaStack = itemHandler.getStackInSlot(CHROMA_INPUT_SLOT_INDEX);
        info.setChroma(((ItemChroma) chromaStack.getItem()).color);

        // grow

        BlockPos crystalPos = getBlockPos().above().above();
        BlockPos seedPos = this.getBlockPos().offset(new BlockPos(0, (int) (-Math.ceil(11 / 2) - 1 - 1), 0));
        BlockPos cornerPos = seedPos.offset(-5, -5, -5);
        HashMap<BlockPos, Boolean> map = new HashMap<>();
        info.temp = level.getBiome(seedPos).get().getBaseTemperature();
        for (int x = 0; x <= 10; x++) {
            for (int y = 0; y <= 10; y++) {
                for (int z = 0; z <= 10; z++) {
                    map.put(new BlockPos(cornerPos.offset(x, y, z)), false);
                }
            }
        }
        if (level.getBlockState(crystalPos).getBlock() instanceof PowerCrystalBlock && this.level.getBlockState(seedPos) != Blocks.BEDROCK.defaultBlockState() && this.level.getBlockState(seedPos) != Blocks.AIR.defaultBlockState() && this.level.getBlockState(seedPos) != Blocks.WATER.defaultBlockState() ) {
            int blocksDrained = 0;
            while (blocksDrained < 200) {
                if (ticks <= 15) {
                    Random r = new Random();
                    BlockPos pos = cornerPos.offset(r.nextInt(11), r.nextInt(11), r.nextInt(11));
                    Block block = level.getBlockState(pos).getBlock();
                    if (!resMap.isEmpty()) {
                        if (!resMap.get(block).isEmpty()) {
                            info.resources[0] += resMap.get(block).get(0);
                            info.resources[1] += resMap.get(block).get(1);
                            info.resources[2] += resMap.get(block).get(2);
                            info.resources[3] += resMap.get(block).get(3);
                            info.resources[4] += resMap.get(block).get(4);
                            info.resources[5] += resMap.get(block).get(5);
                        }
                        if (qualityMap.get(block) != 0 && qualityMap.get(block) != null) {
                            info.quality += qualityMap.get(block);
                        }
                    }
                    drainBlock(pos);
                    blocksDrained++;
                }
            }
        }

        //weigh

        ArrayList<GemInfo> possibleResults = new ArrayList<>();
        System.out.println(Arrays.toString(info.resources));
        for (int i = 0; i < gemInfoList.size(); i++) {
            GemInfo gemInfo = gemInfoList.get(i);
            int distance = 0;
            int[] res = gemInfo.getResources();
            System.out.println(gemInfo.getName());
            System.out.println(Arrays.toString(res));
            for (int a = 0; a < res.length; a++) {
                if (res[a] > info.resources[a]) distance += (res[a] - info.resources[a]);
                else distance += (info.resources[a] - res[a]);
            }
            if (distance < 50) {
                System.out.println("possible gem " + distance);
                for (int b = 0; b < 50 - distance; b++) {
                    possibleResults.add(gemInfo);
                }
            }
        }

        //create gem

        if (!possibleResults.isEmpty()) {
            Random r = new Random();
            GemInfo gem = possibleResults.get(r.nextInt(possibleResults.size()));
            System.out.println(gem.getName() + " " + info.quality);
            Item primer = itemHandler.getStackInSlot(PRIME_INPUT_SLOT_INDEX).getItem();
            GemSeedBlock seedBlock = (GemSeedBlock) ModBlocks.GEM_SEED_BLOCK.get();
            this.level.setBlockAndUpdate(seedPos, seedBlock.defaultBlockState());
            if (this.level.getBlockState(seedPos).getBlock() == ModBlocks.GEM_SEED_BLOCK.get()) {
                this.getLevel().playSound(null, this.getBlockPos(), ModSounds.INJECT.get(), SoundSource.AMBIENT, 2f, 1);
            }
            GemSeedTE gemSeedTE = (GemSeedTE) this.level.getBlockEntity(seedPos);
            if (gemSeedTE != null) {
                gemSeedTE.SetPrimer(primer);
                int facing = InjectorTE.getFacingFromState(this.getBlockState());
                gemSeedTE.setFacing(facing);
                gemSeedTE.setChroma(info.chroma);
                gemSeedTE.setInfo(gem);
                System.out.println("Facing :" + facing);
                itemHandler.extractItem(InjectorTE.CHROMA_INPUT_SLOT_INDEX, 1, false);
                itemHandler.extractItem(InjectorTE.PRIME_INPUT_SLOT_INDEX, 1, false);
                this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
                this.setChanged();
                InjectEvent event = new InjectEvent(gemSeedTE, seedPos);
                MinecraftForge.EVENT_BUS.post(event);
            }
        }
    }

    public void drainBlock(BlockPos blockPos) {
        System.out.println("drain "+blockPos);
        float BLOCK_TEMPERATURE = this.level.getBiome(this.getBlockPos()).get().getBaseTemperature();
        this.setDrainedStoneColor(BLOCK_TEMPERATURE);
        Block block = this.level.getBlockState(blockPos).getBlock();
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
                !(block instanceof PointedDripstoneBlock) &&
                !(block == ModBlocks.DRILL_BLOCK.get()) &&
                !(block == ModBlocks.DRAINED_ICE.get()) &&
                !(block == ModBlocks.DRAINED_LOG_CRACKED.get()) &&
                !(block == ModBlocks.DRAINED_LOG.get()) &&
                !(block == ModBlocks.PEDISTAL.get())) {
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
            } else if (block == Blocks.BLUE_ICE || block == Blocks.PACKED_ICE || block == Blocks.ICE) {
                this.level.setBlockAndUpdate(blockPos, this.drained_ice.defaultBlockState());
            } else if (block == Blocks.VINE ||block == Blocks.CAVE_VINES || block == Blocks.CAVE_VINES_PLANT || block == Blocks.OAK_LEAVES || block == Blocks.DARK_OAK_LEAVES
                    || block == Blocks.BIRCH_LEAVES || block == Blocks.JUNGLE_LEAVES || block == Blocks.ACACIA_LEAVES || block == Blocks.MANGROVE_LEAVES || block == Blocks.AZALEA_LEAVES
                    || block == Blocks.FLOWERING_AZALEA_LEAVES || block == Blocks.SPRUCE_LEAVES) {
                this.level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
            } else if (block == Blocks.SNOW_BLOCK || block == Blocks.POWDER_SNOW) {
                this.level.setBlockAndUpdate(blockPos, Blocks.WATER.defaultBlockState());
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

    public void setDrainedStoneColor(float temperature){
        if(temperature > .1f && temperature <= .5F){
            this.drained_sand = ModBlocks.DRAINED_GREY_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_GREY_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_GREY_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_GREY_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_GREY_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
        else if(temperature > .5f && temperature <= .9f){
            this.drained_sand = ModBlocks.DRAINED_PURPLE_SAND.get();
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
            this.drained_sand = ModBlocks.DRAINED_BLUE_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_BLUE_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_BLUE_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_BLUE_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_BLUE_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
    }

    public void DumpFluids() {
        if (this.pinkOpen) {
            FluidTank tank = this.getTankFromValue(0);
            tank.setFluid(FluidStack.EMPTY);
        }
        if (this.blueOpen) {
            FluidTank tank = this.getTankFromValue(1);
            tank.setFluid(FluidStack.EMPTY);
        }
        if (this.yellowOpen) {
            FluidTank tank = this.getTankFromValue(2);
            tank.setFluid(FluidStack.EMPTY);
        }
        if (this.whiteOpen) {
            FluidTank tank = this.getTankFromValue(3);
            tank.setFluid(FluidStack.EMPTY);
        }
        assert this.level != null;
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public boolean fluidValid() {
        if (pinkOpen) {
            if (!this.blueOpen && !this.yellowOpen && !this.whiteOpen) {
                return this.getFluidFromValue(0) != Fluids.EMPTY;
            } else if (this.blueOpen && !this.yellowOpen && !this.whiteOpen) {
                return this.getFluidFromValue(0) != Fluids.EMPTY && this.getFluidFromValue(1) != Fluids.EMPTY;
            } else if (this.yellowOpen && !this.blueOpen && !this.whiteOpen) {
                return this.getFluidFromValue(0) != Fluids.EMPTY && this.getFluidFromValue(2) != Fluids.EMPTY;
            } else if (this.whiteOpen && !this.blueOpen && !this.yellowOpen) {
                return this.getFluidFromValue(0) != Fluids.EMPTY && this.getFluidFromValue(3) != Fluids.EMPTY;
            } else if (this.blueOpen && this.yellowOpen && !this.whiteOpen) {
                return this.getFluidFromValue(0) != Fluids.EMPTY && this.getFluidFromValue(1) != Fluids.EMPTY && this.getFluidFromValue(2) != Fluids.EMPTY;
            } else if (this.blueOpen && !this.yellowOpen) {
                return this.getFluidFromValue(0) != Fluids.EMPTY && this.getFluidFromValue(1) != Fluids.EMPTY && this.getFluidFromValue(3) != Fluids.EMPTY;
            } else if (!this.blueOpen) {
                return this.getFluidFromValue(0) != Fluids.EMPTY && this.getFluidFromValue(2) != Fluids.EMPTY && this.getFluidFromValue(3) != Fluids.EMPTY;
            } else {
                return this.getFluidFromValue(0) != Fluids.EMPTY && this.getFluidFromValue(1) != Fluids.EMPTY && this.getFluidFromValue(2) != Fluids.EMPTY && this.getFluidFromValue(3) != Fluids.EMPTY;
            }
        }
        if (blueOpen) {
            if (!this.yellowOpen && !this.whiteOpen) {
                return this.getFluidFromValue(1) != Fluids.EMPTY;
            } else if (this.yellowOpen && !this.whiteOpen) {
                return this.getFluidFromValue(1) != Fluids.EMPTY && this.getFluidFromValue(2) != Fluids.EMPTY;
            } else if (!this.yellowOpen) {
                return this.getFluidFromValue(1) != Fluids.EMPTY && this.getFluidFromValue(3) != Fluids.EMPTY;
            } else {
                return this.getFluidFromValue(1) != Fluids.EMPTY && this.getFluidFromValue(2) != Fluids.EMPTY && this.getFluidFromValue(3) != Fluids.EMPTY;
            }
        }
        if (yellowOpen) {
            if (!this.whiteOpen) {
                return this.getFluidFromValue(2) != Fluids.EMPTY;
            } else {
                return this.getFluidFromValue(2) != Fluids.EMPTY && this.getFluidFromValue(3) != Fluids.EMPTY;
            }
        }
        if (whiteOpen) {
            return this.getFluidFromValue(3) != Fluids.EMPTY;
        } else {
            return false;
        }
    }

    public boolean getValid() {
        return !invalid;
    }


    public void Inject() {
        if (getValid()) {
            invalid = true;
            /*BlockPos crystalPos = getBlockPos().above().above();
            BlockPos seedPos = this.getBlockPos().offset(new BlockPos(0, (int) (-Math.ceil(GemSeedTE.DRAIN_SIZE / 2) - 1 - 1), 0));
            if (level.getBlockState(crystalPos).getBlock() instanceof PowerCrystalBlock) {
                if (this.level.getBlockState(seedPos) != Blocks.BEDROCK.defaultBlockState()) {
                    if (itemHandler.getStackInSlot(CHROMA_INPUT_SLOT_INDEX).getItem() instanceof ItemChroma chroma) {
                        int[] resources = new int[4];
                        float quality = 0;
                        GemSeedInfo info = new GemSeedInfo(resources, quality, chroma.color);

                    }
                        int portionToDrain = 0;
                        if (this.pinkOpen) {
                            portionToDrain++;
                        }
                        if (this.blueOpen) {
                            portionToDrain++;
                        }
                        if (this.yellowOpen) {
                            portionToDrain++;
                        }
                        if (this.whiteOpen) {
                            portionToDrain++;
                        }
                        String essences = "";
                        for (int i = 0; i < 4; i++) {
                            String essenceName = "";
                            FluidTank tank = this.getTankFromValue(i);
                            if (i == 0 && this.pinkOpen) {
                                essenceName = "pink";
                            } else if (i == 1 && this.blueOpen) {
                                essenceName = "blue";
                            } else if (i == 2 && this.yellowOpen) {
                                essenceName = "yellow";
                            } else if (i == 3 && this.whiteOpen) {
                                essenceName = "white";
                            }
                            if (!essenceName.isEmpty()) {
                                if (tank.getFluid() != FluidStack.EMPTY && !tank.isEmpty()) {
                                    if (!essences.isEmpty()) {
                                        essences += "-";
                                    }
                                    essences += essenceName;
                                    tank.getFluid().setAmount(Math.max(tank.getFluidAmount() - (200 / portionToDrain), 0));
                                }
                            }
                        }
                        while (this.level.getBlockState(seedPos) == Blocks.AIR.defaultBlockState() ||
                                this.level.getBlockState(seedPos).getBlock() instanceof LiquidBlock ||
                                this.level.getBlockState(seedPos) == ModBlocks.GEM_SEED_BLOCK.get().defaultBlockState()) {
                            seedPos = seedPos.offset(0, -GemSeedTE.DRAIN_SIZE, 0);
                        }
                        Item primer = itemHandler.getStackInSlot(PRIME_INPUT_SLOT_INDEX).getItem();
                        GemSeedBlock seedBlock = (GemSeedBlock) ModBlocks.GEM_SEED_BLOCK.get();
                        this.level.setBlockAndUpdate(seedPos, seedBlock.defaultBlockState());
                        if (this.level.getBlockState(seedPos).getBlock() == ModBlocks.GEM_SEED_BLOCK.get()) {
                            this.getLevel().playSound(null, this.getBlockPos(), ModSounds.INJECT.get(), SoundSource.AMBIENT, 2f, 1);
                        }
                        GemSeedTE gemSeedTE = (GemSeedTE) this.level.getBlockEntity(seedPos);
                        if (gemSeedTE != null) {
                            gemSeedTE.setEssences(essences);
                            //gemSeedTE.SetChroma(chroma);
                            gemSeedTE.SetPrimer(primer);
                            if (level.getBlockState(crystalPos).getBlock() == ModBlocks.POWER_CRYSTAL_BLOCK.get()) {
                                gemSeedTE.setTier(1);
                            } else if (level.getBlockState(crystalPos).getBlock() == ModBlocks.POWER_CRYSTAL_BLOCK_TIER_2.get()) {
                                gemSeedTE.setTier(2);
                            }
                            int facing = InjectorTE.getFacingFromState(this.getBlockState());
                            gemSeedTE.setFacing(facing);
                            System.out.println("Facing :" + facing);
                            itemHandler.extractItem(InjectorTE.CHROMA_INPUT_SLOT_INDEX, 1, false);
                            itemHandler.extractItem(InjectorTE.PRIME_INPUT_SLOT_INDEX, 1, false);
                            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
                            this.setChanged();
                            InjectEvent event = new InjectEvent(gemSeedTE, seedPos);
                            MinecraftForge.EVENT_BUS.post(event);
                        }
                    }
                }*/
            if (!level.isClientSide) inject();
            } else {
            invalid = false;
        }
        }

    public static int getFacingFromState(BlockState state){
        if(state.getValue(TankBlock.FACING) == Direction.EAST){
            return 0;
        }
        else if(state.getValue(TankBlock.FACING) == Direction.NORTH){
            return 1;
        }
        else if(state.getValue(TankBlock.FACING) == Direction.WEST){
            return 2;
        }
        else if(state.getValue(TankBlock.FACING) == Direction.SOUTH){
            return 3;
        }
        else{
            return -1;
        }
    }

    public boolean shouldPullFluidFromStack(int tank){
        return this.getTankFromValue(tank).getFluid().getAmount() < this.getCapacity();
    }

    public boolean isValidForSlot(int slot, BucketItem bucket){
        if(slot == InjectorTE.PINK_INPUT_SLOT_INDEX){
            return bucket == ModItems.PINK_ESSENCE_BUCKET.get();
        }
        else if(slot == InjectorTE.BLUE_INPUT_SLOT_INDEX){
            return bucket == ModItems.BLUE_ESSENCE_BUCKET.get();
        }
        else if(slot == InjectorTE.YELLOW_INPUT_SLOT_INDEX){
            return bucket == ModItems.YELLOW_ESSENCE_BUCKET.get();
        }
        else if(slot == InjectorTE.WHITE_INPUT_SLOT_INDEX){
            return bucket == ModItems.WHITE_ESSENCE_BUCKET.get();
        }
        else return slot == InjectorTE.PRIME_INPUT_SLOT_INDEX;
    }

    public void ToggleTankOpen(String color){
        switch (color) {
            case "blue" -> this.blueOpen = !this.blueOpen;
            case "yellow" -> this.yellowOpen = !this.yellowOpen;
            case "white" -> this.whiteOpen = !this.whiteOpen;
            default -> this.pinkOpen = !this.pinkOpen;
        }
        assert this.level != null;
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    //CONTAINER STUFF

    //CONTAINER STUFF

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("");//TranslationTextComponent("container.gempire.injector");
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.gempire.injector");
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return new InjectorContainer(p_58627_, p_58628_,this);
    }

    @Override
    public int getContainerSize() {
        return InjectorTE.NUMBER_OF_SLOTS;
    }

    // FLUID STUFF

    // FLUID STUFF

    @Override
    public int getCapacity() {
        return this.TANK_CAPACITY();
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }
    public int FillFluidTanks(int value, int amount){
        FluidTank tank = this.getTankFromValue(value);
        if(tank.getFluidAmount() >= tank.getCapacity()) {
            return 0;
        }
        else{
            if(tank.getFluid().getFluid() == Fluids.EMPTY){
                tank.setFluid(new FluidStack(this.getFluidFromValue(value), amount));
                return amount;
            }
            else{
                tank.getFluid();
                if(this.getFluidFromValue(value) == tank.getFluid().getFluid()){
                    if(tank.getFluidAmount() + amount <= tank.getCapacity())
                    {
                        tank.getFluid().setAmount(tank.getFluidAmount() + amount);
                        return amount;
                    }
                    else{
                        tank.getFluid().setAmount(tank.getCapacity());
                        return tank.getCapacity() - tank.getFluidAmount();
                    }
                }
                else{
                    return 0;
                }
            }
        }
    }

    public FluidTank getTankFromFluid(FluidStack fluidStack){
        if(fluidStack.getFluid() == ModFluids.PINK_ESSENCE.get()){
            return this.pinkTank;
        }
        else if(fluidStack.getFluid() == ModFluids.BLUE_ESSENCE.get()){
            return this.blueTank;
        }
        else if(fluidStack.getFluid() == ModFluids.YELLOW_ESSENCE.get()){
            return this.yellowTank;
        }
        else if(fluidStack.getFluid() == ModFluids.WHITE_ESSENCE.get()){
            return this.whiteTank;
        }
        else{
            return null;
        }
    }

    public FluidTank getTankFromValue(int value){
        if(value == 0){
            return this.pinkTank;
        }
        else if(value == 1){
            return this.blueTank;
        }
        else if(value == 2){
            return this.yellowTank;
        }
        else if(value == 3){
            return this.whiteTank;
        }
        else{
            return null;
        }
    }

    public Fluid getFluidFromValue(int value){
        if(value == 0){
            return ModFluids.PINK_ESSENCE.get();
        }
        else if(value == 1){
            return ModFluids.BLUE_ESSENCE.get();
        }
        else if(value == 2){
            return ModFluids.YELLOW_ESSENCE.get();
        }
        else if(value == 3){
            return ModFluids.WHITE_ESSENCE.get();
        }
        else{
            return Fluids.EMPTY;
        }
    }

    public FluidStack getFluid(FluidStack stack) {
        return this.getTankFromFluid(stack).getFluid();
    }

    public int getFluidAmount(FluidStack stack) {
        return this.getTankFromFluid(stack).getFluid().getAmount();
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        int amountAfterDrain = Math.max(this.getTankFromFluid(resource).getFluidAmount() - resource.getAmount(), 0);
        this.getFluid(resource).setAmount(amountAfterDrain);
        return this.getFluid(resource);
    }

    //NETWORKING STUFF

    //NETWORKING STUFF

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

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        System.out.println("[DEBUG]:Handling tag on chunk load");
        this.load(tag);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    //REDUNDANT STUFF

    //REDUNDANT STUFF

    @Nonnull
    @Override
    public FluidStack getFluid() {
        return FluidStack.EMPTY;
    }

    @Override
    public int getFluidAmount() {
        return 0;
    }
    @Override
    public boolean isFluidValid(FluidStack stack) {
        return false;
    }
    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        return FluidStack.EMPTY;
    }
    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        if(this.getFluid(resource) == FluidStack.EMPTY) {
            this.setChanged();
            return resource.getAmount();
        }
        else if(resource == null){
            return 0;
        }
        else{
            if(this.getFluid(resource) == null){
                this.getTankFromFluid(resource).setFluid(resource);
            }
            if (resource.getFluid() != this.getFluid(resource).getFluid()) {
                this.setChanged();
                return 0;
            } else {
                if (this.getFluidAmount(resource) >= this.getCapacity()) {
                    this.setChanged();
                    return 0;
                } else {
                    if (this.getFluidAmount(resource) + resource.getAmount() > this.getCapacity()) {
                        this.getFluid(resource).setAmount(this.getCapacity());
                    } else {
                        this.getFluid(resource).setAmount(this.getFluidAmount(resource) + resource.getAmount());
                    }
                    this.setChanged();
                    return resource.getAmount();
                }
            }
        }
        //return 0;
    }
}