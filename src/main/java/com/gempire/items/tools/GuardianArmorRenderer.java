package com.gempire.items.tools;

import com.gempire.Gempire;
import com.gempire.client.entity.armour.GuardianArmourModel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GuardianArmorRenderer extends GeoArmorRenderer<GuardianArmorItem> {
    public GuardianArmorRenderer() {
        super(new GuardianArmourModel());
    }
}
