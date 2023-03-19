package com.gempire.tileentities;

import com.gempire.blocks.GemSeedBlock;
import com.gempire.blocks.machine.InjectorBlock;
import com.gempire.container.InjectorContainer;
import com.gempire.events.InjectEvent;
import com.gempire.init.*;
import com.gempire.items.ItemChroma;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class InjectorTE extends RandomizableContainerBlockEntity implements IFluidTank, MenuProvider {
    public static final int NUMBER_OF_SLOTS = 6;
    public static final int PINK_INPUT_SLOT_INDEX = 0;
    public static final int BLUE_INPUT_SLOT_INDEX = 1;
    public static final int YELLOW_INPUT_SLOT_INDEX = 2;
    public static final int WHITE_INPUT_SLOT_INDEX = 3;
    public static final int CHROMA_INPUT_SLOT_INDEX = 4;
    public static final int PRIME_INPUT_SLOT_INDEX = 5;
    public int TANK_CAPACITY(){
        return 1000;
    }
    public NonNullList<ItemStack> items = NonNullList.withSize(InjectorTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public FluidTank pinkTank;
    public FluidTank blueTank;
    public FluidTank yellowTank;
    public FluidTank whiteTank;
    public boolean pinkOpen, blueOpen, yellowOpen, whiteOpen = false;
    public int tick = 0;

    public InjectorTE(BlockPos pos, BlockState state) {
        super(ModTE.INJECTOR_TE.get(),pos,state);
        this.pinkTank = new FluidTank(this.TANK_CAPACITY());
        this.blueTank = new FluidTank(this.TANK_CAPACITY());
        this.yellowTank = new FluidTank(this.TANK_CAPACITY());
        this.whiteTank = new FluidTank(this.TANK_CAPACITY());
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.pinkTank.readFromNBT(nbt.getCompound("pinkTank"));
        this.blueTank.readFromNBT(nbt.getCompound("blueTank"));
        this.yellowTank.readFromNBT(nbt.getCompound("yellowTank"));
        this.whiteTank.readFromNBT(nbt.getCompound("whiteTank"));
        this.pinkOpen = nbt.getBoolean("pinkOpen");
        this.blueOpen = nbt.getBoolean("blueOpen");
        this.yellowOpen = nbt.getBoolean("yellowOpen");
        this.whiteOpen = nbt.getBoolean("whiteOpen");
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if(!this.tryLoadLootTable(nbt)){
            ContainerHelper.loadAllItems(nbt, this.items);
        }
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("pinkTank", this.pinkTank.writeToNBT(new CompoundTag()));
        compound.put("blueTank", this.blueTank.writeToNBT(new CompoundTag()));
        compound.put("yellowTank", this.yellowTank.writeToNBT(new CompoundTag()));
        compound.put("whiteTank", this.whiteTank.writeToNBT(new CompoundTag()));
        compound.putBoolean("pinkOpen", this.pinkOpen);
        compound.putBoolean("blueOpen", this.blueOpen);
        compound.putBoolean("yellowOpen", this.yellowOpen);
        compound.putBoolean("whiteOpen", this.whiteOpen);
        if(!this.trySaveLootTable(compound)){
            ContainerHelper.saveAllItems(compound, this.items);
        }
    }

    //TODO: FIX LOTS OF NBT STUFF

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        InjectorTE te = (InjectorTE)be;
        if(!level.isClientSide()) {
            te.HandleSlotUpdates();
        }
    }

    public void HandleSlotUpdates(){
        for (int i = 0; i < 4; i++) {
            ItemStack stack = this.getItem(i);
            if (this.shouldPullFluidFromStack(i)) {
                if (stack != ItemStack.EMPTY) {
                    if(stack.getItem() instanceof BucketItem bucket) {
                        if (this.isValidForSlot(i, bucket)) {
                            int filled = this.FillFluidTanks(i, 1000);
                            //TODO: TEMPORARY
                            this.setItem(i, new ItemStack(Items.BUCKET.asItem()));
                            assert this.level != null;
                            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
                            this.setChanged();
                        }
                    }
                }
            }
        }
    }

    //INJECTOR FUNCTIONALITY

    //INJECTOR FUNCTIONALITY

    public void DumpFluids(){
        if (this.pinkOpen) {
            FluidTank tank = this.getTankFromValue(0);
            tank.setFluid(FluidStack.EMPTY);
        }
        if (this.blueOpen) {
            FluidTank tank = this.getTankFromValue(1);
            tank.setFluid(FluidStack.EMPTY);
        }
        if (this.yellowOpen) {
            FluidTank tank = this.getTankFromValue(2);
            tank.setFluid(FluidStack.EMPTY);
        }
        if (this.whiteOpen) {
            FluidTank tank = this.getTankFromValue(3);
            tank.setFluid(FluidStack.EMPTY);
        }
        assert this.level != null;
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public void Inject() {
        if (this.getItem(InjectorTE.CHROMA_INPUT_SLOT_INDEX).getItem() instanceof ItemChroma &&
                (this.getTankFromValue(0).getFluid().getFluid() != Fluids.EMPTY && this.pinkOpen ||
                        this.getTankFromValue(1).getFluid().getFluid() != Fluids.EMPTY && this.blueOpen ||
                        this.getTankFromValue(2).getFluid().getFluid() != Fluids.EMPTY && this.yellowOpen ||
                        this.getTankFromValue(3).getFluid().getFluid() != Fluids.EMPTY && this.whiteOpen)) {
            int portionToDrain = 0;
            if(this.pinkOpen){
                portionToDrain++;
            }
            if(this.blueOpen){
                portionToDrain++;
            }
            if(this.yellowOpen){
                portionToDrain++;
            }
            if(this.whiteOpen){
                portionToDrain++;
            }
            String essences = "";
            if (this.pinkOpen) {
                FluidTank tank = this.getTankFromValue(0);
                if (tank.getFluid() != FluidStack.EMPTY) {
                    essences+="pink";
                    tank.getFluid().setAmount(Math.max(tank.getFluidAmount() - (200 / portionToDrain), 0));
                }
            }
            if (this.blueOpen) {
                FluidTank tank = this.getTankFromValue(1);
                if (tank.getFluid() != FluidStack.EMPTY) {
                    if(essences.equals("")){
                        essences += "blue";
                    }
                    else{
                        essences+="-blue";
                    }
                    tank.getFluid().setAmount(Math.max(tank.getFluidAmount() - (200 / portionToDrain), 0));
                }
            }
            if (this.yellowOpen) {
                FluidTank tank = this.getTankFromValue(2);
                if (tank.getFluid() != FluidStack.EMPTY) {
                    if(essences.equals("")){
                        essences += "yellow";
                    }
                    else{
                        essences+="-yellow";
                    }
                    tank.getFluid().setAmount(Math.max(tank.getFluidAmount() - (200 / portionToDrain), 0));
                }
            }
            if (this.whiteOpen) {
                FluidTank tank = this.getTankFromValue(3);
                if (tank.getFluid() != FluidStack.EMPTY) {
                    if(essences.equals("")){
                        essences += "white";
                    }
                    else{
                        essences+="-white";
                    }
                    tank.getFluid().setAmount(Math.max(tank.getFluidAmount() - (200 / portionToDrain), 0));
                }
            }
            BlockPos seedPos = this.getBlockPos().offset(new BlockPos(0, -Math.ceil(GemSeedTE.DRAIN_SIZE / 2) - 1, 0));
            while(true){
                assert this.level != null;
                if (!(this.level.getBlockState(seedPos) == Blocks.AIR.defaultBlockState() ||
                                    this.level.getBlockState(seedPos).getBlock() instanceof LiquidBlock ||
                                    this.level.getBlockState(seedPos) == ModBlocks.GEM_SEED_BLOCK.get().defaultBlockState())) break;
                seedPos = seedPos.offset(0, -GemSeedTE.DRAIN_SIZE, 0);
            }
            ItemChroma chroma = (ItemChroma)this.getItem(InjectorTE.CHROMA_INPUT_SLOT_INDEX).getItem();
            Item primer = this.getItem(InjectorTE.PRIME_INPUT_SLOT_INDEX).getItem();
            GemSeedBlock seedBlock = (GemSeedBlock) ModBlocks.GEM_SEED_BLOCK.get();
            this.level.setBlockAndUpdate(seedPos, seedBlock.defaultBlockState());
            if(this.level.getBlockState(seedPos).getBlock() == ModBlocks.GEM_SEED_BLOCK.get()) {
                Objects.requireNonNull(this.getLevel()).playSound(null, this.getBlockPos(), ModSounds.INJECT.get(), SoundSource.AMBIENT, 2f, 1);
            }
            GemSeedTE gemSeedTE = (GemSeedTE) this.level.getBlockEntity(seedPos);
            assert gemSeedTE != null;
            gemSeedTE.setEssences(essences);
            gemSeedTE.SetChroma(chroma);
            gemSeedTE.SetPrimer(primer);
            int facing = InjectorTE.getFacingFromState(this.getBlockState());
            gemSeedTE.setFacing(facing);
            System.out.println("Facing :" + facing);
            this.getItem(InjectorTE.CHROMA_INPUT_SLOT_INDEX).shrink(1);
            this.getItem(InjectorTE.PRIME_INPUT_SLOT_INDEX).shrink(1);

            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
            this.setChanged();
            InjectEvent event = new InjectEvent(gemSeedTE, seedPos);
            MinecraftForge.EVENT_BUS.post(event);
        }
    }

    public static int getFacingFromState(BlockState state){
        if(state.getValue(InjectorBlock.FACING) == Direction.EAST){
            return 0;
        }
        else if(state.getValue(InjectorBlock.FACING) == Direction.NORTH){
            return 1;
        }
        else if(state.getValue(InjectorBlock.FACING) == Direction.WEST){
            return 2;
        }
        else if(state.getValue(InjectorBlock.FACING) == Direction.SOUTH){
            return 3;
        }
        else{
            return -1;
        }
    }

    public CompoundTag getCompountNBTForPacket(ItemChroma chroma, Item primer, Fluid[] essences){
        CompoundTag compound = new CompoundTag();
        compound.putInt("ticks", 0);
        compound.putBoolean("spawned", false);
        compound.put("chroma", new ItemStack(chroma).save(new CompoundTag()));
        compound.put("primer", new ItemStack(primer).save(new CompoundTag()));
        String fluids = "";
        for(int i = 0; i < essences.length; i++){
            if(i == 0){
                fluids+=GemSeedTE.StringFromFluid(essences[0]);
            }
            else{
                fluids+= "-"+GemSeedTE.StringFromFluid(essences[i]);
            }
        }
        compound.putString("essences", fluids);
        return compound;
    }

    public boolean shouldPullFluidFromStack(int tank){
        return this.getTankFromValue(tank).getFluid().getAmount() < this.getCapacity();
    }

    public boolean isValidForSlot(int slot, BucketItem bucket){
        if(slot == InjectorTE.PINK_INPUT_SLOT_INDEX){
            return bucket == ModFluids.PINK_ESSENCE.bucket.get();
        }
        else if(slot == InjectorTE.BLUE_INPUT_SLOT_INDEX){
            return bucket == ModFluids.BLUE_ESSENCE.bucket.get();
        }
        else if(slot == InjectorTE.YELLOW_INPUT_SLOT_INDEX){
            return bucket == ModFluids.YELLOW_ESSENCE.bucket.get();
        }
        else if(slot == InjectorTE.WHITE_INPUT_SLOT_INDEX){
            return bucket == ModFluids.WHITE_ESSENCE.bucket.get();
        }
        else return slot == InjectorTE.PRIME_INPUT_SLOT_INDEX;
    }

    public void ToggleTankOpen(String color){
        switch (color) {
            case "blue" -> this.blueOpen = !this.blueOpen;
            case "yellow" -> this.yellowOpen = !this.yellowOpen;
            case "white" -> this.whiteOpen = !this.whiteOpen;
            default -> this.pinkOpen = !this.pinkOpen;
        }
        assert this.level != null;
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    //CONTAINER STUFF

    //CONTAINER STUFF

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("");//TranslationTextComponent("container.gempire.injector");
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.gempire.injector");
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory player) {
        return new InjectorContainer(id, player, this);
    }

    @Override
    public int getContainerSize() {
        return InjectorTE.NUMBER_OF_SLOTS;
    }

    // FLUID STUFF

    // FLUID STUFF

    @Override
    public int getCapacity() {
        return this.TANK_CAPACITY();
    }

    public int FillFluidTanks(int value, int amount){
        FluidTank tank = this.getTankFromValue(value);
        if(tank.getFluidAmount() >= tank.getCapacity()) {
            return 0;
        }
        else{
            if(tank.getFluid().getFluid() == Fluids.EMPTY){
                tank.setFluid(new FluidStack(this.getFluidFromValue(value), amount));
                return amount;
            }
            else{
                tank.getFluid();
                if(this.getFluidFromValue(value) == tank.getFluid().getFluid()){
                    if(tank.getFluidAmount() + amount <= tank.getCapacity())
                    {
                        tank.getFluid().setAmount(tank.getFluidAmount() + amount);
                        return amount;
                    }
                    else{
                        tank.getFluid().setAmount(tank.getCapacity());
                        return tank.getCapacity() - tank.getFluidAmount();
                    }
                }
                else{
                    return 0;
                }
            }
        }
    }

    public FluidTank getTankFromFluid(FluidStack fluidStack){
        if(fluidStack.getFluid() == ModFluids.PINK_ESSENCE.source.get()){
            return this.pinkTank;
        }
        else if(fluidStack.getFluid() == ModFluids.BLUE_ESSENCE.source.get()){
            return this.blueTank;
        }
        else if(fluidStack.getFluid() == ModFluids.YELLOW_ESSENCE.source.get()){
            return this.yellowTank;
        }
        else if(fluidStack.getFluid() == ModFluids.WHITE_ESSENCE.source.get()){
            return this.whiteTank;
        }
        else{
            return null;
        }
    }

    public FluidTank getTankFromValue(int value){
        if(value == 0){
            return this.pinkTank;
        }
        else if(value == 1){
            return this.blueTank;
        }
        else if(value == 2){
            return this.yellowTank;
        }
        else if(value == 3){
            return this.whiteTank;
        }
        else{
            return null;
        }
    }

    public Fluid getFluidFromValue(int value){
        if(value == 0){
            return ModFluids.PINK_ESSENCE.source.get();
        }
        else if(value == 1){
            return ModFluids.BLUE_ESSENCE.source.get();
        }
        else if(value == 2){
            return ModFluids.YELLOW_ESSENCE.source.get();
        }
        else if(value == 3){
            return ModFluids.WHITE_ESSENCE.source.get();
        }
        else{
            return Fluids.EMPTY;
        }
    }

    public FluidStack getFluid(FluidStack stack) {
        return this.getTankFromFluid(stack).getFluid();
    }

    public FluidStack getFluid(int value) {
        if(value == 0){
            return this.pinkTank.getFluid();
        }
        else if(value == 1){
            return this.blueTank.getFluid();
        }
        else if(value == 2){
            return this.yellowTank.getFluid();
        }
        else if(value == 3){
            return this.whiteTank.getFluid();
        }
        else{
            return null;
        }
    }

    public int getFluidAmount(FluidStack stack) {
        return this.getTankFromFluid(stack).getFluid().getAmount();
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        int amountAfterDrain = Math.max(this.getTankFromFluid(resource).getFluidAmount() - resource.getAmount(), 0);
        this.getFluid(resource).setAmount(amountAfterDrain);
        return this.getFluid(resource);
    }

    public int amountDrained(FluidStack resource){
        if(this.getFluid(resource).getAmount() - resource.getAmount() < 0)
        {
            return this.getFluid(resource).getAmount();
        }
        else {
            return resource.getAmount();
        }
    }

    public boolean isFluidValidForTank(FluidStack fluid, FluidTank tank){
        return fluid.getFluid() == tank.getFluid().getFluid();
    }

    //NETWORKING STUFF

    //NETWORKING STUFF

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        //Debug
        System.out.println("[DEBUG]:Client recived tile sync packet");
        this.load(Objects.requireNonNull(pkt.getTag()));
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

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        System.out.println("[DEBUG]:Handling tag on chunk load");
        this.load(tag);
    }

    //REDUNDANT STUFF

    //REDUNDANT STUFF

    @Nonnull
    @Override
    public FluidStack getFluid() {
        return FluidStack.EMPTY;
    }

    @Override
    public int getFluidAmount() {
        return 0;
    }
    @Override
    public boolean isFluidValid(FluidStack stack) {
        return false;
    }
    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        return FluidStack.EMPTY;
    }
    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        if(this.getFluid(resource) == FluidStack.EMPTY) {
            this.setChanged();
            return resource.getAmount();
        }
        else if(resource == null){
            return 0;
        }
        else{
            if(this.getFluid(resource) == null){
                this.getTankFromFluid(resource).setFluid(resource);
            }
            if (resource.getFluid() != this.getFluid(resource).getFluid()) {
                this.setChanged();
                return 0;
            } else {
                if (this.getFluidAmount(resource) >= this.getCapacity()) {
                    this.setChanged();
                    return 0;
                } else {
                    if (this.getFluidAmount(resource) + resource.getAmount() > this.getCapacity()) {
                        this.getFluid(resource).setAmount(this.getCapacity());
                    } else {
                        this.getFluid(resource).setAmount(this.getFluidAmount(resource) + resource.getAmount());
                    }
                    this.setChanged();
                    return resource.getAmount();
                }
            }
        }
        //return 0;
    }
}