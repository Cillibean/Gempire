package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IRangedAbility;
import com.gempire.entities.abilities.interfaces.ITaskAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.projectiles.IceShardEntity;
import com.gempire.util.Abilities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;

public class AbilityCryokinesis extends Ability implements IRangedAbility, IViolentAbility, ITaskAbility {

    public AbilityCryokinesis() {
        this.ability = Abilities.CRYOKINESIS;
    }

    @Override
    public void attack(LivingEntity target, float distanceFactor) {
        SnowballEntity snowballentity = new SnowballEntity(this.holder.world, this.holder);
        double d0 = target.getPosYEye() - (double) 1.1F;
        double d1 = target.getPosX() - this.holder.getPosX();
        double d2 = d0 - snowballentity.getPosY();
        double d3 = target.getPosZ() - this.holder.getPosZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        snowballentity.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
        this.holder.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.holder.getRNG().nextFloat() * 0.4F + 0.8F));
        this.holder.world.addEntity(snowballentity);
    }

    @Override
    public Goal goal() {
        return new RangedAttackGoal((IRangedAttackMob) this.holder, 1.25D, 20, 10.0F);
    }

    @Override
    public boolean targetTask() {
        return true;
    }
}
