package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.networking.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Gempire.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void registerPackets(){
        int networkId = 0;
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestUpdateInjectorValves.class,
                C2SRequestUpdateInjectorValves::encode,
                C2SRequestUpdateInjectorValves::decode,
                C2SRequestUpdateInjectorValves::handle);
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestInject.class,
                C2SRequestInject::encode,
                C2SRequestInject::decode,
                C2SRequestInject::handle);
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestDumpFluidsInjector.class,
                C2SRequestDumpFluidsInjector::encode,
                C2SRequestDumpFluidsInjector::decode,
                C2SRequestDumpFluidsInjector::handle);
        /*ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                S2SSendGemSeedInfo.class,
                S2SSendGemSeedInfo::encode,
                S2SSendGemSeedInfo::decode,
                S2SSendGemSeedInfo::handle);*/
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestUpdateGemName.class,
                C2SRequestUpdateGemName::encode,
                C2SRequestUpdateGemName::decode,
                C2SRequestUpdateGemName::handle);
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestPoof.class,
                C2SRequestPoof::encode,
                C2SRequestPoof::decode,
                C2SRequestPoof::handle);
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestPageChange.class,
                C2SRequestPageChange::encode,
                C2SRequestPageChange::decode,
                C2SRequestPageChange::handle);
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestHairChange.class,
                C2SRequestHairChange::encode,
                C2SRequestHairChange::decode,
                C2SRequestHairChange::handle);
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestOutfitChange.class,
                C2SRequestOutfitChange::encode,
                C2SRequestOutfitChange::decode,
                C2SRequestOutfitChange::handle);
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestInsigniaChange.class,
                C2SRequestInsigniaChange::encode,
                C2SRequestInsigniaChange::decode,
                C2SRequestInsigniaChange::handle);
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestEnchant.class,
                C2SRequestEnchant::encode,
                C2SRequestEnchant::decode,
                C2SRequestEnchant::handle);
    }
}
