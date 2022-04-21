package com.gempire.items;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import java.util.Random;

import net.minecraft.world.item.Item.Properties;

public class TestItem extends Item {

    public TestItem(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) {
            return InteractionResult.PASS;
        }
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Random random = new Random();
        if(world.getBlockState(pos) == Blocks.GRASS_BLOCK.defaultBlockState()){
            if(random.nextBoolean()) {
                world.setBlockAndUpdate(pos.above(), Blocks.BROWN_MUSHROOM.defaultBlockState());
            }
            else{
                world.setBlockAndUpdate(pos.above(), Blocks.RED_MUSHROOM.defaultBlockState());
            }
        }
        return InteractionResult.PASS;
    }
}
