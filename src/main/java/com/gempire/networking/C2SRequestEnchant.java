package com.gempire.networking;

import com.gempire.entities.gems.EntityZircon;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestEnchant {
    public final int entityID;

    public C2SRequestEnchant(int entityID) {
        this.entityID = entityID;
    }

    public static C2SRequestEnchant decode(FriendlyByteBuf buffer) {
        final int entityID = buffer.readInt();
        return new C2SRequestEnchant(entityID);
    }

    public static void encode(C2SRequestEnchant msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.entityID);
    }

    public static void handle(final C2SRequestEnchant msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            ctx.enqueueWork(() -> {
                EntityZircon gem = (EntityZircon) sender.level.getEntity(msg.entityID);
                gem.beginEnchant();
            });
        }
        ctx.setPacketHandled(true);
    }
}
