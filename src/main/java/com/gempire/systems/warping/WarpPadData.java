package com.gempire.systems.warping;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;

public class WarpPadData extends SavedData {
    private final HashMap<ResourceKey<Level>, WarpPadInfoHolder> holders = new HashMap<>();
    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for(WarpPadInfoHolder holder : holders.values()) {
            list.add(holder.save());
        }
        tag.put("holders", list);
        return tag;
    }
    public static WarpPadData load(CompoundTag tag) {
        WarpPadData data = new WarpPadData();
        ListTag list = tag.getList("holders", Tag.TAG_COMPOUND);
        for(Tag listTag : list) {
            if(listTag instanceof CompoundTag holder) {
                data.addHolder(new WarpPadInfoHolder(holder));
            }
        }
        return data;
    }
    @Override
    public boolean isDirty() {
        return holders.values().stream().anyMatch(WarpPadInfoHolder::isDirty);
    }
    @Override
    public void setDirty(boolean dirty) {
        holders.values().forEach(holder -> holder.setDirty(dirty));
    }
    private void addHolder(WarpPadInfoHolder holder) {
        holders.put(holder.getLevelKey(), holder);
    }
    private WarpPadInfoHolder getHolder(ResourceKey<Level> levelKey) {
        return holders.computeIfAbsent(levelKey, WarpPadInfoHolder::new);
    }
    public static WarpPadInfoHolder get(ServerLevel level) {
        ServerLevel overworld = level.getServer().overworld();
        return overworld.getDataStorage().computeIfAbsent(WarpPadData::load, WarpPadData::new, "warp_pads").getHolder(level.dimension());
    }
}
