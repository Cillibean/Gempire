package com.gempire.networking;

import com.gempire.entities.gems.EntityPearl;
import com.gempire.init.ModBlocks;
import com.gempire.systems.warping.WarpPadData;
import com.gempire.systems.warping.WarpPadInfo;
import com.gempire.systems.warping.WarpPadInfoHolder;
import com.gempire.systems.warping.WarpSelectionMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;
import java.util.function.Supplier;

public class WarpGuiKeyPressed {
    private final BlockPos pos;

        public WarpGuiKeyPressed(BlockPos pos) {
            this.pos = pos;
        }

        public static WarpGuiKeyPressed decode(FriendlyByteBuf data) {
            System.out.println("decode");
            return new WarpGuiKeyPressed(data.readBlockPos());
        }

        public static void encode(WarpGuiKeyPressed msg, FriendlyByteBuf buffer) {
            buffer.writeBlockPos(msg.pos);
            System.out.println("encode");
        }

        public static void handle(final WarpGuiKeyPressed msg, final Supplier<NetworkEvent.Context> contextSupplier) {
            System.out.println("handle");
            NetworkEvent.Context ctx = contextSupplier.get();
            ServerPlayer sender = ctx.getSender();
            boolean hasPermission = true;
            if (hasPermission) {
                if (sender.getLevel().getBlockState(msg.pos).getBlock() == ModBlocks.WARP_PAD.get()) {
                    WarpPadInfoHolder holder = WarpPadData.get(sender.getLevel());
                    List<WarpPadInfo> warpPads = holder.getWarpPads();
                    MenuProvider provider = WarpSelectionMenu.getMenuProvider(msg.pos, warpPads);
                    NetworkHooks.openScreen(sender, provider, data -> {
                        data.writeBlockPos(msg.pos);
                        data.writeInt(warpPads.size());
                        for (WarpPadInfo entry : warpPads) {
                            entry.write(data);
                        }
                    });
                }
                ctx.setPacketHandled(true);
            }
        }
}
