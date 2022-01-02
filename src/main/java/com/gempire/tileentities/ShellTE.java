package com.gempire.tileentities;

import com.gempire.blocks.machine.ShellBlock;
import com.gempire.container.ShellContainer;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModFluids;
import com.gempire.init.ModItems;
import com.gempire.init.ModTE;
import com.gempire.items.ItemChroma;
import com.gempire.items.ItemGem;
import com.gempire.systems.machine.EnergyPackage;
import com.gempire.systems.machine.MachineSide;
import com.gempire.systems.machine.Socket;
import com.gempire.systems.machine.interfaces.IPowerConsumer;
import com.gempire.util.Color;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ShellTE extends LockableLootTileEntity implements INamedContainerProvider, ITickableTileEntity, IPowerConsumer {
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
        setupInitialSockets(this);
        setupSocket(0, Socket.POWER_IN(MachineSide.BOTTOM), this);
        setupSocket(1, Socket.POWER_IN(MachineSide.TOP), this);
        setupSocket(2, Socket.POWER_IN(MachineSide.BACK), this);
        setupSocket(3, Socket.POWER_IN(MachineSide.FRONT), this);
        setupSocket(4, Socket.POWER_IN(MachineSide.LEFT), this);
        setupSocket(5, Socket.POWER_IN(MachineSide.RIGHT), this);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.gravelConsumed = nbt.getInt("gravel");
        this.sandConsumed = nbt.getInt("sand");
        this.clayConsumed = nbt.getInt("clay");
        this.chromaConsumed = nbt.getBoolean("chroma");
        this.essenceConsumed = nbt.getBoolean("essence");
        this.essenceMarker = nbt.getBoolean("marker");
        this.chromaColor = nbt.getInt("color");
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if(!this.checkLootAndRead(nbt)){
            ItemStackHelper.loadAllItems(nbt, this.items);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        WriteConductor(compound, this, this);
        WriteConsumer(compound, this, this);
        compound.putInt("gravel", this.gravelConsumed);
        compound.putInt("sand", this.sandConsumed);
        compound.putInt("clay", this.clayConsumed);
        compound.putBoolean("chroma", this.chromaConsumed);
        compound.putBoolean("essence", this.essenceConsumed);
        compound.putBoolean("marker", this.essenceMarker);
        compound.putInt("color", this.chromaColor);
        if(!this.checkLootAndWrite(compound)){
            ItemStackHelper.saveAllItems(compound, this.items);
        }
        return compound;
    }


    @Override
    public void tick() {
        TickTE(this, this);
        if(this.getStackInSlot(ShellTE.PEARL_OUTPUT_SLOT_INDEX) == ItemStack.EMPTY) {
            if(this.ticks % 1 == 0) {
                DrainPower(this, this);
                this.HandleGravelTick();
                this.HandleSandTick();
                this.HandleClayTick();
                this.HandleChromaTick();
                this.HandleEssenceTick();
                this.HandleFormPearlTick();
                if (this.gravelConsumed == 1) {
                    this.world.setBlockState(this.getPos(), this.getBlockState().with(ShellBlock.STAGE, 1));
                }
                if (this.sandConsumed == ShellTE.MAX_SAND) {
                    this.world.setBlockState(this.getPos(), this.getBlockState().with(ShellBlock.STAGE, 2));
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
            ItemStack stack = this.getStackInSlot(ShellTE.GRAVEL_INPUT_SLOT_INDEX);
            if (stack.getItem() == Blocks.GRAVEL.asItem()) {
                stack.shrink(1);
                this.gravelConsumed++;
            }
        }
    }

    public void HandleSandTick(){
        if(this.gravelConsumed == ShellTE.MAX_GRAVEL && this.sandConsumed < ShellTE.MAX_SAND){
            ItemStack stack = this.getStackInSlot(ShellTE.SAND_INPUT_SLOT_INDEX);
            if(stack.getItem() == Blocks.SAND.asItem()){
                stack.shrink(1);
                this.sandConsumed++;
            }
        }
    }

    public void HandleClayTick(){
        if(this.gravelConsumed == ShellTE.MAX_GRAVEL && this.sandConsumed == ShellTE.MAX_SAND && this.clayConsumed < ShellTE.MAX_CLAY){
            ItemStack stack = this.getStackInSlot(ShellTE.CLAY_INPUT_SLOT_INDEX);
            if(stack.getItem() == Items.CLAY_BALL){
                stack.shrink(1);
                this.clayConsumed++;
            }
        }
    }

    public void HandleChromaTick() {
        if(this.gravelConsumed == ShellTE.MAX_GRAVEL && this.sandConsumed == ShellTE.MAX_SAND && this.clayConsumed == ShellTE.MAX_CLAY && !this.chromaConsumed){
            ItemStack stack = this.getStackInSlot(ShellTE.CHROMA_INPUT_SLOT_INDEX);
            if(stack.getItem() instanceof ItemChroma){
                ItemChroma chroma = (ItemChroma) stack.getItem();
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
                if(this.world.getBlockState(this.pos.add(ShellTE.direction(i))).getBlock() == ModBlocks.WHITE_ESSENCE_BLOCK.get()){
                    FlowingFluidBlock block = (FlowingFluidBlock) this.world.getBlockState(this.pos.add(ShellTE.direction(i))).getBlock();
                    if(block.getFluid() == ModFluids.WHITE_ESSENCE.get()) {
                        this.world.setBlockState(this.pos.add(ShellTE.direction(i)), Blocks.AIR.getDefaultState());
                        this.essenceConsumed = true;
                        this.essenceMarker = true;
                        break;
                    }
                }
            }
        }
        else{
            this.essenceMarker = false;
            for(int i = 0; i < 6; i++){
                if(this.world.getBlockState(this.pos.add(ShellTE.direction(i))).getBlock() == ModBlocks.WHITE_ESSENCE_BLOCK.get()){
                    FlowingFluidBlock block = (FlowingFluidBlock) this.world.getBlockState(this.pos.add(ShellTE.direction(i))).getBlock();
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
                return BlockPos.ZERO.up();
            case 3:
                return BlockPos.ZERO.west();
            case 4:
                return BlockPos.ZERO.down();
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
        this.setInventorySlotContents(ShellTE.PEARL_OUTPUT_SLOT_INDEX, new ItemStack(gem));
        this.gravelConsumed = 0;
        this.sandConsumed = 0;
        this.clayConsumed = 0;
        this.chromaConsumed = false;
        this.essenceConsumed = false;
        this.chromaColor = 0;
        this.world.setBlockState(this.getPos(), this.getBlockState().with(ShellBlock.STAGE, 0));
    }

    //TODO: FIX LOTS OF NBT STUFF

    //CONTAINER STUFF

    //CONTAINER STUFF

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("");//TranslationTextComponent("container.gempire.injector");
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.gempire.injector");
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
    protected Container createMenu(int id, PlayerInventory player) {
        return new ShellContainer(id, player, this);
    }

    @Override
    public int getSizeInventory() {
        return ShellTE.NUMBER_OF_SLOTS;
    }

    public ItemStack getPearlItem(){
        return this.getStackInSlot(ShellTE.PEARL_OUTPUT_SLOT_INDEX);
    }



    //NETWORKING STUFF

    //NETWORKING STUFF

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

    //ENERGY

    ArrayList<Socket> SOCKETS = new ArrayList<>();
    ArrayList<BlockPos> GENERATORS = new ArrayList<>();
    EnergyPackage CURRENT_PACKAGE = EnergyPackage.DEFAULT();
    ArrayList<EnergyPackage> STORED_PACKAGES = new ArrayList<>();
    boolean CHANGED = false;
    boolean CONDUCT = false;
    int tiks = 0;

    @Override
    public ArrayList<EnergyPackage> getStoredPackages() {
        return STORED_PACKAGES;
    }

    @Override
    public EnergyPackage getCurrentPackage() {
        return CURRENT_PACKAGE;
    }

    @Override
    public void setChanged(boolean value) {
        CHANGED = value;
    }

    @Override
    public boolean getChanged() {
        return CHANGED;
    }

    @Override
    public void setConduct(boolean value) {
        CONDUCT = value;
    }

    @Override
    public boolean getConduct() {
        return CONDUCT;
    }

    @Override
    public void setCP(EnergyPackage PACKAGE) {
        CURRENT_PACKAGE = PACKAGE;
        addGenerator(PACKAGE, this, this);
    }

    @Override
    public int getTicks() {
        return tiks;
    }

    @Override
    public void addTicks() {
        tiks++;
    }

    @Override
    public ArrayList<BlockPos> getGenerators() {
        return GENERATORS;
    }

    @Override
    public ArrayList<Socket> getSockets() {
        return SOCKETS;
    }

    /*

    1) Handle Gravel, Sand, Clay consumption in correct order.
    2) Handle Chroma consumption in the end.
    3) Handle gem creation.
    4) Update block with "hasGem" state and "growthStage" state.
    5) Retroactively add essence consumption by means of proximity.

     */
}
