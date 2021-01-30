package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.entities.TestEntity;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.entities.gems.starter.EntityMica;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.entities.gems.starter.EntityShale;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Gempire.MODID);

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
}
