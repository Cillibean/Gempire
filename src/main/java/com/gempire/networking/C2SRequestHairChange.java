package com.gempire.networking;

import com.gempire.entities.gems.EntityPearl;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestHairChange {
    public final int entityID;
    public final boolean forward;

    public C2SRequestHairChange(int entityID, boolean forward) {
        this.entityID = entityID;
        this.forward = forward;
    }

    public static C2SRequestHairChange decode(PacketBuffer buffer) {
        final int entityID = buffer.readInt();
        final boolean forward = buffer.readBoolean();
        return new C2SRequestHairChange(entityID, forward);
    }

    public static void encode(C2SRequestHairChange msg, PacketBuffer buffer) {
        buffer.writeInt(msg.entityID);
        buffer.writeBoolean(msg.forward);
    }

    public static void handle(final C2SRequestHairChange msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            EntityPearl gem = (EntityPearl) sender.world.getEntityByID(msg.entityID);
            boolean forwardd = msg.forward;
            if(forwardd){
                gem.CycleHairForward();
            }
            else{
                gem.CycleHairBackwards();
            }
        }
        ctx.setPacketHandled(true);
    }
}
