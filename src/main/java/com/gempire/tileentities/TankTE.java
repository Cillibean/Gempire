package com.gempire.tileentities;

import com.gempire.container.TankContainer;
import com.gempire.init.ModFluids;
import com.gempire.init.ModItems;
import com.gempire.init.ModTE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.HashMap;

public class TankTE extends RandomizableContainerBlockEntity implements IFluidTank, MenuProvider {
    public static final int NUMBER_OF_SLOTS = 2;
    public static final int BUCKET_INPUT_SLOT_INDEX = 0;
    public static final int BUCKET_OUTPUT_SLOT_INDEX = 1;
    public static HashMap<Fluid, Item> FLUID_BUCKETS = new HashMap<>();
    public NonNullList<ItemStack> items = NonNullList.withSize(TankTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public FluidTank tank;

    public TankTE(BlockPos pos, BlockState state) {
        super(ModTE.TANK_TE.get(), pos, state);
        this.tank = new FluidTank(4000);
    }

    public void load(BlockState state, CompoundTag nbt) {
        super.load(nbt);
        this.tank.readFromNBT(nbt.getCompound("tank"));
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if(!this.tryLoadLootTable(nbt)){
            ContainerHelper.loadAllItems(nbt, this.items);
        }
    }
    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("tank", this.tank.writeToNBT(new CompoundTag()));
        if(!this.trySaveLootTable(compound)){
            ContainerHelper.saveAllItems(compound, this.items);
        }
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        TankTE te = (TankTE)be;
        if(!level.isClientSide()) {
            ItemStack stackToInput = te.getItem(TankTE.BUCKET_INPUT_SLOT_INDEX);
            if (te.getFluid() != null) {
                if (stackToInput != ItemStack.EMPTY) {
                    //System.out.println("Stack is not empty");
                    if (te.shouldPullFluid() && te.canPullFluidFromStack(stackToInput)) {
                        System.out.println("Should Pull");
                        BucketItem bucket = (BucketItem) stackToInput.getItem();
                        te.tank.fill(new FluidStack(bucket.getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE);
                            te.setItem(TankTE.BUCKET_INPUT_SLOT_INDEX, new ItemStack(Items.BUCKET.asItem()));
                        System.out.println("Tank level is at " + te.getFluidAmount() + "mb");
                        te.level.sendBlockUpdated(pos, state, state, 2);
                        te.setChanged();
                    }
                }
            }
            ItemStack stackToOutput = te.getItem(TankTE.BUCKET_OUTPUT_SLOT_INDEX);
            if (te.getFluid() != null) {
                if (stackToOutput != ItemStack.EMPTY) {
                    if (te.shouldPutFluid() && te.canPutFluidFromStack(stackToOutput)) {
                        BucketItem bucket = (BucketItem) stackToOutput.getItem();
                        Fluid fluid = te.tank.getFluid().getFluid();
                        te.tank.drain(new FluidStack(te.tank.getFluid().getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE);
                        te.setItem(TankTE.BUCKET_OUTPUT_SLOT_INDEX, new ItemStack(TankTE.FLUID_BUCKETS.get(fluid)));
                        te.level.sendBlockUpdated(pos, state, state, 2);
                        te.setChanged();
                    } else if (te.shouldPutFluidToButton() && te.canPutFluidToButton(stackToOutput)) {
                        Fluid fluid = te.tank.getFluid().getFluid();
                        if (fluid == ModFluids.PINK_ESSENCE.source.get()) {
                            te.tank.drain(new FluidStack(te.tank.getFluid().getFluid(), 200), IFluidHandler.FluidAction.EXECUTE);
                            te.setItem(TankTE.BUCKET_OUTPUT_SLOT_INDEX, new ItemStack(ModItems.PEBBLE_GEM.get()));
                            te.level.sendBlockUpdated(pos, state, state, 2);
                            te.setChanged();
                        } else if (fluid == ModFluids.BLUE_ESSENCE.source.get()) {
                            te.tank.drain(new FluidStack(te.tank.getFluid().getFluid(), 200), IFluidHandler.FluidAction.EXECUTE);
                            te.setItem(TankTE.BUCKET_OUTPUT_SLOT_INDEX, new ItemStack(ModItems.SHALE_GEM.get()));
                            te.level.sendBlockUpdated(pos, state, state, 2);
                            te.setChanged();
                        } else if (fluid == ModFluids.YELLOW_ESSENCE.source.get()) {
                            te.tank.drain(new FluidStack(te.tank.getFluid().getFluid(), 200), IFluidHandler.FluidAction.EXECUTE);
                            te.setItem(TankTE.BUCKET_OUTPUT_SLOT_INDEX, new ItemStack(ModItems.MICA_GEM.get()));
                            te.level.sendBlockUpdated(pos, state, state, 2);
                            te.setChanged();
                        }
                    } else if (te.shouldPutFluidToButton() && stackToOutput.getItem() == Items.NAUTILUS_SHELL) {
                        Fluid fluid = te.tank.getFluid().getFluid();
                        if (fluid == ModFluids.WHITE_ESSENCE.source.get()) {
                            te.tank.drain(new FluidStack(te.tank.getFluid().getFluid(), 200), IFluidHandler.FluidAction.EXECUTE);
                            te.setItem(TankTE.BUCKET_OUTPUT_SLOT_INDEX, new ItemStack(ModItems.NACRE_GEM.get()));
                            te.level.sendBlockUpdated(pos, state, state, 2);
                            te.setChanged();
                        }
                    }
                }
            }
        }
    }

    public boolean canPutFluidToButton(ItemStack stack){
        return stack.getItem() == Items.STONE_BUTTON;
    }

    public boolean shouldPutFluidToButton(){
        return this.tank.getFluidAmount() >= 200;
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
            return bucket.getFluid() == Fluids.EMPTY;
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
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    //CONTAINER STUFF

    //CONTAINER STUFF

    @Override
    public Component getDisplayName() {
        return Component.translatable("");
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("");
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
        return new TankContainer(id, player, this);
    }

    @Override
    public int getContainerSize() {
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
        return this.getFluid().getFluid() == Fluids.EMPTY || stack.getFluid() == this.getFluid().getFluid();
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        if(this.getFluid() == FluidStack.EMPTY) {
            this.tank.setFluid(resource);
            this.setChanged();
            return resource.getAmount();
        }
        else{
            if (resource.getFluid() != this.getFluid().getFluid()) {
                System.out.println("Couldnt Fill");
                this.setChanged();
                return 0;
            } else {
                if (this.getFluidAmount() >= this.getCapacity()) {
                    System.out.println("Couldn't fill");
                    this.setChanged();
                    return 0;
                } else {
                    if (this.getFluidAmount() + resource.getAmount() > this.getCapacity()) {
                        this.getFluid().setAmount(this.getCapacity());
                    } else {
                        this.getFluid().setAmount(this.getFluidAmount() + resource.getAmount());
                    }
                    System.out.println("Filled");
                    this.setChanged();
                    return resource.getAmount();
                }
            }
        }
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        if(this.getFluid().getAmount() - maxDrain < 0){
            this.setChanged();
            return FluidStack.EMPTY;
        }
        else{
            this.getFluid().setAmount(this.getFluidAmount() - maxDrain);
            this.setChanged();
            return this.getFluid();
        }
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        if(resource.getFluid() != this.getFluid().getFluid()) {
            this.setChanged();
            return null;
        }
        int amount = this.getFluidAmount() - resource.getAmount() < 0 ? this.getFluidAmount() : resource.getAmount();
        this.setChanged();
        return this.drain(amount, IFluidHandler.FluidAction.EXECUTE);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        //Debug
        System.out.println("[DEBUG]:Client recived tile sync packet");
        this.load(this.level.getBlockState(pkt.getPos()), pkt.getTag());
    }

    @Override
    public void handleUpdateTag(CompoundTag compound) {
        this.load(compound);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        //Debug
        System.out.println("[DEBUG]:Server sent tile sync packet");
        return ClientboundBlockEntityDataPacket.create(this);
    }
}