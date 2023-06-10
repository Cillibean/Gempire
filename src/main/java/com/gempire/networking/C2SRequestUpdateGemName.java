package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
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
        assert sender != null;
        EntityGem gem = (EntityGem) sender.level.getEntity(msg.entityID);
        assert gem != null;
        ctx.enqueueWork(() -> {
                    if (!gem.customName()) {
                        gem.setHasCustomName(true);
                    }
                    gem.setCustomName(Component.translatable(msg.newName));
                });
        ctx.setPacketHandled(true);
    }
}
