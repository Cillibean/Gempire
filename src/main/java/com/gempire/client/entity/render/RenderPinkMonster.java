package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelPinkMonster;
import com.gempire.entities.other.EntityPinkMonster;
import com.gempire.entities.other.EntityWhiteMonster;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderPinkMonster extends GeoEntityRenderer<EntityPinkMonster> {

    public RenderPinkMonster(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelPinkMonster());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityPinkMonster animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
