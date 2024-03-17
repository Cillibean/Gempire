package com.gempire.items;

import com.gempire.entities.other.*;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ItemAltarUpgrade extends Item {
    public ItemAltarUpgrade(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide) {
            if (level.getBlockState(context.getClickedPos()) == ModBlocks.PINK_ALTAR.get().defaultBlockState()) {
                if (context.getItemInHand().getItem() == ModItems.IRIDESCENT_FLOWER.get()) {
                    level.setBlockAndUpdate(context.getClickedPos(), ModBlocks.PRISMATIC_PINK_ALTAR.get().withPropertiesOf(level.getBlockState(context.getClickedPos())));
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    return InteractionResult.PASS;
                }
            } else if (level.getBlockState(context.getClickedPos()) == ModBlocks.BLUE_ALTAR.get().defaultBlockState()) {
                if (context.getItemInHand().getItem() == ModItems.MIRRORED_TEAR.get()) {
                    level.setBlockAndUpdate(context.getClickedPos(), ModBlocks.PRISMATIC_BLUE_ALTAR.get().withPropertiesOf(level.getBlockState(context.getClickedPos())));
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    return InteractionResult.PASS;
                }
            } else if (level.getBlockState(context.getClickedPos()) == ModBlocks.YELLOW_ALTAR.get().defaultBlockState()) {
                if (context.getItemInHand().getItem() == ModItems.GILDED_DAGGER.get()) {
                    level.setBlockAndUpdate(context.getClickedPos(), ModBlocks.PRISMATIC_YELLOW_ALTAR.get().withPropertiesOf(level.getBlockState(context.getClickedPos())));
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    return InteractionResult.PASS;
                }
            } else if (level.getBlockState(context.getClickedPos()) == ModBlocks.WHITE_ALTAR.get().defaultBlockState()) {
                if (context.getItemInHand().getItem() == ModItems.PRISMATIC_STAR.get()) {
                    level.setBlockAndUpdate(context.getClickedPos(), ModBlocks.PRISMATIC_WHITE_ALTAR.get().withPropertiesOf(level.getBlockState(context.getClickedPos())));
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    return InteractionResult.PASS;
                }
            }
        }
        return InteractionResult.FAIL;
    }
}
