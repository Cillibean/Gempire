package com.gempire.tileentities;

import com.gempire.init.ModBlocks;
import com.gempire.init.ModTE;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WarpPadTE extends BlockEntity implements MenuProvider{
    public WarpPadTE(BlockPos pos, BlockState state) {
        super(ModTE.WARP_PAD_TE.get(), pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        WarpPadTE te = (WarpPadTE)be;
        if(!level.isClientSide()) {
        }
    }

    public static boolean TestForWarpPad(Player player) {
        boolean test = false;
        Level worldIn = player.level;
        BlockEntity te = worldIn.getBlockEntity(player.blockPosition().below());
        if(!worldIn.isClientSide()){
            if(te instanceof WarpPadTE){
                test = true;
            }
        }
        return test;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        return super.serializeNBT();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public AABB getRenderBoundingBox() {
        return super.getRenderBoundingBox();
    }

    @Override
    public void requestModelDataUpdate() {
        super.requestModelDataUpdate();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return super.getCapability(cap);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.gempire.warppad");
    }
}
