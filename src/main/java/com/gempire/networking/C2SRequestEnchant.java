package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.entities.gems.EntityZircon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestEnchant {
    public final int entityID;

    public C2SRequestEnchant(int entityID) {
        this.entityID = entityID;
    }

    public static C2SRequestEnchant decode(PacketBuffer buffer) {
        final int entityID = buffer.readInt();
        return new C2SRequestEnchant(entityID);
    }

    public static void encode(C2SRequestEnchant msg, PacketBuffer buffer) {
        buffer.writeInt(msg.entityID);
    }

    public static void handle(final C2SRequestEnchant msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            EntityZircon gem = (EntityZircon) sender.world.getEntityByID(msg.entityID);
            gem.beginEnchant();
        }
        ctx.setPacketHandled(true);
    }
}
