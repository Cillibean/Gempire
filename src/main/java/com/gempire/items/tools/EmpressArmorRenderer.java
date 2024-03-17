package com.gempire.items.tools;

import com.gempire.Gempire;
import com.gempire.client.entity.armour.EmpressArmourModel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EmpressArmorRenderer extends GeoArmorRenderer<EmpressArmorItem> {
    public EmpressArmorRenderer() {
        super(new EmpressArmourModel()); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
