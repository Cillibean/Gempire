package com.gempire.client.block.render;

import com.gempire.client.block.model.BlueAltarModel;
import com.gempire.client.block.model.WhiteAltarModel;
import com.gempire.tileentities.BlueAltarTE;
import com.gempire.tileentities.WhiteAltarTE;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BlueAltarRenderer extends GeoBlockRenderer<BlueAltarTE> {

    public BlueAltarRenderer(BlockEntityRendererProvider.Context m) {
        super(new BlueAltarModel());
    }
}
