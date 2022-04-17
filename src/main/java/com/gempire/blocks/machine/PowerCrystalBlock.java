package com.gempire.blocks.machine;

import com.gempire.blocks.markers.IPowerMarker;
import com.gempire.init.ModBlocks;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import com.gempire.tileentities.InjectorTE;
import com.gempire.tileentities.PowerCrystalTE;
import com.gempire.tileentities.PowerGeneratorTE;
import com.gempire.tileentities.WireTE;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

public class PowerCrystalBlock extends SixWayConnectorBlock implements IPowerMarker, EntityBlock {
    public static final BooleanProperty INJECTOR = BooleanProperty.create("injector");

    public PowerCrystalBlock(float apothem, Properties properties) {
        super(apothem, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(INJECTOR, false));
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
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if(!worldIn.isClientSide()) {
            if (worldIn.getBlockState(pos.below()).getBlock() == ModBlocks.TANK_BLOCK.get()) {
                if (worldIn.getBlockState(pos.below().below().below()).getBlock() == ModBlocks.DRILL_BLOCK.get()) {
                    BlockEntity te = worldIn.getBlockEntity(pos.below().below().below());
                    if (te instanceof InjectorTE) {
                        NetworkHooks.openGui((ServerPlayer) player, (InjectorTE) te, pos.below().below().below());
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public BlockState makeConnections(BlockGetter blockReader, BlockPos pos) {
        Block block = blockReader.getBlockState(pos.below()).getBlock();
        Block block1 = blockReader.getBlockState(pos.above()).getBlock();
        Block block2 = blockReader.getBlockState(pos.north()).getBlock();
        Block block3 = blockReader.getBlockState(pos.east()).getBlock();
        Block block4 = blockReader.getBlockState(pos.south()).getBlock();
        Block block5 = blockReader.getBlockState(pos.west()).getBlock();
        return this.defaultBlockState()
                .setValue(DOWN, block == this || typeMarker(block) && facingMarker(pos.below()))
                .setValue(UP, block1 == this || typeMarker(block1) && facingMarker(pos.above()))
                .setValue(NORTH, block2 == this || typeMarker(block2) && facingMarker(pos.north()))
                .setValue(EAST, block3 == this || typeMarker(block3) && facingMarker(pos.east()))
                .setValue(SOUTH, block4 == this || typeMarker(block4) && facingMarker(pos.south()))
                .setValue(WEST, block5 == this || typeMarker(block5) && facingMarker(pos.west()))
                .setValue(INJECTOR, block == ModBlocks.TANK_BLOCK.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, INJECTOR);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(1D, 1D, 1D, 15D, 15D , 15D);
    }

    @SuppressWarnings("deprecation")
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public VoxelShape getVisualShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return Block.box(1D, 1D, 1D, 15D, 15D , 15D);
    }

    public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new PowerCrystalTE();
    }
}
