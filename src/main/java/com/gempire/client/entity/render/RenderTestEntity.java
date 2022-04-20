package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelSpodumene;
import com.gempire.client.entity.model.ModelTest;
import com.gempire.entities.TestEntity;
import com.gempire.entities.gems.EntitySpodumene;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;

public class RenderTestEntity extends MobRenderer<TestEntity, ModelTest<TestEntity>> {

    public RenderTestEntity(EntityRendererProvider.Context renderManagerIn, ModelTest<TestEntity> baseModel) {
        super(renderManagerIn, baseModel, .25f);
    }

    @Override
    public ResourceLocation getTextureLocation(TestEntity entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/test/test.png");
    }
}
