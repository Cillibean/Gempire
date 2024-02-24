package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class AbilityTorchBearer extends Ability implements IIdleAbility {

    public AbilityTorchBearer(){
        super("torchbearer", 5);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.torchbearer");
    }

    @Override
    public void execute() {
        BlockPos pos = holder.getOnPos().above();
        BlockPos onPos = holder.getOnPos();
        Level level = holder.level();
        BlockState blockState = level.getBlockState(pos);
        BlockState onBlockState = level.getBlockState(onPos);
        if (holder.getOwned())
            if (blockState.getBlock() instanceof AirBlock || blockState.getBlock() instanceof SnowLayerBlock || blockState.getBlock() instanceof TallGrassBlock) {
                if (onBlockState.getBlock() instanceof AirBlock) {

                } else {
                    if (!holder.isInWater()) {
                        if (holder.onGround()) {
                            if (level.getBrightness(LightLayer.BLOCK, pos) <= 9) {
                                if (level.getBrightness(LightLayer.SKY, pos) <= 9) {
                                    if (holder.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.TORCH) {
                                        ItemStack stack = holder.getItemBySlot(EquipmentSlot.MAINHAND).copy();
                                        stack.setCount(stack.getCount()-1);
                                        holder.setItemSlot(EquipmentSlot.MAINHAND, stack);
                                        level.setBlock(pos, Blocks.TORCH.defaultBlockState(), 3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
