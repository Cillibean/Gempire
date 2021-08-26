package com.gempire.container;

import com.gempire.Gempire;
import com.gempire.gui.ChromaSlot;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import com.gempire.init.ModItems;
import com.gempire.tileentities.InjectorTE;
import com.gempire.tileentities.ShellTE;
import com.gempire.tileentities.TankTE;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

public class ShellContainer extends Container {
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
    public static final ResourceLocation GRAVEL_PREVIEW = new ResourceLocation(Gempire.MODID,"textures/gui/gravel_preview.png");
    public static final ResourceLocation SAND_PREVIEW = new ResourceLocation(Gempire.MODID,"textures/gui/sand_preview.png");
    public static final ResourceLocation CLAY_PREVIEW = new ResourceLocation(Gempire.MODID,"textures/gui/clay_preview.png");
    public static final ResourceLocation CHROMA_PREVIEW = new ResourceLocation(Gempire.MODID,"textures/gui/chroma_preview.png");
    public static final ResourceLocation PREVIEW_ATLAS = new ResourceLocation(Gempire.MODID,"textures/gui/preview_atlas.png");

    public final IWorldPosCallable canInteract;

    public final ShellTE shell;

    public ShellContainer(int windowID, PlayerInventory playerInventory, ShellTE shell) {
        super(ModContainers.SHELL_CONTAINER.get(), windowID);
        this.shell = shell;
        this.canInteract = IWorldPosCallable.of(this.shell.getWorld(), this.shell.getPos());

        //TILE ENTITY
        this.addSlot(new Slot(this.shell, ShellTE.GRAVEL_INPUT_SLOT_INDEX, 132, 12){
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() == Blocks.GRAVEL.asItem();
            }
        });
        this.addSlot(new Slot(this.shell, ShellTE.SAND_INPUT_SLOT_INDEX, 114, 30){
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() == Blocks.SAND.asItem();
            }
        });
        this.addSlot(new Slot(this.shell, ShellTE.PEARL_OUTPUT_SLOT_INDEX, 132, 30){
            public boolean isItemValid(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.shell, ShellTE.CLAY_INPUT_SLOT_INDEX, 150, 30){
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() == Items.CLAY_BALL;
            }
        });
        this.addSlot(new Slot(this.shell, ShellTE.CHROMA_INPUT_SLOT_INDEX, 132, 48){
            public boolean isItemValid(ItemStack stack) {
                return (stack.getItem() instanceof com.gempire.items.ItemChroma);
            }
            public int getSlotStackLimit() {
                return 1;
            }
        });

        //PLAYER INVENTORY
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 9; col++){
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 176 - (4 - row) * 18 - 10));
            }
        }

        //PLAYER HOTBAR
        for(int col = 0; col < 9; col++){
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 152));
        }
    }

    public ShellContainer(int windowID, PlayerInventory playerInventory, PacketBuffer extraData){
        this(windowID, playerInventory, ShellContainer.getTileEntity(playerInventory, extraData));
    }

    public static ShellTE getTileEntity(PlayerInventory playerInventory, PacketBuffer extraData){
        Objects.requireNonNull(playerInventory, "Player Inventory can not be null");
        Objects.requireNonNull(extraData, "Data Packet can not be null");
        TileEntity te = playerInventory.player.world.getTileEntity(extraData.readBlockPos());
        if(te instanceof ShellTE){
            return (ShellTE)te;
        }
        throw new IllegalStateException("Tile entity is not correct");
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.isWithinUsableDistance(this.canInteract, playerIn, ModBlocks.SHELL_BLOCK.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();
            if(index < ShellTE.NUMBER_OF_SLOTS && !this.mergeItemStack(slotStack, ShellTE.NUMBER_OF_SLOTS, this.inventorySlots.size(), true)){
                return ItemStack.EMPTY;
            }
            if(!this.mergeItemStack(slotStack, 0, ShellTE.NUMBER_OF_SLOTS, false)){
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
