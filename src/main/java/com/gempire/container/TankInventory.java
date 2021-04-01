package com.gempire.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.ItemStackHandler;

public class TankInventory implements IInventory {
    public final ItemStackHandler tankContents;


    public TankInventory(int size) {
        this.tankContents = new ItemStackHandler(size);
    }

    @FunctionalInterface
    public interface Notify {   // Some folks use Runnable, but I prefer not to use it for non-thread-related tasks
        void invoke();
    }

    public CompoundNBT serializeNBT()  {
        return tankContents.serializeNBT();
    }

    public void deserializeNBT(CompoundNBT nbt)   {
        tankContents.deserializeNBT(nbt);
    }

    @Override
    public int getSizeInventory() {
        return this.tankContents.getSlots();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return false;
    }

    @Override
    public void clear() {

    }

    public static TankInventory createForClientSideContainer(int size) {
        return new TankInventory(size);
    }
}
