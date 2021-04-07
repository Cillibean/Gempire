package com.gempire.networking;

import com.gempire.tileentities.GemSeedTE;
import com.gempire.tileentities.InjectorTE;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class S2SSendGemSeedInfo {
    public final BlockPos seedPos;
    public final CompoundNBT seedInfo;

    public S2SSendGemSeedInfo(BlockPos pos, CompoundNBT seedInfo) {
        this.seedPos = pos;
        this.seedInfo = seedInfo;
    }

    public static S2SSendGemSeedInfo decode(PacketBuffer buffer) {
        final BlockPos seed = buffer.readBlockPos();
        final CompoundNBT seedInfo = buffer.readCompoundTag();
        return new S2SSendGemSeedInfo(seed, seedInfo);
    }

    public static void encode(S2SSendGemSeedInfo msg, PacketBuffer buffer) {
        buffer.writeBlockPos(msg.seedPos);
        buffer.writeCompoundTag(msg.seedInfo);
    }

    public static void handle(final S2SSendGemSeedInfo msg, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ServerPlayerEntity sender = ctx.getSender();
        boolean hasPermission = true;
        if (hasPermission) {
            GemSeedTE seedTE = (GemSeedTE) sender.world.getTileEntity(msg.seedPos);
            seedTE.read(seedTE.getBlockState(), msg.seedInfo);
            seedTE.getWorld().notifyBlockUpdate(msg.seedPos, seedTE.getBlockState(), seedTE.getBlockState(), 2);
            seedTE.markDirty();
        }
        ctx.setPacketHandled(true);
    }
}
