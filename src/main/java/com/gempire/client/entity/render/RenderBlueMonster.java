package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelBlueMonster;
import com.gempire.entities.other.EntityBlueMonster;
import com.gempire.entities.other.EntityWhiteMonster;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderBlueMonster extends GeoEntityRenderer<EntityBlueMonster> {

    public RenderBlueMonster(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelBlueMonster());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityBlueMonster animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
