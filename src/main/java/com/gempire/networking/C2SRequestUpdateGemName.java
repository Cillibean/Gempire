package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestUpdateGemName {
    public final String newName;
    public final int entityID;

    public C2SRequestUpdateGemName(String newName, int entityID) {
        this.newName = newName;
        this.entityID = entityID;
    }

    public static C2SRequestUpdateGemName decode(FriendlyByteBuf buffer) {
        final String newName = buffer.readUtf(32767);
        final int entityID = buffer.readInt();
        return new C2SRequestUpdateGemName(newName, entityID);
    }

    public static void encode(C2SRequestUpdateGemName msg, FriendlyByteBuf buffer) {
        buffer.writeUtf(msg.newName);
        buffer.writeInt(msg.entityID);
    }

    public static void handle(final C2SRequestUpdateGemName msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            EntityGem gem = (EntityGem) sender.level.getEntity(msg.entityID);
            if(!gem.customName()){
                gem.setHasCustomName(true);
            }
            gem.setCustomName(new TextComponent(msg.newName));
        }
        ctx.setPacketHandled(true);
    }
}
