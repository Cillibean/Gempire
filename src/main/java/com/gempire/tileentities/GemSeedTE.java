package com.gempire.tileentities;

import com.gempire.blocks.DrainedBlock;
import com.gempire.blocks.GemSeedBlock;
import com.gempire.blocks.machine.PowerCrystalBlock;
import com.gempire.blocks.machine.TankBlock;
import com.gempire.config.GempireServerConfigs;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.entities.gems.EntityPeridot;
import com.gempire.entities.gems.EntityQuartz;
import com.gempire.entities.gems.EntityTourmaline;
import com.gempire.entities.gems.EntityZircon;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import com.gempire.events.DrainEvent;
import com.gempire.events.GemFormEvent;
import com.gempire.init.*;
import com.gempire.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;


public class GemSeedTE extends BlockEntity {
    boolean spawned = false;
    public int ticks = 0;
    public int stage = 0;
    public int tier;

    public Item primer;
    public int facing;
    public boolean checked = false;
    public boolean exposed;
    int speed = GempireServerConfigs.INJECTION.get();
    public static GemSeedInfo info;
    int blocksDrained = 0;

    private static final int EXIT_HOLE_LENGTH = 12;

    public BlockPos pos;

    public Block drained_sand, drained_soil, drained_stone, drained_stone_2, banded_drained_stone, drained_ice, drained_log, drained_log_cracked;
    public int chromaColour;
    public float weight;
    public boolean clod = false;
    public int clodNO;
    public GemInfo toForm;

    public int quality;
    // 0 = prime
    // 1 = normal
    // 2 = defect
    // 3 = crawler
    // 4 = shambler
    // 5 = abomination

    public HashMap<Block, ArrayList<Integer>> resMap = new HashMap<>();
    public HashMap<Block, Float> qualityMap = new HashMap<>();
    public ArrayList<GemInfo> gemInfoList = new ArrayList<>();

    //Create an object to store the total weight
    float totalWeight = 0;

    public GemSeedTE(BlockPos pos, BlockState state) {
        super(ModTE.GEM_SEED_TE.get(), pos, state);
        this.pos = pos;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        GemSeedTE te = (GemSeedTE) be;
        if (!te.exposed) {
            if (te.level.getBlockState(te.getBlockPos().below()) == Blocks.AIR.defaultBlockState() || te.level.getBlockState(te.getBlockPos().below()).getBlock() instanceof LiquidBlock) {
                te.exposed = true;
            }
            if (te.primer == ModItems.GILDED_LAPIS.get()) {
                te.speed = (int)(GempireServerConfigs.INJECTION.get() / 4);
            }
            if (te.ticks % te.speed == 0) {
                if (!te.spawned) {
                    if (!te.level.isClientSide) {
                        te.drainForm();
                        level.sendBlockUpdated(te.getBlockPos(), te.getBlockState(), te.getBlockState(), 2);
                        te.setChanged();
                        }
                    }
                }
            }
            te.ticks++;
        }

    public void drainForm() {
        if (tier == 1) {
            gemInfoList = InjectionRegistry.listBasic;
        } else {
            gemInfoList = InjectionRegistry.list;
        }
        InjectionRegistry.setMap();
        if (blocksDrained < 500) {
            Random r = new Random();
            BlockPos toDrain = pos.offset(-5, -5, -5).offset(r.nextInt(11), r.nextInt(11), r.nextInt(11));
            Block block = this.level.getBlockState(toDrain).getBlock();
            if (!resMap.isEmpty()) {
                if (!resMap.get(block).isEmpty()) {
                    info.resources[0] += resMap.get(block).get(0);
                    info.resources[1] += resMap.get(block).get(1);
                    info.resources[2] += resMap.get(block).get(2);
                    info.resources[3] += resMap.get(block).get(3);
                    info.resources[4] += resMap.get(block).get(4);
                    info.resources[5] += resMap.get(block).get(5);
                }
                if (qualityMap.get(block) != 0 && qualityMap.get(block) != null) {
                    info.quality += qualityMap.get(block);
                }
            }
            drainBlock(toDrain);
            blocksDrained++;
        } else {
            weighResults();
        }
    }

