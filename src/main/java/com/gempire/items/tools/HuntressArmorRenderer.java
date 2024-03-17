package com.gempire.items.tools;

import com.gempire.Gempire;
import com.gempire.client.entity.armour.HuntressArmourModel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class HuntressArmorRenderer extends GeoArmorRenderer<HuntressArmorItem> {
    public HuntressArmorRenderer() {
        super(new HuntressArmourModel());
    }
}
