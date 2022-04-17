package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityAgate;
import com.gempire.items.ItemGem;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gempire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {

    @SubscribeEvent
    public void OnEntitySpawn(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof LivingEntity) {
            if (event.getEntity() instanceof Monster) {
                Monster entity = (Monster) event.getEntity();
                entity.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(entity, EntityGem.class, 1, false, false, (p_234199_0_) -> {
                    return !(p_234199_0_ instanceof EntityAgate);
                }));
                entity.goalSelector.addGoal(1, new AvoidEntityGoal<>(entity, EntityAgate.class, 6.0F, 1.0D, 1.2D));
            }
        }
    }
}