    public void drainBlock(BlockPos blockPos) {
        //System.out.println("drain "+blockPos);
        float BLOCK_TEMPERATURE = this.level.getBiome(pos).get().getBaseTemperature();
        this.setDrainedStoneColor(BLOCK_TEMPERATURE);
        Block block = this.level.getBlockState(blockPos).getBlock();
        if (!(block instanceof AirBlock) &&
                !(block instanceof SlabBlock) &&
                !(block instanceof BushBlock) &&
                !(block instanceof SnowLayerBlock) &&
                !(block instanceof LiquidBlock) &&
                !(block instanceof TorchBlock) &&
                !(block instanceof BedBlock) &&
                !(block instanceof BeaconBlock) &&
                !(block instanceof WoolCarpetBlock) &&
                !(block instanceof TargetBlock) &&
                !(block instanceof DoorBlock) &&
                !(block instanceof RailBlock) &&
                !(block instanceof ChestBlock) &&
                !(block instanceof FurnaceBlock) &&
                !(block instanceof FenceBlock) &&
                !(block instanceof FenceGateBlock) &&
                !(block instanceof GlassBlock) &&
                !(block instanceof IronBarsBlock) &&
                !(block instanceof CraftingTableBlock) &&
                !(block instanceof AnvilBlock) &&
                !(block instanceof BlastFurnaceBlock) &&
                !(block instanceof SmokerBlock) &&
                !(block instanceof LoomBlock) &&
                !(block instanceof CartographyTableBlock) &&
                !(block instanceof CactusBlock) &&
                !(block instanceof TankBlock) &&
                !(block instanceof GemSeedBlock) &&
                !(block instanceof PowerCrystalBlock) &&
                !(block instanceof PointedDripstoneBlock) &&
                !(block == ModBlocks.DRILL_BLOCK.get()) &&
                !(block == ModBlocks.DRAINED_ICE.get()) &&
                !(block == ModBlocks.DRAINED_LOG_CRACKED.get()) &&
                !(block == ModBlocks.DRAINED_LOG.get()) &&
                !(block == ModBlocks.PEDISTAL.get()) &&
                !(block instanceof DrainedBlock)) {
            if (block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT_PATH
                    || block == Blocks.GRAVEL || block == Blocks.MOSS_BLOCK) {
                this.level.setBlockAndUpdate(blockPos, this.drained_soil.defaultBlockState());
            } else if (block == Blocks.SAND || block == Blocks.RED_SAND || block == Blocks.SOUL_SAND) {
                this.level.setBlockAndUpdate(blockPos, this.drained_sand.defaultBlockState());
            } else if (block == Blocks.OAK_LOG || block == Blocks.STRIPPED_OAK_LOG || block == Blocks.STRIPPED_OAK_WOOD || block == Blocks.OAK_WOOD
                    || block == Blocks.SPRUCE_LOG || block == Blocks.STRIPPED_SPRUCE_LOG || block == Blocks.STRIPPED_SPRUCE_WOOD || block == Blocks.SPRUCE_WOOD
                    || block == Blocks.BIRCH_LOG || block == Blocks.STRIPPED_BIRCH_LOG || block == Blocks.STRIPPED_BIRCH_WOOD || block == Blocks.BIRCH_WOOD
                    || block == Blocks.JUNGLE_LOG || block == Blocks.STRIPPED_JUNGLE_LOG || block == Blocks.STRIPPED_JUNGLE_WOOD || block == Blocks.JUNGLE_WOOD
                    || block == Blocks.ACACIA_LOG || block == Blocks.STRIPPED_ACACIA_LOG || block == Blocks.STRIPPED_ACACIA_WOOD || block == Blocks.ACACIA_WOOD
                    || block == Blocks.DARK_OAK_LOG || block == Blocks.STRIPPED_DARK_OAK_LOG || block == Blocks.STRIPPED_DARK_OAK_WOOD || block == Blocks.DARK_OAK_WOOD) {
                this.level.setBlockAndUpdate(blockPos, this.drained_log.withPropertiesOf(this.level.getBlockState(blockPos)));
            } else if (block == Blocks.CRIMSON_STEM || block == Blocks.WARPED_STEM || block == Blocks.STRIPPED_CRIMSON_STEM || block == Blocks.STRIPPED_WARPED_STEM
                    || block == Blocks.CRIMSON_HYPHAE || block == Blocks.WARPED_HYPHAE || block == Blocks.STRIPPED_CRIMSON_HYPHAE || block == Blocks.STRIPPED_WARPED_HYPHAE) {
                this.level.setBlockAndUpdate(blockPos, this.drained_log_cracked.defaultBlockState());
            } else if (block == Blocks.BLUE_ICE || block == Blocks.PACKED_ICE || block == Blocks.ICE) {
                this.level.setBlockAndUpdate(blockPos, this.drained_ice.defaultBlockState());
            } else if (block == Blocks.VINE ||block == Blocks.CAVE_VINES || block == Blocks.CAVE_VINES_PLANT || block == Blocks.OAK_LEAVES || block == Blocks.DARK_OAK_LEAVES
                    || block == Blocks.BIRCH_LEAVES || block == Blocks.JUNGLE_LEAVES || block == Blocks.ACACIA_LEAVES || block == Blocks.MANGROVE_LEAVES || block == Blocks.AZALEA_LEAVES
                    || block == Blocks.FLOWERING_AZALEA_LEAVES || block == Blocks.SPRUCE_LEAVES) {
                this.level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
            } else if (block == Blocks.SNOW_BLOCK || block == Blocks.POWDER_SNOW) {
                this.level.setBlockAndUpdate(blockPos, Blocks.WATER.defaultBlockState());
            } else {
                if (blockPos.getY() < 80) {
                    this.level.setBlockAndUpdate(blockPos, this.drained_stone.defaultBlockState());
                } else {
                    this.level.setBlockAndUpdate(blockPos, this.drained_stone_2.defaultBlockState());
                    if (blockPos.getY() % 6 == 0) {
                        this.level.setBlockAndUpdate(blockPos, this.banded_drained_stone.defaultBlockState());
                    }
                }
                if (blockPos.getY() == 80) {
                    this.level.setBlockAndUpdate(blockPos, this.banded_drained_stone.defaultBlockState());
                }
            }
        }
    }

