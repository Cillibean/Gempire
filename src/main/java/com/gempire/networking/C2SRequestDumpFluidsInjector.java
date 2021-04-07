package com.gempire.networking;

import com.gempire.tileentities.InjectorTE;
import com.gempire.tileentities.TankTE;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class C2SRequestDumpFluidsInjector {
    public final BlockPos injectorPos;

    public C2SRequestDumpFluidsInjector(BlockPos injectorPos) {
        this.injectorPos = injectorPos;
    }

    public static C2SRequestDumpFluidsInjector decode(PacketBuffer buffer) {
        final BlockPos injector = buffer.readBlockPos();
        return new C2SRequestDumpFluidsInjector(injector);
    }

    public static void encode(C2SRequestDumpFluidsInjector msg, PacketBuffer buffer) {
        buffer.writeBlockPos(msg.injectorPos);
    }

    public static void handle(final C2SRequestDumpFluidsInjector msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            if(msg.injectorPos != null) {
                InjectorTE injector = (InjectorTE) sender.world.getTileEntity(msg.injectorPos);
                injector.DumpFluids();
            }/*
            if(msg.tankPos != null) {
                TankTE tank = (TankTE) sender.world.getTileEntity(msg.tankPos);
                tank.EmptyTank();
            }*/
        }
        ctx.setPacketHandled(true);
    }
}
