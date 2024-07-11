package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelMantaShark;
import com.gempire.client.entity.model.ModelOpalMantaShark;
import com.gempire.entities.other.EntityMantaShark;
import com.gempire.entities.other.EntityOpalMantaShark;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderOpalMantaShark extends GeoEntityRenderer<EntityOpalMantaShark> {

    public RenderOpalMantaShark(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelOpalMantaShark());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityOpalMantaShark animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/mantashark/opal.png");
    }
}
