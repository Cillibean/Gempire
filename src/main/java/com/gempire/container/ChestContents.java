package com.gempire.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by TGG on 4/04/2020.
 *
 * This class is used to encapsulate the contents of the chest and provide the link between the parent TileEntity and
 *    the container.
 * 1) stores information about the items in the chest: allows the container to manipulate the data stored in the tile entity
 * 2) provides a way for the container to ask if certain actions are permitted (eg isUsableByPlayer, isItemValidForSlot)
 * 3) provides a way for the container to notify the TileEntity that the container has changed (eg markDirty, openInventory)
 *
 * Typical usage for a TileEntity which needs to store Items:
 * 1) When constructing the TileEntity, create and store a ChestContents using createForTileEntity()
 * 2) In your ContainerType<MyContainer>, create a ChestContents using createForClientSideContainer() and pass it to
 *    the constructor of your client-side container.
 * 3) In your TileEntity write() and read() methods, call the serializeNBT() and deserializeNBT() methods
 * Vanilla and the container code will take care of everything else.
 *
 */

public class ChestContents implements IInventory {

    private ChestContents(int size) {
        this.chestContents = new ItemStackHandler(size);
    }

    private ChestContents(int size, Predicate<PlayerEntity> canPlayerAccessInventoryLambda, Notify markDirtyNotificationLambda) {
        this.chestContents = new ItemStackHandler(size);
        this.canPlayerAccessInventoryLambda = canPlayerAccessInventoryLambda;
        this.markDirtyNotificationLambda = markDirtyNotificationLambda;
    }
    public static ChestContents createForTileEntity(int size,
                                                    Predicate<PlayerEntity> canPlayerAccessInventoryLambda,
                                                    Notify markDirtyNotificationLambda) {
        return new ChestContents(size, canPlayerAccessInventoryLambda, markDirtyNotificationLambda);
    }

    public static ChestContents createForClientSideContainer(int size) {
        return new ChestContents(size);
    }

    public CompoundNBT serializeNBT()  {
        return chestContents.serializeNBT();
    }

    public void deserializeNBT(CompoundNBT nbt)   {
        chestContents.deserializeNBT(nbt);
    }

    public void setCanPlayerAccessInventoryLambda(Predicate<PlayerEntity> canPlayerAccessInventoryLambda) {
        this.canPlayerAccessInventoryLambda = canPlayerAccessInventoryLambda;
    }

    public void setMarkDirtyNotificationLambda(Notify markDirtyNotificationLambda) {
        this.markDirtyNotificationLambda = markDirtyNotificationLambda;
    }

    public void setOpenInventoryNotificationLambda(Notify openInventoryNotificationLambda) {
        this.openInventoryNotificationLambda = openInventoryNotificationLambda;
    }

    public void setCloseInventoryNotificationLambda(Notify closeInventoryNotificationLambda) {
        this.closeInventoryNotificationLambda = closeInventoryNotificationLambda;
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return canPlayerAccessInventoryLambda.test(player);  // on the client, this does nothing. on the server, ask our parent TileEntity.
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return chestContents.isItemValid(index, stack);
    }

    // ----- Methods used to inform the parent tile entity that something has happened to the contents
    //  you can make direct calls to the parent if you like, I've used lambdas because I think it shows the separation
    //   of responsibilities more clearly.

    @FunctionalInterface
    public interface Notify {   // Some folks use Runnable, but I prefer not to use it for non-thread-related tasks
        void invoke();
    }

    @Override
    public void markDirty() {
        markDirtyNotificationLambda.invoke();
    }

    @Override
    public void openInventory(PlayerEntity player) {
        openInventoryNotificationLambda.invoke();
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        closeInventoryNotificationLambda.invoke();
    }

    //---------These following methods are called by Vanilla container methods to manipulate the inventory contents ---

    @Override
    public int getSizeInventory() {
        return chestContents.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < chestContents.getSlots(); ++i) {
            if (!chestContents.getStackInSlot(i).isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return chestContents.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return chestContents.extractItem(index, count, false);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        int maxPossibleItemStackSize = chestContents.getSlotLimit(index);
        return chestContents.extractItem(index, maxPossibleItemStackSize, false);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        chestContents.setStackInSlot(index, stack);
    }

    @Override
    public void clear() {
        for (int i = 0; i < chestContents.getSlots(); ++i) {
            chestContents.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    private Predicate<PlayerEntity> canPlayerAccessInventoryLambda = x-> true;

    private Notify markDirtyNotificationLambda = ()->{};

    private Notify openInventoryNotificationLambda = ()->{};

    private Notify closeInventoryNotificationLambda = ()->{};

    private final ItemStackHandler chestContents;
}
