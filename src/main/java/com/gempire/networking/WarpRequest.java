package com.gempire.networking;

import com.gempire.tileentities.WarpPadTE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WarpRequest {
    private final BlockPos fromPos;
    private final BlockPos toPos;
    public WarpRequest(BlockPos fromPos, BlockPos toPos) {
        this.fromPos = fromPos;
        this.toPos = toPos;
    }
    public static void encode(WarpRequest message, FriendlyByteBuf data) {
        data.writeBlockPos(message.fromPos);
        data.writeBlockPos(message.toPos);
    }
    public static WarpRequest decode(FriendlyByteBuf data) {
        return new WarpRequest(data.readBlockPos(), data.readBlockPos());
    }
    public static void handle(WarpRequest message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            WarpPadTE.handleWarpRequest(player, message.fromPos, message.toPos);
        });
        context.get().setPacketHandled(true);
    }
}
