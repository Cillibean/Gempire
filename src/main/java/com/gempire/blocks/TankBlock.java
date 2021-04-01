package com.gempire.blocks;

import com.gempire.tileentities.TankTE;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class TankBlock extends ContainerBlock {

    public TankBlock(Properties builder) {
        super(builder);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TankTE();
    }

    @SuppressWarnings("deprecated")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote()){
            TileEntity te = worldIn.getTileEntity(pos);
            if(te instanceof TankTE){
                NetworkHooks.openGui((ServerPlayerEntity)player, (TankTE)te, pos);
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
