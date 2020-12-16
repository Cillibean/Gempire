package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.entities.TestEntity;
import com.gempire.entities.gems.EntityGem;
import com.gempire.entities.gems.EntityPebble;
import net.minecraft.client.tutorial.Tutorial;
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
}
