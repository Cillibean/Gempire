package com.gempire.tileentities;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.ModEntities;
import com.gempire.init.ModTE;
import com.gempire.systems.injection.GemFormation;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraftforge.fml.RegistryObject;

import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class GemSeedTE extends TileEntity implements ITickableTileEntity {
    Random random;
    boolean spawned = false;

    //haha
    public GemSeedTE() {
        super(ModTE.GEM_SEED_TE.get());
        random = new Random();
    }

    @Override
    public void tick() {
        if(this.random.nextInt(200) == 0){
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound = super.write(compound);
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
    }
}
