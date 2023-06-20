package com.gempire.proxy;

import com.gempire.Gempire;
import com.gempire.init.ModFluids;
import com.gempire.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.gempire.Gempire.MODID;

@Mod.EventBusSubscriber(modid = Gempire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        DispenseItemBehavior dispenseBucket = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                DispensibleContainerItem container = (DispensibleContainerItem)stack.getItem();
                BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                Level level = source.getLevel();
                if (container.emptyContents(null, level, blockpos, null)) {
                    container.checkExtraContent(null, level, stack, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(source, stack);
                }
            }
        };

        event.enqueueWork(() -> {
            DispenserBlock.registerBehavior(ModItems.YELLOW_ESSENCE_BUCKET.get(), dispenseBucket);
            DispenserBlock.registerBehavior(ModItems.PINK_ESSENCE_BUCKET.get(), dispenseBucket);
            DispenserBlock.registerBehavior(ModItems.BLUE_ESSENCE_BUCKET.get(), dispenseBucket);
            DispenserBlock.registerBehavior(ModItems.WHITE_ESSENCE_BUCKET.get(), dispenseBucket);
        });
    }


}
