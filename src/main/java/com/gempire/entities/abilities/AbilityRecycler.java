package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.google.gson.JsonObject;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

public class AbilityRecycler extends Ability implements IIdleAbility {
    //TODO: IMPLEMENT RECYCLER
    // uncrafter basically
    public AbilityRecycler() {
        super(15, 7);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.recycler");
    }

    public Recipe getRecipe() {
        if (!this.holder.level.isClientSide && this.holder.getItemBySlot(EquipmentSlot.MAINHAND) != Items.AIR.getDefaultInstance()) {
            ItemStack stack = this.holder.getItemBySlot(EquipmentSlot.MAINHAND);
            Item item = stack.getItem();
            Recipe recipe = RecipeManager.fromJson(new ResourceLocation(item.getCreatorModId(stack), item.getDescriptionId()), new JsonObject());
            System.out.println(recipe.getIngredients());
            return recipe;
        }
        return null;
    }


    @Override
    public void execute() {
        //getRecipe();
    }
}
