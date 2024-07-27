package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelFuchsiaPaladin;
import com.gempire.entities.bosses.base.EntityFuchsiaPaladin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderFuchsiaPaladin extends GeoEntityRenderer<EntityFuchsiaPaladin> {

    public RenderFuchsiaPaladin(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelFuchsiaPaladin());
        this.shadowRadius = 2.1F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityFuchsiaPaladin animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/boss/paladin/fuchsia/base.png");
    }
}
