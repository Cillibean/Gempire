package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestPoof {
    public final int entityID;

    public C2SRequestPoof(int entityID) {
        this.entityID = entityID;
    }

    public static C2SRequestPoof decode(FriendlyByteBuf buffer) {
        final int entityID = buffer.readInt();
        return new C2SRequestPoof(entityID);
    }

    public static void encode(C2SRequestPoof msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.entityID);
    }

    public static void handle(final C2SRequestPoof msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        boolean hasPermission = true;
        assert sender != null;
        EntityGem gem = (EntityGem) sender.level.getEntity(msg.entityID);
        assert gem != null;
        gem.hurt(DamageSource.playerAttack(gem.currentPlayer), gem.getMaxHealth() + 20);
        ctx.setPacketHandled(true);
    }
}
