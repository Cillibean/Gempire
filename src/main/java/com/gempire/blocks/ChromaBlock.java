package com.gempire.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class ChromaBlock extends DirectionalBlock {
    protected static final VoxelShape CRYSTAL_VERTICAL_AABB = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    protected static final VoxelShape CRYSTAL_NS_AABB = Block.makeCuboidShape(1D, 1.0D, 0D, 15.0D, 15.0D, 16);
    protected static final VoxelShape CRYSTAL_EW_AABB = Block.makeCuboidShape(0.0D, 1D, 1D, 16.0D, 15.0D, 15);
    public int colour;

    public ChromaBlock(Properties properties, int color) {
        super(properties);
        this.colour = color;
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP));
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(FACING, mirrorIn.mirror(state.get(FACING)));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.get(FACING).getAxis()) {
            case X:
            default:
                return CRYSTAL_EW_AABB;
            case Z:
                return CRYSTAL_NS_AABB;
            case Y:
                return CRYSTAL_VERTICAL_AABB;
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getFace();
        BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(direction.getOpposite()));
        return blockstate.isIn(this) && blockstate.get(FACING) == direction ? this.getDefaultState().with(FACING, direction.getOpposite()) : this.getDefaultState().with(FACING, direction);
    }


    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        Direction direction = stateIn.get(FACING);
        double d0 = (double)pos.getX() + 1 - (double)(rand.nextFloat());
        double d1 = (double)pos.getY() + 1 - (double)(rand.nextFloat());
        double d2 = (double)pos.getZ() + 1 - (double)(rand.nextFloat());
        double d3 = (double)(0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);
        if (rand.nextInt(5) == 0) {
            worldIn.addParticle(ParticleTypes.END_ROD, d0 + (double)direction.getXOffset() * d3, d1 + (double)direction.getYOffset() * d3, d2 + (double)direction.getZOffset() * d3, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
        }
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Direction direction = state.get(FACING);
        if (state.getBlock() == this) { //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            if (direction == Direction.UP) {
                if (worldIn.getBlockState(pos.down()) != Blocks.AIR.getDefaultState()) {
                    return super.isValidPosition(state, worldIn, pos);
                }
            }
            if(direction == Direction.DOWN){
                if(worldIn.getBlockState(pos.up()) != Blocks.AIR.getDefaultState()){
                    return super.isValidPosition(state, worldIn, pos);
                }
            }
            if(direction == Direction.NORTH){
                if(worldIn.getBlockState(pos.south()) != Blocks.AIR.getDefaultState()){
                    return super.isValidPosition(state, worldIn, pos);
                }
            }
            if(direction == Direction.EAST){
                if(worldIn.getBlockState(pos.west()) != Blocks.AIR.getDefaultState()){
                    return super.isValidPosition(state, worldIn, pos);
                }
            }
            if(direction == Direction.SOUTH){
                if(worldIn.getBlockState(pos.north()) != Blocks.AIR.getDefaultState()){
                    return super.isValidPosition(state, worldIn, pos);
                }
            }
            if(direction == Direction.WEST){
                if(worldIn.getBlockState(pos.east()) != Blocks.AIR.getDefaultState()){
                    return super.isValidPosition(state, worldIn, pos);
                }
            }
        }
        return false;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.NORMAL;
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return 5 + (fortune * 2);
    }
}
