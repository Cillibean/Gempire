package com.gempire.networking;

import com.gempire.tileentities.InjectorTE;
import com.gempire.tileentities.TankTE;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestDumpFluidsTank {
    public final BlockPos tankPos;

    public C2SRequestDumpFluidsTank(BlockPos tankPos) {
        this.tankPos = tankPos;
    }

    public static C2SRequestDumpFluidsTank decode(PacketBuffer buffer) {
        final BlockPos injector = buffer.readBlockPos();
        return new C2SRequestDumpFluidsTank(injector);
    }

    public static void encode(C2SRequestDumpFluidsTank msg, PacketBuffer buffer) {
        buffer.writeBlockPos(msg.tankPos);
    }

    public static void handle(final C2SRequestDumpFluidsTank msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            if(msg.tankPos != null) {
                TankTE tank = (TankTE) sender.world.getTileEntity(msg.tankPos);
                tank.EmptyTank();
            }
        }
        ctx.setPacketHandled(true);
    }
}
