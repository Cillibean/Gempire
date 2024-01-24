package com.gempire.container;

import com.gempire.Gempire;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import com.gempire.tileentities.ShellTE;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public class ShellContainer extends AbstractContainerMenu {
    public static final int HOTBAR_SLOT_COUNT = 9;
    public static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    public static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    public static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    public static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public static final int VANILLA_FIRST_SLOT_INDEX = 0;
    public static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    public static final int TILE_INVENTORY_YPOS = 20;
    public static final int PLAYER_INVENTORY_YPOS = 51;
    public static final ResourceLocation GRAVEL_PREVIEW = new ResourceLocation(Gempire.MODID,"textures/gui/gravel_preview.png");
    public static final ResourceLocation SAND_PREVIEW = new ResourceLocation(Gempire.MODID,"textures/gui/sand_preview.png");
    public static final ResourceLocation CLAY_PREVIEW = new ResourceLocation(Gempire.MODID,"textures/gui/clay_preview.png");
    public static final ResourceLocation CHROMA_PREVIEW = new ResourceLocation(Gempire.MODID,"textures/gui/chroma_preview.png");
    public static final ResourceLocation PREVIEW_ATLAS = new ResourceLocation(Gempire.MODID,"textures/gui/preview_atlas.png");

    public final ContainerLevelAccess canInteract;

    public final ShellTE shell;

    public ShellContainer(int windowID, Inventory playerInventory, ShellTE shell) {
        super(ModContainers.SHELL_CONTAINER.get(), windowID);
        this.shell = shell;
        this.canInteract = ContainerLevelAccess.create(this.shell.getLevel(), this.shell.getBlockPos());

        //TILE ENTITY
        this.addSlot(new Slot(this.shell, ShellTE.GRAVEL_INPUT_SLOT_INDEX, 132, 12){
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == Blocks.GRAVEL.asItem();
            }
        });
        this.addSlot(new Slot(this.shell, ShellTE.SAND_INPUT_SLOT_INDEX, 114, 30){
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == Blocks.SAND.asItem();
            }
        });
        this.addSlot(new Slot(this.shell, ShellTE.PEARL_OUTPUT_SLOT_INDEX, 132, 30){
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.shell, ShellTE.CLAY_INPUT_SLOT_INDEX, 150, 30){
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == Items.CLAY_BALL;
            }
        });
        this.addSlot(new Slot(this.shell, ShellTE.CHROMA_INPUT_SLOT_INDEX, 132, 48){
            public boolean mayPlace(ItemStack stack) {
                return (stack.getItem() instanceof com.gempire.items.ItemChroma);
            }
            public int getMaxStackSize() {
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

    public ShellContainer(int windowID, Inventory playerInventory, FriendlyByteBuf extraData){
        this(windowID, playerInventory, ShellContainer.getTileEntity(playerInventory, extraData));
    }

    public static ShellTE getTileEntity(Inventory playerInventory, FriendlyByteBuf extraData){
        Objects.requireNonNull(playerInventory, "Player Inventory can not be null");
        Objects.requireNonNull(extraData, "Data Packet can not be null");
        BlockEntity te = playerInventory.player.level().getBlockEntity(extraData.readBlockPos());
        if(te instanceof ShellTE){
            return (ShellTE)te;
        }
        throw new IllegalStateException("Tile entity is not correct");
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return this.stillValid(this.canInteract, playerIn, ModBlocks.SHELL_BLOCK.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack slotStack = slot.getItem();
            stack = slotStack.copy();
            if(index < ShellTE.NUMBER_OF_SLOTS && !this.moveItemStackTo(slotStack, ShellTE.NUMBER_OF_SLOTS, this.slots.size(), true)){
                return ItemStack.EMPTY;
            }
            if(!this.moveItemStackTo(slotStack, 0, ShellTE.NUMBER_OF_SLOTS, false)){
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
