package com.gempire.datagen;

import com.gempire.Gempire;
import com.gempire.init.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
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
        Advancement rootAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.PINK_ESSENCE_BUCKET.get()),
                        Component.translatable("advancements.gempire.root.title"), Component.translatable("advancements.gempire.root.description"),
                        new ResourceLocation(Gempire.MODID, "textures/block/ruined_marble_chiseled.png"), FrameType.TASK,
                        false, false, false))
                .save(saver, new ResourceLocation(Gempire.MODID, "gempire"), existingFileHelper);

        Advancement essenceAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.WHITE_ESSENCE_BUCKET.get()),
                        Component.translatable("advancements.gempire.essence.title"), Component.translatable("advancements.gempire.essence.description"),
                        null, FrameType.TASK,
                        false, true, false))
                .addCriterion("has_essence", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PINK_ESSENCE_BUCKET.get(), ModItems.WHITE_ESSENCE_BUCKET.get(), ModItems.YELLOW_ESSENCE_BUCKET.get(), ModItems.BLUE_ESSENCE_BUCKET.get()))
                .save(saver, new ResourceLocation(Gempire.MODID, "gempire"), existingFileHelper);
    }
}
