package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.networking.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

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
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                C2SRequestDumpFluidsTank.class,
                C2SRequestDumpFluidsTank::encode,
                C2SRequestDumpFluidsTank::decode,
                C2SRequestDumpFluidsTank::handle);
        ModPacketHandler.INSTANCE.registerMessage(
                networkId++,
                S2SSendGemSeedInfo.class,
                S2SSendGemSeedInfo::encode,
                S2SSendGemSeedInfo::decode,
                S2SSendGemSeedInfo::handle);
    }
}
