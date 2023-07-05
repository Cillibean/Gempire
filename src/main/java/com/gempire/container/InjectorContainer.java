package com.gempire.container;

import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import com.gempire.init.ModFluids;
import com.gempire.init.ModItems;
import com.gempire.items.ItemChroma;
import com.gempire.items.ItemGem;
import com.gempire.tileentities.InjectorTE;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Objects;

public class InjectorContainer extends AbstractContainerMenu {
    public static final int HOTBAR_SLOT_COUNT = 9;
    public static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    public static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    public static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    public static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public static final int VANILLA_FIRST_SLOT_INDEX = 0;
    public static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    public static final int TILE_INVENTORY_YPOS = 20;
    public static final int PLAYER_INVENTORY_YPOS = 51;

    public static ArrayList<Item> primer = new ArrayList<>();

    public final ContainerLevelAccess canInteract;

    public final InjectorTE injector;

    public InjectorContainer(int windowID, Inventory playerInventory, InjectorTE injector) {
        super(ModContainers.INJECTOR_CONTAINER.get(), windowID);
        this.injector = injector;
        this.canInteract = ContainerLevelAccess.create(Objects.requireNonNull(this.injector.getLevel()), this.injector.getBlockPos());

        //TILE ENTITY
        this.injector.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, InjectorTE.WHITE_INPUT_SLOT_INDEX, 61, 14) {
                public boolean mayPlace(ItemStack stack) {
                    return (stack.getItem() == ModItems.WHITE_ESSENCE_BUCKET.get());
                }
            });
            this.addSlot(new SlotItemHandler(handler, InjectorTE.YELLOW_INPUT_SLOT_INDEX, 43    , 32){
                public boolean mayPlace(ItemStack stack) {
                    return (stack.getItem() == ModItems.YELLOW_ESSENCE_BUCKET.get());
                }
            });
            this.addSlot(new SlotItemHandler(handler, InjectorTE.CHROMA_INPUT_SLOT_INDEX, 61, 32){
                public boolean mayPlace(ItemStack stack) {
                    return (stack.getItem() instanceof ItemChroma);
                }
            });
            this.addSlot(new SlotItemHandler(handler, InjectorTE.BLUE_INPUT_SLOT_INDEX, 79, 32){
                public boolean mayPlace(ItemStack stack) {
                    return (stack.getItem() == ModItems.BLUE_ESSENCE_BUCKET.get());
                }
            });
            //this.addSlot(new SlotItemHandler(handler, InjectorTE.PRIME_INPUT_SLOT_INDEX, 43, 50));
            this.addSlot(new SlotItemHandler(handler, InjectorTE.PINK_INPUT_SLOT_INDEX, 61, 50){
                public boolean mayPlace(ItemStack stack) {
                    return (stack.getItem() == ModItems.PINK_ESSENCE_BUCKET.get());
                }
            });
            this.addSlot(new SlotItemHandler(handler, InjectorTE.PRIME_INPUT_SLOT_INDEX, 43, 50){
                public boolean mayPlace(ItemStack stack) {
                    return primer.contains(stack.getItem());
                };
            });
        });
        //PLAYER INVENTORY
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 9; col++){
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 165 - (4 - row) * 18 - 10));
            }
        }

        //PLAYER HOTBAR
        for(int col = 0; col < 9; col++){
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 141));
        }
    }

    public InjectorContainer(int windowID, Inventory playerInventory, FriendlyByteBuf extraData){
        this(windowID, playerInventory, InjectorContainer.getTileEntity(playerInventory, extraData));
    }

    public static InjectorTE getTileEntity(Inventory playerInventory, FriendlyByteBuf extraData){
        Objects.requireNonNull(playerInventory, "Player Inventory can not be null");
        Objects.requireNonNull(extraData, "Data Packet can not be null");
        BlockEntity te = playerInventory.player.level.getBlockEntity(extraData.readBlockPos());
        if(te instanceof InjectorTE){
            return (InjectorTE)te;
        }
        throw new IllegalStateException("Tile entity is not correct");
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(this.canInteract, playerIn, ModBlocks.TANK_BLOCK.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack slotStack = slot.getItem();
            stack = slotStack.copy();
            if(index < InjectorTE.NUMBER_OF_SLOTS && !this.moveItemStackTo(slotStack, InjectorTE.NUMBER_OF_SLOTS, this.slots.size(), true)){
                return ItemStack.EMPTY;
            }
            if(!this.moveItemStackTo(slotStack, 0, InjectorTE.NUMBER_OF_SLOTS, false)){
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