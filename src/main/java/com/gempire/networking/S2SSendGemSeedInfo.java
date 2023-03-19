package com.gempire.networking;

import com.gempire.tileentities.GemSeedTE;
import com.gempire.tileentities.InjectorTE;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class S2SSendGemSeedInfo {
    public static BlockPos seedPos;
    public final CompoundTag seedInfo;

    public S2SSendGemSeedInfo(BlockPos pos, CompoundTag seedInfo) {
        seedPos = pos;
        this.seedInfo = seedInfo;
    }

    public static S2SSendGemSeedInfo decode(FriendlyByteBuf buffer) {
        final BlockPos seed = buffer.readBlockPos();
        final CompoundTag seedInfo = buffer.readNbt();
        return new S2SSendGemSeedInfo(seed, seedInfo);
    }

    public static void encode(S2SSendGemSeedInfo msg, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(seedPos);
        buffer.writeNbt(msg.seedInfo);
    }

    public static void handle(final S2SSendGemSeedInfo msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayer sender = ctx.getSender();
        boolean hasPermission = true;
        if(Minecraft.getInstance().level.getBlockEntity(seedPos) instanceof GemSeedTE blockEntity) {
            blockEntity.load(msg.seedInfo);
            Objects.requireNonNull(blockEntity.getLevel()).sendBlockUpdated(seedPos, blockEntity.getBlockState(), blockEntity.getBlockState(), 2);
            blockEntity.setChanged();
        }
        ctx.setPacketHandled(true);
    }
}
