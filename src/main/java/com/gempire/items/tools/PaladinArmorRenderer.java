package com.gempire.items.tools;

import com.gempire.Gempire;
import com.gempire.client.entity.armour.PaladinArmourModel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class PaladinArmorRenderer extends GeoArmorRenderer<PaladinArmorItem> {
    public PaladinArmorRenderer() {
        super(new PaladinArmourModel()); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
