package com.gempire.networking;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.init.ModBlocks;
import com.gempire.systems.warping.WarpPadData;
import com.gempire.systems.warping.WarpPadInfo;
import com.gempire.systems.warping.WarpPadInfoHolder;
import com.gempire.systems.warping.WarpSelectionMenu;
import com.gempire.util.DesolateTeleporter;
import com.gempire.worldgen.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.ArrayList;
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
                List<EntityGem> list = sender.level().getEntitiesOfClass(EntityGem.class, sender.getBoundingBox().inflate(3.0D, 2.0D, 3.0D));
                List<EntityGem> list2 = new ArrayList<>();
                for (EntityGem gem : list) {
                    if (gem.OWNERS.contains(sender.getUUID())) list2.add(gem);
                }
                if (sender.level().getBlockState(msg.pos).getBlock() == ModBlocks.WARP_PAD.get()) {
                    WarpPadInfoHolder holder = WarpPadData.get(sender.serverLevel());
                    List<WarpPadInfo> warpPads = holder.getWarpPads();
                    MenuProvider provider = WarpSelectionMenu.getMenuProvider(msg.pos, warpPads);
                    NetworkHooks.openScreen(sender, provider, data -> {
                        data.writeBlockPos(msg.pos);
                        data.writeInt(warpPads.size());
                        for (WarpPadInfo entry : warpPads) {
                            entry.write(data);
                        }
                    });
                } else if (sender.level().getBlockState(msg.pos).getBlock() == ModBlocks.GALAXY_WARP.get()) {
                    MinecraftServer minecraftserver = sender.level().getServer();
                    ResourceKey<Level> resourcekey = sender.level().dimension() == Level.OVERWORLD ? ModDimensions.DESOLATE_LANDS_LEVEL_KEY : Level.OVERWORLD;
                    ServerLevel serverlevel1 = minecraftserver.getLevel(resourcekey);
                    if (serverlevel1 != null && !sender.isPassenger()) {
                        if (resourcekey == ModDimensions.DESOLATE_LANDS_LEVEL_KEY) {
                            sender.changeDimension(serverlevel1, new DesolateTeleporter(sender.getOnPos(), true, true));
                            for (EntityGem gem : list2) {
                                gem.changeDimension(serverlevel1, new DesolateTeleporter(sender.getOnPos(), true, false));
                            }
                        } else {
                            sender.changeDimension(serverlevel1, new DesolateTeleporter(sender.getOnPos(), false, true));
                            for (EntityGem gem : list2) {
                                gem.changeDimension(serverlevel1, new DesolateTeleporter(sender.getOnPos(), false, false));
                            }
                        }
                    }
                }
                ctx.setPacketHandled(true);
            }
        }
}
