package com.gempire.networking;

import com.gempire.entities.bases.EntityFlyingVehicleGem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SMayFly {

    private boolean canFly;

    public C2SMayFly() {

    }

    public C2SMayFly(boolean b) {
        this.canFly = b;
    }


    public C2SMayFly(FriendlyByteBuf buf) {
        this.canFly = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(canFly);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = context.getSender().serverLevel();

            if (player != null) {
                if (player.getVehicle() != null) {
                    if (player.getVehicle() instanceof EntityFlyingVehicleGem a) {
                        a.setNoGravity(canFly);
                    }
                }
            } else {
                return;
            }

        });
        return true;
    }
}
