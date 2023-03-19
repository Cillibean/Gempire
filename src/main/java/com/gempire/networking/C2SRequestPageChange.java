package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.entities.gems.EntityZircon;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestPageChange {
    public final int entityID;
    public final boolean forward;

    public C2SRequestPageChange(int entityID, boolean forward) {
        this.entityID = entityID;
        this.forward = forward;
    }

    public static C2SRequestPageChange decode(FriendlyByteBuf buffer) {
        final int entityID = buffer.readInt();
        final boolean forward = buffer.readBoolean();
        return new C2SRequestPageChange(entityID, forward);
    }

    public static void encode(C2SRequestPageChange msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.entityID);
        buffer.writeBoolean(msg.forward);
    }

    public static void handle(final C2SRequestPageChange msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        boolean hasPermission = true;
        EntityGem gem = (EntityGem) sender.level.getEntity(msg.entityID);
        boolean forwardd = msg.forward;
        boolean pearl = gem instanceof EntityPearl;
        if(pearl) {
            if (forwardd) {
                ((EntityPearl)gem).CyclePageForward();
            } else {
                ((EntityPearl)gem).CyclePageBackwards();
            }
        }
        else{
            if (forwardd) {
                assert gem != null;
                ((EntityZircon)gem).CyclePageForward();
            } else {
                assert gem != null;
                ((EntityZircon)gem).CyclePageBackwards();
            }
        }
        ctx.setPacketHandled(true);
    }
}
