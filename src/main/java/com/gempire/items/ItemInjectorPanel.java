package com.gempire.items;

import com.gempire.blocks.machine.DrillBlock;
import com.gempire.blocks.machine.TankBlock;
import com.gempire.tileentities.InjectorTE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.List;

public class ItemInjectorPanel extends Item {
    int colorselected = 0;

    public ItemInjectorPanel(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide && (hand == InteractionHand.MAIN_HAND)) {
            BlockHitResult ray = getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
            BlockPos lookPos = ray.getBlockPos();
            if (world.getBlockState(lookPos).getBlock() instanceof TankBlock) {
                BlockPos tankPos = world.getBlockState(lookPos).getValue(TankBlock.HALF) == DoubleBlockHalf.UPPER ? lookPos.below() : lookPos;
                    InjectorTE te = (InjectorTE) world.getBlockEntity(tankPos);
                    if (te != null) {
                        if(!player.isCrouching()) {
                            switch (colorselected) {
                                case 0 -> {
                                    te.pinkOpen = !te.pinkOpen;
                                    player.sendSystemMessage(Component.translatable(te.pinkOpen ? "Pink Tank Open" : "Pink Tank Closed"));

                                }
                                case 1 -> {
                                    te.blueOpen = !te.blueOpen;
                                    player.sendSystemMessage(Component.translatable(te.blueOpen ? "Blue Tank Open" : "Blue Tank Closed"));
                                }
                                case 2 -> {
                                    te.yellowOpen = !te.yellowOpen;
                                    player.sendSystemMessage(Component.translatable(te.yellowOpen ? "Yellow Tank Open" : "Yellow Tank Closed"));
                                }
                                case 3 -> {
                                    te.whiteOpen = !te.whiteOpen;
                                    player.sendSystemMessage(Component.translatable(te.whiteOpen ? "White Tank Open" : "White Tank Closed"));
                                }
                            }
                        }
                        else {
                            List<String> selectedColors = new ArrayList<>();
                            if (te.pinkOpen) {
                                selectedColors.add("Pink");
                            }
                            if (te.blueOpen) {
                                selectedColors.add("Blue");
                            }
                            if (te.yellowOpen) {
                                selectedColors.add("Yellow");
                            }
                            if (te.whiteOpen) {
                                selectedColors.add("White");
                            }
                            String colorString;
                            if (selectedColors.isEmpty()) {
                                colorString = "No tanks are open.";
                            } else if (selectedColors.size() == 1) {
                                colorString = selectedColors.get(0) + " is open.";
                            } else if (selectedColors.size() == 2) {
                                colorString = selectedColors.get(0) + " and " + selectedColors.get(1) + " are open.";
                            } else {
                                String lastColor = selectedColors.get(selectedColors.size() - 1);
                                selectedColors.remove(selectedColors.size() - 1);
                                String allButLastColors = String.join(", ", selectedColors);
                                colorString = allButLastColors + ", and " + lastColor + " are open.";
                            }
                            player.sendSystemMessage(Component.translatable(colorString));
                        }
                }
            } else {
                final String[] COLORS = {"Pink Selected", "Blue Selected", "Yellow Selected", "White Selected"};
                colorselected = (colorselected + 1) % COLORS.length;
                player.sendSystemMessage(Component.translatable(COLORS[colorselected]));
            }
        }
        return super.use(world, player, hand);
    }
}
