package com.gempire.container;

import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import com.gempire.tileentities.TankTE;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class GemUIContainer extends Container {
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

    public final IWorldPosCallable canInteract;

    public final TankTE tank;

    public GemUIContainer(int windowID, PlayerInventory playerInventory, TankTE tank) {
        super(ModContainers.TANK_CONTAINER.get(), windowID);
        this.tank = tank;
        this.canInteract = IWorldPosCallable.of(this.tank.getWorld(), this.tank.getPos());

        //TILE ENTITY
        this.addSlot(new Slot((IInventory)this.tank, TankTE.BUCKET_INPUT_SLOT_INDEX, 78, 50));
        this.addSlot(new Slot((IInventory)this.tank, TankTE.BUCKET_OUTPUT_SLOT_INDEX, 96, 50));

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

    public GemUIContainer(int windowID, PlayerInventory playerInventory, PacketBuffer extraData){
        this(windowID, playerInventory, GemUIContainer.getTileEntity(playerInventory, extraData));
    }

    public static TankTE getTileEntity(PlayerInventory playerInventory, PacketBuffer extraData){
        Objects.requireNonNull(playerInventory, "Player Inventory can not be null");
        Objects.requireNonNull(playerInventory, "Data Packet can not be null");
        TileEntity te = playerInventory.player.world.getTileEntity(extraData.readBlockPos());
        if(te instanceof TankTE){
            return (TankTE)te;
        }
        throw new IllegalStateException("Tile entity is not correct");
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.isWithinUsableDistance(this.canInteract, playerIn, ModBlocks.TANK_BLOCK.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();
            if(index < TankTE.NUMBER_OF_SLOTS && !this.mergeItemStack(slotStack, TankTE.NUMBER_OF_SLOTS, this.inventorySlots.size(), true)){
                return ItemStack.EMPTY;
            }
            if(!this.mergeItemStack(slotStack, 0, TankTE.NUMBER_OF_SLOTS, false)){
                return ItemStack.EMPTY;
            }
            if(slotStack.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            }
            else{
                slot.onSlotChanged();
            }
        }
        return stack;
    }
}
