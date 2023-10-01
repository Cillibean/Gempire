package com.gempire.tileentities;

import com.gempire.container.IncubatorContainer;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.*;
import com.gempire.items.ItemChroma;
import com.gempire.items.ItemGem;
import com.gempire.items.ItemGemBase;
import com.gempire.util.Color;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class IncubatorTE extends RandomizableContainerBlockEntity implements MenuProvider {
    public static final int NUMBER_OF_SLOTS = 10;
    public static final int BLOCK1_INPUT_SLOT_INDEX = 0;
    public static final int PRIMER_INPUT_SLOT_INDEX = 1;
    public static final int BLOCK2_INPUT_SLOT_INDEX = 2;
    public static final int BLOCK3_INPUT_SLOT_INDEX = 3;

    public static final int GEM_BASE_INPUT_SLOT_INDEX = 4;
    public static final int BLOCK4_INPUT_SLOT_INDEX = 5;
    public static final int ESSENCE1_INPUT_SLOT_INDEX = 6;
    public static final int CHROMA_INPUT_SLOT_INDEX = 7;
    public static final int ESSENCE2_INPUT_SLOT_INDEX = 8;
    public static final int GEM_OUTPUT_SLOT_INDEX = 9;
    public NonNullList<ItemStack> items = NonNullList.withSize(IncubatorTE.NUMBER_OF_SLOTS, ItemStack.EMPTY);
    public static final int MAX_BLOCK1 = 64;
    public static final int MAX_BLOCK2 = 64;
    public static final int MAX_BLOCK3 = 64;
    public static final int MAX_BLOCK4 = 64;
    public String gemBase = "";
    public int block1Consumed = 0;
    public int block2Consumed = 0;
    public int block3Consumed = 0;
    public int block4Consumed = 0;
    public boolean baseConsumed = false;
    public boolean chromaConsumed = false;
    public boolean primerConsumed = false;
    public int essence1Consumed = 0;
    public int essence2Consumed = 0;

    //essence numbering
    // 0 = not consumed
    // 1 = pink
    // 2 = yellow
    // 3 = blue
    // 4 = white
    public int chromaColor = 0;
    public int ticks = 0;

    public HashMap<String, Boolean> colour = new HashMap<>();

    public IncubatorTE(BlockPos pos, BlockState state) {
        super(ModTE.INCUBATOR_TE.get(), pos, state);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.block1Consumed = nbt.getInt("block1");
        this.block2Consumed = nbt.getInt("block2");
        this.block3Consumed = nbt.getInt("block3");
        this.block4Consumed = nbt.getInt("block4");
        this.chromaConsumed = nbt.getBoolean("chroma");
        this.primerConsumed = nbt.getBoolean("primer");
        this.baseConsumed = nbt.getBoolean("base");
        this.essence1Consumed = nbt.getInt("essence1");
        this.essence2Consumed = nbt.getInt("essence2");
        this.chromaColor = nbt.getInt("color");
        this.gemBase = nbt.getString("base");
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if(!this.tryLoadLootTable(nbt)){
            ContainerHelper.loadAllItems(nbt, this.items);
        }
        gemBase = this.items.get(4).getItem().toString();
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("block1", this.block1Consumed);
        compound.putInt("block2", this.block2Consumed);
        compound.putInt("block3", this.block3Consumed);
        compound.putInt("block4", this.block4Consumed);
        compound.putBoolean("chroma", this.chromaConsumed);
        compound.putBoolean("primer", this.primerConsumed);
        compound.putBoolean("base", this.baseConsumed);
        compound.putInt("essence1", this.essence1Consumed);
        compound.putInt("essence2", this.essence2Consumed);
        compound.putInt("color", this.chromaColor);
        compound.putString("base", this.gemBase);
        if(!this.trySaveLootTable(compound)){
            ContainerHelper.saveAllItems(compound, this.items);
        }
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        IncubatorTE te = (IncubatorTE)be;
        if(!level.isClientSide()) {
            if (te.getItem(IncubatorTE.GEM_OUTPUT_SLOT_INDEX) == ItemStack.EMPTY) {
                if (te.ticks % 50 == 0) {
                    te.HandleChromaTick();
                    te.HandleBaseTick();
                    //te.HandleEssenceTick();
                    //te.HandleGravelTick();
                    //te.HandleSandTick();
                    //te.HandleClayTick();
                    te.HandleFormGemTick();
                }
                if (te.ticks > 25) {
                    te.ticks = 0;
                } else {
                    te.ticks++;
                }
            }
        }
    }

    /*public void HandleGravelTick(){
        ItemStack stack = this.getItem(IncubatorTE.BLOCK1_INPUT_SLOT_INDEX);
        int count = stack.getCount();
        if (count + block1Consumed >= 64) {
            if (block1Consumed < 64) {
                if (stack.getItem() == Blocks.BLOCK1.asItem()) {
                    stack.shrink(1);
                    this.block1Consumed++;
                }
            }
        } else if (count + block1Consumed >= 32) {
            if (block1Consumed < 32) {
                if (stack.getItem() == Blocks.BLOCK1.asItem()) {
                    stack.shrink(1);
                    this.block1Consumed++;
                }
            }
        } else if (count + block1Consumed >= 16) {
            if (block1Consumed < 16) {
                if (stack.getItem() == Blocks.BLOCK1.asItem()) {
                    stack.shrink(1);
                    this.block1Consumed++;
                }
            }
        }
    }

    public void HandleSandTick(){
        if(this.block1Consumed == IncubatorTE.MAX_BLOCK1 && this.block2Consumed < IncubatorTE.MAX_BLOCK2){
            System.out.println(64);
            ItemStack stack = this.getItem(IncubatorTE.BLOCK2_INPUT_SLOT_INDEX);
            if(stack.getItem() == Blocks.BLOCK2.asItem()){
                stack.shrink(1);
                this.block2Consumed++;
            }
        } else if(this.block1Consumed == 32 && this.block2Consumed < 32){
            System.out.println(32);
            ItemStack stack = this.getItem(IncubatorTE.BLOCK2_INPUT_SLOT_INDEX);
            if(stack.getItem() == Blocks.BLOCK2.asItem()){
                stack.shrink(1);
                this.block2Consumed++;
            }
        } else if(this.block1Consumed == 16 && this.block2Consumed < 16){
            System.out.println(16);
            ItemStack stack = this.getItem(IncubatorTE.BLOCK2_INPUT_SLOT_INDEX);
            if(stack.getItem() == Blocks.BLOCK2.asItem()){
                stack.shrink(1);
                this.block2Consumed++;
            }
        }
    }

    public void HandleClayTick(){
        if(this.block1Consumed == IncubatorTE.MAX_BLOCK1 && this.block2Consumed == IncubatorTE.MAX_BLOCK2 && this.block3Consumed < IncubatorTE.MAX_BLOCK3){
            ItemStack stack = this.getItem(IncubatorTE.BLOCK3_INPUT_SLOT_INDEX);
            if(stack.getItem() == Items.BLOCK3_BALL){
                stack.shrink(1);
                this.block3Consumed++;
            }
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1 / 2 && this.block2Consumed == IncubatorTE.MAX_BLOCK2/ 2 && this.block3Consumed < IncubatorTE.MAX_BLOCK3/ 2){
            ItemStack stack = this.getItem(IncubatorTE.BLOCK3_INPUT_SLOT_INDEX);
            if(stack.getItem() == Items.BLOCK3_BALL){
                stack.shrink(1);
                this.block3Consumed++;
            }
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1 / 4 && this.block2Consumed == IncubatorTE.MAX_BLOCK2/ 4 && this.block3Consumed < IncubatorTE.MAX_BLOCK3/ 4){
            ItemStack stack = this.getItem(IncubatorTE.BLOCK3_INPUT_SLOT_INDEX);
            if(stack.getItem() == Items.BLOCK3_BALL){
                stack.shrink(1);
                this.block3Consumed++;
            }
        }
    }
*/
    public void HandleChromaTick() {
        if (!chromaConsumed) {
            ItemStack stack = this.getItem(IncubatorTE.CHROMA_INPUT_SLOT_INDEX);
            if (stack.getItem() instanceof ItemChroma chroma) {
                this.chromaConsumed = true;
                this.chromaColor = chroma.color;
                stack.shrink(1);
            }
        }
    }

    public void HandleBaseTick() {
        if (!baseConsumed) {
            ItemStack stack = this.getItem(IncubatorTE.GEM_BASE_INPUT_SLOT_INDEX);
            if (stack.getItem() instanceof ItemGemBase base) {
                this.baseConsumed = true;
                this.gemBase = base.toString();
                stack.shrink(1);
            }
        }
    }
/*
    public void HandleEssenceTick() {
        if (this.block1Consumed == IncubatorTE.MAX_BLOCK1 && this.block2Consumed == IncubatorTE.MAX_BLOCK2 && this.block3Consumed == IncubatorTE.MAX_BLOCK3 && this.chromaConsumed) {
            //ESSENCE CHECK
                if (this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock() == ModBlocks.WHITE_ESSENCE_BLOCK.get()) {
                    LiquidBlock block = (LiquidBlock) this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock();
                    if (block.getFluid() == ModFluids.WHITE_ESSENCE.get()) {
                        this.level.setBlockAndUpdate(this.worldPosition.offset(IncubatorTE.direction(4)), Blocks.AIR.defaultBlockState());
                        this.essenceConsumed = true;
                    }
                }
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1/2 && this.block2Consumed == IncubatorTE.MAX_BLOCK2/2 && this.block3Consumed == IncubatorTE.MAX_BLOCK3/2 && this.chromaConsumed) {
            //ESSENCE CHECK
            if (this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock() == ModBlocks.WHITE_ESSENCE_BLOCK.get()) {
                LiquidBlock block = (LiquidBlock) this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock();
                if (block.getFluid() == ModFluids.WHITE_ESSENCE.get()) {
                    this.level.setBlockAndUpdate(this.worldPosition.offset(IncubatorTE.direction(4)), Blocks.AIR.defaultBlockState());
                    this.essenceConsumed = true;
                }
            }
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1/4 && this.block2Consumed == IncubatorTE.MAX_BLOCK2/4 && this.block3Consumed == IncubatorTE.MAX_BLOCK3/4 && this.chromaConsumed) {
            //ESSENCE CHECK
            if (this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock() == ModBlocks.WHITE_ESSENCE_BLOCK.get()) {
                LiquidBlock block = (LiquidBlock) this.level.getBlockState(this.worldPosition.offset(IncubatorTE.direction(4))).getBlock();
                if (block.getFluid() == ModFluids.WHITE_ESSENCE.get()) {
                    this.level.setBlockAndUpdate(this.worldPosition.offset(IncubatorTE.direction(4)), Blocks.AIR.defaultBlockState());
                    this.essenceConsumed = true;
                }
            }
        }
    }*/

    public void setRequiresColour() {
        colour.put("ruby", false);
        colour.put("nephrite", false);
        colour.put("rutile", false);
        colour.put("bismuth", false);
        colour.put("aquamarine", false);
        colour.put("emerald", false);
        colour.put("bixbite", false);
        colour.put("lapis", false);
        colour.put("obsidian", false);
        colour.put("larimar", false);
        colour.put("morganite", false);
        colour.put("peridot", false);
        colour.put("jasper", true);
        colour.put("garnet", true);
        colour.put("quartz", true);
        colour.put("sapphire", true);
        colour.put("agate", true);
        colour.put("spinel", true);
        colour.put("tourmaline", true);
        colour.put("zircon", true);
        colour.put("spodumene", true);
        colour.put("topaz", true);
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


    public void HandleFormGemTick(){
        /*if(this.block1Consumed == IncubatorTE.MAX_BLOCK1 && this.block2Consumed == IncubatorTE.MAX_BLOCK2 && this.block3Consumed == IncubatorTE.MAX_BLOCK3 && this.chromaConsumed
                && this.essenceConsumed) {
            this.formGem(this.chromaColor, 0);
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1 / 2 && this.block2Consumed == IncubatorTE.MAX_BLOCK2 / 2 && this.block3Consumed == IncubatorTE.MAX_BLOCK3 / 2 && this.chromaConsumed
                && this.essenceConsumed) {
            this.formGem(this.chromaColor, 1);
        } else if (this.block1Consumed == IncubatorTE.MAX_BLOCK1 / 4 && this.block2Consumed == IncubatorTE.MAX_BLOCK2 / 4 && this.block3Consumed == IncubatorTE.MAX_BLOCK3 / 4 && this.chromaConsumed
                && this.essenceConsumed) {
            this.formGem(this.chromaColor, 2);
        }*/
        if (baseConsumed && chromaConsumed) this.formGem(this.chromaColor, 1);
    }

    //perfect = 0
    //normal = 1
    //defect = 2
    public void formGem(int chroma, int quality){
        setRequiresColour();
        /*RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
        String skinColorVariant = "";
        EntityGem gem = gemm.get().create(world);
        String namee = "";
        boolean dying = false;
        List<EntityGem> list;
            if (Objects.equals(this.ID, Gempire.MODID)) {
                namee = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this)).toString().replaceAll("gempire", "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
            } else {
                namee = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this)).toString().replaceAll(this.ID, "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
            }
            //This whole section here checks for variations in color so it can spawn the correct type of gem
            String[] ainmneacha = namee.split("_");
            boolean nullFlag = false;
            int idx = 0;
            for (int i = 0; i < ainmneacha.length; i++) {
                if (ainmneacha[i].isEmpty()) {
                    nullFlag = true;
                    idx = i;
                }
            }
            if (nullFlag) ainmneacha = ArrayUtils.remove(ainmneacha, idx);
            namee = ainmneacha[0];
            if (ainmneacha.length > 1) skinColorVariant = ainmneacha[1];
            for (String s : ainmneacha) {
                System.out.println(s);
            }
            //End of check and set
            try {
                if (Objects.equals(this.ID, Gempire.MODID)) {
                    gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(namee.toUpperCase()).get(null);
                } else {
                    gemm = (RegistryObject<EntityType<EntityPebble>>) AddonHandler.ADDON_ENTITY_REGISTRIES.get(this.ID).getField(namee.toUpperCase()).get(null);
                }
                gem = gemm.get().create(world);
                System.out.println("gem " + gem);
                assert gem != null;
                gem.setUUID(Mth.createInsecureUUID(world.random));
                System.out.println(gem.getUUID());
            } catch (Exception e) {
                e.printStackTrace();
            }
                if (ainmneacha.length > 1) {
                    assert gem != null;
                    gem.setSkinVariantOnInitialSpawn = false;
                    gem.initalSkinVariant = Integer.parseInt(skinColorVariant);
                }
            GemFormEvent event = new GemFormEvent(gem, gem.blockPosition());
            */
        RegistryObject<Item> gemm = ModItems.PEBBLE_GEM;
        ItemGem gem = null;
        String baseName = gemBase.toUpperCase().replaceAll("INACTIVE_", "").replaceAll("_BASE", "");
        String name = "";
        if (colour.get(baseName.toLowerCase())) {
            name = Color.getColorName(chroma).toUpperCase() +"_"+baseName+"_GEM";
        } else {
            name = baseName+"_GEM";
        }
        System.out.println("name "+name);
        RegistryObject<EntityType<EntityPebble>> egemm = ModEntities.PEBBLE;
        String namee = "";
        String skinColorVariant = "";
        System.out.println("name "+name);
        String[] array = name.split("_");
        boolean nullFlag = false;
        int idx = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].isEmpty()) {
                nullFlag = true;
                idx = i;
            }
        }
        if (nullFlag) array = ArrayUtils.remove(array, idx);
        namee = array[0];
        if (array.length > 1) skinColorVariant = array[0];
        for (String s : array) {
            System.out.println(s);
        }
        System.out.println("skin variant string " +skinColorVariant);
        System.out.println("array "+ Arrays.toString(array));
        System.out.println("name "+name);
        if (!colour.get(baseName.toLowerCase())) skinColorVariant = "";
        try {
            egemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(name.toUpperCase().replaceAll("_", "").replaceAll("GEM", "").replaceAll(skinColorVariant, "")).get(null);
        } catch(Exception e){
            e.printStackTrace();
        }
        EntityGem egem = egemm.get().create(this.level);
        if (egem instanceof EntityVaryingGem) {
            if (((EntityVaryingGem) egem).UsesUniqueNames()) {
                String uniqueColor = Component.translatable("nickname.gempire." + egem.getWholeGemName() + "_" + chromaColor).getString();
                System.out.println(uniqueColor.toLowerCase());
                System.out.println(egem.getWholeGemName());
                uniqueColor = uniqueColor.toLowerCase().replaceAll(egem.getWholeGemName(), "").replaceAll(" ", "");
                System.out.println(uniqueColor);
                name = name.replaceAll(skinColorVariant.toUpperCase(), uniqueColor.toUpperCase());
                System.out.println("name "+ name);
            }
        }
        try {
            gemm = (RegistryObject<Item>) ModItems.class.getField(name.toUpperCase()).get(null);
            gem = (ItemGem) gemm.get();
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(name);
        egem.setUUID(Mth.createInsecureUUID(this.level.random));
        if (array.length > 1) {
            assert gem != null;
            egem.setSkinVariantOnInitialSpawn = false;
            egem.initalSkinVariant = chromaColor;
        }
        egem.setSkinColorVariant(chroma);
        egem.finalizeSpawn((ServerLevelAccessor) this.level, this.level.getCurrentDifficultyAt(this.worldPosition), MobSpawnType.MOB_SUMMONED, null, null);
        ItemStack stack = new ItemStack(gem);
        ItemGem.saveData(stack, egem);
        egem.remove(Entity.RemovalReason.DISCARDED);
        if (quality == 0) {
            stack.getOrCreateTag().putBoolean("prime", true);
        } else if (quality == 2) {
            stack.getOrCreateTag().putBoolean("defective", true);
        }
        this.setItem(IncubatorTE.GEM_OUTPUT_SLOT_INDEX, stack);
        this.block1Consumed = 0;
        this.block2Consumed = 0;
        this.block3Consumed = 0;
        this.block4Consumed = 0;
        this.chromaConsumed = false;
        this.primerConsumed = false;
        this.baseConsumed = false;
        this.essence1Consumed = 0;
        this.essence2Consumed = 0;
        this.chromaColor = 0;
    }

    //CONTAINER STUFF

    @Override
    public Component getDisplayName() {
        return Component.translatable("");//TranslationTextComponent("container.gempire.injector");
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.gempire.incubator");
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
        return new IncubatorContainer(id, player, this);
    }

    @Override
    public int getContainerSize() {
        return IncubatorTE.NUMBER_OF_SLOTS;
    }

    public ItemStack getGemItem(){
        return this.getItem(IncubatorTE.GEM_OUTPUT_SLOT_INDEX);
    }

    //NETWORKING STUFF

    //NETWORKING STUFF

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        //Debug
        System.out.println("[DEBUG]:Client recived tile sync packet");
        this.load(Objects.requireNonNull(pkt.getTag()));
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        //Debug
        System.out.println("[DEBUG]:Server sent tile sync packet");
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compound = new CompoundTag();
        this.saveAdditional(compound);
        return compound;
    }
    @Override
    public void handleUpdateTag(CompoundTag tag) {
        System.out.println("[DEBUG]:Handling tag on chunk load");
        this.load(tag);
    }
}
