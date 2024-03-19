package com.gempire.init;

import com.gempire.brewing.BetterBrewingRecipe;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class ModBrewingRecipes {
    public static void addRecipes() {
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                ModItems.SHOCK_BERRY.get(), ModPotions.SHOCK_RESISTANCE_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                ModItems.AMBER_SHARDS.get(), ModPotions.ELECTROCUTION_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                ModItems.ALABASTER_SHARDS.get(), ModPotions.BLINDING_LIGHT_POTION.get()));
    }
}
