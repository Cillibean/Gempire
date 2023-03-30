package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.util.Abilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class AbilityTorchBearer extends Ability implements IIdleAbility {

    public AbilityTorchBearer(){
        this.ability = Abilities.TORCHBEARER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.torchbearer");
    }

    @Override
    public void execute() {
        BlockPos pos = holder.getOnPos().above();
        Level level = holder.getLevel();
        BlockState blockState = level.getBlockState(pos);
        if (holder.getOwned())
            if (blockState.getBlock() instanceof AirBlock || blockState.getBlock() instanceof SnowLayerBlock || blockState.getBlock() instanceof TallGrassBlock) {
                if (!holder.isInWater()) {
                    if (holder.isOnGround()) {
                        if (level.getBrightness(LightLayer.BLOCK, pos) <= 9) {
                            if (level.getBrightness(LightLayer.SKY, pos) <= 9) {
                                if (holder.consumeItemCheck(Items.TORCH)) {
                                    level.setBlock(pos, Blocks.TORCH.defaultBlockState(), 3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
