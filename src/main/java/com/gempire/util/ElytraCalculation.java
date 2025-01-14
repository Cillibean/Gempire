package com.gempire.util;

import com.gempire.entities.bases.EntityFlyingVehicleGem;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ElytraCalculation {
    public static void calculateGlide(EntityFlyingVehicleGem entity, Vec3 lookVec) {
        Vec3 vec3 = entity.getDeltaMovement();
        double d0 = 0.08D;
        AttributeInstance gravity = entity.getAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
        //d0 = gravity.getValue();

        if (vec3.y > -0.5D) {
            entity.fallDistance = 1.0F;
        }

        Vec3 vec31 = lookVec;
        float f = entity.getXRot() * ((float)Math.PI / 180F);
        double d1 = Math.sqrt(vec31.x * vec31.x + vec31.z * vec31.z);
        double d3 = vec3.horizontalDistance();
        double d4 = vec31.length();
        double d5 = Math.cos((double)f);
        d5 = d5 * d5 * Math.min(1.0D, d4 / 0.4D);
        vec3 = entity.getDeltaMovement().add(0.0D, d0 * (-1.0D + d5 * 0.75D), 0.0D);
        if (vec3.y < 0.0D && d1 > 0.0D) {
            double d6 = vec3.y * -0.1D * d5;
            vec3 = vec3.add(vec31.x * d6 / d1, d6, vec31.z * d6 / d1);
        }

        if (f < 0.0F && d1 > 0.0D) {
            double d10 = d3 * (double)(-Mth.sin(f)) * 0.04D;
            vec3 = vec3.add(-vec31.x * d10 / d1, d10 * 3.2D, -vec31.z * d10 / d1);
        }

        if (d1 > 0.0D) {
            vec3 = vec3.add((vec31.x / d1 * d3 - vec3.x) * 0.1D, 0.0D, (vec31.z / d1 * d3 - vec3.z) * 0.1D);
        }

        entity.setDeltaMovement(vec3.multiply((double)0.99F, (double)0.98F, (double)0.99F));
        // If the entity is riding a rocket for the speed boost, do not apply the multiplier.

        boolean dashflag = isFlightBoosting(entity);

        if (dashflag == true) {
            entity.move(MoverType.SELF, entity.getDeltaMovement().multiply(1.20,1.0,1.20));
        } else {
            entity.move(MoverType.SELF, entity.getDeltaMovement().multiply(2.20,1.0,2.20));
            //entity.move(MoverType.SELF, Vec3.ZERO);
        }

    }

    public static ItemStack createLapisFirework() {

        CompoundTag fireworksTag = new CompoundTag();
        fireworksTag.putInt("Flight", 1);

        ItemStack lapisFirework = new ItemStack(Items.FIREWORK_ROCKET);
        CompoundTag fireWorkItemTag = lapisFirework.getOrCreateTag();
        fireWorkItemTag.put("Fireworks",fireworksTag);


        return lapisFirework;
    }

    public static boolean isFlightBoosting(EntityFlyingVehicleGem entity) {
        AABB box = entity.getBoundingBox().inflate(1.5);
        List<Entity> nearby = Minecraft.getInstance().level.getEntities(entity, box);
        // iterate through the entities to see if there's a firework rocket.
        for (Entity listEntity : nearby) {
            if (listEntity != null) {
                if (listEntity instanceof FireworkRocketEntity rocket) {
                    return true;
                }
            }
        }

        return false;
    }
}