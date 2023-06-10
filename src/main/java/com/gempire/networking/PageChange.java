package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.entities.gems.EntityZircon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PageChange {
        public final int entityID;
        public final boolean forward;

        public PageChange(int entityID, boolean forward) {
            this.entityID = entityID;
            this.forward = forward;
        }

        public static PageChange decode(FriendlyByteBuf buffer) {
            final int entityID = buffer.readInt();
            final boolean forward = buffer.readBoolean();
            return new PageChange(entityID, forward);
        }

        public static void encode(PageChange msg, FriendlyByteBuf buffer) {
            buffer.writeInt(msg.entityID);
            buffer.writeBoolean(msg.forward);
        }

        public static void handle(final PageChange msg, final Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context ctx = contextSupplier.get();
            ServerPlayer sender = ctx.getSender();
            boolean hasPermission = true;
            ctx.enqueueWork(() -> {
                        EntityGem gem = (EntityGem) sender.level.getEntity(msg.entityID);
                        boolean forwardd = msg.forward;
                        boolean pearl = gem instanceof EntityPearl;
                        if (pearl) {
                            if (gem.isPrimary()) {
                                if (forwardd) {
                                    ((EntityPearl) gem).CyclePageForward();
                                } else {
                                    ((EntityPearl) gem).CyclePageBackwards();
                                }
                            }
                        } else {
                            if (forwardd) {
                                ((EntityZircon) gem).CyclePageForward();
                            } else {
                                ((EntityZircon) gem).CyclePageBackwards();
                            }
                        }
                    });
            ctx.setPacketHandled(true);
        }
}
