package com.gempire.networking;

import com.gempire.client.screen.InjectorScreen;
import com.gempire.systems.warping.WarpPadData;
import com.gempire.systems.warping.WarpPadInfo;
import com.gempire.tileentities.InjectorTE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenTank {
    private final BlockPos pos;
    private final int tank;
    public OpenTank(int tank, BlockPos pos) {
        this.pos = pos;
        this.tank = tank;
    }
    public static void encode(OpenTank message, FriendlyByteBuf data) {
        data.writeInt(message.tank);
        data.writeBlockPos(message.pos);
    }
    public static OpenTank decode(FriendlyByteBuf data) {
        return new OpenTank(data.readInt(), data.readBlockPos());
    }
    public static void handle(OpenTank message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            ServerLevel level = player.serverLevel();
            InjectorTE injector = ((InjectorTE) level.getBlockEntity(message.pos));
            if (message.tank == 0) injector.pinkOpen = !injector.pinkOpen;
            if (message.tank == 1) injector.blueOpen = !injector.blueOpen;
            if (message.tank == 2) injector.yellowOpen = !injector.yellowOpen;
            if (message.tank == 3) injector.whiteOpen = !injector.whiteOpen;
            level.sendBlockUpdated(message.pos, level.getBlockState(message.pos), level.getBlockState(message.pos), 2);
        });
        context.get().setPacketHandled(true);
    }
}
