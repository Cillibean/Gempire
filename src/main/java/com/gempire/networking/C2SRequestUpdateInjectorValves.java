package com.gempire.networking;

import com.gempire.tileentities.InjectorTE;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestUpdateInjectorValves {
    public final String color;
    private static BlockPos pos;

    public C2SRequestUpdateInjectorValves(String pinkValve, BlockPos pos) {
        this.color = pinkValve;
        C2SRequestUpdateInjectorValves.pos = pos;
    }

    public static C2SRequestUpdateInjectorValves decode(FriendlyByteBuf buffer) {
        final String color = buffer.readUtf(32767);
        final BlockPos injector = buffer.readBlockPos();
        return new C2SRequestUpdateInjectorValves(color, injector);
    }

    public static void encode(C2SRequestUpdateInjectorValves msg, FriendlyByteBuf buffer) {
        buffer.writeUtf(msg.color);
        buffer.writeBlockPos(pos);
    }

    public static void handle(final C2SRequestUpdateInjectorValves msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        assert Minecraft.getInstance().level != null;
        if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof InjectorTE blockEntity) {
            boolean hasPermission = true;
            blockEntity.ToggleTankOpen(msg.color);
            ctx.setPacketHandled(true);
        }
    }
}
