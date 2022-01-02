package com.gempire.tileentities;

import com.gempire.systems.injection.GemFormation;
import com.gempire.systems.machine.EnergyPackage;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.SocketType;
import com.gempire.systems.machine.interfaces.IPowerConductor;
import com.gempire.systems.machine.interfaces.IPowerGenerator;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class PowerGeneratorTE extends PowerProviderTE implements IPowerGenerator {

    public PowerGeneratorTE(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
