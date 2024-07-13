package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelIridescentPaladin;
import com.gempire.entities.bosses.prism.EntityIridescentPaladin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderIridescentPaladin extends GeoEntityRenderer<EntityIridescentPaladin> {

    public RenderIridescentPaladin(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelIridescentPaladin());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityIridescentPaladin animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
