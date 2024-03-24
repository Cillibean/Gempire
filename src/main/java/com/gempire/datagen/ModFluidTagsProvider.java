package com.gempire.datagen;

import com.gempire.Gempire;
import com.gempire.fluids.ModFluidTags;
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
        tag(ModFluidTags.BLUE_ESSENCE)
                .add(ModFluids.BLUE_ESSENCE.get(),
                        ModFluids.BLUE_ESSENCE_FLOWING.get()
                );
        tag(ModFluidTags.PINK_ESSENCE)
                .add(ModFluids.PINK_ESSENCE.get(),
                        ModFluids.PINK_ESSENCE_FLOWING.get()
                );
        tag(ModFluidTags.YELLOW_ESSENCE)
                .add(ModFluids.YELLOW_ESSENCE.get(),
                        ModFluids.YELLOW_ESSENCE_FLOWING.get()
                );
        tag(ModFluidTags.WHITE_ESSENCE)
                .add(ModFluids.WHITE_ESSENCE.get(),
                        ModFluids.WHITE_ESSENCE_FLOWING.get()
                );
    }
}
