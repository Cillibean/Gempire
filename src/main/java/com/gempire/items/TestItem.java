package com.gempire.items;

import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.MushroomBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class TestItem extends Item {

    public TestItem(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResultType.PASS;
        }
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        Random random = new Random();
        if(world.getBlockState(pos) == Blocks.GRASS_BLOCK.getDefaultState()){
            if(random.nextBoolean()) {
                world.setBlockState(pos.up(), Blocks.BROWN_MUSHROOM.getDefaultState());
            }
            else{
                world.setBlockState(pos.up(), Blocks.RED_MUSHROOM.getDefaultState());
            }
        }
        return ActionResultType.PASS;
    }
}
