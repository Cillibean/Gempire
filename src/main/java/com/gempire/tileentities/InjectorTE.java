package com.gempire.tileentities;

import com.gempire.blocks.GemSeedBlock;
import com.gempire.blocks.machine.InjectorBlock;
import com.gempire.blocks.machine.PowerCrystalBlock;
import com.gempire.container.InjectorContainer;
import com.gempire.events.InjectEvent;
import com.gempire.init.*;
import com.gempire.items.ItemChroma;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.MossBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
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
import java.util.Objects;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import static com.gempire.blocks.machine.TankBlock.HALF;

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

    public NonNullList<ItemStack> items = NonNullList.withSize(InjectorTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public FluidTank pinkTank;
    public FluidTank blueTank;
    public FluidTank yellowTank;
    public FluidTank whiteTank;
    public boolean pinkOpen, blueOpen, yellowOpen, whiteOpen = false;
    public int tick = 0;

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

    //TODO: FIX LOTS OF NBT STUFF

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        InjectorTE te = (InjectorTE) be;
        if (!level.isClientSide()) {
            te.HandleSlotUpdates();
        }
    }

    public void HandleSlotUpdates() {
        for (int i = 0; i < 4; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (this.shouldPullFluidFromStack(i)) {
                if (stack.getItem() != Items.AIR) {
                    if (stack.getItem() instanceof BucketItem bucket) {
                        if (this.isValidForSlot(i, bucket)) {
                            //TODO: TEMPORARY
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


    public void Inject() {
        BlockPos crystalPos = getBlockPos().above().above().above();
        if (level.getBlockState(crystalPos).getBlock() instanceof PowerCrystalBlock) {
            System.out.println(fluidValid());
            if (itemHandler.getStackInSlot(CHROMA_INPUT_SLOT_INDEX).getItem() instanceof ItemChroma chroma &&
                    fluidValid()) {
                System.out.println("got thrpugh fluid check");
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
                    BlockPos seedPos = this.getBlockPos().offset(new BlockPos(0, -Math.ceil(GemSeedTE.DRAIN_SIZE / 2) - 1, 0));
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
                        gemSeedTE.SetChroma(chroma);
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
        }
    }

    public static int getFacingFromState(BlockState state){
        if(state.getValue(InjectorBlock.FACING) == Direction.EAST){
            return 0;
        }
        else if(state.getValue(InjectorBlock.FACING) == Direction.NORTH){
            return 1;
        }
        else if(state.getValue(InjectorBlock.FACING) == Direction.WEST){
            return 2;
        }
        else if(state.getValue(InjectorBlock.FACING) == Direction.SOUTH){
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
            return bucket == ModFluids.PINK_ESSENCE.bucket.get();
        }
        else if(slot == InjectorTE.BLUE_INPUT_SLOT_INDEX){
            return bucket == ModFluids.BLUE_ESSENCE.bucket.get();
        }
        else if(slot == InjectorTE.YELLOW_INPUT_SLOT_INDEX){
            return bucket == ModFluids.YELLOW_ESSENCE.bucket.get();
        }
        else if(slot == InjectorTE.WHITE_INPUT_SLOT_INDEX){
            return bucket == ModFluids.WHITE_ESSENCE.bucket.get();
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
        if(fluidStack.getFluid() == ModFluids.PINK_ESSENCE.source.get()){
            return this.pinkTank;
        }
        else if(fluidStack.getFluid() == ModFluids.BLUE_ESSENCE.source.get()){
            return this.blueTank;
        }
        else if(fluidStack.getFluid() == ModFluids.YELLOW_ESSENCE.source.get()){
            return this.yellowTank;
        }
        else if(fluidStack.getFluid() == ModFluids.WHITE_ESSENCE.source.get()){
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
            return ModFluids.PINK_ESSENCE.source.get();
        }
        else if(value == 1){
            return ModFluids.BLUE_ESSENCE.source.get();
        }
        else if(value == 2){
            return ModFluids.YELLOW_ESSENCE.source.get();
        }
        else if(value == 3){
            return ModFluids.WHITE_ESSENCE.source.get();
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