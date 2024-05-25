package com.gempire.client.block.render;

import com.gempire.client.block.model.PinkAltarModel;
import com.gempire.client.block.model.WhiteAltarModel;
import com.gempire.tileentities.PinkAltarTE;
import com.gempire.tileentities.WhiteAltarTE;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class WhiteAltarRenderer extends GeoBlockRenderer<WhiteAltarTE> {

    public WhiteAltarRenderer(BlockEntityRendererProvider.Context m) {
        super(new WhiteAltarModel());
    }
}
