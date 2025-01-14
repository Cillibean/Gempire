package com.gempire.networking;

import com.gempire.entities.bases.EntityFlyingVehicleGem;
import com.gempire.util.ElytraCalculation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SFlightEntityDescend {
    protected int mobId;

    public C2SFlightEntityDescend() {

    }

    public C2SFlightEntityDescend(int id) {
        this.mobId = id;
    }

    public C2SFlightEntityDescend(FriendlyByteBuf buf) {
        mobId = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(mobId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                if (player.getVehicle() instanceof EntityFlyingVehicleGem flightEntity) {
                    if (flightEntity.isFlying()) {
                        flightEntity.setDescend(true);
                    }
                }
            } else {
                return;
            }

        });
        return true;
    }
}
