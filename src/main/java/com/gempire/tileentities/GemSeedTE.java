package com.gempire.tileentities;

import com.gempire.init.*;
import com.gempire.util.GemFormation;
import com.gempire.util.GemInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;


public class GemSeedTE extends BlockEntity {
    boolean spawned = false;
    public int ticks = 0;
    public int stage = 0;
    public int tier;

    public int chroma;

    public Item primer;
    public int facing;
    public boolean checked = false;
    public boolean exposed;
    int speed = 8;
    public static GemInfo info;

    public GemSeedTE(BlockPos pos, BlockState state) {
        super(ModTE.GEM_SEED_TE.get(), pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        GemSeedTE te = (GemSeedTE) be;
        //System.out.println("Gem List is of size: " + GemFormation.POSSIBLE_GEMS.size());
        if (!te.exposed) {
            if (te.level.getBlockState(te.getBlockPos().below()) == Blocks.AIR.defaultBlockState() || te.level.getBlockState(te.getBlockPos().below()).getBlock() instanceof LiquidBlock) {
                te.exposed = true;
            }
            if (te.primer == ModItems.GILDED_LAPIS.get()) {
                te.speed = 2;
            } else {
                te.speed = 8;
            }
            if (te.ticks % te.speed == 0) {
                if (!te.spawned) {
                    if (!te.level.isClientSide) {
                        GemFormation form = new GemFormation(info, te.level, te.getBlockPos(), new BlockPos(11, 11, 11), te.chroma, te.primer, te.facing, te.tier);
                        form.SpawnGem();
                        level.sendBlockUpdated(te.getBlockPos(), te.getBlockState(), te.getBlockState(), 2);
                        te.setChanged();
                        }
                    }
                }
            }
            te.ticks++;
        }


    public void setChroma(int chroma){
        this.chroma = chroma;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public int getChroma(){
        return this.chroma;
    }

    public void setInfo(GemInfo info){
        this.info = info;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
        System.out.println("info set");
    }

    public GemInfo getInfo(){
        return info;
    }

    public void SetPrimer(Item primer){
        this.primer = primer;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public Item getPrimer(){
        return this.primer;
    }

    public void setTier(int tier){
        this.tier = tier;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public int getTier(){
        return this.tier;
    }


    public void setFacing(int facing){
        this.facing = facing;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public int getFacing(){
        return this.facing;
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("stage", this.stage);
        compound.putBoolean("spawned", this.spawned);
        compound.putInt("chroma", this.chroma);
        compound.put("primer", new ItemStack(this.primer).save(new CompoundTag()));
        compound.putInt("facing", this.facing);
        compound.putBoolean("checked", this.checked);
        compound.putInt("tier", this.tier);
    }


    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.stage = nbt.getInt("stage");
        this.spawned = nbt.getBoolean("spawned");
        this.chroma = nbt.getInt("chroma");
        ItemStack primer = ItemStack.of(nbt.getCompound("primer"));
        this.primer = primer.getItem();
        this.facing = nbt.getInt("facing");
        this.checked = nbt.getBoolean("checked");
        this.tier = nbt.getInt("tier");
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.load(nbt);
        this.stage = nbt.getInt("stage");
        this.spawned = nbt.getBoolean("spawned");
        this.chroma = nbt.getInt("chroma");
        ItemStack primer = ItemStack.of(nbt.getCompound("primer"));
        this.primer = primer.getItem();
        this.facing = nbt.getInt("facing");
        this.tier = nbt.getInt("tier");
        this.checked = nbt.getBoolean("checked");
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        super.serializeNBT();
        compound.putInt("stage", this.stage);
        compound.putInt("chroma", this.chroma);
        compound.putBoolean("spawned", this.spawned);
        compound.put("primer", new ItemStack(this.primer).save(new CompoundTag()));
        compound.putInt("facing", this.facing);
        compound.putBoolean("checked", this.checked);
        compound.putInt("tier", this.tier);
        return compound;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        //Debug
        System.out.println("[DEBUG]:Client recived tile sync packet");
        this.load(Objects.requireNonNull(pkt.getTag()));
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        System.out.println("[DEBUG]:Handling tag on chunk load");
        this.load(tag);
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compound = new CompoundTag();
        this.saveAdditional(compound);
        return compound;
    }
    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        //Debug
        System.out.println("[DEBUG]:Server sent tile sync packet");
        return ClientboundBlockEntityDataPacket.create(this);
    }
}