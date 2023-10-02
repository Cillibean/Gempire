package com.gempire.container;

import com.gempire.Gempire;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import com.gempire.init.ModItems;
import com.gempire.tileentities.IncubatorTE;
import com.gempire.tileentities.IncubatorTE;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.Objects;

public class IncubatorContainer extends AbstractContainerMenu {
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

    public final IncubatorTE incubator;
    private final Level level;
    private final ContainerData data;

    public static ArrayList<Item> blocks = new ArrayList<>();

    public IncubatorContainer(int windowId, Inventory inv, FriendlyByteBuf extraData) {
        this(windowId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public IncubatorContainer(int windowID, Inventory playerInventory, BlockEntity entity, ContainerData data) {
        super(ModContainers.INCUBATOR_CONTAINER.get(), windowID);
        checkContainerSize(playerInventory, 10);
        incubator = (IncubatorTE) entity;
        this.level = playerInventory.player.level;
        this.data = data;

        addDataSlots(data);

        this.canInteract = ContainerLevelAccess.create(this.incubator.getLevel(), this.incubator.getBlockPos());

        //TILE ENTITY
        this.addSlot(new Slot(this.incubator, IncubatorTE.BLOCK1_INPUT_SLOT_INDEX, 21, 17){
            public boolean mayPlace(ItemStack stack) {
                return blocks.contains(stack.getItem());
            }
        });
        this.addSlot(new Slot(this.incubator, IncubatorTE.PRIMER_INPUT_SLOT_INDEX, 39, 17){
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == ModItems.PRIME_BOOST.get() || stack.getItem() == ModItems.GILDED_LAPIS.get();
            }
        });
        this.addSlot(new Slot(this.incubator, IncubatorTE.BLOCK2_INPUT_SLOT_INDEX, 57, 17){
            public boolean mayPlace(ItemStack stack) {return blocks.contains(stack.getItem());}
        });
        this.addSlot(new Slot(this.incubator, IncubatorTE.BLOCK3_INPUT_SLOT_INDEX, 21, 35){
            public boolean mayPlace(ItemStack stack) {
                return blocks.contains(stack.getItem());
            }
        });
        this.addSlot(new Slot(this.incubator, IncubatorTE.GEM_BASE_INPUT_SLOT_INDEX, 39, 35){
            public boolean mayPlace(ItemStack stack) {
                return (stack.getItem() instanceof com.gempire.items.ItemGemBase);
            }
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.incubator, IncubatorTE.BLOCK4_INPUT_SLOT_INDEX, 57, 35){
            public boolean mayPlace(ItemStack stack) {
                return blocks.contains(stack.getItem());
            }
        });
        this.addSlot(new Slot(this.incubator, IncubatorTE.ESSENCE1_INPUT_SLOT_INDEX, 21, 53){
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == ModItems.PINK_ESSENCE_BUCKET.get() ||
                        stack.getItem() == ModItems.YELLOW_ESSENCE_BUCKET.get() ||
                        stack.getItem() == ModItems.BLUE_ESSENCE_BUCKET.get() ||
                        stack.getItem() == ModItems.WHITE_ESSENCE_BUCKET.get();
            }
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.incubator, IncubatorTE.CHROMA_INPUT_SLOT_INDEX, 39, 53){
            public boolean mayPlace(ItemStack stack) {
                return (stack.getItem() instanceof com.gempire.items.ItemChroma);
            }
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.incubator, IncubatorTE.ESSENCE2_INPUT_SLOT_INDEX, 57, 53){
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == ModItems.PINK_ESSENCE_BUCKET.get() ||
                        stack.getItem() == ModItems.YELLOW_ESSENCE_BUCKET.get() ||
                        stack.getItem() == ModItems.BLUE_ESSENCE_BUCKET.get() ||
                        stack.getItem() == ModItems.WHITE_ESSENCE_BUCKET.get();
            }
            public int getMaxStackSize() {
                return 1;
            }
        });

        this.addSlot(new Slot(this.incubator, IncubatorTE.GEM_OUTPUT_SLOT_INDEX, 116, 35){
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        //PLAYER INVENTORY
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 9; col++){
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        //PLAYER HOTBAR
        for(int col = 0; col < 9; col++){
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public static IncubatorTE getTileEntity(Inventory playerInventory, FriendlyByteBuf extraData){
        Objects.requireNonNull(playerInventory, "Player Inventory can not be null");
        Objects.requireNonNull(extraData, "Data Packet can not be null");
        BlockEntity te = playerInventory.player.level.getBlockEntity(extraData.readBlockPos());
        if(te instanceof IncubatorTE){
            return (IncubatorTE)te;
        }
        throw new IllegalStateException("Tile entity is not correct");
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return this.stillValid(this.canInteract, playerIn, ModBlocks.INCUBATOR_BLOCK.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack slotStack = slot.getItem();
            stack = slotStack.copy();
            if(index < IncubatorTE.NUMBER_OF_SLOTS && !this.moveItemStackTo(slotStack, IncubatorTE.NUMBER_OF_SLOTS, this.slots.size(), true)){
                return ItemStack.EMPTY;
            }
            if(!this.moveItemStackTo(slotStack, 0, IncubatorTE.NUMBER_OF_SLOTS, false)){
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
