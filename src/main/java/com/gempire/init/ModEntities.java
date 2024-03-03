package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.container.InjectorContainer;
import com.gempire.entities.bases.EntityFusion;
import com.gempire.entities.other.*;
import com.gempire.entities.gems.*;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.gems.starter.EntityNacre;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.entities.projectiles.AcidSpitEntity;
import com.gempire.entities.projectiles.ElectrokinesisLightning;
import com.gempire.entities.projectiles.IceShardEntity;
import com.gempire.entities.projectiles.WaterOrbEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Gempire.MODID);

    // Entity Types

    public static final RegistryObject<EntityType<EntityBeastmasterWolf>> BEASTMASTER_WOLF = ENTITIES.register("beastmaster_wolf",
            () -> EntityType.Builder.of(EntityBeastmasterWolf::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.85F) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "beastmaster_wolf").toString()));

    public static final RegistryObject<EntityType<EntityFusion>> FUSION = ENTITIES.register("fusion",
            () -> EntityType.Builder.of(EntityFusion::new, MobCategory.CREATURE)
                    .sized(1F, 4F) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "fusion").toString()));

    public static final RegistryObject<EntityType<EntityAlabasterEmpress>> ALABASTER_EMPRESS = ENTITIES.register("alabaster_empress",
            () -> EntityType.Builder.of(EntityAlabasterEmpress::new, MobCategory.MONSTER)
                    .sized(1f, 3.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "alabaster_empress").toString()));

    public static final RegistryObject<EntityType<EntityAmberHuntress>> AMBER_HUNTRESS = ENTITIES.register("amber_huntress",
            () -> EntityType.Builder.of(EntityAmberHuntress::new, MobCategory.MONSTER)
                    .sized(1f, 3.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "amber_huntress").toString()));

    public static final RegistryObject<EntityType<EntityCobaltGuardian>> COBALT_GUARDIAN = ENTITIES.register("cobalt_guardian",
            () -> EntityType.Builder.of(EntityCobaltGuardian::new, MobCategory.MONSTER)
                    .sized(1f, 3.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "cobalt_guardian").toString()));

    public static final RegistryObject<EntityType<EntityFuchsiaPaladin>> FUCHSIA_PALADIN = ENTITIES.register("fuchsia_paladin",
            () -> EntityType.Builder.of(EntityFuchsiaPaladin::new, MobCategory.MONSTER)
                    .sized(1f, 3.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "fuchsia_paladin").toString()));

    public static final RegistryObject<EntityType<EntityPrismaticEmpress>> PRISMATIC_EMPRESS = ENTITIES.register("prismatic_empress",
            () -> EntityType.Builder.of(EntityPrismaticEmpress::new, MobCategory.MONSTER)
                    .sized(1f, 3.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "prismatic_empress").toString()));

    public static final RegistryObject<EntityType<EntityGildedHuntress>> GILDED_HUNTRESS = ENTITIES.register("gilded_huntress",
            () -> EntityType.Builder.of(EntityGildedHuntress::new, MobCategory.MONSTER)
                    .sized(1f, 3.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "gilded_huntress").toString()));

    public static final RegistryObject<EntityType<EntityMirroredGuardian>> MIRRORED_GUARDIAN = ENTITIES.register("mirrored_guardian",
            () -> EntityType.Builder.of(EntityMirroredGuardian::new, MobCategory.MONSTER)
                    .sized(1f, 3.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "mirrored_guardian").toString()));

    public static final RegistryObject<EntityType<EntityIridescentPaladin>> IRIDESCENT_PALADIN = ENTITIES.register("iridescent_paladin",
            () -> EntityType.Builder.of(EntityIridescentPaladin::new, MobCategory.MONSTER)
                    .sized(1f, 3.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "iridescent_paladin").toString()));

    public static final RegistryObject<EntityType<EntityCrawler>> CRAWLER = ENTITIES.register("crawler",
            () -> EntityType.Builder.of(EntityCrawler::new, MobCategory.CREATURE)
                    .sized(1f, 1.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "crawler").toString()));

    public static final RegistryObject<EntityType<EntityAbomination>> ABOMINATION = ENTITIES.register("abomination",
            () -> EntityType.Builder.of(EntityAbomination::new, MobCategory.CREATURE)
                    .sized(1f, 1.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "abomination").toString()));

    public static final RegistryObject<EntityType<EntityShambler>> SHAMBLER = ENTITIES.register("shambler",
            () -> EntityType.Builder.of(EntityShambler::new, MobCategory.CREATURE)
                    .sized(1f, 1.5f) // Hitbox Size
                    .build(new ResourceLocation(Gempire.MODID, "shambler").toString()));
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

    public static final RegistryObject<EntityType<ElectrokinesisLightning>> ELECTROKINESIS_LIGHTNING = ENTITIES.register("electrokinesis_lightning", () -> {
        return EntityType.Builder.<ElectrokinesisLightning>of(ElectrokinesisLightning::new, MobCategory.MISC)
                .noSave().sized(0.0F, 0.0F).clientTrackingRange(16)
                .updateInterval(Integer.MAX_VALUE)
                .build(new ResourceLocation(Gempire.MODID, "electrokinesis_lightning").toString());
    });
    public static void registerCruxes() {
        InjectorContainer.primer.add(Items.GOLDEN_APPLE);
        InjectorContainer.primer.add(Items.GOLD_BLOCK);
        InjectorContainer.primer.add(Items.BLAZE_ROD);
        InjectorContainer.primer.add(Items.BLUE_ICE);
        InjectorContainer.primer.add(Items.EXPERIENCE_BOTTLE);
        InjectorContainer.primer.add(Items.NETHER_STAR);
        InjectorContainer.primer.add(Items.NETHERITE_INGOT);
        InjectorContainer.primer.add(Items.FIRE_CHARGE);
        InjectorContainer.primer.add(Items.DRAGON_BREATH);
        InjectorContainer.primer.add(Items.GHAST_TEAR);
        InjectorContainer.primer.add(Items.NETHERITE_SWORD);
        InjectorContainer.primer.add(Items.BONE_BLOCK);
        InjectorContainer.primer.add(Items.NAUTILUS_SHELL);
        InjectorContainer.primer.add(Items.WITHER_ROSE);
        InjectorContainer.primer.add(Items.ENDER_EYE);
        InjectorContainer.primer.add(Items.HEART_OF_THE_SEA);
        InjectorContainer.primer.add(Items.ENCHANTED_GOLDEN_APPLE);
        InjectorContainer.primer.add(Items.EMERALD_BLOCK);
        InjectorContainer.primer.add(Items.WITHER_SKELETON_SKULL);
        InjectorContainer.primer.add(Items.PHANTOM_MEMBRANE);
        InjectorContainer.primer.add(Items.END_CRYSTAL);
        InjectorContainer.primer.add(Items.NETHER_WART);
        InjectorContainer.primer.add(Items.TOTEM_OF_UNDYING);
        InjectorContainer.primer.add(ModItems.GILDED_LAPIS.get());
        InjectorContainer.primer.add(ModItems.PRIME_BOOST.get());
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
        AddonHandler.VANILLA_GEMS.add("emerald");
        AddonHandler.VANILLA_GEMS.add("lapis");
        AddonHandler.VANILLA_GEMS.add("larimar");
        AddonHandler.VANILLA_GEMS.add("morganite");
        AddonHandler.VANILLA_GEMS.add("peridot");
        AddonHandler.VANILLA_GEMS.add("garnet");
        AddonHandler.VANILLA_GEMS.add("rutile");
        AddonHandler.VANILLA_GEMS.add("spinel");
        AddonHandler.VANILLA_GEMS.add("tourmaline");
    }
}
