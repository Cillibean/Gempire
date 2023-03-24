package com.gempire.container;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityZircon;
import com.gempire.init.ModContainers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ZirconUIContainer extends AbstractContainerMenu {
    public static final int HOTBAR_SLOT_COUNT = 9;
    public static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    public static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    public static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    public static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public static final int VANILLA_FIRST_SLOT_INDEX = 0;
    public static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    public static final int TE_INVENTORY_SLOT_COUNT = EntityGem.NUMBER_OF_SLOTS;

    public static final int TILE_INVENTORY_YPOS = 20;
    public static final int PLAYER_INVENTORY_YPOS = 51;

    public final ContainerLevelAccess canInteract;

    public final EntityZircon gem;

    public ZirconUIContainer(int windowID, Inventory playerInventory, EntityZircon gem) {
        super(ModContainers.ZIRCON_UI_CONTAINER.get(), windowID);
        this.gem = gem;
        this.canInteract = ContainerLevelAccess.create(this.gem.level, this.gem.blockPosition());

        this.addSlot(new Slot(gem, 0, 46, 83){
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == Items.LAPIS_LAZULI;
            }
            public int getMaxStackSize() {
                return 32;
            }
        });

        this.addSlot(new Slot(gem, 1, 66, 83));

        this.addSlot(new Slot(gem, 2, 86, 83));

        //PLAYER INVENTORY
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 9; col++){
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 25 + col * 18, 208 - (4 - row) * 18 - 10));
            }
        }

        //PLAYER HOTBAR
        for(int col = 0; col < 9; col++){
            this.addSlot(new Slot(playerInventory, col, 25 + col * 18, 184));
        }
    }

    public ZirconUIContainer(int windowID, Inventory playerInventory, FriendlyByteBuf extraData){
        this(windowID, playerInventory, ZirconUIContainer.getGem(playerInventory, extraData));
    }

    public static EntityZircon getGem(Inventory playerInventory, FriendlyByteBuf extraData){
        Objects.requireNonNull(playerInventory, "Player Inventory can not be null");
        Objects.requireNonNull(extraData, "Data Packet can not be null");
        int ID = extraData.readInt();
        Entity entity = playerInventory.player.level.getEntity(ID);
        if(entity instanceof EntityZircon){
            return (EntityZircon) entity;
        }
        throw new IllegalStateException("Entity is not correct");
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return gem.isOwner(playerIn);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack slotStack = slot.getItem();
            stack = slotStack.copy();
            if(index < EntityZircon.NUMBER_OF_SLOTS && !this.moveItemStackTo(slotStack, EntityZircon.NUMBER_OF_SLOTS - 1, this.slots.size(), true)){
                return ItemStack.EMPTY;
            }
            if(!this.moveItemStackTo(slotStack, 0, EntityZircon.NUMBER_OF_SLOTS - 1, false)){
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
