package com.gempire.systems.injection;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.entities.gems.EntityQuartz;
import com.gempire.entities.gems.EntityZircon;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityCrawler;
import com.gempire.entities.other.EntityShambler;
import com.gempire.events.DrainEvent;
import com.gempire.events.GemFormEvent;
import com.gempire.init.*;
import com.gempire.items.ItemChroma;
import com.gempire.items.ItemGem;
import com.gempire.util.PaletteType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.*;

public class GemFormation {
    private static final int EXIT_HOLE_LENGTH = 12;
    public Level world;
    public BlockPos pos;
    public BlockPos volumeToCheck;
    public static ArrayList<String> POSSIBLE_GEMS_TIER_1 = new ArrayList<>();
    public static ArrayList<String> POSSIBLE_GEMS_TIER_2 = new ArrayList<>();
    public static ArrayList<String> NETHER_POSSIBLE_GEMS_TIER_1 = new ArrayList<>();
    public static ArrayList<String> NETHER_POSSIBLE_GEMS_TIER_2 = new ArrayList<>();
    public static ArrayList<String> END_POSSIBLE_GEMS_TIER_1 = new ArrayList<>();
    public static ArrayList<String> END_POSSIBLE_GEMS_TIER_2 = new ArrayList<>();
    public Block drained_sand, drained_soil, drained_stone, drained_stone_2, banded_drained_stone, drained_ice, drained_log, drained_log_cracked;
    public ItemChroma chroma;
    public Item primer;
    public String essences;
    public int facing = 0;
    public int chromaColour;

    public int yLevelNeeded;
    public int tier;
    public float weight;
    public boolean clod = false;
    public int clodNO;
    public int essenceCount;

    HashMap<String, Float> WEIGHTS_OF_GEMS = new HashMap<>();
    HashMap<String, GemConditions> CONDITIONS = new HashMap<>();
    public GemConditions conditions;

    //Create an object to store the total weight
    float totalWeight = 0;

    public GemFormation(Level world, BlockPos pos, BlockPos volumeToCheck, ItemChroma chroma, Item primer, String essences, int facing, HashMap<String, Float> weights, float total, int tier){
        this.world = world;
        this.pos = pos;
        this.volumeToCheck = volumeToCheck;
        this.chroma = chroma;
        this.primer = primer;
        this.essences = essences;
        this.facing = facing;
        this.WEIGHTS_OF_GEMS = weights;
        this.totalWeight = total;
        this.tier = tier;
    }

