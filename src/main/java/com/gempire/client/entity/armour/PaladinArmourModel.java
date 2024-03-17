package com.gempire.client.entity.armour;

import com.gempire.Gempire;
import com.gempire.items.tools.PaladinArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PaladinArmourModel extends GeoModel<PaladinArmorItem> {
    @Override
    public ResourceLocation getModelResource(PaladinArmorItem object) {
        return new ResourceLocation(Gempire.MODID, "geo/paladin_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PaladinArmorItem object) {
        return new ResourceLocation(Gempire.MODID, "textures/armor/paladin_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PaladinArmorItem animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/paladin_armor.animation.json");
    }
}
