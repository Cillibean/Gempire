package com.gempire.networking;

import com.gempire.tileentities.InjectorTE;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestInject {
    public static BlockPos injectorPos;

    public C2SRequestInject(BlockPos pos) {
        injectorPos = pos;
    }

    public static C2SRequestInject decode(FriendlyByteBuf buffer) {
        final BlockPos injector = buffer.readBlockPos();
        return new C2SRequestInject(injector);
    }

    public static void encode(C2SRequestInject msg, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(injectorPos);
    }

    public static void handle(final C2SRequestInject msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            if (Minecraft.getInstance().level.getBlockEntity(injectorPos) instanceof InjectorTE blockEntity) {
                blockEntity.Inject();
            }
            ctx.setPacketHandled(true);
        }
    }
}
