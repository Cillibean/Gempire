package com.gempire.tileentities;

import com.gempire.container.TankContainer;
import com.gempire.init.ModFluids;
import com.gempire.init.ModItems;
import com.gempire.init.ModTE;
import com.sun.org.apache.xpath.internal.operations.String;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;

public class TankTE extends LockableLootTileEntity implements IFluidTank, INamedContainerProvider, ITickableTileEntity {
    public static final int NUMBER_OF_SLOTS = 2;
    public static final int BUCKET_INPUT_SLOT_INDEX = 0;
    public static final int BUCKET_OUTPUT_SLOT_INDEX = 1;
    public static HashMap<Fluid, Item> FLUID_BUCKETS = new HashMap<>();
    public NonNullList<ItemStack> items = NonNullList.withSize(TankTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public FluidTank tank;

    public TankTE() {
        super(ModTE.TANK_TE.get());
        this.tank = new FluidTank(4000);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.tank.readFromNBT(nbt.getCompound("tank"));
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if(!this.checkLootAndRead(nbt)){
            ItemStackHelper.loadAllItems(nbt, this.items);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.put("tank", this.tank.writeToNBT(new CompoundNBT()));
        if(!this.checkLootAndWrite(compound)){
            ItemStackHelper.saveAllItems(compound, this.items);
        }
        return compound;
    }

    @Override
    public void tick() {
        ItemStack stackToInput = this.getStackInSlot(TankTE.BUCKET_INPUT_SLOT_INDEX);
        if(this.getFluid() != null) {
            if (stackToInput != ItemStack.EMPTY) {
                //System.out.println("Stack is not empty");
                if (this.shouldPullFluid() && this.canPullFluidFromStack(stackToInput)) {
                    System.out.println("Should Pull");
                    BucketItem bucket = (BucketItem) stackToInput.getItem();
                    this.tank.fill(new FluidStack(bucket.getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE);
                    if(bucket == ModItems.WHITE_ESSENCE.get() || bucket == ModItems.YELLOW_ESSENCE.get() || bucket == ModItems.BLUE_ESSENCE.get()
                    || bucket == ModItems.PINK_ESSENCE.get()){
                        this.setInventorySlotContents(TankTE.BUCKET_INPUT_SLOT_INDEX, new ItemStack(ModItems.ESSENCE_BOTTLE.get()));
                    }
                    else {
                        this.setInventorySlotContents(TankTE.BUCKET_INPUT_SLOT_INDEX, new ItemStack(Items.BUCKET));
                    }
                    System.out.println("Tank level is at " + this.getFluidAmount() + "mb");
                    this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), 2);
                    this.markDirty();
                }
            }
        }
        ItemStack stackToOutput = this.getStackInSlot(TankTE.BUCKET_OUTPUT_SLOT_INDEX);
        if(this.getFluid() != null) {
            if (stackToOutput != ItemStack.EMPTY) {
                if (this.shouldPutFluid() && this.canPutFluidFromStack(stackToOutput)) {
                    BucketItem bucket = (BucketItem) stackToOutput.getItem();
                    /*if(this.tank.getFluid().getFluid() == ModFluids.WHITE_ESSENCE.get() ||
                            this.tank.getFluid().getFluid() == ModFluids.YELLOW_ESSENCE.get() ||
                            this.tank.getFluid().getFluid() == ModFluids.BLUE_ESSENCE.get() ||
                            this.tank.getFluid().getFluid() == ModFluids.PINK_ESSENCE.get()){
                        if(bucket == ModItems.ESSENCE_BOTTLE.get()) {
                            this.tank.drain(new FluidStack(this.tank.getFluid().getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE);
                            this.setInventorySlotContents(TankTE.BUCKET_OUTPUT_SLOT_INDEX, new ItemStack(this.tank.getFluid().getFluid().getFilledBucket()));
                            this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), 2);
                            this.markDirty();
                        }
                    }
                    else {
                    }*/
                    Fluid fluid = this.tank.getFluid().getFluid();
                    this.tank.drain(new FluidStack(this.tank.getFluid().getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE);
                    this.setInventorySlotContents(TankTE.BUCKET_OUTPUT_SLOT_INDEX, new ItemStack((BucketItem)TankTE.FLUID_BUCKETS.get(fluid)));
                    this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), 2);
                    this.markDirty();
                }
            }
        }
    }

