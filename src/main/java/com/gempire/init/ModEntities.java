package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.config.GempireCommonConfigs;
import com.gempire.entities.gems.*;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.gems.starter.EntityNacre;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.entities.projectiles.AcidSpitEntity;
import com.gempire.entities.projectiles.IceShardEntity;
import com.gempire.entities.projectiles.WaterOrbEntity;
import com.gempire.systems.injection.GemConditions;
import com.gempire.systems.injection.GemFormation;
import com.gempire.util.DateTime;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Gempire.MODID);
    public static HashMap<String, GemConditions> CRUXTOGEM = new HashMap<>();

    // Entity Types
    //Pebble
    public static final RegistryObject<EntityType<EntityPebble>> PEBBLE = ENTITIES.register("pebble",
            () -> EntityType.Builder.of(EntityPebble::new, MobCategory.CREATURE)
                    .sized(.4f, .4f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "pebble").toString()));

    public static final RegistryObject<EntityType<EntityMica>> MICA = ENTITIES.register("mica",
            () -> EntityType.Builder.of(EntityMica::new, MobCategory.CREATURE)
                    .sized(.4f, .4f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "mica").toString()));

    public static final RegistryObject<EntityType<EntityShale>> SHALE = ENTITIES.register("shale",
            () -> EntityType.Builder.of(EntityShale::new, MobCategory.CREATURE)
                    .sized(.4f, .4f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "shale").toString()));

    public static final RegistryObject<EntityType<EntityNacre>> NACRE = ENTITIES.register("nacre",
            () -> EntityType.Builder.of(EntityNacre::new, MobCategory.CREATURE)
                    .sized(.4f, .4f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "nacre").toString()));

    public static final RegistryObject<EntityType<EntityRuby>> RUBY = ENTITIES.register("ruby",
            () -> EntityType.Builder.of(EntityRuby::new, MobCategory.CREATURE)
                    .sized(.75f, 1.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "ruby").toString()));

    public static final RegistryObject<EntityType<EntitySapphire>> SAPPHIRE = ENTITIES.register("sapphire",
            () -> EntityType.Builder.of(EntitySapphire::new, MobCategory.CREATURE)
                    .sized(.75f, 1.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "sapphire").toString()));

    public static final RegistryObject<EntityType<EntityQuartz>> QUARTZ = ENTITIES.register("quartz",
            () -> EntityType.Builder.of(EntityQuartz::new, MobCategory.CREATURE)
                    .sized(.7f, 1.99f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "quartz").toString()));

    public static final RegistryObject<EntityType<EntityJasper>> JASPER = ENTITIES.register("jasper",
            () -> EntityType.Builder.of(EntityJasper::new, MobCategory.CREATURE)
                    .sized(.7f, 1.99f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "jasper").toString()));

    public static final RegistryObject<EntityType<EntityAgate>> AGATE = ENTITIES.register("agate",
            () -> EntityType.Builder.of(EntityAgate::new, MobCategory.CREATURE)
                    .sized(.7f, 1.99f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "agate").toString()));

    public static final RegistryObject<EntityType<EntityTopaz>> TOPAZ = ENTITIES.register("topaz",
            () -> EntityType.Builder.of(EntityTopaz::new, MobCategory.CREATURE)
                    .sized(.95f, 1.8f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "topaz").toString()));

    public static final RegistryObject<EntityType<EntityObsidian>> OBSIDIAN = ENTITIES.register("obsidian",
            () -> EntityType.Builder.of(EntityObsidian::new, MobCategory.CREATURE)
                    .sized(.95f, 2.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "obsidian").toString()));

    public static final RegistryObject<EntityType<EntityPearl>> PEARL = ENTITIES.register("pearl",
            () -> EntityType.Builder.of(EntityPearl::new, MobCategory.CREATURE)
                    .sized(.5f, 1.99f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "pearl").toString()));

    public static final RegistryObject<EntityType<EntityNephrite>> NEPHRITE = ENTITIES.register("nephrite",
            () -> EntityType.Builder.of(EntityNephrite::new, MobCategory.CREATURE)
                    .sized(.5f, 1.99f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "nephrite").toString()));

    public static final RegistryObject<EntityType<EntitySpodumene>> SPODUMENE = ENTITIES.register("spodumene",
            () -> EntityType.Builder.of(EntitySpodumene::new, MobCategory.CREATURE)
                    .sized(.75f, 3.0f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "spodumene").toString()));

    public static final RegistryObject<EntityType<EntityZircon>> ZIRCON = ENTITIES.register("zircon",
            () -> EntityType.Builder.of(EntityZircon::new, MobCategory.CREATURE)
                    .sized(.5f, 1.99f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "zircon").toString()));

    public static final RegistryObject<EntityType<EntityAquamarine>> AQUAMARINE = ENTITIES.register("aquamarine",
            () -> EntityType.Builder.of(EntityAquamarine::new, MobCategory.CREATURE)
                    .sized(.75f, 1.0f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "aquamarine").toString()));

    public static final RegistryObject<EntityType<EntityBismuth>> BISMUTH = ENTITIES.register("bismuth",
            () -> EntityType.Builder.of(EntityBismuth::new, MobCategory.CREATURE)
                    .sized(.75f, 1.99f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "bismuth").toString()));

    public static final RegistryObject<EntityType<EntityBixbite>> BIXBITE = ENTITIES.register("bixbite",
            () -> EntityType.Builder.of(EntityBixbite::new, MobCategory.CREATURE)
                    .sized(.75f, 2.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "bixbite").toString()));

    public static final RegistryObject<EntityType<EntityGarnet>> GARNET = ENTITIES.register("garnet",
            () -> EntityType.Builder.of(EntityGarnet::new, MobCategory.CREATURE)
                    .sized(.75f, 3.0f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "garnet").toString()));

    public static final RegistryObject<EntityType<EntityEmerald>> EMERALD = ENTITIES.register("emerald",
            () -> EntityType.Builder.of(EntityEmerald::new, MobCategory.CREATURE)
                    .sized(.75f, 3.0f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "emerald").toString()));

    public static final RegistryObject<EntityType<EntityLapis>> LAPIS = ENTITIES.register("lapis",
            () -> EntityType.Builder.of(EntityLapis::new, MobCategory.CREATURE)
                    .sized(.75f, 1.8f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "lapis").toString()));

    public static final RegistryObject<EntityType<EntityLarimar>> LARIMAR = ENTITIES.register("larimar",
            () -> EntityType.Builder.of(EntityLarimar::new, MobCategory.CREATURE)
                    .sized(.75f, 1.99f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "larimar").toString()));

    public static final RegistryObject<EntityType<EntityMorganite>> MORGANITE = ENTITIES.register("morganite",
            () -> EntityType.Builder.of(EntityMorganite::new, MobCategory.CREATURE)
                    .sized(.75f, 1.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "morganite").toString()));

    public static final RegistryObject<EntityType<EntityPeridot>> PERIDOT = ENTITIES.register("peridot",
            () -> EntityType.Builder.of(EntityPeridot::new, MobCategory.CREATURE)
                    .sized(.75f, 1.99f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "peridot").toString()));
    
    public static final RegistryObject<EntityType<EntityRutile>> RUTILE = ENTITIES.register("rutile",
            () -> EntityType.Builder.of(EntityRutile::new, MobCategory.CREATURE)
                    .sized(.75f, 1.99f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "rutile").toString()));

    public static final RegistryObject<EntityType<EntitySpinel>> SPINEL = ENTITIES.register("spinel",
            () -> EntityType.Builder.of(EntitySpinel::new, MobCategory.CREATURE)
                    .sized(.75f, 1.7f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "spinel").toString()));

    public static final RegistryObject<EntityType<EntityTourmaline>> TOURMALINE = ENTITIES.register("tourmaline",
            () -> EntityType.Builder.of(EntityTourmaline::new, MobCategory.CREATURE)
                    .sized(.75f, 2.0f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "tourmaline").toString()));

    public static final RegistryObject<EntityType<IceShardEntity>> ICE_SHARD = ENTITIES.register("ice_shard", () -> {
        return EntityType.Builder.<IceShardEntity>of(IceShardEntity::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .setTrackingRange(64)
                .setUpdateInterval(10)
                .setShouldReceiveVelocityUpdates(true)
                .build(new ResourceLocation(Gempire.MODID, "ice_shard").toString());
    });

    public static final RegistryObject<EntityType<AcidSpitEntity>> ACID_SPIT = ENTITIES.register("acid_spit", () -> {
        return EntityType.Builder.<AcidSpitEntity>of(AcidSpitEntity::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .setTrackingRange(64)
                .setUpdateInterval(10)
                .setShouldReceiveVelocityUpdates(true)
                .build(new ResourceLocation(Gempire.MODID, "acid_spit").toString());
    });
    public static final RegistryObject<EntityType<WaterOrbEntity>> WATER_ORB = ENTITIES.register("water_orb", () -> {
        return EntityType.Builder.<WaterOrbEntity>of(WaterOrbEntity::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .setTrackingRange(64)
                .setUpdateInterval(10)
                .setShouldReceiveVelocityUpdates(true)
                .build(new ResourceLocation(Gempire.MODID, "water_orb").toString());
    });

    public static void registerCruxes() {
        ModEntities.CRUXTOGEM.put("ruby", ModCruxes.RUBY_CONDITIONS());
        ModEntities.CRUXTOGEM.put("sapphire", ModCruxes.SAPPHIRE_CONDITIONS());
        ModEntities.CRUXTOGEM.put("quartz", ModCruxes.QUARTZ_CONDITIONS());
        ModEntities.CRUXTOGEM.put("jasper", ModCruxes.JASPER_CONDITIONS());
        ModEntities.CRUXTOGEM.put("agate", ModCruxes.AGATE_CONDITIONS());
        ModEntities.CRUXTOGEM.put("topaz", ModCruxes.TOPAZ_CONDITIONS());
        ModEntities.CRUXTOGEM.put("obsidian", ModCruxes.OBSIDIAN_CONDITIONS());
        ModEntities.CRUXTOGEM.put("nephrite", ModCruxes.NEPHRITE_CONDITIONS());
        ModEntities.CRUXTOGEM.put("spodumene", ModCruxes.SPODUMENE_CONDITIONS());
        ModEntities.CRUXTOGEM.put("zircon", ModCruxes.ZIRCON_CONDITIONS());
        ModEntities.CRUXTOGEM.put("aquamarine", ModCruxes.AQUAMARINE_CONDITIONS());
        ModEntities.CRUXTOGEM.put("bismuth", ModCruxes.BISMUTH_CONDITIONS());
        ModEntities.CRUXTOGEM.put("bixbite", ModCruxes.BIXBITE_CONDITIONS());
        ModEntities.CRUXTOGEM.put("garnet", ModCruxes.GARNET_CONDITIONS());
        ModEntities.CRUXTOGEM.put("emerald", ModCruxes.EMERALD_CONDITIONS());
        ModEntities.CRUXTOGEM.put("lapis", ModCruxes.LAPIS_CONDITIONS());
        ModEntities.CRUXTOGEM.put("larimar", ModCruxes.LARIMAR_CONDITIONS());
        ModEntities.CRUXTOGEM.put("morganite", ModCruxes.MORGANITE_CONDITIONS());
        ModEntities.CRUXTOGEM.put("peridot", ModCruxes.PERIDOT_CONDITIONS());
        ModEntities.CRUXTOGEM.put("rutile", ModCruxes.RUTILE_CONDITIONS());
        ModEntities.CRUXTOGEM.put("spinel", ModCruxes.SPINEL_CONDITIONS());
        ModEntities.CRUXTOGEM.put("tourmaline", ModCruxes.TOURMALINE_CONDITIONS());

        GemFormation.POSSIBLE_GEMS.add("ruby");
        GemFormation.POSSIBLE_GEMS.add("sapphire");
        GemFormation.POSSIBLE_GEMS.add("quartz");
        GemFormation.POSSIBLE_GEMS.add("jasper");
        GemFormation.POSSIBLE_GEMS.add("agate");
        GemFormation.POSSIBLE_GEMS.add("topaz");
        GemFormation.POSSIBLE_GEMS.add("obsidian");
        GemFormation.POSSIBLE_GEMS.add("nephrite");
        GemFormation.POSSIBLE_GEMS.add("spodumene");
        GemFormation.POSSIBLE_GEMS.add("zircon");
        GemFormation.POSSIBLE_GEMS.add("aquamarine");
        GemFormation.POSSIBLE_GEMS.add("bismuth");
        GemFormation.POSSIBLE_GEMS.add("bixbite");
        GemFormation.POSSIBLE_GEMS.add("demantoid");
        GemFormation.POSSIBLE_GEMS.add("emerald");
        GemFormation.POSSIBLE_GEMS.add("hessonite");
        GemFormation.POSSIBLE_GEMS.add("lapis");
        GemFormation.POSSIBLE_GEMS.add("larimar");
        GemFormation.POSSIBLE_GEMS.add("melanite");
        GemFormation.POSSIBLE_GEMS.add("morganite");
        GemFormation.POSSIBLE_GEMS.add("peridot");
        GemFormation.POSSIBLE_GEMS.add("pyrope");
        GemFormation.POSSIBLE_GEMS.add("rutile");
        GemFormation.POSSIBLE_GEMS.add("spinel");
        GemFormation.POSSIBLE_GEMS.add("tourmaline");
    }

    public static void setVanillaGems(){
        AddonHandler.VANILLA_GEMS.add("pebble");
        AddonHandler.VANILLA_GEMS.add("mica");
        AddonHandler.VANILLA_GEMS.add("shale");
        AddonHandler.VANILLA_GEMS.add("nacre");
        AddonHandler.VANILLA_GEMS.add("ruby");
        AddonHandler.VANILLA_GEMS.add("sapphire");
        AddonHandler.VANILLA_GEMS.add("quartz");
        AddonHandler.VANILLA_GEMS.add("jasper");
        AddonHandler.VANILLA_GEMS.add("agate");
        AddonHandler.VANILLA_GEMS.add("topaz");
        AddonHandler.VANILLA_GEMS.add("obsidian");
        AddonHandler.VANILLA_GEMS.add("pearl");
        AddonHandler.VANILLA_GEMS.add("nephrite");
        AddonHandler.VANILLA_GEMS.add("spodumene");
        AddonHandler.VANILLA_GEMS.add("zircon");
        AddonHandler.VANILLA_GEMS.add("aquamarine");
        AddonHandler.VANILLA_GEMS.add("bismuth");
        AddonHandler.VANILLA_GEMS.add("bixbite");
        AddonHandler.VANILLA_GEMS.add("demantoid");
        AddonHandler.VANILLA_GEMS.add("emerald");
        AddonHandler.VANILLA_GEMS.add("hessonite");
        AddonHandler.VANILLA_GEMS.add("lapis");
        AddonHandler.VANILLA_GEMS.add("larimar");
        AddonHandler.VANILLA_GEMS.add("melanite");
        AddonHandler.VANILLA_GEMS.add("morganite");
        AddonHandler.VANILLA_GEMS.add("peridot");
        AddonHandler.VANILLA_GEMS.add("pyrope");
        AddonHandler.VANILLA_GEMS.add("rutile");
        AddonHandler.VANILLA_GEMS.add("spinel");
        AddonHandler.VANILLA_GEMS.add("tourmaline");
    }
}