    public void weighResults() {
        ArrayList<GemInfo> possibleResults = new ArrayList<>();
        ArrayList<Float> possibleQualities = new ArrayList<>();
        int threshhold = 45;
        for (GemInfo gemInfo : gemInfoList) {
            int distance = 0;
            int[] res = gemInfo.getResources();
            for (int a = 0; a < res.length; a++) {
                if (res[a] > info.resources[a]) distance += (res[a] - info.resources[a]);
                else distance += (info.resources[a] - res[a]);
            }
            if (distance < threshhold) {
                float subtracted = ((float)distance)/threshhold;
                System.out.println(gemInfo.getName());
                System.out.println(Arrays.toString(res));
                System.out.println("possible gem " + distance+ " quality "+subtracted);
                for (int b = 0; b < threshhold - distance; b++) {
                    possibleResults.add(gemInfo);
                    possibleQualities.add((float) 1 - subtracted);
                }
            }
        }
        if (!possibleResults.isEmpty()) {
            RandomSource r = level.getRandom();
            int random = r.nextInt(possibleResults.size());
            toForm = possibleResults.get(random);
            float qualityFloat = possibleQualities.get(random);
            if (qualityFloat <= 0.25) {
                if (qualityFloat <= 0.09) {
                    quality = 5;
                } else if (qualityFloat <= 0.175) {
                    quality = 4;
                } else {
                    quality = 3;
                }
                clod = true;
            } else if (qualityFloat >= 0.95) {
                quality = 0;
            } else if (qualityFloat <= 0.4) {
                quality = 2;
            } else {
                quality = 1;
            }
            formGem();
        } else {
            clod = true;
            quality = 5;
            formGem();
        }
    }

