package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelAbomination;
import com.gempire.client.entity.model.ModelMantaShark;
import com.gempire.entities.other.EntityAbomination;
import com.gempire.entities.other.EntityMantaShark;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderMantaShark extends GeoEntityRenderer<EntityMantaShark> {

    public RenderMantaShark(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelMantaShark());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityMantaShark animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/mantashark/base.png");
    }
}
