package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelCrystalDeer;
import com.gempire.client.entity.model.ModelOverseer;
import com.gempire.entities.other.EntityCrystalDeer;
import com.gempire.entities.other.EntityOverseer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderOverseer extends GeoEntityRenderer<EntityOverseer> {

    public RenderOverseer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelOverseer());
        this.shadowRadius = 0.3F;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityOverseer animatable) {
        return new ResourceLocation(Gempire.MODID, "textures/entity/overseer/overseer.png");
    }
}