    public void formGem() {
        if (!clod) {
            RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
            EntityGem gem = gemm.get().create(this.level);
            String gemtoform = toForm.getName();
            if (gem.chromaColourRequired) {
                if (Objects.equals(gemtoform, "")) {
                    //this.Drain(GemFormation.getBlockPosInVolume(this.level, this.pos, this.volumeToCheck));
                    return;
                }
                try {
                    boolean isVanillaGem = false;
                    for (String gemama : AddonHandler.VANILLA_GEMS) {
                        if (Objects.equals(gemtoform, gemama)) {
                            isVanillaGem = true;
                            break;
                        }
                    }
                    if (isVanillaGem) {
                        gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(gemtoform.toUpperCase()).get(null);
                    } else {
                        gemm = (RegistryObject<EntityType<EntityPebble>>) AddonHandler.ENTITY_ADDON_ENTITY_REGISTRIES.get(gemtoform).getField(gemtoform.toUpperCase()).get(null);
                    }
                    gem = gemm.get().create(this.level);
                    if (gem instanceof EntityVaryingGem) {
                        EntityVaryingGem varyingGem = (EntityVaryingGem) gem;
                        varyingGem.setSkinVariantOnInitialSpawn = false;
                        int variant = this.chromaColour;
                        Random rand = new Random();
                        if (gem instanceof EntityQuartz && variant == 16) {
                            variant += rand.nextBoolean() ? 1 : 0;
                        }
                        if (varyingGem.isColorValid(variant)) {
                            varyingGem.initalSkinVariant = variant;
                        } else {
                            varyingGem.initalSkinVariant = varyingGem.generateRandomInitialSkin();
                        }
                    }
                    gem.setCracked(false);
                    gem.setUUID(Mth.createInsecureUUID(this.level.random));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (gemtoform == "") {
                    //this.Drain(GemFormation.getBlockPosInVolume(this.level, this.pos, this.volumeToCheck));
                    return;
                }
                try {
                    boolean isVanillaGem = false;
                    for (String gemama : AddonHandler.VANILLA_GEMS) {
                        if (gemtoform == gemama) isVanillaGem = true;
                    }
                    if (isVanillaGem) {
                        gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(gemtoform.toUpperCase()).get(null);
                    } else {
                        gemm = (RegistryObject<EntityType<EntityPebble>>) AddonHandler.ENTITY_ADDON_ENTITY_REGISTRIES.get(gemtoform).getField(gemtoform.toUpperCase()).get(null);
                    }
                    gem = gemm.get().create(this.level);
                    if (gem instanceof EntityVaryingGem) {
                        EntityVaryingGem varyingGem = (EntityVaryingGem) gem;
                        varyingGem.setSkinVariantOnInitialSpawn = false;
                        int variant = this.chromaColour;
                        Random rand = new Random();
                        if (gem instanceof EntityQuartz && variant == 16) {
                            variant += rand.nextBoolean() ? 1 : 0;
                        }
                        if (varyingGem.isColorValid(variant)) {
                            varyingGem.initalSkinVariant = variant;
                        } else {
                            varyingGem.initalSkinVariant = varyingGem.generateRandomInitialSkin();
                        }
                    }
                    gem.setUUID(Mth.createInsecureUUID(this.level.random));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (quality == 0) {
                gem.setQuality(2);
            }
            if (quality == 2) {
                gem.setQuality(0);
            }
            gem.setGemPlacement(gem.generateGemPlacement());
            gem.setSkinVariant(gem.generateSkinVariant());
            if (gem.setSkinVariantOnInitialSpawn) {
                gem.setSkinColorVariant(gem.generateSkinColorVariant());
            } else gem.setSkinColorVariant(gem.initalSkinVariant);
            //gem.setAssignedGem(((ItemGem)gem.getGemItem().getDefaultInstance().getItem()).assigned_gem);
            System.out.println(gem.getAssignedGem());
            gem.setHairVariant(gem.generateHairVariant());
            gem.setSkinColor(gem.generatePaletteColor(PaletteType.SKIN));
            gem.setHairColor(gem.generatePaletteColor(PaletteType.HAIR));
            gem.setGemColor(gem.generatePaletteColor(PaletteType.GEM));
            gem.setWingColor(gem.generatePaletteColor(PaletteType.WING));
            gem.setWingVariant(gem.generateWingVariant());
            gem.setOutfitVariant(gem.generateOutfitVariant());
            gem.setOutfitColor(gem.generateOutfitColor());
            gem.setInsigniaVariant(gem.generateInsigniaVariant());
            gem.setInsigniaColor(gem.generateInsigniaColor());
            gem.setAbilitySlots(gem.generateAbilitySlots());
            gem.setAbilities(gem.generateAbilities());
            gem.setFacet(gem.generateFacet());
            gem.setCut(gem.generateCut());
            gem.setEmotional(gem.generateIsEmotional());
            gem.setAbilityPowers(gem.findAbilities(gem.getAbilities()));
            gem.addAbilityGoals();
            gem.applyAttributeAbilities();
            gem.FOLLOW_ID = UUID.randomUUID();
            //gem.ASSIGNED_ID = UUID.randomUUID();
            gem.MASTER_OWNER = UUID.randomUUID();
            gem.setMarkingVariant(gem.generateMarkingVariant());
            gem.setMarkingColor(gem.generatePaletteColor(PaletteType.MARKINGS));
            gem.setMarking2Variant(gem.generateMarking2Variant());
            gem.setMarking2Color(gem.generatePaletteColor(PaletteType.MARKINGS_2));
            gem.setXScale(gem.generateXScale());
            gem.setYScale(gem.generateYScale());
            gem.setZScale(gem.generateZScale());
            gem.setCustomName(gem.getNickname());
            if (gem instanceof EntityZircon) {
                if (gem.isPrimary()) {
                    ((EntityZircon) gem).setEnchantPage(RandomSource.create().nextInt(ModEnchants.GEMPIRE_ENCHANTMENTS.size()));
                } else {
                    ((EntityZircon) gem).setEnchantPage(RandomSource.create().nextInt(ModEnchants.VANILLA_ENCHANTMENTS.size()));
                }
                ((EntityZircon) gem).setEnchantPageDefined(true);
            } else if (gem instanceof EntityTourmaline) {
                ((EntityTourmaline) gem).setCrops(((EntityTourmaline) gem).generateCrops());
                ((EntityTourmaline) gem).setBuilding(false);
            } else if (gem instanceof EntityPeridot) {
                ((EntityPeridot) gem).generateMaterials();
            }
            //gem.generateScoutList();
            gem.idlePowers = gem.generateIdlePowers();
            if (gem.spawnGem != null) {
                gem.spawnGem.remove(Entity.RemovalReason.DISCARDED);
            }
            gem.setCracked(gem.getCracked());
            AttributeModifier PRIME = new AttributeModifier(UUID.randomUUID(), "gempirePrimaryModifier", 5D, AttributeModifier.Operation.ADDITION);
            AttributeModifier PRIME_SPEED = new AttributeModifier(UUID.randomUUID(), "gempirePrimarySpeedModifier", .2D, AttributeModifier.Operation.ADDITION);
            AttributeModifier DEFECTIVE = new AttributeModifier(UUID.randomUUID(), "gempireDefectiveModifier", -5D, AttributeModifier.Operation.ADDITION);
            AttributeModifier DEFECTIVE_SPEED = new AttributeModifier(UUID.randomUUID(), "gempireDefectiveSpeedModifier", -.1D, AttributeModifier.Operation.ADDITION);
            if (gem.isPrimary()) {
                System.out.println("prime modifiers");
                gem.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(PRIME);
                gem.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(PRIME_SPEED);
                gem.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(PRIME);
                gem.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(PRIME);
                gem.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(PRIME);
            } else if (gem.isDefective()) {
                System.out.println("off colour modifiers");
                gem.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(DEFECTIVE);
                gem.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(DEFECTIVE_SPEED);
                gem.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(DEFECTIVE);
                gem.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(DEFECTIVE);
                gem.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(DEFECTIVE);
            }
            System.out.println(gem.getCracked());
            gem.setPos(this.pos.getX() + .5f, this.pos.getY(), this.pos.getZ() + .5f);
            gem.setHealth(gem.getMaxHealth());
            gem.GUARD_POS = gem.getOnPos().above();
            GemFormEvent event1 = new GemFormEvent(gem, gem.blockPosition());
            MinecraftForge.EVENT_BUS.post(event1);
            this.level.addFreshEntity(gem);
            if (getClosestExitDirection() == 4) {
                this.GenerateFacingExitHole(gem.exitHoleSize());
            } else {
                this.GenerateClosestExitHole(getClosestExitDirection(), gem.exitHoleSize());
            }
        } else {
            System.out.println("clod");
            RegistryObject<EntityType<EntityAbomination>> abominationr = ModEntities.ABOMINATION;
            EntityAbomination abomination = abominationr.get().create(this.level);
            RegistryObject<EntityType<EntityCrawler>> crawlerr = ModEntities.CRAWLER;
            EntityCrawler crawler = crawlerr.get().create(this.level);
            RegistryObject<EntityType<EntityShambler>> shamblerr = ModEntities.SHAMBLER;
            EntityShambler shambler = shamblerr.get().create(this.level);
            if (quality == 3) {
                crawler.setPos(this.pos.getX() + .5f, this.pos.getY(), this.pos.getZ() + .5f);
                crawler.setHealth(crawler.getMaxHealth());
                crawler.setUUID(Mth.createInsecureUUID(this.level.random));
                this.level.addFreshEntity(crawler);
            } else if (quality == 4) {
                shambler.setPos(this.pos.getX() + .5f, this.pos.getY(), this.pos.getZ() + .5f);
                shambler.setHealth(shambler.getMaxHealth());
                shambler.setUUID(Mth.createInsecureUUID(this.level.random));
                this.level.addFreshEntity(shambler);
            } else if (quality == 5) {
                abomination.setPos(this.pos.getX() + .5f, this.pos.getY(), this.pos.getZ() + .5f);
                abomination.setHealth(abomination.getMaxHealth());
                abomination.setUUID(Mth.createInsecureUUID(this.level.random));
                this.level.addFreshEntity(abomination);
            }
            if (getClosestExitDirection() == 4) {
                this.GenerateFacingExitHole(3);
            } else {
                this.GenerateClosestExitHole(getClosestExitDirection(), 3);
            }
        }
    }

    public static BlockPos DirectionFromFacing(int face){
        BlockPos pos = new BlockPos(1,0,0);
        switch(face){
            case 0: pos = new BlockPos(1,0, 0);
                break;
            case 1: pos = new BlockPos(0,0, -1);
                break;
            case 2: pos = new BlockPos(-1,0, 0);
                break;
            case 3: pos = new BlockPos(0,0, 1);
                break;
        }
        return pos;
    }

    public void GenerateFacingExitHole(int height) {
        System.out.println("This block is facing: " + this.facing);
        BlockPos direction = DirectionFromFacing(this.facing);
        BlockPos currentPos;
        boolean flag = false;
        for (int n = 0; n < height; n++) {
            currentPos = this.pos.offset(0, n, 0);
            for (int i = 0; i < this.EXIT_HOLE_LENGTH; i++) {
                if (!flag) {
                    if (this.level.getBlockState(currentPos).getBlock() instanceof AirBlock
                            && this.level.getBlockState(currentPos.above()).getBlock() instanceof AirBlock) {
                        flag = true;
                    }
                    this.level.destroyBlock(currentPos, false);
                    currentPos = currentPos.offset(direction);
                } else {
                    break;
                }
            }
        }
    }

    public void GenerateClosestExitHole(int facing, int height){
        System.out.println("This block is facing: " + facing);
        BlockPos direction = DirectionFromFacing(facing);
        BlockPos currentPos = new BlockPos(this.pos);
        boolean flag = false;
        for (int i = 0; i < this.EXIT_HOLE_LENGTH; i++) {
            if (!flag) {
                if (this.level.getBlockState(currentPos).getBlock() instanceof AirBlock) {
                    flag = true;
                }
                for (int n = 0; n < height; n++) {
                    this.level.destroyBlock(currentPos, false);
                    currentPos = currentPos.offset(0, 1, 0);
                }
                currentPos = currentPos.offset(0, -height, 0);
                currentPos = currentPos.offset(direction);
            } else {
                break;
            }
        }
    }

    public int getClosestExitDirection(){
        BlockPos currentPos = new BlockPos(this.pos);
        boolean flag = false;
        int direction = 4;
        for (int i = 0; i < EXIT_HOLE_LENGTH; i++){
            if(!flag) {
                if (this.level.getBlockState(currentPos.north(i)).getBlock() instanceof AirBlock) {
                    flag = true;
                    direction = 1;
                    System.out.println("north");
                } else if (this.level.getBlockState(currentPos.south(i)).getBlock() instanceof AirBlock) {
                    flag = true;
                    System.out.println("south");
                    direction = 3;

                } else if (this.level.getBlockState(currentPos.east(i)).getBlock() instanceof AirBlock) {
                    flag = true;
                    System.out.println("east");
                    direction = 0;

                } else if (this.level.getBlockState(currentPos.west(i)).getBlock() instanceof AirBlock) {
                    flag = true;
                    System.out.println("west");
                    direction = 2;

                }
            }
        }
        return direction;
    }

    public void setDrainedStoneColor(float temperature){
        if(temperature > .1f && temperature <= .5F){
            this.drained_sand = ModBlocks.DRAINED_GREY_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_GREY_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_GREY_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_GREY_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_GREY_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
        else if(temperature > .5f && temperature <= .9f){
            this.drained_sand = ModBlocks.DRAINED_PURPLE_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_PURPLE_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_PURPLE_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_PURPLE_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_PURPLE_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
        else if(temperature > .9f && temperature <= 1.2f){
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_YELLOW_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_YELLOW_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_YELLOW_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_YELLOW_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
        else if(temperature > 1.2f && temperature <= 2f){
            this.drained_sand = ModBlocks.DRAINED_RED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_RED_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_RED_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_RED_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_RED_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
        else{
            this.drained_sand = ModBlocks.DRAINED_BLUE_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_BLUE_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_BLUE_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_BLUE_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_BLUE_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
    }


    public void setChroma(int chroma){
        this.chromaColour = chroma;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
    }

    public int getChroma(){
        return this.chromaColour;
    }

    public void setInfo(GemSeedInfo info){
        this.info = info;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        this.setChanged();
        System.out.println("info set");
    }

    public GemSeedInfo getInfo(){
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
        compound.putInt("chroma", this.chromaColour);
        compound.put("primer", new ItemStack(this.primer).save(new CompoundTag()));
        compound.putInt("facing", this.facing);
        compound.putBoolean("checked", this.checked);
        compound.putInt("tier", this.tier);
        compound.putInt("blocksDrained", this.blocksDrained);
        for(int i =0; i < info.resources.length; i++) {
            compound.putInt("resources"+i, info.resources[i]);
        }
        compound.putFloat("temp", info.temp);
        compound.putFloat("quality", info.quality);
        compound.putInt("infochroma", info.chroma);
    }


    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.stage = nbt.getInt("stage");
        this.spawned = nbt.getBoolean("spawned");
        this.chromaColour = nbt.getInt("chroma");
        ItemStack primer = ItemStack.of(nbt.getCompound("primer"));
        this.primer = primer.getItem();
        this.facing = nbt.getInt("facing");
        this.checked = nbt.getBoolean("checked");
        this.tier = nbt.getInt("tier");
        this.blocksDrained = nbt.getInt("blocksDrained");
        int[] resources = new int[6];
        for (int i = 0; i < 6; i++) {
            resources[i] = nbt.getInt("resources"+i);
        }
        float temp = nbt.getFloat("temp");
        float quality = nbt.getFloat("quality");
        int chroma = nbt.getInt("infochroma");
        info = new GemSeedInfo(resources, temp, quality, chroma);
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.load(nbt);
        this.stage = nbt.getInt("stage");
        this.spawned = nbt.getBoolean("spawned");
        this.chromaColour = nbt.getInt("chroma");
        ItemStack primer = ItemStack.of(nbt.getCompound("primer"));
        this.primer = primer.getItem();
        this.facing = nbt.getInt("facing");
        this.tier = nbt.getInt("tier");
        this.checked = nbt.getBoolean("checked");
        this.blocksDrained = nbt.getInt("blocksDrained");
        int[] resources = new int[6];
        for (int i = 0; i < 6; i++) {
            resources[i] = nbt.getInt("resources"+i);
        }
        float temp = nbt.getFloat("temp");
        float quality = nbt.getFloat("quality");
        int chroma = nbt.getInt("infochroma");
        info = new GemSeedInfo(resources, temp, quality, chroma);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        super.serializeNBT();
        compound.putInt("stage", this.stage);
        compound.putInt("chroma", this.chromaColour);
        compound.putBoolean("spawned", this.spawned);
        compound.put("primer", new ItemStack(this.primer).save(new CompoundTag()));
        compound.putInt("facing", this.facing);
        compound.putBoolean("checked", this.checked);
        compound.putInt("tier", this.tier);
        compound.putInt("blocksDrained", this.blocksDrained);
        for(int i =0; i < info.resources.length; i++) {
            compound.putInt("resources"+i, info.resources[i]);
        }
        compound.putFloat("temp", info.temp);
        compound.putFloat("quality", info.quality);
        compound.putInt("chroma", info.chroma);
        return compound;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        //Debug
        //System.out.println("[DEBUG]:Client recived tile sync packet");
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
        //System.out.println("[DEBUG]:Server sent tile sync packet");
        return ClientboundBlockEntityDataPacket.create(this);
    }
}