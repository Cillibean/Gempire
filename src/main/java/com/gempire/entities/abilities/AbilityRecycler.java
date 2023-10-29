package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbilityRecycler extends Ability implements IIdleAbility {
    //TODO: IMPLEMENT RECYCLER
    // uncrafter basically

    RecipeManager manager;
    public AbilityRecycler() {
        super(15, 7);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.recycler");
    }

    public ArrayList<ItemStack> getList() {
            if (!this.holder.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                manager = holder.level.getRecipeManager();
                ItemStack stack = this.holder.getItemBySlot(EquipmentSlot.MAINHAND);
                Item item = stack.getItem();
                Collection<Recipe<?>> collection = manager.getRecipes();
                ArrayList<Recipe> list = new ArrayList<>();
                ArrayList<Ingredient> listi = new ArrayList<>();
                ArrayList<ItemStack> listi2 = new ArrayList<>();
                for (Recipe recipe : collection) {
                    if (recipe.getResultItem(RegistryAccess.EMPTY).is(item)) {
                        list.add(recipe);
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

                return listi2;
            }
        return null;
    }


    @Override
    public void execute() {
        runRecycle(getList());
    }

    public void runRecycle(ArrayList<ItemStack> list) {
        if (list != null && !holder.level.isClientSide) {
            holder.addToInventory(list);
            holder.setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
        }
    }
}
