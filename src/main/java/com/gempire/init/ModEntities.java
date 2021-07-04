package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.entities.TestEntity;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.*;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.systems.injection.Crux;
import com.gempire.systems.injection.GemConditions;
import com.gempire.systems.injection.GemFormation;
import com.gempire.util.CruxType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Gempire.MODID);
    public static HashMap<String, GemConditions> CRUXTOGEM = new HashMap<>();
    // Entity Types
    public static final RegistryObject<EntityType<TestEntity>> TEST = ENTITIES.register("test",
            () -> EntityType.Builder.create(TestEntity::new, EntityClassification.CREATURE)
                    .size(1.0f, 1.0f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "test").toString()));
    //Pebble
    public static final RegistryObject<EntityType<EntityPebble>> PEBBLE = ENTITIES.register("pebble",
            () -> EntityType.Builder.create(EntityPebble::new, EntityClassification.CREATURE)
                    .size(.4f, .4f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "pebble").toString()));

    public static final RegistryObject<EntityType<EntityMica>> MICA = ENTITIES.register("mica",
            () -> EntityType.Builder.create(EntityMica::new, EntityClassification.CREATURE)
                    .size(.4f, .4f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "mica").toString()));

    public static final RegistryObject<EntityType<EntityShale>> SHALE = ENTITIES.register("shale",
            () -> EntityType.Builder.create(EntityShale::new, EntityClassification.CREATURE)
                    .size(.4f, .4f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "shale").toString()));

    public static final RegistryObject<EntityType<EntityRuby>> RUBY = ENTITIES.register("ruby",
            () -> EntityType.Builder.create(EntityRuby::new, EntityClassification.CREATURE)
                    .size(.75f, 1.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "ruby").toString()));

    public static final RegistryObject<EntityType<EntitySapphire>> SAPPHIRE = ENTITIES.register("sapphire",
            () -> EntityType.Builder.create(EntitySapphire::new, EntityClassification.CREATURE)
                    .size(.75f, 1.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "sapphire").toString()));

    public static final RegistryObject<EntityType<EntityQuartz>> QUARTZ = ENTITIES.register("quartz",
            () -> EntityType.Builder.create(EntityQuartz::new, EntityClassification.CREATURE)
                    .size(.7f, 1.9f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "quartz").toString()));

    public static final RegistryObject<EntityType<EntityJasper>> JASPER = ENTITIES.register("jasper",
            () -> EntityType.Builder.create(EntityJasper::new, EntityClassification.CREATURE)
                    .size(.7f, 1.9f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "jasper").toString()));

    public static final RegistryObject<EntityType<EntityAgate>> AGATE = ENTITIES.register("agate",
            () -> EntityType.Builder.create(EntityAgate::new, EntityClassification.CREATURE)
                    .size(.7f, 1.9f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "agate").toString()));

    public static void registerCruxes(){
        ModEntities.CRUXTOGEM.put("ruby", ModCruxes.RUBY_CONDITIONS());
        ModEntities.CRUXTOGEM.put("sapphire", ModCruxes.SAPPHIRE_CONDITIONS());
        ModEntities.CRUXTOGEM.put("quartz", ModCruxes.QUARTZ_CONDITIONS());
        ModEntities.CRUXTOGEM.put("jasper", ModCruxes.QUARTZ_CONDITIONS());
        ModEntities.CRUXTOGEM.put("agate", ModCruxes.QUARTZ_CONDITIONS());
        GemFormation.POSSIBLE_GEMS.add("ruby");
        GemFormation.POSSIBLE_GEMS.add("sapphire");
        GemFormation.POSSIBLE_GEMS.add("quartz");
        GemFormation.POSSIBLE_GEMS.add("jasper");
        GemFormation.POSSIBLE_GEMS.add("agate");
    }

    public static void setVanillaGems(){
        AddonHandler.VANILLA_GEMS.add("ruby");
        AddonHandler.VANILLA_GEMS.add("sapphire");
        AddonHandler.VANILLA_GEMS.add("quartz");
        AddonHandler.VANILLA_GEMS.add("jasper");
        AddonHandler.VANILLA_GEMS.add("agate");
        AddonHandler.VANILLA_GEMS.add("pebble");
        AddonHandler.VANILLA_GEMS.add("mica");
        AddonHandler.VANILLA_GEMS.add("shale");
    }
}
