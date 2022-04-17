package com.gempire.blocks;

import com.gempire.init.ModTE;
import com.gempire.items.ItemChroma;
import com.gempire.systems.injection.GemFormation;
import com.gempire.tileentities.GemSeedTE;
import com.gempire.tileentities.InjectorTE;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class GemSeedBlock extends Block implements EntityBlock {

    public GemSeedBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GemSeedTE();
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        VoxelShape shape1 = Block.box(3, 3, 3, 13, 13, 13);
        return shape1;
    }


}
