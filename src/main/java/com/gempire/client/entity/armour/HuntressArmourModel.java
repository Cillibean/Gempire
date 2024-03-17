package com.gempire.client.entity.armour;

import com.gempire.Gempire;
import com.gempire.items.tools.HuntressArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HuntressArmourModel extends GeoModel<HuntressArmorItem> {
    @Override
    public ResourceLocation getModelResource(HuntressArmorItem object) {
        return new ResourceLocation(Gempire.MODID, "geo/huntress_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HuntressArmorItem object) {
        return new ResourceLocation(Gempire.MODID, "textures/armor/huntress_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HuntressArmorItem animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/huntress_armor.animation.json");
    }
}
