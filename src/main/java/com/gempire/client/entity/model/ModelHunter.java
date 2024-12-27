package com.gempire.client.entity.model;

import com.gempire.Gempire;
import com.gempire.entities.other.EntityHunter;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import software.bernie.geckolib.model.GeoModel;

public class ModelHunter extends GeoModel<EntityHunter> {
    @Override
    public ResourceLocation getModelResource(EntityHunter object) {
        return new ResourceLocation(Gempire.MODID, "geo/entity/hunter.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityHunter object) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/hunter/hunter.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityHunter animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/entity/hunter.animation.json");
    }
}
