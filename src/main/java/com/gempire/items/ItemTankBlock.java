package com.gempire.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ItemTankBlock extends BlockItem {
    public ItemTankBlock(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
    }

    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> p_40553_, TooltipFlag p_40554_) {
        if(level != null) {
            if (level.isClientSide) {
                if (itemStack.getOrCreateTag().get("BlockEntityTag") != null) {
                    String bET = itemStack.getOrCreateTag().get("BlockEntityTag").toString();
                    int pink = 0, yellow = 0, blue = 0, white = 0;
                    bET = bET.replace(",inventory:{Items:[],Size:6}", "");
                    String[] strings = bET.split("},");
                    for (String string : strings) {
                        string = string.replace("FluidName:\"minecraft:empty\"", "")
                                .replace("FluidName:\"gempire:white_essence_source\"", "")
                                .replace("FluidName:\"gempire:pink_essence_source\"", "")
                                .replace("FluidName:\"gempire:blue_essence_source\"", "")
                                .replace("FluidName:\"gempire:yellow_essence_source\"", "")
                                .replace("{", "").replace("}", "").replace(",", "")
                                .replace("Amount", "").replace("::", ",");
                        String[] strings2 = string.split(",");
                        if (strings2[0].equals("pinkTank")) pink = Integer.parseInt(strings2[1]);
                        if (strings2[0].equals("blueTank")) blue = Integer.parseInt(strings2[1]);
                        if (strings2[0].equals("yellowTank")) yellow = Integer.parseInt(strings2[1]);
                        if (strings2[0].equals("whiteTank")) white = Integer.parseInt(strings2[1]);

                    }

                    p_40553_.add(Component.translatable("Pink: " + pink + "mb").withStyle(ChatFormatting.LIGHT_PURPLE));
                    p_40553_.add(Component.translatable("Blue: " + blue + "mb").withStyle(ChatFormatting.BLUE));
                    p_40553_.add(Component.translatable("Yellow: " + yellow + "mb").withStyle(ChatFormatting.YELLOW));
                    p_40553_.add(Component.translatable("White: " + white + "mb"));
                } else {
                    p_40553_.add(Component.translatable("Empty").withStyle(ChatFormatting.GRAY));
                }
            }
        }
    }

}
