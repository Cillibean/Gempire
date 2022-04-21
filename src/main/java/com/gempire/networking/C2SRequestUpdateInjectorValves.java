package com.gempire.networking;

import com.gempire.tileentities.InjectorTE;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestUpdateInjectorValves {
    public final String color;
    public final BlockPos injectorPos;

    public C2SRequestUpdateInjectorValves(String pinkValve, BlockPos pos) {
        this.color = pinkValve;
        this.injectorPos = pos;
    }

    public static C2SRequestUpdateInjectorValves decode(FriendlyByteBuf buffer) {
        final String color = buffer.readUtf(32767);
        final BlockPos injector = buffer.readBlockPos();
        return new C2SRequestUpdateInjectorValves(color, injector);
    }

    public static void encode(C2SRequestUpdateInjectorValves msg, FriendlyByteBuf buffer) {
        buffer.writeUtf(msg.color);
        buffer.writeBlockPos(msg.injectorPos);
    }

    public static void handle(final C2SRequestUpdateInjectorValves msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            InjectorTE injector = (InjectorTE)sender.level.getBlockEntity(msg.injectorPos);
            injector.ToggleTankOpen(msg.color);
        }
        ctx.setPacketHandled(true);
    }
}
