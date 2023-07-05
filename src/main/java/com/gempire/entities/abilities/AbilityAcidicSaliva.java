package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IRangedAbility;
import com.gempire.entities.abilities.interfaces.ITaskAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.projectiles.AcidSpitEntity;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class AbilityAcidicSaliva extends Ability implements IRangedAbility, IViolentAbility, ITaskAbility {

    public AbilityAcidicSaliva() {
        this.ability = GempireAbilities.ACIDIC_SPIT;
    }

    @Override
    public void attack(LivingEntity target, float distanceFactor) {
        AcidSpitEntity acidSpit = new AcidSpitEntity(this.holder.level, this.holder);
        double d0 = target.getEyeY() - (double) 1.1F;
        double d1 = target.getX() - this.holder.getX();
        double d2 = d0 - acidSpit.getY();
        double d3 = target.getZ() - this.holder.getZ();
        float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
        acidSpit.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
        this.holder.playSound(SoundEvents.LLAMA_SPIT, 1.0F, 0.4F / (this.holder.getRandom().nextFloat() * 0.4F + 0.8F));
        this.holder.level.addFreshEntity(acidSpit);
        this.holder.enemy = target;
        this.holder.enemyDying = true;
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
    public Component getName() {
        return Component.translatable("ability.gempire.acidic_spit");
    }
}
