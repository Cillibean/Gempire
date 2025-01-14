package com.gempire.networking;

import com.gempire.entities.bases.EntityFlyingVehicleGem;
import com.gempire.init.ModMessages;
import com.gempire.util.ElytraCalculation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class C2SFlightEntityDash {
    protected int mobId;

    public C2SFlightEntityDash() {

    }

    public C2SFlightEntityDash(int id) {
        this.mobId = id;
    }

    public C2SFlightEntityDash(FriendlyByteBuf buf) {
        mobId = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(mobId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = context.getSender().serverLevel();

            if (player != null) {
                if (player.getVehicle() instanceof EntityFlyingVehicleGem flightEntity) {

                    ItemStack itemstack = ElytraCalculation.createLapisFirework();
                    if (!level.isClientSide()) {
                        FireworkRocketEntity lapisfirework = new FireworkRocketEntity(level, itemstack, flightEntity);
                        lapisfirework.setInvisible(true);
                        lapisfirework.setSilent(true);
                        level.addFreshEntity(lapisfirework);
                    }

                }
            } else {
                return;
            }

        });
        return true;
    }
}
