package com.gempire.client.entity.render;

import com.gempire.Gempire;
import com.gempire.client.entity.model.ModelTest;
import com.gempire.entities.TestEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.client.renderer.entity.model.ShulkerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;

public class RenderTestEntity extends MobRenderer<TestEntity, ModelTest<TestEntity>> {

    public RenderTestEntity(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTest<>(), .25f);
    }

    @Override
    public ResourceLocation getEntityTexture(TestEntity entity) {
        return new ResourceLocation(Gempire.MODID+":textures/entity/test/test.png");
    }
}
