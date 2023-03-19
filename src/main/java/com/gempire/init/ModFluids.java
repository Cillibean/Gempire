package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.fluids.FluidRegistryContainer;
import com.gempire.util.ModItemGroup;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Gempire.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Gempire.MODID);

    public static final FluidRegistryContainer PINK_ESSENCE = new FluidRegistryContainer(
            "pink_essence",
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Gempire.MODID,
                            "pink_essence"
                    ).tint(0xFFFFFF)
                            .fogColor(1.0f, 0.4f, 0.7f)
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties()
                    .tab(ModItemGroup.BLOCKS)
                    .stacksTo(1)
    );

    public static final FluidRegistryContainer WHITE_ESSENCE = new FluidRegistryContainer(
            "white_essence",
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Gempire.MODID,
                            "white_essence"
                    ).tint(0xFFFFFF)
                            .fogColor(1.0f, 1f, 1f)
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties()
                    .tab(ModItemGroup.BLOCKS)
                    .stacksTo(1)
    );

    public static final FluidRegistryContainer YELLOW_ESSENCE = new FluidRegistryContainer(
            "yellow_essence",
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Gempire.MODID,
                            "yellow_essence"
                    ).tint(0xFFFFFF)
                            .fogColor(1.0f, 1f, .7f)
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties()
                    .tab(ModItemGroup.BLOCKS)
                    .stacksTo(1)
    );

    public static final FluidRegistryContainer BLUE_ESSENCE = new FluidRegistryContainer(
            "blue_essence",
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Gempire.MODID,
                            "blue_essence"
                    ).tint(0xFFFFFF)
                            .fogColor(.3f, .3f, 1f)
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties()
                    .tab(ModItemGroup.BLOCKS)
                    .stacksTo(1)
    );
}