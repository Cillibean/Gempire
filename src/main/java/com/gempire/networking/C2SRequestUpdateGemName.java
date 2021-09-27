package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import com.gempire.tileentities.InjectorTE;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SRequestUpdateGemName {
    public final String newName;
    public final int entityID;

    public C2SRequestUpdateGemName(String newName, int entityID) {
        this.newName = newName;
        this.entityID = entityID;
    }

    public static C2SRequestUpdateGemName decode(PacketBuffer buffer) {
        final String newName = buffer.readString(32767);
        final int entityID = buffer.readInt();
        return new C2SRequestUpdateGemName(newName, entityID);
    }

    public static void encode(C2SRequestUpdateGemName msg, PacketBuffer buffer) {
        buffer.writeString(msg.newName);
        buffer.writeInt(msg.entityID);
    }

    public static void handle(final C2SRequestUpdateGemName msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            EntityGem gem = (EntityGem) sender.world.getEntityByID(msg.entityID);
            if(!gem.customName()){
                gem.setHasCustomName(true);
            }
            gem.setCustomName(new StringTextComponent(msg.newName));
        }
        ctx.setPacketHandled(true);
    }
}
