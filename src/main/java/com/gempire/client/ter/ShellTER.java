package com.gempire.client.ter;

import com.gempire.blocks.ShellBlock;
import com.gempire.tileentities.ShellTE;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class ShellTER extends TileEntityRenderer<ShellTE> {
    public Minecraft mc = Minecraft.getInstance();

    public ShellTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(ShellTE te, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if(te.getPearlItem() == ItemStack.EMPTY || te.getPearlItem().getItem().equals(Items.AIR)){
            return;
        }
        ClientPlayerEntity player = mc.player;
        int lightLevel = getLightLevel(te.getWorld(), te.getPos());
        renderItem(te.getPearlItem(), new double[]{.5D, .75D, .5D}, Vector3f.YP.rotationDegrees(rotationFromFacing(te.getBlockState().get(ShellBlock.FACING))),
                matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, .8f);
    }

    public void renderItem(ItemStack stack, double[] translation, Quaternion rotation, MatrixStack matrixStack, IRenderTypeBuffer buffer,
                           float partialTicks, int combinedOverley, int lightLevel, float scale){
        matrixStack.push();
        matrixStack.translate(translation[0], translation[1], translation[2]);
        matrixStack.rotate(rotation);
        matrixStack.scale(scale, scale, scale);
        IBakedModel model = mc.getItemRenderer().getItemModelWithOverrides(stack, null, null);
        mc.getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, true, matrixStack, buffer, lightLevel, combinedOverley, model);
        matrixStack.pop();
    }

    public int getLightLevel(World world, BlockPos pos){
        int blockLight = world.getLightFor(LightType.BLOCK, pos);
        int sLight = world.getLightFor(LightType.SKY, pos);
        return LightTexture.packLight(blockLight, sLight);
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
