package com.gempire.networking;

import com.gempire.aura.ClientAuraData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AuraDataSyncS2C {
    private final int aura;

    public AuraDataSyncS2C(int aura) {
        this.aura = aura;
    }

    public AuraDataSyncS2C(FriendlyByteBuf buf) {
        this.aura = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(aura);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientAuraData.set(aura);
        });
        return true;
    }
}
