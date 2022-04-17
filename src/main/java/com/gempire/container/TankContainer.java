package com.gempire.container;

import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import com.gempire.tileentities.TankTE;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.inventory.ContainerLevelAccess;

import java.util.Objects;

public class TankContainer extends AbstractContainerMenu {
    public static final int HOTBAR_SLOT_COUNT = 9;
    public static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    public static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    public static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    public static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public static final int VANILLA_FIRST_SLOT_INDEX = 0;
    public static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    public static final int TE_INVENTORY_SLOT_COUNT = TankTE.NUMBER_OF_SLOTS;

    public static final int TILE_INVENTORY_YPOS = 20;
    public static final int PLAYER_INVENTORY_YPOS = 51;

    public final ContainerLevelAccess canInteract;

    public final TankTE tank;

    public TankContainer(int windowID, Inventory playerInventory, TankTE tank) {
        super(ModContainers.TANK_CONTAINER.get(), windowID);
        this.tank = tank;
        this.canInteract = ContainerLevelAccess.create(this.tank.getLevel(), this.tank.getBlockPos());

        //TILE ENTITY
        this.addSlot(new Slot((Container)this.tank, TankTE.BUCKET_INPUT_SLOT_INDEX, 78, 50){
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new Slot((Container)this.tank, TankTE.BUCKET_OUTPUT_SLOT_INDEX, 96, 50){
            public int getMaxStackSize() {
                return 1;
            }
        });

        //PLAYER INVENTORY
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 9; col++){
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 160 - (4 - row) * 18 - 10));
            }
        }

        //PLAYER HOTBAR
        for(int col = 0; col < 9; col++){
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 136));
        }
    }

    public TankContainer(int windowID, Inventory playerInventory, net.minecraft.network.FriendlyByteBuf extraData){
        this(windowID, playerInventory, TankContainer.getTileEntity(playerInventory, extraData));
    }

    public static TankTE getTileEntity(Inventory playerInventory, FriendlyByteBuf extraData){
        Objects.requireNonNull(playerInventory, "Player Inventory can not be null");
        Objects.requireNonNull(playerInventory, "Data Packet can not be null");
        BlockEntity te = playerInventory.player.level.getBlockEntity(extraData.readBlockPos());
        if(te instanceof TankTE){
            return (TankTE)te;
        }
        throw new IllegalStateException("Tile entity is not correct");
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return this.stillValid(this.canInteract, playerIn, ModBlocks.TANK_BLOCK.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack slotStack = slot.getItem();
            stack = slotStack.copy();
            if(index < TankTE.NUMBER_OF_SLOTS && !this.moveItemStackTo(slotStack, TankTE.NUMBER_OF_SLOTS, this.slots.size(), true)){
                return ItemStack.EMPTY;
            }
            if(!this.moveItemStackTo(slotStack, 0, TankTE.NUMBER_OF_SLOTS, false)){
                return ItemStack.EMPTY;
            }
            if(slotStack.isEmpty()){
                slot.set(ItemStack.EMPTY);
            }
            else{
                slot.setChanged();
            }
        }
        return stack;
    }
}