    public boolean canPullFluidFromStack(ItemStack stack){
        if(stack.getItem() instanceof BucketItem){
            BucketItem bucket = (BucketItem)stack.getItem();
            if(bucket.getFluid() != Fluids.EMPTY){
                System.out.println("Should Pull");
                return this.isFluidValid(new FluidStack(bucket.getFluid(), 1000));
            }
        }
        return false;
    }

    public boolean shouldPullFluid(){
        return this.getFluid().getAmount() < this.getCapacity();
    }

    public boolean canPutFluidFromStack(ItemStack stack){
        if(stack.getItem() instanceof BucketItem){
            BucketItem bucket = (BucketItem)stack.getItem();
            if(bucket.getFluid() == Fluids.EMPTY){
                return true;
            }
        }
        return false;
    }

    public boolean shouldPutFluid(){
        return this.getFluid().getAmount() >= 1000;
    }

    //TANK FUNCTIONALITY

    //TANK FUNCTIONALITY

    public void EmptyTank(){
        this.tank.setFluid(FluidStack.EMPTY);
        this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), 2);
        this.markDirty();
    }

    //CONTAINER STUFF

    //CONTAINER STUFF

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("");
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new StringTextComponent("");
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
        return new TankContainer(id, player, this);
    }

    @Override
    public int getSizeInventory() {
        return TankTE.NUMBER_OF_SLOTS;
    }

    // FLUID STUFF

    // FLUID STUFF

    @Nonnull
    @Override
    public FluidStack getFluid() {
        return this.tank.getFluid();
    }

    @Override
    public int getFluidAmount() {
        return this.tank.getFluidAmount();
    }

    @Override
    public int getCapacity() {
        return 4000;
    }

    @Override
    public boolean isFluidValid(FluidStack stack) {
        System.out.println(stack.getFluid());
        System.out.println(this.tank.getFluid().getFluid());
        return this.getFluid().getFluid() == Fluids.EMPTY ? true : stack.getFluid() == this.getFluid().getFluid();
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        if(this.getFluid() == FluidStack.EMPTY) {
            this.tank.setFluid(resource);
            this.markDirty();
            return resource.getAmount();
        }
        else{
            if (resource.getFluid() != this.getFluid().getFluid()) {
                System.out.println("Couldnt Fill");
                this.markDirty();
                return 0;
            } else {
                if (this.getFluidAmount() >= this.getCapacity()) {
                    System.out.println("Couldn't fill");
                    this.markDirty();
                    return 0;
                } else {
                    if (this.getFluidAmount() + resource.getAmount() > this.getCapacity()) {
                        this.getFluid().setAmount(this.getCapacity());
                    } else {
                        this.getFluid().setAmount(this.getFluidAmount() + resource.getAmount());
                    }
                    System.out.println("Filled");
                    this.markDirty();
                    return resource.getAmount();
                }
            }
        }
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        if(this.getFluid().getAmount() - maxDrain < 0){
            this.markDirty();
            return FluidStack.EMPTY;
        }
        else{
            this.getFluid().setAmount(this.getFluidAmount() - maxDrain);
            this.markDirty();
            return this.getFluid();
        }
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        if(resource.getFluid() != this.getFluid().getFluid()) {
            this.markDirty();
            return null;
        }
        int amount = this.getFluidAmount() - resource.getAmount() < 0 ? this.getFluidAmount() : resource.getAmount();
        this.markDirty();
        return this.drain(amount, IFluidHandler.FluidAction.EXECUTE);
    }

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
}
