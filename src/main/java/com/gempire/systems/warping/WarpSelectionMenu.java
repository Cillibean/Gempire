package com.gempire.systems.warping;

import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WarpSelectionMenu extends AbstractContainerMenu {
    public static final Component TITLE = Component.translatable("container.gempire.warp_selection");
    private final ContainerLevelAccess levelAccess;
    private List<WarpPadInfo> warpPads;
    private BlockPos pos;
    public WarpSelectionMenu(int id, ContainerLevelAccess levelAccess) {
        super(ModContainers.WARP_SELECTION.get(), id);
        this.levelAccess = levelAccess;
    }
    public WarpSelectionMenu(int id, ContainerLevelAccess levelAccess, BlockPos pos, List<WarpPadInfo> warpPads) {
        this(id, levelAccess);
        this.pos = pos;
        this.warpPads = warpPads;
    }
    public WarpSelectionMenu(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, ContainerLevelAccess.NULL);
        pos = data.readBlockPos();
        warpPads = new ArrayList<>();
        int count = data.readInt();
        for(int i = 0; i < count; i++) {
            WarpPadInfo entry = new WarpPadInfo(data);
            warpPads.add(entry);
        }
    }
    public static MenuProvider getMenuProvider(BlockPos pos, List<WarpPadInfo> warpPads) {
        return new SimpleMenuProvider((id, inventory, player) -> new WarpSelectionMenu(id, ContainerLevelAccess.create(player.getLevel(), pos), pos, warpPads), TITLE);
    }
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
    @Override
    public boolean stillValid(Player player) {
        return stillValid(levelAccess, player, ModBlocks.WARP_PAD.get());
    }
    public BlockPos getPos() {
        return pos;
    }
    public List<WarpPadInfo> getWarpPads() {
        return warpPads;
    }
}
