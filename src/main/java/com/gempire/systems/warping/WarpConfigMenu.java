package com.gempire.systems.warping;

import com.gempire.init.ModBlocks;
import com.gempire.init.ModContainers;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.EditWarpName;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class WarpConfigMenu extends AbstractContainerMenu {
    public static final Component TITLE = Component.translatable("container.gempire.warp_config");
    private WarpPadInfo info;

    private final ContainerLevelAccess levelAccess;
    public WarpConfigMenu(int id, ContainerLevelAccess levelAccess) {
        super(ModContainers.WARP_CONFIG.get(), id);
        this.levelAccess = levelAccess;
    }
    public WarpConfigMenu(int id, ContainerLevelAccess levelAccess, WarpPadInfo entry) {
        this(id, levelAccess);
        this.info = entry;
    }
    public WarpConfigMenu(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, ContainerLevelAccess.NULL);
        info = new WarpPadInfo(data);
    }
    public static MenuProvider getMenuProvider(WarpPadInfo info, IItemHandler itemHandler) {
        return new SimpleMenuProvider((id, inventory, player) -> new WarpConfigMenu(id, ContainerLevelAccess.create(player.getLevel(), info.getPos()), info), TITLE);
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(levelAccess, player, ModBlocks.WARP_PAD.get());
    }
    public WarpPadInfo getInfo() {
        return info;
    }
    public void saveName(String name) {
        if(name.length() > 0 && name != info.getName()) {
            ModPacketHandler.INSTANCE.sendToServer(new EditWarpName(info.getPos(), name));
        }
    }
}