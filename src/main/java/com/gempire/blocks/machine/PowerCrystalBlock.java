package com.gempire.blocks.machine;

import com.gempire.blocks.markers.IPowerMarker;
import com.gempire.init.ModBlocks;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import com.gempire.tileentities.InjectorTE;
import com.gempire.tileentities.PowerCrystalTE;
import com.gempire.tileentities.PowerGeneratorTE;
import com.gempire.tileentities.WireTE;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
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

public class PowerCrystalBlock extends SixWayConnectorBlock implements IPowerMarker {
    public static final BooleanProperty INJECTOR = BooleanProperty.create("injector");

    public PowerCrystalBlock(float apothem, Properties properties) {
        super(apothem, properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(INJECTOR, false));
    }

    @Override
    public boolean facingMarker(BlockPos direction) {
        return true;
    }

    @Override
    public boolean typeMarker(Block predicate) {
        return predicate instanceof IPowerMarker;
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
    public BlockState makeConnections(IBlockReader blockReader, BlockPos pos) {
        Block block = blockReader.getBlockState(pos.down()).getBlock();
        Block block1 = blockReader.getBlockState(pos.up()).getBlock();
        Block block2 = blockReader.getBlockState(pos.north()).getBlock();
        Block block3 = blockReader.getBlockState(pos.east()).getBlock();
        Block block4 = blockReader.getBlockState(pos.south()).getBlock();
        Block block5 = blockReader.getBlockState(pos.west()).getBlock();
        return this.getDefaultState()
                .with(DOWN, block == this || typeMarker(block) && facingMarker(pos.down()))
                .with(UP, block1 == this || typeMarker(block1) && facingMarker(pos.up()))
                .with(NORTH, block2 == this || typeMarker(block2) && facingMarker(pos.north()))
                .with(EAST, block3 == this || typeMarker(block3) && facingMarker(pos.east()))
                .with(SOUTH, block4 == this || typeMarker(block4) && facingMarker(pos.south()))
                .with(WEST, block5 == this || typeMarker(block5) && facingMarker(pos.west()))
                .with(INJECTOR, block == ModBlocks.TANK_BLOCK.get());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, INJECTOR);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(1D, 1D, 1D, 15D, 15D , 15D);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PowerCrystalTE();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
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
