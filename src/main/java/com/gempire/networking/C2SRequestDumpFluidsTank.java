package com.gempire.networking;

import com.gempire.tileentities.InjectorTE;
import com.gempire.tileentities.TankTE;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestDumpFluidsTank {
    public final BlockPos tankPos;

    public C2SRequestDumpFluidsTank(BlockPos tankPos) {
        this.tankPos = tankPos;
    }

    public static C2SRequestDumpFluidsTank decode(FriendlyByteBuf buffer) {
        final BlockPos injector = buffer.readBlockPos();
        return new C2SRequestDumpFluidsTank(injector);
    }

    public static void encode(C2SRequestDumpFluidsTank msg, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(msg.tankPos);
    }

    public static void handle(final C2SRequestDumpFluidsTank msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            if(msg.tankPos != null) {
                TankTE tank = (TankTE) sender.level.getBlockEntity(msg.tankPos);
                tank.EmptyTank();
            }
        }
        ctx.setPacketHandled(true);
    }
}
