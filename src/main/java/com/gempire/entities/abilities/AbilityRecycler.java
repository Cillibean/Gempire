package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.init.ModItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbilityRecycler extends Ability implements IIdleAbility {

    RecipeManager manager;
    int timer = 0;
    public AbilityRecycler() {
        super("recycler", 7);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.recycler");
    }

    public ArrayList<ItemStack> getList() {
        if (!this.holder.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
            manager = holder.level().getRecipeManager();
            ItemStack stack = this.holder.getItemBySlot(EquipmentSlot.MAINHAND);
            Item item = stack.getItem();
            if (item instanceof ArmorItem || item instanceof PickaxeItem || item instanceof AxeItem ||
                    item instanceof SwordItem || item instanceof ShovelItem || item instanceof HoeItem) {
                if (item == Items.NETHERITE_AXE || item == Items.NETHERITE_PICKAXE ||
                        item == Items.NETHERITE_SWORD || item == Items.NETHERITE_SHOVEL ||
                        item == Items.NETHERITE_HOE || item == Items.NETHERITE_HELMET ||
                        item == Items.NETHERITE_LEGGINGS || item == Items.NETHERITE_BOOTS ||
                        item == Items.NETHERITE_CHESTPLATE || item == ModItems.PRISMATIC_AXE.get()
                        || item == ModItems.PRISMATIC_PICKAXE.get() || item == ModItems.PRISMATIC_SWORD.get() ||
                        item == ModItems.PRISMATIC_SHOVEL.get() || item == ModItems.PRISMATIC_HOE.get() ||
                        item == ModItems.GUARDIAN_HELMET.get() || item == ModItems.GUARDIAN_LEGGINGS.get() ||
                        item == ModItems.GUARDIAN_BOOTS.get() || item == ModItems.GUARDIAN_CHESTPLATE.get() ||
                        item == ModItems.PALADIN_HELMET.get() || item == ModItems.PALADIN_LEGGINGS.get() ||
                        item == ModItems.PALADIN_BOOTS.get() || item == ModItems.PALADIN_CHESTPLATE.get() ||
                        item == ModItems.HUNTRESS_HELMET.get() || item == ModItems.HUNTRESS_LEGGINGS.get() ||
                        item == ModItems.HUNTRESS_BOOTS.get() || item == ModItems.HUNTRESS_CHESTPLATE.get() ||
                        item == ModItems.EMPRESS_HELMET.get() || item == ModItems.EMPRESS_LEGGINGS.get() ||
                        item == ModItems.EMPRESS_BOOTS.get() || item == ModItems.EMPRESS_CHESTPLATE.get() ||
                        item == ModItems.PALADIN_AXE.get() || item == ModItems.GUARDIAN_SHIELD.get() ||
                        item == ModItems.HUNTRESS_SWORD.get() || item == ModItems.EMPRESS_BOW.get()) return null;
                Collection<Recipe<?>> collection = manager.getRecipes();
                ArrayList<Recipe> list = new ArrayList<>();
                ArrayList<Ingredient> listi = new ArrayList<>();
                ArrayList<ItemStack> listi2 = new ArrayList<>();
                ArrayList<Item> listi3 = new ArrayList<>();
                ArrayList<ItemStack> listi4 = new ArrayList<>();
                list.clear();
                listi.clear();
                listi2.clear();
                for (Recipe recipe : collection) {
                    if (!(recipe instanceof SmithingTrimRecipe)) {
                        if (recipe.getResultItem(RegistryAccess.EMPTY).is(item)) {
                            list.add(recipe);
                        }
                    }
                }
                for (Recipe recipe : list) {
                    for (Object item2 : recipe.getIngredients()) {
                        listi.add((Ingredient) item2);
                    }
                }
                for (Ingredient ingredient : listi) {
                    listi2.addAll(List.of(ingredient.getItems()));
                }
                for (ItemStack stack2 : listi2) {
                    listi3.add(stack2.getItem());
                }
                Item cobblestone = Items.COBBLESTONE;
                Item deepslate = Items.COBBLED_DEEPSLATE;
                Item blackstone = Items.BLACKSTONE;
                Item stick = Items.STICK;
                if (listi3.contains(cobblestone) && listi3.contains(deepslate) && listi3.contains(blackstone)) {
                    listi3.remove(deepslate);
                    listi3.remove(blackstone);
                    listi3.remove(deepslate);
                    listi3.remove(blackstone);
                    listi3.remove(deepslate);
                    listi3.remove(blackstone);
                }
                listi3.remove(stick);
                listi3.remove(stick);
                for (Item item2 : listi3) {
                    listi4.add(new ItemStack(item2));
                }
                return listi4;
            }
        }
        return null;
    }


    @Override
    public void execute() {
        if (!holder.level().isClientSide) {
            if (timer >= 10) {
                runRecycle(getList());
                timer = 0;
            } else timer++;
            System.out.println(timer);
        }
    }

    public void runRecycle(ArrayList<ItemStack> list) {
        if (list != null) {
            holder.addToInventory(list);
            holder.setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
        }
    }
}
