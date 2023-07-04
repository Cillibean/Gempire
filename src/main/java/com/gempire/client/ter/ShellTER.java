package com.gempire.client.ter;

import com.gempire.blocks.machine.ShellBlock;
import com.gempire.tileentities.ShellTE;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.Level;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ShellTER implements BlockEntityRenderer<ShellTE> {
    public Minecraft mc = Minecraft.getInstance();
    private final BlockEntityRendererProvider.Context context;

    public ShellTER(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(ShellTE te, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if(te.getPearlItem() == ItemStack.EMPTY || te.getPearlItem().getItem().equals(Items.AIR)){
            return;
        }
        LocalPlayer player = mc.player;
        int lightLevel = getLightLevel(te.getLevel(), te.getBlockPos());
        renderItem(te.getPearlItem(), new double[]{.5D, .75D, .5D}, Axis.YP.rotationDegrees(rotationFromFacing(te.getBlockState().getValue(ShellBlock.FACING))),
                matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, .8f);
    }

    public void renderItem(ItemStack stack, double[] translation, Quaternionf rotation, PoseStack matrixStack, MultiBufferSource buffer,
                           float partialTicks, int combinedOverley, int lightLevel, float scale){
        matrixStack.pushPose();
        matrixStack.translate(translation[0], translation[1], translation[2]);
        matrixStack.mulPose(rotation);
        matrixStack.scale(scale, scale, scale);
        BakedModel model = mc.getItemRenderer().getModel(stack, null, null, 1);
        mc.getItemRenderer().render(stack, ItemTransforms.TransformType.GROUND, true, matrixStack, buffer, lightLevel, combinedOverley, model);
        matrixStack.popPose();
    }

    public int getLightLevel(Level world, BlockPos pos){
        int blockLight = world.getBrightness(LightLayer.BLOCK, pos);
        int sLight = world.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, sLight);
    }

    public int rotationFromFacing(Direction facing) {
        switch (facing){
            case SOUTH:
                return 0;
            case EAST:
                return 90;
            case NORTH:
                return 180;
            default:
                return 270;
        }
    }
}
