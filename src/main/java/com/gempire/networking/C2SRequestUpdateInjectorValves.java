package com.gempire.networking;

import com.gempire.tileentities.InjectorTE;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestUpdateInjectorValves {
    public final String color;
    public final BlockPos injectorPos;

    public C2SRequestUpdateInjectorValves(String pinkValve, BlockPos pos) {
        this.color = pinkValve;
        this.injectorPos = pos;
    }

    public static C2SRequestUpdateInjectorValves decode(PacketBuffer buffer) {
        final String color = buffer.readString();
        final BlockPos injector = buffer.readBlockPos();
        return new C2SRequestUpdateInjectorValves(color, injector);
    }

    public static void encode(C2SRequestUpdateInjectorValves msg, PacketBuffer buffer) {
        buffer.writeString(msg.color);
        buffer.writeBlockPos(msg.injectorPos);
    }

    public static void handle(final C2SRequestUpdateInjectorValves msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            InjectorTE injector = (InjectorTE)sender.world.getTileEntity(msg.injectorPos);
            injector.ToggleTankOpen(msg.color);
        }
        ctx.setPacketHandled(true);
    }
}
