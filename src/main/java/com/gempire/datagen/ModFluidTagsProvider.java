package com.gempire.datagen;

import com.gempire.Gempire;
import com.gempire.init.ModFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModFluidTagsProvider extends FluidTagsProvider {
    public ModFluidTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, Gempire.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(FluidTags.WATER)
                .add(ModFluids.PINK_ESSENCE.get())
                .add(ModFluids.PINK_ESSENCE_FLOWING.get())
                .add(ModFluids.BLUE_ESSENCE.get())
                .add(ModFluids.BLUE_ESSENCE_FLOWING.get())
                .add(ModFluids.YELLOW_ESSENCE.get())
                .add(ModFluids.YELLOW_ESSENCE_FLOWING.get())
                .add(ModFluids.WHITE_ESSENCE.get())
                .add(ModFluids.WHITE_ESSENCE_FLOWING.get());
    }
}
