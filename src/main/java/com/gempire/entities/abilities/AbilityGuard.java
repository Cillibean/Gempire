package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IRangedAbility;
import com.gempire.entities.abilities.interfaces.ITaskAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.ai.EntityAIGuard;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class AbilityGuard extends Ability implements ITaskAbility {

    public AbilityGuard() {
        this.ability = Abilities.GUARD;
    }

    @Override
    public Goal goal() {
        return new EntityAIGuard((EntityGem)this.holder, 1D);
    }

    @Override
    public boolean targetTask() {
        return false;
    }
    @Override
    public ITextComponent getName() {
        return new TranslationTextComponent("ability.gempire.guard");
    }
}
