package com.gempire.datagen;

import com.gempire.Gempire;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        /*ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TUNGSTEN_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.TUNGSTEN_INGOT.get())
                .unlockedBy("has_tungsten", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModItems.TUNGSTEN_INGOT.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TUNGSTEN_INGOT.get(), 9)
                .requires(ModBlocks.TUNGSTEN_BLOCK.get())
                .unlockedBy("has_tungsten_block", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.TUNGSTEN_BLOCK.get()).build()))
                .save(pWriter);
*/
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.TUNGSTEN_INGOT.get(), RecipeCategory.MISC, ModBlocks.TUNGSTEN_BLOCK.get(),
                "gempire:tungsten_ingot", "tungsten","gempire:tungsten_block", "tungsten");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RAW_TUNGSTEN.get(), RecipeCategory.MISC, ModBlocks.RAW_TUNGSTEN_BLOCK.get(),
                "gempire:raw_tungsten", "tungsten","gempire:raw_tungsten_block", "tungsten");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.PYRITE_INGOT.get(), RecipeCategory.MISC, ModBlocks.PYRITE_BLOCK.get(),
                "gempire:pyrite_ingot", "pyrite","gempire:pyrite_block", "pyrite");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RAW_PYRITE.get(), RecipeCategory.MISC, ModBlocks.RAW_PYRITE_BLOCK.get(),
                "gempire:raw_pyrite", "pyrite","gempire:raw_pyrite_block", "pyrite");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.THULITE_INGOT.get(), RecipeCategory.MISC, ModBlocks.THULITE_BLOCK.get(),
                "gempire:thulite_ingot", "thulite","gempire:thulite_block", "thulite");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RAW_THULITE.get(), RecipeCategory.MISC, ModBlocks.RAW_THULITE_BLOCK.get(),
                "gempire:raw_thulite", "thulite","gempire:raw_thulite_block", "thulite");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.ANATASE_INGOT.get(), RecipeCategory.MISC, ModBlocks.ANATASE_BLOCK.get(),
                "gempire:anatase_ingot", "anatase","gempire:anatase_block", "anatase");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RAW_ANATASE.get(), RecipeCategory.MISC, ModBlocks.RAW_ANATASE_BLOCK.get(),
                "gempire:raw_anatase", "anatase","gempire:raw_anatase_block", "anatase");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.ELECTRUM_INGOT.get(), RecipeCategory.MISC, ModBlocks.ELECTRUM_BLOCK.get(),
                "gempire:electrum_ingot", "electrum","gempire:electrum_block", "electrum");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RAW_ELECTRUM.get(), RecipeCategory.MISC, ModBlocks.RAW_ELECTRUM_BLOCK.get(),
                "gempire:raw_electrum", "electrum","gempire:raw_electrum_block", "electrum");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.PLATINUM_INGOT.get(), RecipeCategory.MISC, ModBlocks.PLATINUM_BLOCK.get(),
                "gempire:platinum_ingot", "platinum","gempire:platinum_block", "platinum");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RAW_PLATINUM.get(), RecipeCategory.MISC, ModBlocks.RAW_PLATINUM_BLOCK.get(),
                "gempire:raw_platinum", "platinum","gempire:raw_platinum_block", "platinum");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.PRISMATIC_INGOT.get(), RecipeCategory.MISC, ModBlocks.PRISMATIC_BLOCK.get(),
                "gempire:prismatic_ingot", "prismatic","gempire:prismatic_block", "prismatic");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer,
                                     List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                            pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, Gempire.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
