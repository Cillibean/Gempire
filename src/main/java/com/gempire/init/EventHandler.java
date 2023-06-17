package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.entities.abilities.AbilityAbundance;
import com.gempire.entities.abilities.AbilityLootmaster;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.ai.EntityAIFollowSpinel;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gempire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {

    @SubscribeEvent
    public void OnEntitySpawn(EntityJoinLevelEvent event){
        if(event.getEntity() instanceof LivingEntity) {
            if (event.getEntity().getClassification(true) == MobCategory.MONSTER) {
                Mob entity = (Mob) event.getEntity();
                entity.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(entity, EntityGem.class, 1, false, false, (p_234199_0_) -> {
                    return !(p_234199_0_ instanceof EntityAgate);
                }));
                if (entity instanceof PathfinderMob) {
                    entity.goalSelector.addGoal(1, new AvoidEntityGoal<>((PathfinderMob) entity, EntityAgate.class, 6.0F, 1.0D, 1.2D));
                }
            }
            else if (event.getEntity().getClassification(true) == MobCategory.CREATURE)
            {
                if (event.getEntity() instanceof EntityGem || event.getEntity() instanceof TamableAnimal)
                {
                }
                else
                {
                    Mob entity = (Mob) event.getEntity();
                    entity.goalSelector.addGoal(3, new EntityAIFollowSpinel((Mob) event.getEntity(), 1.1D));
                }
            }
        }
    }

    @SubscribeEvent
    public void OnLootmaster(LootingLevelEvent event) {
        if (event.getEntity().getLastHurtByMob() instanceof EntityGarnet) {
            EntityGarnet garnet = ((EntityGarnet) event.getEntity().getLastHurtByMob());
            for(Ability ability : garnet.getAbilityPowers()){
                if(ability instanceof AbilityLootmaster){
                    if (garnet.isPrimary())
                        event.setLootingLevel(4);
                    else if (!garnet.isDefective())
                        event.setLootingLevel(2);
                }
            }
        }
    }
}
