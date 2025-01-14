package com.gempire.networking;

import com.gempire.entities.bases.EntityFlyingVehicleGem;
import com.gempire.init.ModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class C2SFlightEntityMovement {
    protected boolean movement;
    protected int mobId;
    protected boolean isElytraGliding;

    public C2SFlightEntityMovement() {

    }

    public C2SFlightEntityMovement(boolean move, int id, boolean gliding) {
        this.movement = move;
        this.mobId = id;
        this.isElytraGliding = gliding;
    }

    public C2SFlightEntityMovement(FriendlyByteBuf buf) {
        movement = buf.readBoolean();
        mobId = buf.readInt();
        isElytraGliding = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(movement);
        buf.writeInt(mobId);
        buf.writeBoolean(isElytraGliding);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel serverLevel = context.getSender().serverLevel();
            EntityFlyingVehicleGem rideableFlightEntity = (EntityFlyingVehicleGem) serverLevel.getEntity(this.mobId);

            if (!serverLevel.isClientSide()) {
                // Set the serverside entity to match what the client sent.
                rideableFlightEntity.hasMoved = this.movement;
                rideableFlightEntity.moving = this.movement;
                rideableFlightEntity.setElytraFlying(isElytraGliding);

                // Send the packet to nearby clients
                PacketDistributor.TargetPoint  areaForSync = new PacketDistributor.TargetPoint(context.getSender(), rideableFlightEntity.getX(),
                        rideableFlightEntity.getY(), rideableFlightEntity.getZ(), 50,serverLevel.dimension());
                ModMessages.sendToNearbyPlayersByEntity(new S2CFlightEntityMovement(this.movement,this.mobId, this.isElytraGliding), areaForSync);
            }


        });
        return true;
    }
}
