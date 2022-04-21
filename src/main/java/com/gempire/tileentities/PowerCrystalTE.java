package com.gempire.tileentities;

import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.init.ModTE;
import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.util.Debug;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;

public class PowerCrystalTE extends PowerGeneratorTE {

    public PowerCrystalTE(BlockPos pos, BlockState state) {
        super(ModTE.POWER_CRYSTAL_TE.get(), pos, state);
        setVoltage(9);
        setupInitialSockets(this);
        setupSocket(0, Socket.POWER_OUT(MachineSide.BOTTOM), this);
        setupSocket(1, Socket.POWER_OUT(MachineSide.TOP), this);
        setupSocket(2, Socket.POWER_OUT(MachineSide.BACK), this);
        setupSocket(3, Socket.POWER_OUT(MachineSide.FRONT), this);
        setupSocket(4, Socket.POWER_OUT(MachineSide.LEFT), this);
        setupSocket(5, Socket.POWER_OUT(MachineSide.RIGHT), this);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        PowerCrystalTE te = (PowerCrystalTE)be;
        if(!level.isClientSide()) {
            te.generatePower();
        }
    }

    @Override
    public void generatePower() {
        Random rand = new Random();
        if(getLevel().isDay()) {
            getBattery().chargeBattery(1);
        }
        /*if(rand.nextInt(12000) == 0){
            getWorld().destroyBlock(getPos(), false);
        }*/
        /*AxisAlignedBB box = new AxisAlignedBB(getPos().getX(), getPos().getY(), getPos().getZ(),
                getPos().getX() + 1, getPos().getY() + 1, getPos().getZ() + 1).grow(4);
        List<EntityMica> entities = getWorld().getEntitiesWithinAABB(EntityMica.class, box);
        for(EntityMica mica : entities){
            if(mica.getPlaying()){
                getBattery().chargeBattery(1);
            }
        }*/
    }

    @Override
    public BlockEntity getTE() {
        return this;
    }
}
