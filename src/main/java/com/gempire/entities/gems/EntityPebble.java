package com.gempire.entities.gems;

import com.gempire.init.ModItems;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class EntityPebble extends EntityGem {

    public EntityPebble(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        this.droppedGemstone = ModItems.PEBBLE_GEM.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }
}
