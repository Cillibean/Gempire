package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEmotionalAbility;
import com.gempire.entities.abilities.interfaces.IRangedAbility;
import com.gempire.entities.abilities.interfaces.ITaskAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.projectiles.AcidSpitEntity;
import com.gempire.entities.projectiles.ElectrokinesisLightning;
import com.gempire.init.ModEntities;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class AbilityElectrokinesis extends Ability implements ITaskAbility, IViolentAbility, IRangedAbility, IEmotionalAbility {

    public AbilityElectrokinesis() {
        super("electrokinesis", 1);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.electrokinesis");
    }

    @Override
    public void attack(LivingEntity target, float distanceFactor) {
        ElectrokinesisLightning lightning = new ElectrokinesisLightning(this.holder.level(), this.holder, target);
        double d0 = target.getEyeY() - (double) 1.1F;
        double d1 = target.getX() - this.holder.getX();
        double d2 = d0 - lightning.getY();
        double d3 = target.getZ() - this.holder.getZ();
        float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
        //lightning.(d1, d2 + (double) f, d3, 1.6F, 6.0F);
        //this.holder.playSound(SoundEvents.LLAMA_SPIT, 1.0F, 0.4F / (this.holder.getRandom().nextFloat() * 0.4F + 0.8F));
        lightning.setPos(target.getX(), target.getY(), target.getZ());
        //if (this.holder.level().canSeeSky(target.getOnPos())) {
            System.out.println("can see sky");
            ElectrokinesisLightning electro = ModEntities.ELECTROKINESIS_LIGHTNING.get().create(this.holder.level());
            assert electro != null;
            electro.owner = this.holder;
            //TODO: fix electro
            //electro.level() = this.holder.level();
            electro.target = target;
            //electro.moveTo(Vec3.atBottomCenterOf(target.getOnPos()));
            lightning.moveTo(Vec3.atBottomCenterOf(target.getOnPos()));
            //this.holder.level().addFreshEntity(electro);
            this.holder.level().addFreshEntity(lightning);
            this.holder.enemy = target;
            this.holder.enemyDying = true;
            target.hurt(this.holder.damageSources().lightningBolt(), 5);
            target.level().playSound(holder, target.getOnPos(), SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.PLAYERS, 1, 1);
        //}
        System.out.println("electrokinesis");
    }

    @Override
    public Goal goal() {
        return new RangedAttackGoal((RangedAttackMob) this.holder, 1.25D, 20, 10.0F);
    }

    @Override
    public boolean targetTask() {
        return true;
    }

    @Override
    public void outburst() {
        List<LivingEntity> list = this.holder.level().getEntitiesOfClass(LivingEntity.class, this.holder.getBoundingBox().inflate(14.0D, 8.0D, 14.0D));
        List<ElectrokinesisLightning> electro = new ArrayList<>();
        for (LivingEntity entity : list) {
            ElectrokinesisLightning lightning = new ElectrokinesisLightning(this.holder.level(), this.holder, entity);
            electro.add(lightning);
        }
        int remainingBolts = 3;
        for (ElectrokinesisLightning lightning : electro) {
            if (holder.getRandom().nextInt(3) == 3) {
                this.holder.level().addFreshEntity(lightning);
                remainingBolts--;
                lightning.moveTo(Vec3.atBottomCenterOf(lightning.target.getOnPos()));
                lightning.target.hurt(this.holder.damageSources().lightningBolt(), 5);
            }
        }
    }
}
