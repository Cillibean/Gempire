package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;

public class AbilityTinkerer extends Ability implements IIdleAbility {

    public AbilityTinkerer(){
        this.ability = Abilities.TINKERER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.tinkerer");
    }


    @Override
    public void execute() {
        if (holder.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PickaxeItem ||
                holder.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof AxeItem ||
                holder.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof SwordItem ||
                holder.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ShovelItem ||
                holder.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof HoeItem ||
                holder.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ArmorItem) {
            ItemStack stack = holder.getItemBySlot(EquipmentSlot.MAINHAND);
            Item item = holder.getItemBySlot(EquipmentSlot.MAINHAND).getItem();
            if (item == Items.WOODEN_PICKAXE) {
                if (holder.consumeItemCheck(Items.STONE, 1)) {
                    ItemStack item2 = Items.STONE_PICKAXE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.STONE_PICKAXE) {
                if (holder.consumeItemCheck(Items.IRON_INGOT, 1)) {
                    ItemStack item2 = Items.IRON_PICKAXE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.IRON_PICKAXE) {
                if (holder.consumeItemCheck(Items.GOLD_INGOT, 1)) {
                    ItemStack item2 = Items.GOLDEN_PICKAXE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.GOLDEN_PICKAXE) {
                if (holder.consumeItemCheck(Items.DIAMOND, 1)) {
                    ItemStack item2 = Items.DIAMOND_PICKAXE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.DIAMOND_PICKAXE) {
                if (holder.consumeItemCheck(Items.NETHERITE_INGOT, 1)) {
                    ItemStack item2 = Items.NETHERITE_PICKAXE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.WOODEN_AXE) {
                if (holder.consumeItemCheck(Items.STONE, 1)) {
                    ItemStack item2 = Items.STONE_AXE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.STONE_AXE) {
                if (holder.consumeItemCheck(Items.IRON_INGOT, 1)) {
                    ItemStack item2 = Items.IRON_AXE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.IRON_AXE) {
                if (holder.consumeItemCheck(Items.GOLD_INGOT, 1)) {
                    ItemStack item2 = Items.GOLDEN_AXE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.GOLDEN_AXE) {
                if (holder.consumeItemCheck(Items.DIAMOND, 1)) {
                    ItemStack item2 = Items.DIAMOND_AXE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.DIAMOND_AXE) {
                if (holder.consumeItemCheck(Items.NETHERITE_INGOT, 1)) {
                    ItemStack item2 = Items.NETHERITE_AXE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.WOODEN_SHOVEL) {
                if (holder.consumeItemCheck(Items.STONE, 1)) {
                    ItemStack item2 = Items.STONE_SHOVEL.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.STONE_SHOVEL) {
                if (holder.consumeItemCheck(Items.IRON_INGOT, 1)) {
                    ItemStack item2 = Items.IRON_SHOVEL.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.IRON_SHOVEL) {
                if (holder.consumeItemCheck(Items.GOLD_INGOT, 1)) {
                    ItemStack item2 = Items.GOLDEN_SHOVEL.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.GOLDEN_SHOVEL) {
                if (holder.consumeItemCheck(Items.DIAMOND, 1)) {
                    ItemStack item2 = Items.DIAMOND_SHOVEL.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.DIAMOND_SHOVEL) {
                if (holder.consumeItemCheck(Items.NETHERITE_INGOT, 1)) {
                    ItemStack item2 = Items.NETHERITE_SHOVEL.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.WOODEN_HOE) {
                if (holder.consumeItemCheck(Items.STONE, 1)) {
                    ItemStack item2 = Items.STONE_HOE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.STONE_HOE) {
                if (holder.consumeItemCheck(Items.IRON_INGOT, 1)) {
                    ItemStack item2 = Items.IRON_HOE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.IRON_HOE) {
                if (holder.consumeItemCheck(Items.GOLD_INGOT, 1)) {
                    ItemStack item2 = Items.GOLDEN_HOE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.GOLDEN_HOE) {
                if (holder.consumeItemCheck(Items.DIAMOND, 1)) {
                    ItemStack item2 = Items.DIAMOND_HOE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.DIAMOND_HOE) {
                if (holder.consumeItemCheck(Items.NETHERITE_INGOT, 1)) {
                    ItemStack item2 = Items.NETHERITE_HOE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.WOODEN_SWORD) {
                if (holder.consumeItemCheck(Items.STONE, 1)) {
                    ItemStack item2 = Items.STONE_SWORD.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.STONE_SWORD) {
                if (holder.consumeItemCheck(Items.IRON_INGOT, 1)) {
                    ItemStack item2 = Items.IRON_SWORD.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.IRON_SWORD) {
                if (holder.consumeItemCheck(Items.GOLD_INGOT, 1)) {
                    ItemStack item2 = Items.GOLDEN_SWORD.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.GOLDEN_SWORD) {
                if (holder.consumeItemCheck(Items.DIAMOND, 1)) {
                    ItemStack item2 = Items.DIAMOND_SWORD.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.DIAMOND_SWORD) {
                if (holder.consumeItemCheck(Items.NETHERITE_INGOT, 1)) {
                    ItemStack item2 = Items.NETHERITE_SWORD.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.LEATHER_BOOTS) {
                if (holder.consumeItemCheck(Items.IRON_INGOT, 2)) {
                    ItemStack item2 = Items.IRON_BOOTS.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.IRON_BOOTS) {
                if (holder.consumeItemCheck(Items.GOLD_INGOT, 2)) {
                    ItemStack item2 = Items.GOLDEN_BOOTS.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.GOLDEN_BOOTS) {
                if (holder.consumeItemCheck(Items.DIAMOND, 2)) {
                    ItemStack item2 = Items.DIAMOND_BOOTS.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.DIAMOND_BOOTS) {
                if (holder.consumeItemCheck(Items.NETHERITE_INGOT, 1)) {
                    ItemStack item2 = Items.NETHERITE_BOOTS.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.LEATHER_LEGGINGS) {
                if (holder.consumeItemCheck(Items.IRON_INGOT, 4)) {
                    ItemStack item2 = Items.IRON_LEGGINGS.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.IRON_LEGGINGS) {
                if (holder.consumeItemCheck(Items.GOLD_INGOT, 4)) {
                    ItemStack item2 = Items.GOLDEN_LEGGINGS.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.GOLDEN_LEGGINGS) {
                if (holder.consumeItemCheck(Items.DIAMOND, 4)) {
                    ItemStack item2 = Items.DIAMOND_LEGGINGS.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.DIAMOND_LEGGINGS) {
                if (holder.consumeItemCheck(Items.NETHERITE_INGOT, 1)) {
                    ItemStack item2 = Items.NETHERITE_LEGGINGS.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.LEATHER_CHESTPLATE) {
                if (holder.consumeItemCheck(Items.IRON_INGOT, 5)) {
                    ItemStack item2 = Items.IRON_CHESTPLATE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.IRON_CHESTPLATE) {
                if (holder.consumeItemCheck(Items.GOLD_INGOT, 5)) {
                    ItemStack item2 = Items.GOLDEN_CHESTPLATE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.GOLDEN_CHESTPLATE) {
                if (holder.consumeItemCheck(Items.DIAMOND, 5)) {
                    ItemStack item2 = Items.DIAMOND_CHESTPLATE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.DIAMOND_CHESTPLATE) {
                if (holder.consumeItemCheck(Items.NETHERITE_INGOT, 1)) {
                    ItemStack item2 = Items.NETHERITE_CHESTPLATE.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.LEATHER_HELMET) {
                if (holder.consumeItemCheck(Items.IRON_INGOT, 3)) {
                    ItemStack item2 = Items.IRON_HELMET.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.IRON_HELMET) {
                if (holder.consumeItemCheck(Items.GOLD_INGOT, 3)) {
                    ItemStack item2 = Items.GOLDEN_HELMET.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.GOLDEN_HELMET) {
                if (holder.consumeItemCheck(Items.DIAMOND, 3)) {
                    ItemStack item2 = Items.DIAMOND_HELMET.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            } else if (item == Items.DIAMOND_HELMET) {
                if (holder.consumeItemCheck(Items.NETHERITE_INGOT, 1)) {
                    ItemStack item2 = Items.NETHERITE_HELMET.getDefaultInstance();
                    item2.setTag(stack.getTag());
                    holder.setItemSlot(EquipmentSlot.MAINHAND, item2);
                }
            }
        }
    }
}
