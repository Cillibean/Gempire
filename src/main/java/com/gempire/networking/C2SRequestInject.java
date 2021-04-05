package com.gempire.networking;

import com.gempire.tileentities.InjectorTE;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestInject {
    public final BlockPos injectorPos;

    public C2SRequestInject(BlockPos pos) {
        this.injectorPos = pos;
    }

    public static C2SRequestInject decode(PacketBuffer buffer) {
        final BlockPos injector = buffer.readBlockPos();
        return new C2SRequestInject(injector);
    }

    public static void encode(C2SRequestInject msg, PacketBuffer buffer) {
        buffer.writeBlockPos(msg.injectorPos);
    }

    public static void handle(final C2SRequestInject msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            InjectorTE injector = (InjectorTE)sender.world.getTileEntity(msg.injectorPos);
            injector.Inject();
        }
        ctx.setPacketHandled(true);
    }
}
