package com.gempire.tileentities;

import com.gempire.blocks.machine.ShellBlock;
import com.gempire.container.ShellContainer;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModFluids;
import com.gempire.init.ModItems;
import com.gempire.init.ModTE;
import com.gempire.items.ItemChroma;
import com.gempire.items.ItemGem;
import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.interfaces.IPowerConductor;
import com.gempire.systems.machine.interfaces.IPowerConsumer;
import com.gempire.systems.machine.interfaces.IPowerProvider;
import com.gempire.util.Color;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ShellTE extends RandomizableContainerBlockEntity implements MenuProvider, TickableBlockEntity, IPowerConsumer {
    public static final int NUMBER_OF_SLOTS = 5;
    public static final int CHROMA_INPUT_SLOT_INDEX = 0;
    public static final int CLAY_INPUT_SLOT_INDEX = 1;
    public static final int SAND_INPUT_SLOT_INDEX = 2;
    public static final int GRAVEL_INPUT_SLOT_INDEX = 3;
    public static final int PEARL_OUTPUT_SLOT_INDEX = 4;
    public NonNullList<ItemStack> items = NonNullList.withSize(ShellTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public boolean waterlogged = false;
    public static final int MAX_GRAVEL = 64;
    public static final int MAX_SAND = 64;
    public static final int MAX_CLAY = 64;
    public int gravelConsumed = 0;
    public int sandConsumed = 0;
    public int clayConsumed = 0;
    public boolean chromaConsumed = false;
    public boolean essenceConsumed = false;
    public boolean essenceMarker = false;
    public int chromaColor = 0;
    public int ticks = 0;

    public ShellTE() {
        super(ModTE.SHELL_TE.get());
        setupBattery(800);
        setupInitialSockets(this);
        //setupSocket(0, Socket.POWER_IN(MachineSide.BOTTOM), this);
        //setupSocket(1, Socket.POWER_IN(MachineSide.TOP), this);
        setupSocket(2, Socket.POWER_IN(MachineSide.BACK), this);
        //setupSocket(3, Socket.POWER_IN(MachineSide.FRONT), this);
        //setupSocket(4, Socket.POWER_IN(MachineSide.LEFT), this);
        //setupSocket(5, Socket.POWER_IN(MachineSide.RIGHT), this);
    }

    @Override
    public void load(BlockState state, CompoundTag nbt) {
        super.load(state, nbt);
        ReadPoweredMachine(nbt);
        this.gravelConsumed = nbt.getInt("gravel");
        this.sandConsumed = nbt.getInt("sand");
        this.clayConsumed = nbt.getInt("clay");
        this.chromaConsumed = nbt.getBoolean("chroma");
        this.essenceConsumed = nbt.getBoolean("essence");
        this.essenceMarker = nbt.getBoolean("marker");
        this.chromaColor = nbt.getInt("color");
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if(!this.tryLoadLootTable(nbt)){
            ContainerHelper.loadAllItems(nbt, this.items);
        }
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        super.save(compound);
        WritePoweredMachine(compound);
        compound.putInt("gravel", this.gravelConsumed);
        compound.putInt("sand", this.sandConsumed);
        compound.putInt("clay", this.clayConsumed);
        compound.putBoolean("chroma", this.chromaConsumed);
        compound.putBoolean("essence", this.essenceConsumed);
        compound.putBoolean("marker", this.essenceMarker);
        compound.putInt("color", this.chromaColor);
        if(!this.trySaveLootTable(compound)){
            ContainerHelper.saveAllItems(compound, this.items);
        }
        return compound;
    }


    @Override
    public void tick() {
        ConductorTick();
        if(this.getItem(ShellTE.PEARL_OUTPUT_SLOT_INDEX) == ItemStack.EMPTY) {
            if(this.ticks % 100 == 0) {
                if(isPowered()) {
                    this.HandleGravelTick();
                    this.HandleSandTick();
                    this.HandleClayTick();
                    this.HandleChromaTick();
                    this.HandleEssenceTick();
                    this.HandleFormPearlTick();
                    if (this.gravelConsumed == 1) {
                        this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(ShellBlock.STAGE, 1));
                    }
                    if (this.sandConsumed == ShellTE.MAX_SAND) {
                        this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(ShellBlock.STAGE, 2));
                    }
                }
            }
            if(this.ticks > 100){
                this.ticks = 0;
            }
            else {
                this.ticks++;
            }
        }
    }

    public void HandleGravelTick(){
        if(this.gravelConsumed < ShellTE.MAX_GRAVEL) {
            ItemStack stack = this.getItem(ShellTE.GRAVEL_INPUT_SLOT_INDEX);
            if (stack.getItem() == Blocks.GRAVEL.asItem()) {
                stack.shrink(1);
                usePower();
                this.gravelConsumed++;
            }
        }
    }

    public void HandleSandTick(){
        if(this.gravelConsumed == ShellTE.MAX_GRAVEL && this.sandConsumed < ShellTE.MAX_SAND){
            ItemStack stack = this.getItem(ShellTE.SAND_INPUT_SLOT_INDEX);
            if(stack.getItem() == Blocks.SAND.asItem()){
                stack.shrink(1);
                usePower();
                this.sandConsumed++;
            }
        }
    }

    public void HandleClayTick(){
        if(this.gravelConsumed == ShellTE.MAX_GRAVEL && this.sandConsumed == ShellTE.MAX_SAND && this.clayConsumed < ShellTE.MAX_CLAY){
            ItemStack stack = this.getItem(ShellTE.CLAY_INPUT_SLOT_INDEX);
            if(stack.getItem() == Items.CLAY_BALL){
                stack.shrink(1);
                usePower();
                this.clayConsumed++;
            }
        }
    }

    public void HandleChromaTick() {
        if(this.gravelConsumed == ShellTE.MAX_GRAVEL && this.sandConsumed == ShellTE.MAX_SAND && this.clayConsumed == ShellTE.MAX_CLAY && !this.chromaConsumed){
            ItemStack stack = this.getItem(ShellTE.CHROMA_INPUT_SLOT_INDEX);
            if(stack.getItem() instanceof ItemChroma){
                ItemChroma chroma = (ItemChroma) stack.getItem();
                usePower();
                this.chromaConsumed = true;
                this.chromaColor = chroma.color;
                stack.shrink(1);
            }
        }
    }

    public void HandleEssenceTick(){
        if(this.gravelConsumed == ShellTE.MAX_GRAVEL && this.sandConsumed == ShellTE.MAX_SAND && this.clayConsumed == ShellTE.MAX_CLAY && this.chromaConsumed){
            //ESSENCE CHECK
            for(int i = 0; i < 6; i++){
                //TODO: MAKE THERE BE A CHANCE OF MAGIC MOSS APPEARING
                if(this.level.getBlockState(this.worldPosition.offset(ShellTE.direction(i))).getBlock() == ModBlocks.WHITE_ESSENCE_BLOCK.get()){
                    LiquidBlock block = (LiquidBlock) this.level.getBlockState(this.worldPosition.offset(ShellTE.direction(i))).getBlock();
                    if(block.getFluid() == ModFluids.WHITE_ESSENCE.get()) {
                        this.level.setBlockAndUpdate(this.worldPosition.offset(ShellTE.direction(i)), Blocks.AIR.defaultBlockState());
                        this.essenceConsumed = true;
                        this.essenceMarker = true;
                        usePower();
                        break;
                    }
                }
            }
        }
        else{
            this.essenceMarker = false;
            for(int i = 0; i < 6; i++){
                if(this.level.getBlockState(this.worldPosition.offset(ShellTE.direction(i))).getBlock() == ModBlocks.WHITE_ESSENCE_BLOCK.get()){
                    LiquidBlock block = (LiquidBlock) this.level.getBlockState(this.worldPosition.offset(ShellTE.direction(i))).getBlock();
                    if(block.getFluid() == ModFluids.WHITE_ESSENCE.get()) {
                        this.essenceMarker = true;
                        break;
                    }
                }
            }
        }
    }

    public static BlockPos direction(int i){
        switch (i){
            case 1:
                return BlockPos.ZERO.south();
            case 2:
                return BlockPos.ZERO.above();
            case 3:
                return BlockPos.ZERO.west();
            case 4:
                return BlockPos.ZERO.below();
            case 5:
                return BlockPos.ZERO.east();
            default:
                return BlockPos.ZERO.north();
        }
    }

    public void HandleFormPearlTick(){
        if(this.gravelConsumed == ShellTE.MAX_GRAVEL && this.sandConsumed == ShellTE.MAX_SAND && this.clayConsumed == ShellTE.MAX_CLAY && this.chromaConsumed
                && this.essenceConsumed) {
            this.formPearl(this.chromaColor);
            usePower();
        }
    }

    public void formPearl(int chroma){
        RegistryObject<Item> gemm = ModItems.PEBBLE_GEM;
        ItemGem gem = null;
        String name = Color.getColorName(chroma).toUpperCase() +"_PEARL_GEM";
        try {
            gemm = (RegistryObject<Item>) ModItems.class.getField(name.toUpperCase()).get(null);
            gem = (ItemGem) gemm.get();
        } catch(Exception e){
            e.printStackTrace();
        }
        this.setItem(ShellTE.PEARL_OUTPUT_SLOT_INDEX, new ItemStack(gem));
        this.gravelConsumed = 0;
        this.sandConsumed = 0;
        this.clayConsumed = 0;
        this.chromaConsumed = false;
        this.essenceConsumed = false;
        this.chromaColor = 0;
        this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(ShellBlock.STAGE, 0));
    }

    //TODO: FIX LOTS OF NBT STUFF

    //CONTAINER STUFF

    //CONTAINER STUFF

    @Override
    public Component getDisplayName() {
        return new TextComponent("");//TranslationTextComponent("container.gempire.injector");
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container.gempire.injector");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new ShellContainer(id, player, this);
    }

    @Override
    public int getContainerSize() {
        return ShellTE.NUMBER_OF_SLOTS;
    }

    public ItemStack getPearlItem(){
        return this.getItem(ShellTE.PEARL_OUTPUT_SLOT_INDEX);
    }

    //NETWORKING STUFF

    //NETWORKING STUFF

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        //Debug
        System.out.println("[DEBUG]:Client recived tile sync packet");
        this.load(this.level.getBlockState(pkt.getPos()), pkt.getTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.save(new CompoundTag());
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        //Debug
        System.out.println("[DEBUG]:Server sent tile sync packet");
        return new ClientboundBlockEntityDataPacket(this.worldPosition, -1, this.getUpdateTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundTag tag) {
        System.out.println("[DEBUG]:Handling tag on chunk load");
        this.load(state, tag);
    }

    //ENERGY

    ArrayList<Socket> SOCKETS = new ArrayList<>();
    Battery battery;
    float voltage;

    @Override
    public ArrayList<Socket> getSockets() {
        return SOCKETS;
    }

    @Override
    public float getVoltage() {
        return voltage;
    }

    @Override
    public void combineVoltage(float inVoltage) {
        voltage += inVoltage;
    }

    @Override
    public void setVoltage(float inVoltage) {
        voltage = inVoltage;
    }

    @Override
    public boolean isSource() {
        return false;
    }

    @Override
    public Battery getBattery() {
        return battery;
    }

    @Override
    public void setupBattery(float maxCapacity) {
        battery = new Battery(maxCapacity);
    }

    @Override
    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    @Override
    public BlockEntity getTE() {
        return this;
    }

    int drawTicks = 0;

    @Override
    public int getTicks() {
        return drawTicks;
    }

    @Override
    public void addTick() {
        drawTicks++;
    }

    @Override
    public void setTicks(int ticks) {
        drawTicks = ticks;
    }

    @Override
    public float getBandwidth() {
        return 5f;
    }

    @Override
    public float minimumUnitPower() {
        return 10;
    }

    /*

    1) Handle Gravel, Sand, Clay consumption in correct order.
    2) Handle Chroma consumption in the end.
    3) Handle gem creation.
    4) Update block with "hasGem" state and "growthStage" state.
    5) Retroactively add essence consumption by means of proximity.

    */
}
