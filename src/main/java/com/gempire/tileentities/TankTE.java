package com.gempire.tileentities;

import com.gempire.container.TankContainer;
import com.gempire.init.ModTE;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TankTE extends LockableLootTileEntity implements IFluidTank, INamedContainerProvider {
    public static final int NUMBER_OF_SLOTS = 1;
    public NonNullList<ItemStack> items = NonNullList.withSize(TankTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public FluidStack fluid;

    public TankTE() {
        super(ModTE.TANK_TE.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        if(nbt.contains("fluidStack")) this.fluid = FluidStack.loadFluidStackFromNBT(nbt);
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if(!this.checkLootAndRead(nbt)){
            ItemStackHelper.loadAllItems(nbt, this.items);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.put("fluidStack", this.fluid.getOrCreateTag());
        if(!this.checkLootAndWrite(compound)){
            ItemStackHelper.saveAllItems(compound, this.items);
        }
        return compound;
    }

    //CONTAINER STUFF

    //CONTAINER STUFF

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.gempire.tank.title");
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.gempire.tank.title");
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
        return this.fluid;
    }

    @Override
    public int getFluidAmount() {
        return fluid.getAmount();
    }

    @Override
    public int getCapacity() {
        return 8000;
    }

    @Override
    public boolean isFluidValid(FluidStack stack) {
        return stack.getFluid() == this.getFluid().getFluid();
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        if(resource.getFluid() != this.getFluid().getFluid()){
            return 0;
        }
        else{
            if(this.getFluidAmount() >= this.getCapacity()){
                return 0;
            }
            else{
                this.getFluid().setAmount(this.getFluidAmount() + resource.getAmount());
                return resource.getAmount();
            }
        }
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        if(this.getFluid().getAmount() - maxDrain < 0){
            return FluidStack.EMPTY;
        }
        else{
            this.getFluid().setAmount(this.getFluidAmount() - maxDrain);
            return this.getFluid();
        }
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        if(resource.getFluid() != this.getFluid().getFluid())
            return null;
        int amount = this.getFluidAmount() - resource.getAmount() < 0 ? this.getFluidAmount() : resource.getAmount();
        return this.drain(amount, IFluidHandler.FluidAction.EXECUTE);
    }
}
