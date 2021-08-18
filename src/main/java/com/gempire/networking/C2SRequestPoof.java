package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestPoof {
    public final int entityID;

    public C2SRequestPoof(int entityID) {
        this.entityID = entityID;
    }

    public static C2SRequestPoof decode(PacketBuffer buffer) {
        final int entityID = buffer.readInt();
        return new C2SRequestPoof(entityID);
    }

    public static void encode(C2SRequestPoof msg, PacketBuffer buffer) {
        buffer.writeInt(msg.entityID);
    }

    public static void handle(final C2SRequestPoof msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            EntityGem gem = (EntityGem) sender.world.getEntityByID(msg.entityID);
            gem.attackEntityFrom(DamageSource.GENERIC, gem.getMaxHealth());
        }
        ctx.setPacketHandled(true);
    }
}
