package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.entities.TestEntity;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.EntitySapphire;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.systems.injection.Crux;
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
    public static HashMap<String, ArrayList<Crux>> CRUXTOGEM = new HashMap<>();
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

    public static void registerCruxes(){
        ArrayList<Crux> defaultCruxes = new ArrayList<>();
        defaultCruxes.add(new Crux(Blocks.STONE, 8, CruxType.MINERAL));
        ArrayList<Crux> rubyCruxes = new ArrayList<>();
        float rubyTemperature = 1.5f;
        rubyCruxes.add(new Crux(Blocks.MAGMA_BLOCK, 5, CruxType.INORGANIC, rubyTemperature));
        rubyCruxes.add(new Crux(Blocks.IRON_ORE, 3, CruxType.MINERAL));
        rubyCruxes.add(new Crux(Blocks.NETHERRACK, 1, CruxType.INORGANIC, rubyTemperature));
        rubyCruxes.add(new Crux(Blocks.NETHERITE_BLOCK, 10, CruxType.INORGANIC, rubyTemperature));
        rubyCruxes.add(new Crux(Blocks.NETHER_GOLD_ORE, 4, CruxType.MINERAL, rubyTemperature));
        rubyCruxes.add(new Crux(Blocks.ANCIENT_DEBRIS, 7, CruxType.MINERAL, rubyTemperature));
        EntityRuby.CRUXES.addAll(defaultCruxes);
        EntityRuby.CRUXES.addAll(rubyCruxes);
        ModEntities.CRUXTOGEM.put("ruby", EntityRuby.CRUXES);
        ArrayList<Crux> sapphireCruxes = new ArrayList<>();
        float sapphireTemperature = .5f;
        sapphireCruxes.add(new Crux(Blocks.ICE, 5, CruxType.INORGANIC, sapphireTemperature));
        sapphireCruxes.add(new Crux(Blocks.PACKED_ICE, 5, CruxType.INORGANIC, sapphireTemperature));
        sapphireCruxes.add(new Crux(Blocks.SNOW_BLOCK, 5, CruxType.INORGANIC, sapphireTemperature));
        sapphireCruxes.add(new Crux(Blocks.IRON_ORE, 3, CruxType.MINERAL, sapphireTemperature));
        sapphireCruxes.add(new Crux(Blocks.DIAMOND_BLOCK, 10, CruxType.INORGANIC, sapphireTemperature));
        EntitySapphire.CRUXES.addAll(defaultCruxes);
        EntitySapphire.CRUXES.addAll(sapphireCruxes);
        ModEntities.CRUXTOGEM.put("sapphire", EntitySapphire.CRUXES);
    }
}
