package com.gempire.blocks.machine;

import com.gempire.blocks.machine.interfaces.IGenerator;
import com.gempire.blocks.markers.IPowerMarker;
import com.gempire.init.ModBlocks;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import com.gempire.tileentities.InjectorTE;
import com.gempire.tileentities.PowerCrystalTE;
import com.gempire.tileentities.PowerGeneratorTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class PowerCrystalBlock extends ContainerBlock implements IPowerMarker, IGenerator {

    public PowerCrystalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        IPowerGenerator generator = getGenerator(pos, worldIn);
        generator.emitPackage(false, generator, worldIn.getTileEntity(pos));
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote()) {
            if (worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.TANK_BLOCK.get()) {
                if (worldIn.getBlockState(pos.down().down().down()).getBlock() == ModBlocks.DRILL_BLOCK.get()) {
                    TileEntity te = worldIn.getTileEntity(pos.down().down().down());
                    if (te instanceof InjectorTE) {
                        NetworkHooks.openGui((ServerPlayerEntity) player, (InjectorTE) te, pos.down().down().down());
                        return ActionResultType.SUCCESS;
                    }
                }
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(worldIn.isRemote){
            return;
        }
        if(!(newState.getBlock() instanceof PowerCrystalBlock)){
            IPowerGenerator generator = getGenerator(pos, worldIn);
            generator.emitPackage(true, generator, worldIn.getTileEntity(pos));
            worldIn.removeTileEntity(pos);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(1D, 1D, 1D, 15D, 15D , 15D);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new PowerCrystalTE();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        IPowerGenerator generator = getGenerator(currentPos, worldIn);
        generator.emitPackage(false, generator, worldIn.getTileEntity(currentPos));
        return super.updatePostPlacement(stateIn,facing,facingState,worldIn,currentPos,facingPos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(1D, 1D, 1D, 15D, 15D , 15D);
    }

    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }
}
