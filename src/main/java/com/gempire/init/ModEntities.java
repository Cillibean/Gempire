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
        EntityRuby.setCruxes();
        EntitySapphire.setCruxes();
        EntityPebble.setCruxes();
        EntityMica.setCruxes();
        EntityShale.setCruxes();
        ModEntities.CRUXTOGEM.put("ruby", EntityRuby.RUBY_CRUXES);
        ModEntities.CRUXTOGEM.put("sapphire", EntitySapphire.SAPPHIRE_CRUXES);
        ModEntities.CRUXTOGEM.put("pebble", EntityPebble.PEBBLE_CRUXES);
        ModEntities.CRUXTOGEM.put("mica", EntityMica.MICA_CRUXES);
        ModEntities.CRUXTOGEM.put("shale", EntityShale.SHALE_CRUXES);
        for(String name : GemFormation.POSSIBLE_GEMS){
            System.out.println("GEM DEBUG - Number of cruxes " + name + " has: " + ModEntities.CRUXTOGEM.get(name).size());
        }
    }
}
