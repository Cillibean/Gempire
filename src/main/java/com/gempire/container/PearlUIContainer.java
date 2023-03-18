package com.gempire.container;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.init.ModContainers;
import com.gempire.init.ModItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

public class PearlUIContainer extends AbstractContainerMenu {
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

    public final EntityPearl gem;
    public final DataSlot brewProgress = DataSlot.standalone();

    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");

    private static final ResourceLocation[] ARMOR_SLOT_TEXTURES = new ResourceLocation[]{EMPTY_ARMOR_SLOT_BOOTS, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_HELMET};
    private static final EquipmentSlot[] VALID_EQUIPMENT_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public PearlUIContainer(int windowID, Inventory playerInventory, EntityPearl gem) {
        super(ModContainers.PEARL_UI_CONTAINER.get(), windowID);
        this.gem = gem;
        this.canInteract = ContainerLevelAccess.create(this.gem.level, this.gem.blockPosition());

        //INITIALIZE ARMOR SLOTS
        for(int k = 0; k < 4; ++k) {
            final EquipmentSlot equipmentslottype = VALID_EQUIPMENT_SLOTS[k];
            this.addSlot(new Slot(gem, 54 + k, 272 + k*18, 9){

                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, PearlUIContainer.ARMOR_SLOT_TEXTURES[equipmentslottype.getIndex()]);
                }
                public boolean mayPlace(ItemStack stack) {
                    return stack.canEquip(equipmentslottype, gem);
                }
                public boolean mayPickup(Player playerIn) {
                    ItemStack itemstack = this.getItem();
                    return !itemstack.isEmpty() && !playerIn.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.mayPickup(playerIn);
                }
                public int getMaxStackSize() {
                    return 1;
                }
            });
        }

        //INITIALIZE GEM INVENTORY HERE
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 9; col++){
                this.addSlot(new Slot(gem, col + row * 9, 182 + col * 18, 135 - (6 - row) * 18));
            }
        }

        //PLAYER INVENTORY
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 9; col++){
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 95 + col * 18, 240 - (4 - row) * 18 - 10));
            }
        }

        //PLAYER HOTBAR
        for(int col = 0; col < 9; col++){
            this.addSlot(new Slot(playerInventory, col, 95 + col * 18, 216));
        }
    }

    public PearlUIContainer(int windowID, Inventory playerInventory, FriendlyByteBuf extraData){
        this(windowID, playerInventory, PearlUIContainer.getGem(playerInventory, extraData));
    }

    public static EntityPearl getGem(Inventory playerInventory, FriendlyByteBuf extraData){
        Objects.requireNonNull(playerInventory, "Player Inventory can not be null");
        Objects.requireNonNull(extraData, "Data Packet can not be null");
        int ID = extraData.readInt();
        Entity entity = playerInventory.player.level.getEntity(ID);
        if(entity instanceof EntityPearl){
            return (EntityPearl) entity;
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
            if(index < EntityPearl.NUMBER_OF_SLOTS_PEARL && !this.moveItemStackTo(slotStack, EntityPearl.NUMBER_OF_SLOTS_PEARL - 1, this.slots.size(), true)){
                return ItemStack.EMPTY;
            }
            if(!this.moveItemStackTo(slotStack, 0, EntityPearl.NUMBER_OF_SLOTS_PEARL - 1, false)){
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