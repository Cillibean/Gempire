package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SFusionStick {
    public final int entityID;
    public final int toFuseID;

    public C2SFusionStick(int entityID, int toFuseID) {
        this.entityID = entityID;
        this.toFuseID = toFuseID;
    }

    public static C2SFusionStick decode(FriendlyByteBuf buffer) {
        final int entityID = buffer.readInt();
        final int toFuseID = buffer.readInt();
        return new C2SFusionStick(entityID, toFuseID);
    }

    public static void encode(C2SFusionStick msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.entityID);
        buffer.writeInt(msg.toFuseID);
    }

    public static void handle(final C2SFusionStick msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            ctx.enqueueWork(() -> {
                System.out.println("fusion");
                EntityGem gem = (EntityGem) sender.level().getEntity(msg.entityID);
                EntityGem toFuse = (EntityGem) sender.level().getEntity(msg.toFuseID);
                gem.fuse(toFuse);
            });
        }
        ctx.setPacketHandled(true);
    }
}
