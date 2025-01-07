package com.gempire.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

public class ItemTankBlock extends BlockItem {
    public ItemTankBlock(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
    }

    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> p_40553_, TooltipFlag p_40554_) {
        if(level != null) {
            if (level.isClientSide) {
                /*if (checkTags(itemStack)) {
                    if (!itemStack.getTag().getString("abilities").isEmpty()) {
                        if (Screen.hasShiftDown()) {
                            if (!itemStack.getTag().getString("name").isEmpty()) {
                                p_40553_.add(Component.translatable(itemStack.getTag().getString("name")).withStyle(ChatFormatting.GRAY));
                            }
                            if (!itemStack.getTag().getString("facetCut").isEmpty()) {
                                String[] string = itemStack.getTag().getString("facetCut").split(",");
                                p_40553_.add(Component.translatable(string[0]).withStyle(ChatFormatting.GRAY));
                            }
                            if (!itemStack.getTag().getString("facetCut").isEmpty()) {
                                String[] string = itemStack.getTag().getString("facetCut").split(",");
                                p_40553_.add(Component.translatable(string[1]).withStyle(ChatFormatting.GRAY));
                            }
                            String[] util = itemStack.getTag().getString("util").split(",");
                            if (Boolean.parseBoolean(util[2])) {
                                p_40553_.add(Component.translatable("Rebel").withStyle(ChatFormatting.RED));
                            }
                            String[] crackShatter = itemStack.getTag().getString("crackShatter").split(",");
                            if (Boolean.parseBoolean(crackShatter[0])) {
                                p_40553_.add(Component.translatable("Cracked").withStyle(ChatFormatting.RED));
                            }
                            if (Integer.parseInt(crackShatter[3]) >= 5) {
                                p_40553_.add(Component.translatable("Sludged").withStyle(ChatFormatting.RED));
                            }
                            if (!itemStack.getTag().getString("assignedID").isEmpty()) {
                                p_40553_.add(Component.translatable("Assigned" + itemStack.getTag().getUUID("assignedID"))); //to " + assigned_gem.getName().getString() + " " + assigned_gem.getFacetAndCut()));
                            }
                            if (Integer.parseInt(util[3]) == 2) {
                                p_40553_.add(Component.translatable("Perfect").withStyle(ChatFormatting.LIGHT_PURPLE));
                            }
                            if (Integer.parseInt(util[3]) == 0) {
                                p_40553_.add(Component.translatable("Off Colour").withStyle(ChatFormatting.LIGHT_PURPLE));
                            }
                        } else {
                            if (!itemStack.getTag().getString("name").equals(" ")) {
                                p_40553_.add(Component.translatable(itemStack.getTag().getString("name")).withStyle(ChatFormatting.GRAY));
                            }
                            String[] crackShatter = itemStack.getTag().getString("crackShatter").split(",");
                            if (Boolean.parseBoolean(crackShatter[0])) {
                                p_40553_.add(Component.translatable("Cracked").withStyle(ChatFormatting.RED));
                            }
                            if (Integer.parseInt(crackShatter[3]) >= 5) {
                                p_40553_.add(Component.translatable("Sludged").withStyle(ChatFormatting.RED));
                            }
                            p_40553_.add(Component.translatable("Hold Shift for more info").withStyle(ChatFormatting.GOLD));
                        }
                    }
                }*/
            }
        }
    }
}
