package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelYellowMonster;
import com.gempire.entities.other.EntityWhiteMonster;
import com.gempire.entities.other.EntityYellowMonster;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderYellowMonster extends GeoEntityRenderer<EntityYellowMonster> {

    public RenderYellowMonster(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelYellowMonster());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityYellowMonster animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/clods/shambler/shambler.png");
    }
}
