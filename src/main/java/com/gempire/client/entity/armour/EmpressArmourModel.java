package com.gempire.client.entity.armour;

import com.gempire.Gempire;
import com.gempire.items.tools.EmpressArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpressArmourModel extends GeoModel<EmpressArmorItem> {
    @Override
    public ResourceLocation getModelResource(EmpressArmorItem object) {
        return new ResourceLocation(Gempire.MODID, "geo/empress_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpressArmorItem object) {
        return new ResourceLocation(Gempire.MODID, "textures/armor/empress_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpressArmorItem animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/empress_armor.animation.json");
    }
}
