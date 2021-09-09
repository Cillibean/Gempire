package com.gempire.container;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityZircon;
import com.gempire.init.ModContainers;
import com.mojang.datafixers.util.Pair;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

public class ZirconUIContainer extends Container {
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

    public final IWorldPosCallable canInteract;

    public final EntityZircon gem;

    public ZirconUIContainer(int windowID, PlayerInventory playerInventory, EntityZircon gem) {
        super(ModContainers.ZIRCON_UI_CONTAINER.get(), windowID);
        this.gem = gem;
        this.canInteract = IWorldPosCallable.of(this.gem.world, this.gem.getPosition());

        this.addSlot(new Slot(gem, 0, 38, 83){
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() == Items.LAPIS_LAZULI;
            }
            public int getSlotStackLimit() {
                return 32;
            }
        });

        this.addSlot(new Slot(gem, 1, 97, 83));

        this.addSlot(new Slot(gem, 2, 156, 83));

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

    public ZirconUIContainer(int windowID, PlayerInventory playerInventory, PacketBuffer extraData){
        this(windowID, playerInventory, ZirconUIContainer.getGem(playerInventory, extraData));
    }

    public static EntityZircon getGem(PlayerInventory playerInventory, PacketBuffer extraData){
        Objects.requireNonNull(playerInventory, "Player Inventory can not be null");
        Objects.requireNonNull(extraData, "Data Packet can not be null");
        int ID = extraData.readInt();
        Entity entity = playerInventory.player.world.getEntityByID(ID);
        if(entity instanceof EntityZircon){
            return (EntityZircon) entity;
        }
        throw new IllegalStateException("Entity is not correct");
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return gem.isOwner(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();
            if(index < EntityZircon.NUMBER_OF_SLOTS && !this.mergeItemStack(slotStack, EntityZircon.NUMBER_OF_SLOTS - 1, this.inventorySlots.size(), true)){
                return ItemStack.EMPTY;
            }
            if(!this.mergeItemStack(slotStack, 0, EntityZircon.NUMBER_OF_SLOTS - 1, false)){
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
