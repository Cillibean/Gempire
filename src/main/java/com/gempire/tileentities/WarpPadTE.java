package com.gempire.tileentities;

import com.gempire.init.ModBlocks;
import com.gempire.init.ModTE;
import com.gempire.systems.warping.WarpPadData;
import com.gempire.systems.warping.WarpPadInfo;
import com.gempire.util.WarpPadUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.extensions.IForgeBlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WarpPadTE extends BlockEntity implements IForgeBlockEntity {
    private final ItemStackHandler itemStackHandler = createItemStackHandler();
    private final LazyOptional<IItemHandler> itemHandlerOptional = LazyOptional.of(() -> itemStackHandler);
    private boolean warping = false;
    private int animation = 0;
    private boolean render = false;
    private float[] cachedColor;
    private BlockPos targetPos;
    public WarpPadTE(BlockPos pos, BlockState state) {
        super(ModTE.WARP_PAD_TE.get(), pos, state);
    }
    private ItemStackHandler createItemStackHandler() {
        return new ItemStackHandler() {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return stack.getItem() instanceof DyeItem;
            }
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }
    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }
    private static void sync(ServerLevel level, BlockPos pos) {
        BlockState state = ModBlocks.WARP_PAD.get().defaultBlockState();
        level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
    }
    private static void scheduleTick(Level level, BlockPos pos, int delay) {
        level.scheduleTick(pos, ModBlocks.WARP_PAD.get(), delay);
    }
    private static void addTicket(ServerPlayer player, ServerLevel level, BlockPos pos) {
        level.getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, new ChunkPos(pos), 1, player.getId());
    }
    private void onSync(CompoundTag tag) {
        itemStackHandler.deserializeNBT(tag.getCompound("inv"));
        warping = tag.getBoolean("warping");
        if(warping) {
            animation = 0;
            render = true;
        }
        cacheColor();
    }
    private void warpOut(ServerPlayer player, ServerLevel level, BlockPos pos, BlockPos targetPos) {
        warping = true;
        sync(level, pos);
        //level.playSound(null, pos, WarpPads.WARP_OUT_SOUND.get(), SoundSource.BLOCKS, 1, 1);
        addTicket(player, level, pos);
        scheduleTick(level, pos, 30);
        this.targetPos = targetPos;
    }
    private void teleport(ServerLevel level, BlockPos pos) {
        AABB box = WarpPadUtils.getBoxAbovePosition(WarpPadUtils.getTopCenter(pos), 3, 6);
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, box);
        int players = 0;
        for(LivingEntity entity : entities) {
            double x = entity.xo - pos.getX() + targetPos.getX();
            double y = entity.yo - pos.getY() + targetPos.getY();
            double z = entity.zo - pos.getZ() + targetPos.getZ();
            entity.teleportTo(x, y, z);
            if(entity instanceof Player) {
                players++;
            }
        }
        scheduleTick(level, pos, 10);
        if(level.getExistingBlockEntity(targetPos) instanceof WarpPadTE toPad && !toPad.isRemoved()) {
            toPad.tryWarpIn(level);
        } else if(players == 0) {
            WarpPadInfo info = WarpPadData.get(level).getWarpPad(targetPos);
            if(info != null) {
                info.setWarping(false);
            }
        }
        targetPos = null;
    }
    private void tryWarpIn(ServerLevel level) {
        WarpPadInfo info = WarpPadData.get(level).getWarpPad(worldPosition);
        if(info != null && info.isWarping()) {
            warpIn(level, worldPosition);
            info.setWarping(false);
        }
    }
    private void warpIn(ServerLevel level, BlockPos pos) {
        warping = true;
        sync(level, pos);
        //level.playSound(null, pos, WarpPads.WARP_IN_SOUND.get(), SoundSource.BLOCKS, 1, 1);
        scheduleTick(level, pos, 40);
    }
    private void setIdle(ServerLevel level, BlockPos pos) {
        warping = false;
        sync(level, pos);
    }
    public void handleScheduledTick(ServerLevel level, BlockPos pos) {
        if(targetPos != null) {
            teleport(level, pos);
        } else {
            setIdle(level, pos);
        }
    }
    public static void handleWarpRequest(ServerPlayer player, BlockPos fromPos, BlockPos toPos) {
        ServerLevel level = player.getLevel();
        BlockEntity fromEntity = level.getBlockEntity(fromPos);
        if(fromEntity instanceof WarpPadTE fromPad && !fromPad.isWarping()) {
            WarpPadInfo info = WarpPadData.get(level).getWarpPad(toPos);
            if(info != null && !info.isWarping()) {
                info.setWarping(true);
                fromPad.warpOut(player, level, fromPos, toPos);
            }
        }
    }
    public boolean isWarping() {
        return warping;
    }
    private void stepAnimation() {
        animation++;
        if(animation > 40) {
            render = false;
        }
    }
    public int getAnimation() {
        return animation;
    }
    public boolean shouldRender() {
        return render;
    }
    public static void animateTick(Level level, BlockPos pos, BlockState state, WarpPadTE entity) {
        entity.stepAnimation();
    }
    public void cacheColor() {
        ItemStack stack = itemStackHandler.getStackInSlot(0);
        if(!stack.isEmpty() && stack.getItem() instanceof DyeItem dye) {
            float[] color = dye.getDyeColor().getTextureDiffuseColors();
            cachedColor = WarpPadUtils.brightenColor(color, 0.3F);
        } else {
            cachedColor = null;
        }
    }
    public float[] getCachedColor() {
        return cachedColor;
    }
    @Override
    public void onLoad() {
        super.onLoad();
        if(level instanceof ServerLevel serverLevel) {
            tryWarpIn(serverLevel);
        }
    }
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inv", itemStackHandler.serializeNBT());
    }
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        onSync(tag);
    }
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putBoolean("warping", warping);
        tag.put("inv", itemStackHandler.serializeNBT());
        return tag;
    }
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Override
    public AABB getRenderBoundingBox() {
        Vec3 pos = Vec3.atBottomCenterOf(getBlockPos());
        return WarpPadUtils.getBoxAbovePosition(pos, 3, 7);
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability) {
        if(capability == ForgeCapabilities.ITEM_HANDLER) {
            return itemHandlerOptional.cast();
        }
        return super.getCapability(capability);
    }
}