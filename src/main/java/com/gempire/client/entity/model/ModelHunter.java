package com.gempire.client.entity.model;

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

public class ModelHunter<T extends Mob & RangedAttackMob> extends HumanoidModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("gempire", "hunter"), "main");
    public ModelHunter(ModelPart p_170941_) {
        super(p_170941_);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition $$0 = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition $$1 = $$0.getRoot();
        $$1.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
        $$1.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
        $$1.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
        $$1.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
        return LayerDefinition.create($$0, 64, 32);
    }

    public void prepareMobModel(T p_103793_, float p_103794_, float p_103795_, float p_103796_) {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;
        ItemStack $$4 = p_103793_.getItemInHand(InteractionHand.MAIN_HAND);
        if ($$4.is(Items.BOW) && p_103793_.isAggressive()) {
            if (p_103793_.getMainArm() == HumanoidArm.RIGHT) {
                this.rightArmPose = ArmPose.BOW_AND_ARROW;
            } else {
                this.leftArmPose = ArmPose.BOW_AND_ARROW;
            }
        }

        super.prepareMobModel(p_103793_, p_103794_, p_103795_, p_103796_);
    }

    public void setupAnim(T p_103798_, float p_103799_, float p_103800_, float p_103801_, float p_103802_, float p_103803_) {
        super.setupAnim(p_103798_, p_103799_, p_103800_, p_103801_, p_103802_, p_103803_);
        ItemStack $$6 = p_103798_.getMainHandItem();
        if (p_103798_.isAggressive() && ($$6.isEmpty() || !$$6.is(Items.BOW))) {
            float $$7 = Mth.sin(this.attackTime * 3.1415927F);
            float $$8 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * 3.1415927F);
            this.rightArm.zRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightArm.yRot = -(0.1F - $$7 * 0.6F);
            this.leftArm.yRot = 0.1F - $$7 * 0.6F;
            this.rightArm.xRot = -1.5707964F;
            this.leftArm.xRot = -1.5707964F;
            ModelPart var10000 = this.rightArm;
            var10000.xRot -= $$7 * 1.2F - $$8 * 0.4F;
            var10000 = this.leftArm;
            var10000.xRot -= $$7 * 1.2F - $$8 * 0.4F;
            AnimationUtils.bobArms(this.rightArm, this.leftArm, p_103801_);
        }

    }

    public void translateToHand(HumanoidArm p_103778_, PoseStack p_103779_) {
        float $$2 = p_103778_ == HumanoidArm.RIGHT ? 1.0F : -1.0F;
        ModelPart $$3 = this.getArm(p_103778_);
        $$3.x += $$2;
        $$3.translateAndRotate(p_103779_);
        $$3.x -= $$2;
    }
}