    public void SpawnGem(){
        RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
        EntityGem gem = gemm.get().create(this.world);
        float BIOME_TEMPERATURE = this.world.getBiome(this.pos).get().getBaseTemperature();
        this.SetDrainedStoneColor(BIOME_TEMPERATURE);
        String gemtoform = this.EvaluateCruxes();
        if (gem.chromaColourRequired) {
            if (getColorFromChroma() == chromaColour) {
                if (Objects.equals(gemtoform, "")) {
                    //this.Drain(GemFormation.getBlockPosInVolume(this.world, this.pos, this.volumeToCheck));
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
                    gem = gemm.get().create(this.world);
                    if (gem instanceof EntityVaryingGem) {
                        EntityVaryingGem varyingGem = (EntityVaryingGem) gem;
                        varyingGem.setSkinVariantOnInitialSpawn = false;
                        int variant = this.getColorFromChroma();
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
                    gem.setUUID(Mth.createInsecureUUID(this.world.random));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (gemtoform == "") {
                //this.Drain(GemFormation.getBlockPosInVolume(this.world, this.pos, this.volumeToCheck));
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
                gem = gemm.get().create(this.world);
                if (gem instanceof EntityVaryingGem) {
                    EntityVaryingGem varyingGem = (EntityVaryingGem) gem;
                    varyingGem.setSkinVariantOnInitialSpawn = false;
                    int variant = this.getColorFromChroma();
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
                gem.setUUID(Mth.createInsecureUUID(this.world.random));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("placement");
        double rarity = 1 / CONDITIONS.get(gemtoform).rarity;
        double check = weight / rarity;
        System.out.println("weight / rarity check "+weight / rarity);
        System.out.println("weight "+weight);
        if (world.dimension() != Level.NETHER && world.dimension() != Level.END) {
            if (primer.asItem() == ModItems.PRIME_BOOST.get()) {
                if (check <= 4) {
                    clod = true;
                    clodNO = 2;
                } else if (check <= 7) {
                    clod = true;
                    clodNO = 1;
                } else if (check <= 13) {
                    gem.setDefective(false);
                    System.out.println("defective");
                } else if (check >= 25) {
                    gem.setPrimary(true);
                    System.out.println("prime");
                }
            } else {
                if (check <= 4) {
                    clod = true;
                    clodNO = 3;
                } else if (check <= 7) {
                    clod = true;
                    clodNO = 2;
                } else if (check <= 10) {
                    clod = true;
                    clodNO = 1;
                } else if (check <= 17) {
                    gem.setDefective(true);
                    System.out.println("defective");
                } else if (check >= 30) {
                    gem.setPrimary(true);
                    System.out.println("prime");
                }
            }
        } else {

        }
        if (!clod) {
            gem.setGemPlacement(gem.generateGemPlacement());
            gem.setSkinVariant(gem.generateSkinVariant());
            if(gem.setSkinVariantOnInitialSpawn) {
                gem.setSkinColorVariant(gem.generateSkinColorVariant());
            } else gem.setSkinColorVariant(gem.initalSkinVariant);
            //gem.setAssignedGem(((ItemGem)gem.getGemItem().getDefaultInstance().getItem()).assigned_gem);
            System.out.println(gem.getAssignedGem());
            gem.setHairVariant(gem.generateHairVariant());
            gem.setSkinColor(gem.generatePaletteColor(PaletteType.SKIN));
            gem.setHairColor(gem.generatePaletteColor(PaletteType.HAIR));
            gem.setGemColor(gem.generatePaletteColor(PaletteType.GEM));
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
            gem.ASSIGNED_ID = UUID.randomUUID();
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
            }
            //gem.generateScoutList();
            gem.idlePowers = gem.generateIdlePowers();
            if(gem.spawnGem != null){
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
            this.world.addFreshEntity(gem);
        } else {
            System.out.println("clod");
            RegistryObject<EntityType<EntityAbomination>> abominationr = ModEntities.ABOMINATION;
            EntityAbomination abomination = abominationr.get().create(this.world);
            RegistryObject<EntityType<EntityCrawler>> crawlerr = ModEntities.CRAWLER;
            EntityCrawler crawler = crawlerr.get().create(this.world);
            RegistryObject<EntityType<EntityShambler>> shamblerr = ModEntities.SHAMBLER;
            EntityShambler shambler = shamblerr.get().create(this.world);
            if (clodNO == 1) {
                crawler.setPos(this.pos.getX() + .5f, this.pos.getY(), this.pos.getZ() + .5f);
                crawler.setHealth(crawler.getMaxHealth());
                crawler.setUUID(Mth.createInsecureUUID(this.world.random));
                this.world.addFreshEntity(crawler);
            } else if (clodNO == 2) {
                shambler.setPos(this.pos.getX() + .5f, this.pos.getY(), this.pos.getZ() + .5f);
                shambler.setHealth(shambler.getMaxHealth());
                shambler.setUUID(Mth.createInsecureUUID(this.world.random));
                this.world.addFreshEntity(shambler);
            } else if (clodNO == 3) {
                abomination.setPos(this.pos.getX() + .5f, this.pos.getY(), this.pos.getZ() + .5f);
                abomination.setHealth(abomination.getMaxHealth());
                abomination.setUUID(Mth.createInsecureUUID(this.world.random));
                this.world.addFreshEntity(abomination);
            }
        }
        ArrayList<BlockPos> blocks = GemFormation.getBlockPosInVolume(this.world, this.pos, this.volumeToCheck);
        DrainEvent event2 = new DrainEvent(blocks);
        MinecraftForge.EVENT_BUS.post(event2);
        //this.Drain(blocks);
        System.out.println("exit hole attempt");
        if (getClosestExitDirection() == 4) {
            this.GenerateFacingExitHole();
        } else {
            this.GenerateClosestExitHole(getClosestExitDirection());
        }
    }

    public static ArrayList<Block> getBlocksInVolume(Level domhain, BlockPos position, BlockPos volume){
        ArrayList<Block> blocksInVolume = new ArrayList<>();
        float xo = GemFormation.getHalfMiddleOffsetRight(volume.getX());
        float yo = GemFormation.getHalfMiddleOffsetRight(volume.getY());
        float zo = GemFormation.getHalfMiddleOffsetRight(volume.getZ());
        for(int z = GemFormation.getHalfMiddleOffsetLeft(volume.getZ()); z < xo; z++){
            for(int y = GemFormation.getHalfMiddleOffsetLeft(volume.getY()); y < yo; y++){
                for(int x = GemFormation.getHalfMiddleOffsetLeft(volume.getX()); x < zo; x++){
                    Block block = domhain.getBlockState(position.offset(new BlockPos(x, y, z))).getBlock();
                    if(block instanceof AirBlock){
                        continue;
                    }
                    else{
                        blocksInVolume.add(block);
                    }
                }
            }
        }
        return blocksInVolume;
    }
    public static ArrayList<BlockPos> getBlockPosInVolume(Level domhain, BlockPos position, BlockPos volume){
        ArrayList<BlockPos> blocksInVolume = new ArrayList<>();
        float xo = GemFormation.getHalfMiddleOffsetRight(volume.getX());
        float yo = GemFormation.getHalfMiddleOffsetRight(volume.getY());
        float zo = GemFormation.getHalfMiddleOffsetRight(volume.getZ());
        for(int z = GemFormation.getHalfMiddleOffsetLeft(volume.getZ()); z < xo; z++){
            for(int y = GemFormation.getHalfMiddleOffsetLeft(volume.getY()); y < yo; y++){
                for(int x = GemFormation.getHalfMiddleOffsetLeft(volume.getX()); x < zo; x++){
                    BlockPos block = position.offset(new BlockPos(x, y, z));
                    if(domhain.getBlockState(block).getBlock() instanceof AirBlock){
                        continue;
                    }
                    else{
                        blocksInVolume.add(block);
                    }
                }
            }
        }
        return blocksInVolume;
    }

    public static int getHalfMiddleOffsetLeft(float value){
        return -(int)Math.floor(value / 2);
    }

    public static int getHalfMiddleOffsetRight(float value){
        return (int)Math.ceil(value / 2);
    }

    public String EvaluateCruxes() {
        String returnGem = "";
        //System.out.println("test tier");
        Random rand = new Random();
        if (tier == 1) {
            double lowestR = 100000000;
            String lowestRGem = "";
            double lowestR2 = 100000000;
            String lowestRGem2 = "";
            double lowestR3 = 100000000;
            String lowestRGem3 = "";
            //System.out.println("check out of possible gems");
            //System.out.println("total " + totalWeight);
            if (world.dimension() != Level.NETHER && world.dimension() != Level.END) {
                System.out.println("overworld");
                for (String gem : POSSIBLE_GEMS_TIER_1) {
                    boolean primed = false;
                    CONDITIONS = ModEntities.CRUXTOGEM;
                    GemConditions conditions = CONDITIONS.get(gem);
                    //System.out.println(" ");
                    //System.out.println(gem);
                    // if (this.primer == conditions.primer && conditions.primer != Items.AIR) {
                    if (this.primer == conditions.primer && conditions.primer != Items.AIR) {
                        primed = true;
                    }
                    //double r1 = rand.nextDouble();
                    //System.out.println("r1 random " + r1);
                    double r = /*r1 **/ totalWeight;
                    //System.out.println("random " + r);
                    if (primed) {
                        r -= (3 * WEIGHTS_OF_GEMS.get(gem));
                    } else {
                        r -= WEIGHTS_OF_GEMS.get(gem);
                    }
                    System.out.println(gem);
                    System.out.println(r);
                    System.out.println();
                /*if (WEIGHTS_OF_GEMS.get(gem) < 12) {
                    r = 1000000;
                }*/
                    if (r < lowestR) {
                        lowestR3 = lowestR2;
                        lowestR2 = lowestR;
                        lowestRGem3 = lowestRGem2;
                        lowestRGem2 = lowestRGem;
                        lowestR = r;
                        lowestRGem = gem;
                    } else if (r < lowestR2) {
                        lowestR3 = lowestR2;
                        lowestRGem3 = lowestRGem2;
                        lowestR2 = r;
                        lowestRGem2 = gem;
                    } else if (r < lowestR3) {
                        lowestR3 = r;
                        lowestRGem3 = gem;
                    }
                    System.out.println("lowest: " + lowestRGem);
                    System.out.println("lowest: " + lowestR);
                    System.out.println("lowest 2: " + lowestRGem2);
                    System.out.println("lowest 2: " + lowestR2);
                    System.out.println("lowest 3: " + lowestRGem3);
                    System.out.println("lowest 3: " + lowestR3);
                }
                int r2 = rand.nextInt(4);
                    switch (r2) {
                        case 0,3 -> {
                            returnGem = lowestRGem;
                            weight = (float) (totalWeight - lowestR);
                        }
                        case 1 -> {
                            returnGem = lowestRGem2;
                            weight = (float) (totalWeight - lowestR2);
                        }
                        case 2 -> {
                            returnGem = lowestRGem3;
                            weight = (float) (totalWeight - lowestR3);
                        }
                    }
            } else if (world.dimension() == Level.NETHER) {
                for (String gem : NETHER_POSSIBLE_GEMS_TIER_1) {
                    boolean primed = false;
                    CONDITIONS = ModEntities.CRUXTOGEM;
                    GemConditions conditions = CONDITIONS.get(gem);
                    //System.out.println(" ");
                    //System.out.println(gem);
                    // if (this.primer == conditions.primer && conditions.primer != Items.AIR) {
                    if (this.primer == conditions.primer && conditions.primer != Items.AIR) {
                        primed = true;
                    }
                    //double r1 = rand.nextDouble();
                    //System.out.println("r1 random " + r1);
                    double r = /*r1 **/ totalWeight;
                    //System.out.println("random " + r);
                    if (primed) {
                        r -= (3 * WEIGHTS_OF_GEMS.get(gem));
                    } else {
                        r -= WEIGHTS_OF_GEMS.get(gem);
                    }
                    System.out.println(gem);
                    System.out.println(r);
                    System.out.println();
                /*if (WEIGHTS_OF_GEMS.get(gem) < 12) {
                    r = 1000000;
                }*/
                    if (r < lowestR) {
                        lowestR3 = lowestR2;
                        lowestR2 = lowestR;
                        lowestRGem3 = lowestRGem2;
                        lowestRGem2 = lowestRGem;
                        lowestR = r;
                        lowestRGem = gem;
                    } else if (r < lowestR2) {
                        lowestR3 = lowestR2;
                        lowestRGem3 = lowestRGem2;
                        lowestR2 = r;
                        lowestRGem2 = gem;
                    } else if (r < lowestR3) {
                        lowestR3 = r;
                        lowestRGem3 = gem;
                    }
                    System.out.println("lowest: " + lowestRGem);
                    System.out.println("lowest: " + lowestR);
                    System.out.println("lowest 2: " + lowestRGem2);
                    System.out.println("lowest 2: " + lowestR2);
                    System.out.println("lowest 3: " + lowestRGem3);
                    System.out.println("lowest 3: " + lowestR3);
                }
                int r2 = rand.nextInt(4);
                switch (r2) {
                    case 0,3 -> {
                        returnGem = lowestRGem;
                        weight = (float) (totalWeight - lowestR);
                    }
                    case 1 -> {
                        returnGem = lowestRGem2;
                        weight = (float) (totalWeight - lowestR2);
                    }
                    case 2 -> {
                        returnGem = lowestRGem3;
                        weight = (float) (totalWeight - lowestR3);
                    }
                }
            } else if (world.dimension() == Level.END) {
                for (String gem : END_POSSIBLE_GEMS_TIER_1) {
                    boolean primed = false;
                    CONDITIONS = ModEntities.CRUXTOGEM;
                    GemConditions conditions = CONDITIONS.get(gem);
                    //System.out.println(" ");
                    //System.out.println(gem);
                    // if (this.primer == conditions.primer && conditions.primer != Items.AIR) {
                    if (this.primer == conditions.primer && conditions.primer != Items.AIR) {
                        primed = true;
                    }
                    //double r1 = rand.nextDouble();
                    //System.out.println("r1 random " + r1);
                    double r = /*r1 **/ totalWeight;
                    //System.out.println("random " + r);
                    if (primed) {
                        r -= (3 * WEIGHTS_OF_GEMS.get(gem));
                    } else {
                        r -= WEIGHTS_OF_GEMS.get(gem);
                    }
                    System.out.println(gem);
                    System.out.println(r);
                    System.out.println();
                /*if (WEIGHTS_OF_GEMS.get(gem) < 12) {
                    r = 1000000;
                }*/
                    if (r < lowestR) {
                        lowestR3 = lowestR2;
                        lowestR2 = lowestR;
                        lowestRGem3 = lowestRGem2;
                        lowestRGem2 = lowestRGem;
                        lowestR = r;
                        lowestRGem = gem;
                    } else if (r < lowestR2) {
                        lowestR3 = lowestR2;
                        lowestRGem3 = lowestRGem2;
                        lowestR2 = r;
                        lowestRGem2 = gem;
                    } else if (r < lowestR3) {
                        lowestR3 = r;
                        lowestRGem3 = gem;
                    }
                    System.out.println("lowest: " + lowestRGem);
                    System.out.println("lowest: " + lowestR);
                    System.out.println("lowest 2: " + lowestRGem2);
                    System.out.println("lowest 2: " + lowestR2);
                    System.out.println("lowest 3: " + lowestRGem3);
                    System.out.println("lowest 3: " + lowestR3);
                }
                int r2 = rand.nextInt(4);
                switch (r2) {
                    case 0,3 -> {
                        returnGem = lowestRGem;
                        weight = (float) (totalWeight - lowestR);
                    }
                    case 1 -> {
                        returnGem = lowestRGem2;
                        weight = (float) (totalWeight - lowestR2);
                    }
                    case 2 -> {
                        returnGem = lowestRGem3;
                        weight = (float) (totalWeight - lowestR3);
                    }
                }
            }
            } else if (tier == 2) {

                double lowestR = 100000000;
                String lowestRGem = "";
                double lowestR2 = 100000000;
                String lowestRGem2 = "";
                double lowestR3 = 100000000;
                String lowestRGem3 = "";
            if (world.dimension() != Level.NETHER && world.dimension() != Level.END) {
                System.out.println("overworld");
                for (String gem : POSSIBLE_GEMS_TIER_2) {
                    boolean primed = false;
                    CONDITIONS = ModEntities.CRUXTOGEM;
                    GemConditions conditions = CONDITIONS.get(gem);
                    //System.out.println(" ");
                    //System.out.println(gem);
                    if (this.primer == conditions.primer && conditions.primer != Items.AIR) {
                        primed = true;
                    }
                    //double r1 = rand.nextDouble();
                    //System.out.println("r1 random " + r1);
                    double r = /*r1 **/ totalWeight;
                    //System.out.println("random " + r);
                    if (primed) {
                        r -= (3 * WEIGHTS_OF_GEMS.get(gem));
                    } else {
                        r -= WEIGHTS_OF_GEMS.get(gem);
                    }
                    System.out.println(gem);
                    System.out.println(r);
                    System.out.println();
                /*if (WEIGHTS_OF_GEMS.get(gem) < 12) {
                    r = 1000000;
                }*/
                    if (r < lowestR) {
                        lowestR3 = lowestR2;
                        lowestR2 = lowestR;
                        lowestRGem3 = lowestRGem2;
                        lowestRGem2 = lowestRGem;
                        lowestR = r;
                        lowestRGem = gem;
                    } else if (r < lowestR2) {
                        lowestR3 = lowestR2;
                        lowestRGem3 = lowestRGem2;
                        lowestR2 = r;
                        lowestRGem2 = gem;
                    } else if (r < lowestR3) {
                        lowestR3 = r;
                        lowestRGem3 = gem;
                    }
                    System.out.println("lowest: " + lowestRGem);
                    System.out.println("lowest: " + lowestR);
                    System.out.println("lowest 2: " + lowestRGem2);
                    System.out.println("lowest 2: " + lowestR2);
                    System.out.println("lowest 3: " + lowestRGem3);
                    System.out.println("lowest 3: " + lowestR3);
                }
                int r2 = rand.nextInt(4);
                switch (r2) {
                    case 0,3 -> {
                        returnGem = lowestRGem;
                        weight = (float) (totalWeight - lowestR);
                    }
                    case 1 -> {
                        returnGem = lowestRGem2;
                        weight = (float) (totalWeight - lowestR2);
                    }
                    case 2 -> {
                        returnGem = lowestRGem3;
                        weight = (float) (totalWeight - lowestR3);
                    }
                }
            } else if (world.dimension() == Level.NETHER) {
                for (String gem : NETHER_POSSIBLE_GEMS_TIER_2) {
                    boolean primed = false;
                    CONDITIONS = ModEntities.CRUXTOGEM;
                    GemConditions conditions = CONDITIONS.get(gem);
                    System.out.println(" ");
                    System.out.println(gem);
                    if (this.primer == conditions.primer && conditions.primer != Items.AIR) {
                        primed = true;
                    }
                    //double r1 = rand.nextDouble();
                    //System.out.println("r1 random " + r1);
                    double r = /*r1 **/ totalWeight;
                    System.out.println("random " + r);
                    if (primed) {
                        r -= (3 * WEIGHTS_OF_GEMS.get(gem));
                    } else {
                        r -= WEIGHTS_OF_GEMS.get(gem);
                    }
                /*if (WEIGHTS_OF_GEMS.get(gem) < 12) {
                    r = 1000000;
                }*/
                    if (r < lowestR) {
                        lowestR3 = lowestR2;
                        lowestR2 = lowestR;
                        lowestRGem3 = lowestRGem2;
                        lowestRGem2 = lowestRGem;
                        lowestR = r;
                        lowestRGem = gem;
                    } else if (r < lowestR2) {
                        lowestR3 = lowestR2;
                        lowestRGem3 = lowestRGem2;
                        lowestR2 = r;
                        lowestRGem2 = gem;
                    } else if (r < lowestR3) {
                        lowestR3 = r;
                        lowestRGem3 = gem;
                    }
                    int r2 = rand.nextInt(4);
                    switch (r2) {
                        case 0,3 -> {
                            returnGem = lowestRGem;
                            weight = (float) (totalWeight - lowestR);
                        }
                        case 1 -> {
                            returnGem = lowestRGem2;
                            weight = (float) (totalWeight - lowestR2);
                        }
                        case 2 -> {
                            returnGem = lowestRGem3;
                            weight = (float) (totalWeight - lowestR3);
                        }
                    }
                }
            } else if (world.dimension() == Level.END) {
                for (String gem : END_POSSIBLE_GEMS_TIER_2) {
                    boolean primed = false;
                    CONDITIONS = ModEntities.CRUXTOGEM;
                    GemConditions conditions = CONDITIONS.get(gem);
                    System.out.println(" ");
                    System.out.println(gem);
                    if (this.primer == conditions.primer && conditions.primer != Items.AIR) {
                        primed = true;
                    }
                    //double r1 = rand.nextDouble();
                    //System.out.println("r1 random " + r1);
                    double r = /*r1 **/ totalWeight;
                    System.out.println("random " + r);
                    if (primed) {
                        r -= (3 * WEIGHTS_OF_GEMS.get(gem));
                    } else {
                        r -= WEIGHTS_OF_GEMS.get(gem);
                    }
                /*if (WEIGHTS_OF_GEMS.get(gem) < 12) {
                    r = 1000000;
                }*/
                    if (r < lowestR) {
                        lowestR3 = lowestR2;
                        lowestR2 = lowestR;
                        lowestRGem3 = lowestRGem2;
                        lowestRGem2 = lowestRGem;
                        lowestR = r;
                        lowestRGem = gem;
                    } else if (r < lowestR2) {
                        lowestR3 = lowestR2;
                        lowestRGem3 = lowestRGem2;
                        lowestR2 = r;
                        lowestRGem2 = gem;
                    } else if (r < lowestR3) {
                        lowestR3 = r;
                        lowestRGem3 = gem;
                    }

                    int r2 = rand.nextInt(4);
                    switch (r2) {
                        case 0,3 -> {
                            returnGem = lowestRGem;
                            weight = (float) (totalWeight - lowestR);
                        }
                        case 1 -> {
                            returnGem = lowestRGem2;
                            weight = (float) (totalWeight - lowestR2);
                        }
                        case 2 -> {
                            returnGem = lowestRGem3;
                            weight = (float) (totalWeight - lowestR3);
                        }
                    }
                }
            }
        }
        System.out.println(returnGem);
        //OUTPUT: A gem
        return returnGem;
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

    public void GenerateFacingExitHole(){
        System.out.println("This block is facing: " + this.facing);
        BlockPos direction = GemFormation.DirectionFromFacing(this.facing);
        BlockPos currentPos = new BlockPos(this.pos);
        boolean flag = false;
        for(int i = 0; i < this.EXIT_HOLE_LENGTH; i++){
            if(!flag) {
                if(this.world.getBlockState(currentPos).getBlock() instanceof AirBlock
                        && this.world.getBlockState(currentPos.above()).getBlock() instanceof AirBlock){
                    flag = true;
                }
                this.world.destroyBlock(currentPos, false);
                this.world.destroyBlock(currentPos.above(), false);
                this.world.destroyBlock(currentPos.above().above(), false);
                currentPos = currentPos.offset(direction);
            }
            else{
                break;
            }
        }
    }

    public void GenerateClosestExitHole(int facing){
        System.out.println("This block is facing: " + facing);
        BlockPos direction = GemFormation.DirectionFromFacing(facing);
        BlockPos currentPos = new BlockPos(this.pos);
        boolean flag = false;
        for(int i = 0; i < this.EXIT_HOLE_LENGTH; i++){
            if(!flag) {
                if(this.world.getBlockState(currentPos).getBlock() instanceof AirBlock
                        && this.world.getBlockState(currentPos.above()).getBlock() instanceof AirBlock){
                    flag = true;
                }
                this.world.destroyBlock(currentPos, false);
                this.world.destroyBlock(currentPos.above(), false);
                this.world.destroyBlock(currentPos.above().above(), false);
                currentPos = currentPos.offset(direction);
            }
            else{
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
                if (this.world.getBlockState(currentPos.north(i)).getBlock() instanceof AirBlock) {
                    flag = true;
                    direction = 1;
                    System.out.println("north");
                } else if (this.world.getBlockState(currentPos.south(i)).getBlock() instanceof AirBlock) {
                    flag = true;
                    System.out.println("south");
                    direction = 3;

                } else if (this.world.getBlockState(currentPos.east(i)).getBlock() instanceof AirBlock) {
                    flag = true;
                    System.out.println("east");
                    direction = 0;

                } else if (this.world.getBlockState(currentPos.west(i)).getBlock() instanceof AirBlock) {
                    flag = true;
                    System.out.println("west");
                    direction = 2;

                }
            }
        }
        return direction;
    }

    /*public void GenerateExitHole(){
        boolean found = false;
        if(!found) {
            ArrayList<BlockPos> blocks = new ArrayList<>();
            ArrayList<BlockPos> blocksToDrain = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                if (this.world.getBlockState(this.pos.add(i, 0, 0)).getBlock() != Blocks.AIR) {
                    blocks.add(this.pos.add(i, 0, 0));
                    blocks.add(this.pos.add(i, 1, 0));
                    blocks.add(this.pos.add(i, 2, 0));
                    blocksToDrain.add(this.pos.add(i, 0, 0).down());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().up().up());
                    blocksToDrain.add(this.pos.add(i, 0, 0).north());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().north());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().up().north());
                    blocksToDrain.add(this.pos.add(i, 0, 0).south());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().south());
                    blocksToDrain.add(this.pos.add(i, 0, 0).up().up().south());
                } else {
                    found = true;
                    break;
                }
            }
            if(found){
                for(BlockPos pos : blocks){
                    this.world.destroyBlock(pos, false);
                }
                this.Drain(blocksToDrain);
            }
        }
        if(!found) {
            ArrayList<BlockPos> blocks = new ArrayList<>();
            ArrayList<BlockPos> blocksToDrain = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                if (this.world.getBlockState(this.pos.add(-i, 0, 0)).getBlock() != Blocks.AIR) {
                    blocks.add(this.pos.add(-i, 0, 0));
                    blocks.add(this.pos.add(-i, 1, 0));
                    blocks.add(this.pos.add(-i, 2, 0));
                    blocksToDrain.add(this.pos.add(-i, 0, 0).down());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).up().up().up());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).north());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).up().north());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).up().up().north());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).south());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).up().south());
                    blocksToDrain.add(this.pos.add(-i, 0, 0).up().up().south());
                } else {
                    found = true;
                    break;
                }
            }
            if(found){
                for(BlockPos pos : blocks){
                    this.world.destroyBlock(pos, false);
                }
                this.Drain(blocksToDrain);
            }
        }
        if(!found) {
            ArrayList<BlockPos> blocks = new ArrayList<>();
            ArrayList<BlockPos> blocksToDrain = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                if (this.world.getBlockState(this.pos.add(0, 0, i)).getBlock() != Blocks.AIR) {
                    blocks.add(this.pos.add(0, 0, i));
                    blocks.add(this.pos.add(0, 1, i));
                    blocks.add(this.pos.add(0, 2, i));
                    blocksToDrain.add(this.pos.add(0, 0, i).down());
                    blocksToDrain.add(this.pos.add(0, 0, i).up().up().up());
                    blocksToDrain.add(this.pos.add(0, 0, i).west());
                    blocksToDrain.add(this.pos.add(0, 0, i).up().west());
                    blocksToDrain.add(this.pos.add(0, 0, i).up().up().west());
                    blocksToDrain.add(this.pos.add(0, 0, i).east());
                    blocksToDrain.add(this.pos.add(0, 0, i).up().east());
                    blocksToDrain.add(this.pos.add(0, 0, i).up().up().east());
                } else {
                    found = true;
                    break;
                }
            }
            if(found){
                for(BlockPos pos : blocks){
                    this.world.destroyBlock(pos, false);
                }
                this.Drain(blocksToDrain);
            }
        }
        if(!found) {
            ArrayList<BlockPos> blocksToDrain = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                this.world.destroyBlock(this.pos.add(0, 0, -i), false);
                this.world.destroyBlock(this.pos.add(0, 1, -i), false);
                this.world.destroyBlock(this.pos.add(0, 2, -i), false);
                if(this.world.getBlockState(this.pos.add(0, 0, -i)) != Blocks.AIR.defaultBlockState()) {
                    blocksToDrain.add(this.pos.add(0, 0, -i).down());
                    blocksToDrain.add(this.pos.add(0, 0, -i).up().up().up());
                    blocksToDrain.add(this.pos.add(0, 0, -i).west());
                    blocksToDrain.add(this.pos.add(0, 0, -i).up().west());
                    blocksToDrain.add(this.pos.add(0, 0, -i).up().up().west());
                    blocksToDrain.add(this.pos.add(0, 0, -i).east());
                    blocksToDrain.add(this.pos.add(0, 0, -i).up().east());
                    blocksToDrain.add(this.pos.add(0, 0, -i).up().up().east());
                }
            }
            this.Drain(blocksToDrain);
        }
    }*/

    public int getColorFromChroma(){
        return this.chroma.color;
    }

    public void Drain(ArrayList<BlockPos> blockPosList){
        for (BlockPos pos : blockPosList){
            BlockState block = this.world.getBlockState(pos);
            if(block.getBlock() == ModBlocks.GEM_SEED_BLOCK.get() ||
                    block.getBlock() == ModBlocks.DRILL_BLOCK.get() || block.getBlock() == ModBlocks.TANK_BLOCK.get() ||
                    block.getBlock() == ModBlocks.POWER_CRYSTAL_BLOCK.get() || block.getBlock() == ModBlocks.POWER_CRYSTAL_BLOCK_TIER_2.get()){
                continue;
            }
            if(block == Blocks.DIRT.defaultBlockState() || block == Blocks.GRASS_BLOCK.defaultBlockState() || block == Blocks.DIRT_PATH.defaultBlockState()
                    || block == Blocks.GRAVEL.defaultBlockState()){
                this.world.setBlockAndUpdate(pos, this.drained_soil.defaultBlockState());
            }
            else if(block == Blocks.SAND.defaultBlockState() || block == Blocks.RED_SAND.defaultBlockState() || block == Blocks.SOUL_SAND.defaultBlockState()){
                this.world.setBlockAndUpdate(pos, this.drained_sand.defaultBlockState());
            }
            else if(block == Blocks.OAK_LOG.defaultBlockState() || block == Blocks.STRIPPED_OAK_LOG.defaultBlockState() || block == Blocks.STRIPPED_OAK_WOOD.defaultBlockState() || block == Blocks.OAK_WOOD
                    .defaultBlockState() || block == Blocks.SPRUCE_LOG.defaultBlockState() || block == Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState() || block == Blocks.STRIPPED_SPRUCE_WOOD.defaultBlockState() || block == Blocks.SPRUCE_WOOD
                    .defaultBlockState() || block == Blocks.BIRCH_LOG.defaultBlockState() || block == Blocks.STRIPPED_BIRCH_LOG.defaultBlockState() || block == Blocks.STRIPPED_BIRCH_WOOD.defaultBlockState() || block == Blocks.BIRCH_WOOD
                    .defaultBlockState() || block == Blocks.JUNGLE_LOG.defaultBlockState() || block == Blocks.STRIPPED_JUNGLE_LOG.defaultBlockState() || block == Blocks.STRIPPED_JUNGLE_WOOD.defaultBlockState() || block == Blocks.JUNGLE_WOOD
                    .defaultBlockState() || block == Blocks.ACACIA_LOG.defaultBlockState() || block == Blocks.STRIPPED_ACACIA_LOG.defaultBlockState() || block == Blocks.STRIPPED_ACACIA_WOOD.defaultBlockState() || block == Blocks.ACACIA_WOOD
                    .defaultBlockState() || block == Blocks.DARK_OAK_LOG.defaultBlockState() || block == Blocks.STRIPPED_DARK_OAK_LOG.defaultBlockState() || block == Blocks.STRIPPED_DARK_OAK_WOOD.defaultBlockState() || block == Blocks.DARK_OAK_WOOD.defaultBlockState()) {
                this.world.setBlockAndUpdate(pos, this.drained_log.defaultBlockState());
            }
            else if(block == Blocks.CRIMSON_STEM.defaultBlockState() || block == Blocks.WARPED_STEM.defaultBlockState() || block == Blocks.STRIPPED_CRIMSON_STEM.defaultBlockState() || block == Blocks.STRIPPED_WARPED_STEM
                    .defaultBlockState() || block == Blocks.CRIMSON_HYPHAE.defaultBlockState() || block == Blocks.WARPED_HYPHAE.defaultBlockState() || block == Blocks.STRIPPED_CRIMSON_HYPHAE.defaultBlockState() || block == Blocks.STRIPPED_WARPED_HYPHAE.defaultBlockState()) {
                this.world.setBlockAndUpdate(pos, this.drained_log_cracked.defaultBlockState());
            }
            else if(block == Blocks.BLUE_ICE.defaultBlockState() || block == Blocks.PACKED_ICE.defaultBlockState()) {
                this.world.setBlockAndUpdate(pos, this.drained_ice.defaultBlockState());
            }
            else{
                if(pos.getY() < 80) {
                    this.world.setBlockAndUpdate(pos, this.drained_stone.defaultBlockState());
                }
                else{
                    this.world.setBlockAndUpdate(pos, this.drained_stone_2.defaultBlockState());
                    if(pos.getY() % 6 == 0){
                        this.world.setBlockAndUpdate(pos, this.banded_drained_stone.defaultBlockState());
                    }
                }
                if(pos.getY() == 80){
                    this.world.setBlockAndUpdate(pos, this.banded_drained_stone.defaultBlockState());
                }
            }
        }
    }

    public void SetDrainedStoneColor(float temperature){
        if(temperature > .1f && temperature <= .5F){
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_GREY_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_GREY_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_GREY_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_GREY_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
        else if(temperature > .5f && temperature <= .9f){
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
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
            this.drained_sand = ModBlocks.DRAINED_SAND.get();
            this.drained_soil = ModBlocks.DRAINED_BLUE_SOIL.get();
            this.drained_ice = ModBlocks.DRAINED_ICE.get();
            this.drained_stone = ModBlocks.DRAINED_BLUE_STONE.get();
            this.drained_stone_2 = ModBlocks.DRAINED_BLUE_STONE_2.get();
            this.banded_drained_stone = ModBlocks.DRAINED_BANDED_BLUE_STONE.get();
            this.drained_log = ModBlocks.DRAINED_LOG.get();
            this.drained_log_cracked = ModBlocks.DRAINED_LOG_CRACKED.get();
        }
    }
}