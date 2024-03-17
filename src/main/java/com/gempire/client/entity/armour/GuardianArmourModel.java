package com.gempire.client.entity.armour;

import com.gempire.Gempire;
import com.gempire.items.tools.GuardianArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GuardianArmourModel extends GeoModel<GuardianArmorItem> {
    @Override
    public ResourceLocation getModelResource(GuardianArmorItem object) {
        return new ResourceLocation(Gempire.MODID, "geo/guardian_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GuardianArmorItem object) {
        return new ResourceLocation(Gempire.MODID, "textures/armor/guardian_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GuardianArmorItem animatable) {
        return new ResourceLocation(Gempire.MODID, "animations/guardian_armor.animation.json");
    }
}
