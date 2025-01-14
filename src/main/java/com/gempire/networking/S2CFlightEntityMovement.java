package com.gempire.networking;

import com.gempire.entities.bases.EntityFlyingVehicleGem;
import com.gempire.init.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class S2CFlightEntityMovement {
    protected boolean movement;
    protected int mobId;
    protected boolean isElytraGliding;

    public S2CFlightEntityMovement() {

    }

    public S2CFlightEntityMovement(boolean move, int id, boolean gliding) {
        this.movement = move;
        this.mobId = id;
        this.isElytraGliding = gliding;
    }

    public S2CFlightEntityMovement(FriendlyByteBuf buf) {
        this.movement = buf.readBoolean();
        this.mobId = buf.readInt();
        this.isElytraGliding = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(movement);
        buf.writeInt(mobId);
        buf.writeBoolean(isElytraGliding);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> S2CFlightEntityMovement.syncFlightEntity(supplier, movement, mobId, isElytraGliding));
            }




        });
        return true;
    }

    public static boolean syncFlightEntity(Supplier<NetworkEvent.Context> supplier, boolean move, int mobId, boolean gliding) {

        if (supplier.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ServerPlayer player = supplier.get().getSender();
            EntityFlyingVehicleGem rideableFlightEntity = (EntityFlyingVehicleGem) Minecraft.getInstance().level.getEntity(mobId);

            rideableFlightEntity.moving = move;
            rideableFlightEntity.hasMoved = move;
            rideableFlightEntity.setElytraFlying(gliding);


        }
        return true;
    }
}
