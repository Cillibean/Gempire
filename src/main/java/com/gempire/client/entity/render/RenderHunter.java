package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelGildedHuntress;
import com.gempire.client.entity.model.ModelHunter;
import com.gempire.entities.bosses.prism.EntityGildedHuntress;
import com.gempire.entities.other.EntityHunter;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import software.bernie.example.client.renderer.entity.GremlinRenderer;
import software.bernie.example.entity.DynamicExampleEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;
import software.bernie.geckolib.renderer.layer.ItemArmorGeoLayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderHunter extends GeoEntityRenderer<EntityHunter> {

    protected ItemStack mainHandItem;
    protected ItemStack offhandItem;
    public RenderHunter(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelHunter());
        this.shadowRadius = 0.3F;
        this.addRenderLayer(new BlockAndItemGeoLayer<EntityHunter>(this) {
            @Nullable
            protected ItemStack getStackForBone(GeoBone bone, EntityHunter animatable) {
                ItemStack var10000;
                switch (bone.getName()) {
                    case "leftarm":
                        var10000 = animatable.isLeftHanded() ? RenderHunter.this.mainHandItem : RenderHunter.this.offhandItem;
                        break;
                    case "rightarm":
                        var10000 = animatable.isLeftHanded() ? RenderHunter.this.offhandItem : RenderHunter.this.mainHandItem;
                        break;
                    default:
                        var10000 = null;
                }

                return var10000;
            }

            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, EntityHunter animatable) {
                ItemDisplayContext var10000;
                switch (bone.getName()) {
                    case "leftarm":
                    case "rightarm":
                        var10000 = ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
                        break;
                    default:
                        var10000 = ItemDisplayContext.NONE;
                }

                return var10000;
            }

            protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, EntityHunter animatable, MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
                if (stack == RenderHunter.this.mainHandItem) {
                    poseStack.translate(0.0, -1.6, 0.5);
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                    if (stack.getItem() instanceof ShieldItem) {
                        poseStack.translate(0.0, 0.125, -0.25);
                    }
                } else if (stack == RenderHunter.this.offhandItem) {
                    poseStack.translate(0.0, -1.6, 0.5);
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                    if (stack.getItem() instanceof ShieldItem) {
                        poseStack.translate(0.0, 0.125, 0.25);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
                    }
                }

                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });
    }


    @Override
    public ResourceLocation getTextureLocation(EntityHunter animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/hunter/hunter.png");
    }

    public void preRender(PoseStack poseStack, EntityHunter animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        this.mainHandItem = animatable.getMainHandItem();
        this.offhandItem = animatable.getOffhandItem();
    }
}
