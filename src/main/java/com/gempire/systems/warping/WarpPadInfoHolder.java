package com.gempire.systems.warping;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class WarpPadInfoHolder {
    private final HashMap<BlockPos, WarpPadInfo> warpPadMap = new HashMap<>();
    private final ArrayList<WarpPadInfo> warpPadList = new ArrayList<>();
    private final ResourceKey<Level> levelKey;
    private boolean dirty;
    public WarpPadInfoHolder(CompoundTag tag) {
        Optional<ResourceKey<Level>> key = Level.RESOURCE_KEY_CODEC.parse(NbtOps.INSTANCE, tag.get("level")).result();
        this.levelKey = key.isPresent() ? key.get() : Level.OVERWORLD;
        ListTag list = tag.getList("warpPads", Tag.TAG_COMPOUND);
        for(Tag listTag : list) {
            if(listTag instanceof CompoundTag info) {
                addWarpPad(new WarpPadInfo(info));
            }
        }
    }
    public WarpPadInfoHolder(ResourceKey<Level> key) {
        this.levelKey = key;
    }
    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        Level.RESOURCE_KEY_CODEC.encodeStart(NbtOps.INSTANCE, levelKey).result().ifPresent(key -> tag.put("level", key));
        ListTag list = new ListTag();
        for(WarpPadInfo info : warpPadList) {
            list.add(info.save());
        }
        tag.put("warpPads", list);
        return tag;
    }
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
    public boolean isDirty() {
        return dirty;
    }
    public void addWarpPad(WarpPadInfo info) {
        WarpPadInfo old = warpPadMap.put(info.getPos(), info);
        if(old != null) {
            warpPadList.remove(old);
        }
        warpPadList.add(info);
        setDirty(true);
    }
    public void removeWarpPad(BlockPos pos) {
        WarpPadInfo info = warpPadMap.remove(pos);
        warpPadList.remove(info);
        setDirty(true);
    }
    public boolean hasWarpPad(BlockPos pos) {
        return warpPadMap.containsKey(pos);
    }
    public WarpPadInfo getWarpPad(BlockPos pos) {
        return warpPadMap.get(pos);
    }
    public WarpPadInfo getNewWarpPad(BlockPos pos) {
        WarpPadInfo info = getWarpPad(pos);
        return info != null ? info : new WarpPadInfo(pos, "");
    }
    public ArrayList<WarpPadInfo> getWarpPads() {
        return warpPadList;
    }
    public ResourceKey<Level> getLevelKey() {
        return levelKey;
    }
}
