package com.gempire.client.block.render;

import com.gempire.client.block.model.PinkAltarModel;
import com.gempire.client.block.model.YellowAltarModel;
import com.gempire.tileentities.PinkAltarTE;
import com.gempire.tileentities.YellowAltarTE;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class YellowAltarRenderer extends GeoBlockRenderer<YellowAltarTE> {
    public YellowAltarRenderer(BlockEntityRendererProvider.Context context) {
        super(new YellowAltarModel());
    }
}
