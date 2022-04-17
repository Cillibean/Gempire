package com.gempire.entities;

import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

public class TestEntity extends Animal {

    public TestEntity(EntityType<? extends Animal> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Nullable
    @Override
    public AgableMob getBreedOffspring(ServerLevel p_241840_1_, AgableMob p_241840_2_) {
        return null;
    }
}
