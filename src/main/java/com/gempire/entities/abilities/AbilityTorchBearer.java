package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.util.GempireAbilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class AbilityTorchBearer extends Ability implements IIdleAbility {

    public AbilityTorchBearer(){
        super(24, 5);
        this.ability = GempireAbilities.TORCHBEARER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.torchbearer");
    }

    @Override
    public void execute() {
        BlockPos pos = holder.getOnPos().above();
        BlockPos onPos = holder.getOnPos();
        Level level = holder.getLevel();
        BlockState blockState = level.getBlockState(pos);
        BlockState onBlockState = level.getBlockState(onPos);
        if (holder.getOwned())
            if (blockState.getBlock() instanceof AirBlock || blockState.getBlock() instanceof SnowLayerBlock || blockState.getBlock() instanceof TallGrassBlock) {
                if (onBlockState.getBlock() instanceof AirBlock) {

                } else {
                    if (!holder.isInWater()) {
                        if (holder.isOnGround()) {
                            if (level.getBrightness(LightLayer.BLOCK, pos) <= 9) {
                                if (level.getBrightness(LightLayer.SKY, pos) <= 9) {
                                    if (holder.consumeItemCheck(Items.TORCH, 1)) {
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
