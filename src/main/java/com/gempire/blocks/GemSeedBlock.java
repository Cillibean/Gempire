package com.gempire.blocks;

import com.gempire.init.ModTE;
import com.gempire.items.ItemChroma;
import com.gempire.systems.injection.GemFormation;
import com.gempire.tileentities.GemSeedTE;
import com.gempire.tileentities.InjectorTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class GemSeedBlock extends Block {
    public ItemChroma chroma;
    public Item primer;
    public Fluid[] essences;

    public GemSeedBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GemSeedTE();
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        super.onExplosionDestroy(worldIn, pos, explosionIn);
        GemFormation form = new GemFormation(worldIn, pos, new BlockPos(5, 5, 5), this.chroma, this.primer, this.essences);
        form.SpawnGem();
        worldIn.destroyBlock(pos, false);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
