package com.gempire.tileentities;

import com.gempire.blocks.GemSeedBlock;
import com.gempire.blocks.InjectorBlock;
import com.gempire.container.InjectorContainer;
import com.gempire.container.TankContainer;
import com.gempire.init.*;
import com.gempire.items.ItemChroma;
import com.gempire.networking.S2SSendGemSeedInfo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public class InjectorTE extends LockableLootTileEntity implements IFluidTank, INamedContainerProvider, ITickableTileEntity {
    public static final int NUMBER_OF_SLOTS = 6;
    public static final int PINK_INPUT_SLOT_INDEX = 0;
    public static final int BLUE_INPUT_SLOT_INDEX = 1;
    public static final int YELLOW_INPUT_SLOT_INDEX = 2;
    public static final int WHITE_INPUT_SLOT_INDEX = 3;
    public static final int CHROMA_INPUT_SLOT_INDEX = 4;
    public static final int PRIME_INPUT_SLOT_INDEX = 5;
    public int TANK_CAPACITY(){
        return 1000;
    }
    public NonNullList<ItemStack> items = NonNullList.withSize(InjectorTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public FluidTank pinkTank;
    public FluidTank blueTank;
    public FluidTank yellowTank;
    public FluidTank whiteTank;
    public boolean pinkOpen, blueOpen, yellowOpen, whiteOpen = false;
    public int tick = 0;

    public InjectorTE() {
        super(ModTE.INJECTOR_TE.get());
        this.pinkTank = new FluidTank(this.TANK_CAPACITY());
        this.blueTank = new FluidTank(this.TANK_CAPACITY());
        this.yellowTank = new FluidTank(this.TANK_CAPACITY());
        this.whiteTank = new FluidTank(this.TANK_CAPACITY());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.pinkTank.readFromNBT(nbt.getCompound("pinkTank"));
        this.blueTank.readFromNBT(nbt.getCompound("blueTank"));
        this.yellowTank.readFromNBT(nbt.getCompound("yellowTank"));
        this.whiteTank.readFromNBT(nbt.getCompound("whiteTank"));
        this.pinkOpen = nbt.getBoolean("pinkOpen");
        this.blueOpen = nbt.getBoolean("blueOpen");
        this.yellowOpen = nbt.getBoolean("yellowOpen");
        this.whiteOpen = nbt.getBoolean("whiteOpen");
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if(!this.checkLootAndRead(nbt)){
            ItemStackHelper.loadAllItems(nbt, this.items);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.put("pinkTank", this.pinkTank.writeToNBT(new CompoundNBT()));
        compound.put("blueTank", this.blueTank.writeToNBT(new CompoundNBT()));
        compound.put("yellowTank", this.yellowTank.writeToNBT(new CompoundNBT()));
        compound.put("whiteTank", this.whiteTank.writeToNBT(new CompoundNBT()));
        compound.putBoolean("pinkOpen", this.pinkOpen);
        compound.putBoolean("blueOpen", this.blueOpen);
        compound.putBoolean("yellowOpen", this.yellowOpen);
        compound.putBoolean("whiteOpen", this.whiteOpen);
        if(!this.checkLootAndWrite(compound)){
            ItemStackHelper.saveAllItems(compound, this.items);
        }
        return compound;
    }

    //TODO: FIX LOTS OF NBT STUFF

    @Override
    public void tick() {
        this.HandleSlotUpdates();
    }

    public void HandleSlotUpdates(){
        for (int i = 0; i < 4; i++) {
            ItemStack stack = this.getStackInSlot(i);
            if (this.shouldPullFluidFromStack(i)) {
                if (stack != ItemStack.EMPTY) {
                    if(stack.getItem() instanceof BucketItem) {
                        BucketItem bucket = (BucketItem) stack.getItem();
                        if (this.isValidForSlot(i, bucket)) {
                            int filled = this.FillFluidTanks(i, 1000);
                            //TODO: TEMPORARY
                            this.setInventorySlotContents(i, new ItemStack(ModItems.ESSENCE_BOTTLE.get()));
                            this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), 2);
                            this.markDirty();
                        }
                    }
                }
            }
        }
    }

    //INJECTOR FUNCTIONALITY

    //INJECTOR FUNCTIONALITY

    public void DumpFluids(){
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
        this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), 2);
        this.markDirty();
    }

    public void Inject() {
        if (this.getStackInSlot(InjectorTE.CHROMA_INPUT_SLOT_INDEX).getItem() instanceof ItemChroma &&
                (this.getTankFromValue(0).getFluid().getFluid() != Fluids.EMPTY && this.pinkOpen ||
                        this.getTankFromValue(1).getFluid().getFluid() != Fluids.EMPTY && this.blueOpen ||
                        this.getTankFromValue(2).getFluid().getFluid() != Fluids.EMPTY && this.yellowOpen ||
                        this.getTankFromValue(3).getFluid().getFluid() != Fluids.EMPTY && this.whiteOpen)) {
            int portionToDrain = 0;
            if(this.pinkOpen){
                portionToDrain++;
            }
            if(this.blueOpen){
                portionToDrain++;
            }
            if(this.yellowOpen){
                portionToDrain++;
            }
            if(this.whiteOpen){
                portionToDrain++;
            }
            String essences = "";
            if (this.pinkOpen) {
                FluidTank tank = this.getTankFromValue(0);
                if (tank.getFluid() != FluidStack.EMPTY) {
                    essences+="pink";
                    if (tank.getFluidAmount() - 200 / portionToDrain > 0) {
                        tank.getFluid().setAmount(tank.getFluidAmount() - 200);
                    } else {
                        tank.getFluid().setAmount(0);
                    }
                }
            }
            if (this.blueOpen) {
                FluidTank tank = this.getTankFromValue(1);
                if (tank.getFluid() != FluidStack.EMPTY) {
                    if(essences == ""){
                        essences += "blue";
                    }
                    else{
                        essences+="-blue";
                    }
                    if (tank.getFluidAmount() - 200 / portionToDrain > 0) {
                        tank.getFluid().setAmount(tank.getFluidAmount() - 200);
                    } else {
                        tank.getFluid().setAmount(0);
                    }
                }
            }
            if (this.yellowOpen) {
                FluidTank tank = this.getTankFromValue(2);
                if (tank.getFluid() != FluidStack.EMPTY) {
                    if(essences == ""){
                        essences += "yellow";
                    }
                    else{
                        essences+="-yellow";
                    }
                    if (tank.getFluidAmount() - 200 / portionToDrain > 0) {
                        tank.getFluid().setAmount(tank.getFluidAmount() - 200);
                    } else {
                        tank.getFluid().setAmount(0);
                    }
                }
            }
            if (this.whiteOpen) {
                FluidTank tank = this.getTankFromValue(3);
                if (tank.getFluid() != FluidStack.EMPTY) {
                    if(essences == ""){
                        essences += "white";
                    }
                    else{
                        essences+="-white";
                    }
                    if (tank.getFluidAmount() - 200 / portionToDrain > 0) {
                        tank.getFluid().setAmount(tank.getFluidAmount() - 200);
                    } else {
                        tank.getFluid().setAmount(0);
                    }
                }
            }
            BlockPos seedPos = this.getPos().add(new BlockPos(0, -Math.ceil(GemSeedTE.DRAIN_SIZE / 2), 0));
            while(this.world.getBlockState(seedPos) == Blocks.AIR.getDefaultState() ||
                    this.world.getBlockState(seedPos) == ModBlocks.GEM_SEED_BLOCK.get().getDefaultState()){
                seedPos = seedPos.add(0, -GemSeedTE.DRAIN_SIZE, 0);
            }
            ItemChroma chroma = (ItemChroma)this.getStackInSlot(InjectorTE.CHROMA_INPUT_SLOT_INDEX).getItem();
            Item primer = this.getStackInSlot(InjectorTE.PRIME_INPUT_SLOT_INDEX).getItem();
            GemSeedBlock seedBlock = (GemSeedBlock) ModBlocks.GEM_SEED_BLOCK.get();
            this.world.setBlockState(seedPos, seedBlock.getDefaultState());
            GemSeedTE gemSeedTE = (GemSeedTE) this.world.getTileEntity(seedPos);
            gemSeedTE.SetChroma(chroma);
            gemSeedTE.SetPrimer(primer);
            gemSeedTE.setEssences(essences);
            int facing = InjectorTE.getFacingFromState(this.getBlockState());
            gemSeedTE.setFacing(facing);
            System.out.println("Facing :" + facing);
            this.getStackInSlot(InjectorTE.CHROMA_INPUT_SLOT_INDEX).shrink(1);
            this.getStackInSlot(InjectorTE.PRIME_INPUT_SLOT_INDEX).shrink(1);
            this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), 2);
            this.markDirty();
        }
    }

    public static int getFacingFromState(BlockState state){
        if(state.get(InjectorBlock.FACING) == Direction.EAST){
            return 0;
        }
        else if(state.get(InjectorBlock.FACING) == Direction.NORTH){
            return 1;
        }
        else if(state.get(InjectorBlock.FACING) == Direction.WEST){
            return 2;
        }
        else if(state.get(InjectorBlock.FACING) == Direction.SOUTH){
            return 3;
        }
        else{
            return -1;
        }
    }

    public CompoundNBT getCompountNBTForPacket(ItemChroma chroma, Item primer, Fluid[] essences){
        CompoundNBT compound = new CompoundNBT();
        compound.putInt("ticks", 0);
        compound.putBoolean("spawned", false);
        compound.put("chroma", new ItemStack(chroma).write(new CompoundNBT()));
        compound.put("primer", new ItemStack(primer).write(new CompoundNBT()));
        String fluids = "";
        for(int i = 0; i < essences.length; i++){
            if(i == 0){
                fluids+=GemSeedTE.StringFromFluid(essences[0]);
            }
            else{
                fluids+= "-"+GemSeedTE.StringFromFluid(essences[i]);
            }
        }
        compound.putString("essences", fluids);
        return compound;
    }

    public boolean shouldPullFluidFromStack(int tank){
        return this.getTankFromValue(tank).getFluid().getAmount() < this.getCapacity();
    }

    public boolean isValidForSlot(int slot, BucketItem bucket){
        if(slot == InjectorTE.PINK_INPUT_SLOT_INDEX){
            return bucket == ModItems.PINK_ESSENCE.get();
        }
        else if(slot == InjectorTE.BLUE_INPUT_SLOT_INDEX){
            return bucket == ModItems.BLUE_ESSENCE.get();
        }
        else if(slot == InjectorTE.YELLOW_INPUT_SLOT_INDEX){
            return bucket == ModItems.YELLOW_ESSENCE.get();
        }
        else if(slot == InjectorTE.WHITE_INPUT_SLOT_INDEX){
            return bucket == ModItems.WHITE_ESSENCE.get();
        }
        else if(slot == InjectorTE.PRIME_INPUT_SLOT_INDEX){
            return true;
        }
        else{
            return false;
        }
    }

    public void ToggleTankOpen(String color){
        switch (color){
            case "pink":
                this.pinkOpen = this.pinkOpen ? false : true;
                break;
            case "blue":
                this.blueOpen = this.blueOpen ? false : true;
                break;
            case "yellow":
                this.yellowOpen = this.yellowOpen ? false : true;
                break;
            case "white":
                this.whiteOpen = this.whiteOpen ? false : true;
                break;
            default:
                this.pinkOpen = this.pinkOpen ? false : true;
                break;
        }
        this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), 2);
        this.markDirty();
    }

    //CONTAINER STUFF

    //CONTAINER STUFF

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("");//TranslationTextComponent("container.gempire.injector");
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.gempire.injector");
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
    protected Container createMenu(int id, PlayerInventory player) {
        return new InjectorContainer(id, player, this);
    }

    @Override
    public int getSizeInventory() {
        return InjectorTE.NUMBER_OF_SLOTS;
    }

    // FLUID STUFF

    // FLUID STUFF

    @Override
    public int getCapacity() {
        return this.TANK_CAPACITY();
    }

    public int FillFluidTanks(int value, int amount){
        FluidTank tank = this.getTankFromValue(value);
        if(tank.getFluidAmount() >= tank.getCapacity()) {
            return 0;
        }
        else{
            if(tank.getFluid().getFluid() == Fluids.EMPTY || tank.getFluid() == null){
                tank.setFluid(new FluidStack(this.getFluidFromValue(value), amount));
                return amount;
            }
            else{
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

    public FluidStack getFluid(int value) {
        if(value == 0){
            return this.pinkTank.getFluid();
        }
        else if(value == 1){
            return this.blueTank.getFluid();
        }
        else if(value == 2){
            return this.yellowTank.getFluid();
        }
        else if(value == 3){
            return this.whiteTank.getFluid();
        }
        else{
            return null;
        }
    }

    public int getFluidAmount(FluidStack stack) {
        return this.getTankFromFluid(stack).getFluid().getAmount();
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        int amountAfterDrain = this.getTankFromFluid(resource).getFluidAmount() - resource.getAmount() < 0 ? 0 : this.getTankFromFluid(resource).getFluidAmount() - resource.getAmount();
        this.getFluid(resource).setAmount(amountAfterDrain);
        return this.getFluid(resource);
    }

    public int amountDrained(FluidStack resource){
        if(this.getFluid(resource).getAmount() - resource.getAmount() < 0)
        {
            return this.getFluid(resource).getAmount();
        }
        else {
            return resource.getAmount();
        }
    }

    public boolean isFluidValidForTank(FluidStack fluid, FluidTank tank){
        return fluid.getFluid() == tank.getFluid().getFluid();
    }

    //NETWORKING STUFF

    //NETWORKING STUFF

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        //Debug
        System.out.println("[DEBUG]:Client recived tile sync packet");
        this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        //Debug
        System.out.println("[DEBUG]:Server sent tile sync packet");
        return new SUpdateTileEntityPacket(this.pos, -1, this.getUpdateTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        System.out.println("[DEBUG]:Handling tag on chunk load");
        this.read(state, tag);
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
        /*if(this.getFluid(resource) == FluidStack.EMPTY) {
            this.markDirty();
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
                this.markDirty();
                return 0;
            } else {
                if (this.getFluidAmount(resource) >= this.getCapacity()) {
                    this.markDirty();
                    return 0;
                } else {
                    if (this.getFluidAmount(resource) + resource.getAmount() > this.getCapacity()) {
                        this.getFluid(resource).setAmount(this.getCapacity());
                    } else {
                        this.getFluid(resource).setAmount(this.getFluidAmount(resource) + resource.getAmount());
                    }
                    this.markDirty();
                    return resource.getAmount();
                }
            }
        }*/
        return 0;
    }
}
