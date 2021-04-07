package com.gempire.tileentities;

import com.gempire.blocks.GemSeedBlock;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.*;
import com.gempire.items.ItemChroma;
import com.gempire.systems.injection.GemFormation;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class GemSeedTE extends TileEntity implements ITickableTileEntity {
    Random random;
    boolean spawned = false;
    public int ticks = 0;
    public ItemChroma chroma;
    public Item primer;
    public Fluid[] essences;

    public GemSeedTE() {
        super(ModTE.GEM_SEED_TE.get());
        this.random = new Random();
    }

    @Override
    public void tick() {
        this.ticks++;
        if(this.ticks > 200 && !this.spawned){
            this.spawned = true;
            TNTEntity tnt = new TNTEntity(this.world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), null);
            this.world.addEntity(tnt);
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
            this.markDirty();
        }
    }

    public void SetChroma(ItemChroma chroma){
        this.chroma = chroma;
        this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
        this.markDirty();
    }

    public ItemChroma getChroma(){
        return this.chroma;
    }

    public void SetPrimer(Item primer){
        this.primer = primer;
        this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
        this.markDirty();
    }

    public Item getPrimer(){
        return this.primer;
    }

    public void setEssences(Fluid[] essec){
        this.essences = essec;
        this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
        this.markDirty();
    }

    public Fluid[] getEssences(){
        return this.essences;
    }
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("ticks", this.ticks);
        compound.putBoolean("spawned", this.spawned);
        compound.put("chroma", new ItemStack(this.chroma).write(new CompoundNBT()));
        compound.put("primer", new ItemStack(this.primer).write(new CompoundNBT()));
        String fluids = "";
        for(int i = 0; i < this.essences.length; i++){
            if(i == 0){
                fluids+=GemSeedTE.StringFromFluid(this.essences[0]);
            }
            else{
                fluids+= "-"+GemSeedTE.StringFromFluid(this.essences[i]);
            }
        }
        compound.putString("essences", fluids);
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.ticks = nbt.getInt("ticks");
        this.spawned = nbt.getBoolean("spawned");
        ItemStack chroma = ItemStack.read(nbt.getCompound("chroma"));
        this.chroma = (ItemChroma)chroma.getItem();
        ItemStack primer = ItemStack.read(nbt.getCompound("primer"));
        this.primer = primer.getItem();
        String[] fluids = nbt.getString("essences").split("-");
        ArrayList<Fluid> fluidArrayList = new ArrayList<>();
        for (String string : fluids){
            fluidArrayList.add(GemSeedTE.FluidFromString(string));
        }
        Fluid[] array = new Fluid[fluidArrayList.size()];
        for(int i = 0; i < fluidArrayList.size(); i++){
            if(fluidArrayList.get(i) != null && fluidArrayList.get(i) != Fluids.EMPTY){
                array[i] = fluidArrayList.get(i);
            }
        }
        this.essences = array;
        GemSeedBlock block = (GemSeedBlock) this.world.getBlockState(this.getPos()).getBlock();
        block.chroma = this.chroma;
        block.primer = this.primer;
        block.essences = this.essences;
    }

    public static String StringFromFluid(Fluid fluid){
        if(fluid == ModFluids.PINK_ESSENCE.get()){
            return "pink";
        }
        else if(fluid == ModFluids.BLUE_ESSENCE.get()){
            return "blue";
        }
        else if(fluid == ModFluids.YELLOW_ESSENCE.get()){
            return "yellow";
        }
        else if(fluid == ModFluids.WHITE_ESSENCE.get()){
            return "white";
        }
        else{
            return "";
        }
    }

    public static Fluid FluidFromString(String fluid){
        if(fluid == "pink"){
            return ModFluids.PINK_ESSENCE.get();
        }
        else if(fluid == "blue"){
            return ModFluids.BLUE_ESSENCE.get();
        }
        else if(fluid == "yellow"){
            return ModFluids.YELLOW_ESSENCE.get();
        }
        else if(fluid == "white"){
            return ModFluids.WHITE_ESSENCE.get();
        }
        else {
            return Fluids.EMPTY;
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        //Debug
        System.out.println("[DEBUG]:Client recived tile sync packet");
        this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        //Debug
        System.out.println("[DEBUG]:Server sent tile sync packet");
        return new SUpdateTileEntityPacket(this.pos, -1, this.getUpdateTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        System.out.println("[DEBUG]:Handling tag on chunk load");
        this.read(state, tag);
    }
}
