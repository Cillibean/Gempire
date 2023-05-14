package com.gempire.systems.warping;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.FriendlyByteBuf;

public class WarpPadInfo {
    private final BlockPos pos;
    private final String name;
    private boolean warping = false;
    public WarpPadInfo(BlockPos pos, String name) {
        this.pos = pos;
        this.name = name;
    }
    public WarpPadInfo(CompoundTag tag) {
        pos = NbtUtils.readBlockPos(tag);
        name = tag.getString("name");
    }
    public WarpPadInfo(FriendlyByteBuf data) {
        pos = data.readBlockPos();
        name = data.readUtf();
    }
    public CompoundTag save() {
        CompoundTag tag = NbtUtils.writeBlockPos(pos);
        tag.putString("name", name);
        return tag;
    }
    public void write(FriendlyByteBuf data) {
        data.writeBlockPos(pos);
        data.writeUtf(name);
    }
    public BlockPos getPos() {
        return pos;
    }
    public String getName() {
        return name;
    }
    public boolean isWarping() {
        return warping;
    }
    public void setWarping(boolean warping) {
        this.warping = warping;
    }
}
