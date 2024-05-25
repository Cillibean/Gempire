package com.gempire.client.block.render;

import com.gempire.client.block.model.PinkAltarModel;
import com.gempire.tileentities.PinkAltarTE;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PinkAltarRenderer extends GeoBlockRenderer<PinkAltarTE> {
    public PinkAltarRenderer(BlockEntityRendererProvider.Context context) {
        super(new PinkAltarModel());
    }
}
