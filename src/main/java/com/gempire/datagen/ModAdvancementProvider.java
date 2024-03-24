package com.gempire.datagen;

import com.gempire.Gempire;
import com.gempire.init.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        /*
        Advancement rootAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.PINK_ESSENCE_BUCKET.get()),
                        Component.literal(""), Component.literal("The Power lies in the Alexandrite!"),
                        new ResourceLocation(Gempire.MODID, "textures/block/alexandrite_ore.png"), FrameType.TASK,
                        true, true, false))
                .addCriterion("has_alexandrite", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ALEXANDRITE.get()))
                .save(saver, new ResourceLocation(Gempire.MODID, "gempire"), existingFileHelper);*/
    }
}
