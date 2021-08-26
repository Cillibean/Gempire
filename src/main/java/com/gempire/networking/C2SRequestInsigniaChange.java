package com.gempire.networking;

import com.gempire.entities.gems.EntityPearl;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestInsigniaChange {
    public final int entityID;
    public final boolean forward;

    public C2SRequestInsigniaChange(int entityID, boolean forward) {
        this.entityID = entityID;
        this.forward = forward;
    }

    public static C2SRequestInsigniaChange decode(PacketBuffer buffer) {
        final int entityID = buffer.readInt();
        final boolean forward = buffer.readBoolean();
        return new C2SRequestInsigniaChange(entityID, forward);
    }

    public static void encode(C2SRequestInsigniaChange msg, PacketBuffer buffer) {
        buffer.writeInt(msg.entityID);
        buffer.writeBoolean(msg.forward);
    }

    public static void handle(final C2SRequestInsigniaChange msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            EntityPearl gem = (EntityPearl) sender.world.getEntityByID(msg.entityID);
            boolean forwardd = msg.forward;
            if(forwardd){
                gem.CycleInsigniaForward();
            }
            else{
                gem.CycleInsigniaBackwards();
            }
        }
        ctx.setPacketHandled(true);
    }
}
