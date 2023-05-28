package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModBlocks;
import com.gempire.systems.warping.WarpPadData;
import com.gempire.systems.warping.WarpPadInfo;
import com.gempire.systems.warping.WarpPadInfoHolder;
import com.gempire.systems.warping.WarpSelectionMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;
import java.util.function.Supplier;

public class RequestPoof {
    public final int entityID;

    public RequestPoof(int entityID) {
        this.entityID = entityID;
    }

    public static RequestPoof decode(FriendlyByteBuf data) {
        return new RequestPoof(data.readInt());
    }

    public static void encode(RequestPoof msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.entityID);
    }

    public static void handle(final RequestPoof msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            if (sender != null ) {
                EntityGem gem = (EntityGem) sender.level.getEntity(msg.entityID);
                if (gem != null) {
                    gem.hurt(DamageSource.MAGIC, gem.getMaxHealth() * 20);
                }
            }
            ctx.setPacketHandled(true);
        }
    }
}
