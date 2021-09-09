package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.entities.gems.EntityZircon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestPageChange {
    public final int entityID;
    public final boolean forward;

    public C2SRequestPageChange(int entityID, boolean forward) {
        this.entityID = entityID;
        this.forward = forward;
    }

    public static C2SRequestPageChange decode(PacketBuffer buffer) {
        final int entityID = buffer.readInt();
        final boolean forward = buffer.readBoolean();
        return new C2SRequestPageChange(entityID, forward);
    }

    public static void encode(C2SRequestPageChange msg, PacketBuffer buffer) {
        buffer.writeInt(msg.entityID);
        buffer.writeBoolean(msg.forward);
    }

    public static void handle(final C2SRequestPageChange msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            EntityGem gem = (EntityGem) sender.world.getEntityByID(msg.entityID);
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
                    ((EntityZircon)gem).CyclePageForward();
                } else {
                    ((EntityZircon)gem).CyclePageBackwards();
                }
            }
        }
        ctx.setPacketHandled(true);
    }
}
