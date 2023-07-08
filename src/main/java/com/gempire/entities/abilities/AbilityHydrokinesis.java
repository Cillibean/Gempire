package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEmotionalAbility;
import com.gempire.entities.abilities.interfaces.IRangedAbility;
import com.gempire.entities.abilities.interfaces.ITaskAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.projectiles.WaterOrbEntity;
import com.gempire.init.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;

import java.util.Random;

public class AbilityHydrokinesis extends Ability implements IRangedAbility, IViolentAbility, ITaskAbility, IEmotionalAbility {

    public AbilityHydrokinesis() {
        super(16, 1);
    }

    @Override
    public void attack(LivingEntity target, float distanceFactor) {
        WaterOrbEntity waterOrb = new WaterOrbEntity(this.holder.level, this.holder);
        double d0 = target.getEyeY() - (double) 1.1F;
        double d1 = target.getX() - this.holder.getX();
        double d2 = d0 - waterOrb.getY();
        double d3 = target.getZ() - this.holder.getZ();
        float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
        waterOrb.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
        this.holder.playSound(SoundEvents.WATER_AMBIENT, 1.0F, 0.4F / (this.holder.getRandom().nextFloat() * 0.4F + 0.8F));
        this.holder.level.addFreshEntity(waterOrb);
        for(Ability ability : this.holder.getAbilityPowers()){
            if(ability instanceof AbilityParalysis){
                if(target instanceof PathfinderMob){
                    target = (PathfinderMob) target;
                }
                else{
                    return;
                }
                Random rand = new Random();
                if (rand.nextInt(3) == 0) target.addEffect(new MobEffectInstance(ModEffects.PARALYSIS.get(), 20 * 3, 1, false, false));
            }
        }
        this.holder.enemy = target;
        this.holder.enemyDying = true;
    }

    @Override
    public Goal goal() {
        return new RangedAttackGoal(this.holder, 1.25D, 20, 10.0F);
    }

    @Override
    public boolean targetTask() {
        return true;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.hydrokinesis");
    }

    @Override
    public void outburst() {
        //this.holder.level.setRainLevel(1);
        if (!this.holder.level.isClientSide) {
            System.out.println("rain");
            ((ServerLevel) this.holder.level).setWeatherParameters(0, 1000, true, true);
        }
    }
}
