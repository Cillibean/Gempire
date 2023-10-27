package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelShambler;
import com.gempire.client.entity.model.ModelWhiteMonster;
import com.gempire.entities.other.EntityShambler;
import com.gempire.entities.other.EntityWhiteMonster;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderWhiteMonster extends GeoEntityRenderer<EntityWhiteMonster> {

    public RenderWhiteMonster(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelWhiteMonster());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityWhiteMonster animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
