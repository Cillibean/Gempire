package com.gempire.entities.ai;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAreaAbility;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.bases.EntityGem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityAIAreaAbility extends Goal {
    public EntityGem gem;
    public World world;
    public double speed;
    boolean flag1, flag2, flag3, flagOwner, flagFocus, flagGem, flagPlayer;
    List<LivingEntity> entities = null;
    ArrayList<Ability> abilities = null;

    public EntityAIAreaAbility(EntityGem entityIn, double speedIn) {
        this.gem = entityIn;
        this.speed = speedIn;
        this.world = entityIn.world;
    }

    @Override
    public boolean shouldExecute() {
        List<LivingEntity> entity = this.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.gem.getPosX(), this.gem.getPosY(), this.gem.getPosZ(), this.gem.getPosX() + 1, this.gem.getPosY() + 1 , this.gem.getPosZ() + 1)
                        .grow(16, this.world.getHeight(), 16));
        this.entities = entity;
        this.abilities = this.gem.getAbilityPowers();
        return this.gem.usesAreaAbilities() && this.gem.ticksExisted % 100 == 0;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.abilities != null && this.entities != null;
    }

    @Override
    public void startExecuting(){
        for(Ability ability : this.gem.getAbilityPowers()){
            this.flag1 = ability instanceof IEffectAbility;
            this.flag2 = ability instanceof IAreaAbility;
            this.flag3 = ability instanceof IViolentAbility;
            for(LivingEntity entity : this.entities){
                this.flagOwner = this.gem.isOwner(entity);
                this.flagGem = entity instanceof EntityGem;
                this.flagPlayer = entity instanceof PlayerEntity;
                this.flagFocus = this.gem.focusCheck();
                IEffectAbility effectAbility = null;
                IAreaAbility areaAbility = null;
                if (this.flagFocus && entity != this.gem && !this.flag3) {
                    //Run through effect abilities - Check if they apply to the player only - Apply for each entity
                    if (this.flag1) {
                        effectAbility = (IEffectAbility) ability;
                        if (effectAbility.playerOnly()) {
                            if (this.flagPlayer) {
                                if(this.flagOwner) {
                                    if(effectAbility.hasMultipleEffects()){
                                        for(EffectInstance effect : effectAbility.effects()){
                                            entity.addPotionEffect(effect);
                                        }
                                    }
                                    else {
                                        entity.addPotionEffect(effectAbility.effect());
                                    }
                                }
                            }
                        }
                        else if(effectAbility.gemAndPlayerOnly()){
                            if(this.flagGem){
                                if(effectAbility.hasMultipleEffects()){
                                    for(EffectInstance effect : effectAbility.effects()){
                                        entity.addPotionEffect(effect);
                                    }
                                }
                                else {
                                    entity.addPotionEffect(effectAbility.effect());
                                }
                            }
                            if(this.flagPlayer){
                                if(this.flagOwner){
                                    if(effectAbility.hasMultipleEffects()){
                                        for(EffectInstance effect : effectAbility.effects()){
                                            entity.addPotionEffect(effect);
                                        }
                                    }
                                    else {
                                        entity.addPotionEffect(effectAbility.effect());
                                    }
                                }
                            }
                        }
                        else {
                            if(effectAbility.hasMultipleEffects()){
                                for(EffectInstance effect : effectAbility.effects()){
                                    entity.addPotionEffect(effect);
                                }
                            }
                            else {
                                entity.addPotionEffect(effectAbility.effect());
                            }
                        }
                    }
                    //Run through area abilities - Apply for each entity
                    if (this.flag2) {
                        areaAbility = (IAreaAbility) ability;
                        areaAbility.AOeffect(entity, this.gem.OWNERS);
                    }
                }
            }
        }
        super.startExecuting();
    }

    @Override
    public void resetTask() {
        this.entities = null;
    }
}
