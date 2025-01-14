package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.networking.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Gempire.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(AuraDataSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(AuraDataSyncS2C::new)
                .encoder(AuraDataSyncS2C::toBytes)
                .consumerMainThread(AuraDataSyncS2C::handle)
                .add();

        net.messageBuilder(C2SFlightEntityMovement.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SFlightEntityMovement::new)
                .encoder(C2SFlightEntityMovement::toBytes)
                .consumerMainThread(C2SFlightEntityMovement::handle)
                .add();

        net.messageBuilder(C2SFlightEntityDash.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SFlightEntityDash::new)
                .encoder(C2SFlightEntityDash::toBytes)
                .consumerMainThread(C2SFlightEntityDash::handle)
                .add();

        net.messageBuilder(C2SFlightEntityDescend.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SFlightEntityDescend::new)
                .encoder(C2SFlightEntityDescend::toBytes)
                .consumerMainThread(C2SFlightEntityDescend::handle)
                .add();

        net.messageBuilder(C2SMayFly.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SMayFly::new)
                .encoder(C2SMayFly::toBytes)
                .consumerMainThread(C2SMayFly::handle)
                .add();

        net.messageBuilder(S2CFlightEntityMovement.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CFlightEntityMovement::new)
                .encoder(S2CFlightEntityMovement::toBytes)
                .consumerMainThread(S2CFlightEntityMovement::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToNearbyPlayersByEntity(MSG message, PacketDistributor.TargetPoint targetPoint) {
        INSTANCE.send(PacketDistributor.NEAR.with(() -> targetPoint), message);
    }
}
