package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAreaAbility;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.AABB;
import net.minecraft.network.chat.Component;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AbilityStern extends Ability implements IAreaAbility, IEffectAbility {

    public AbilityStern() {
        this.ability = Abilities.STERN;
    }

    @Override
    public void AOeffect() {
        ArrayList<EntityGem> entities = new ArrayList<>(this.holder.level.getEntitiesOfClass(EntityGem.class,
                new AABB(this.holder.getX(), this.holder.getY(), this.holder.getZ(), this.holder.getX() + 1, this.holder.getY() + 1 , this.holder.getZ() + 1)
                .inflate(16, this.holder.level.getMaxBuildHeight(), 16)));
        for(int i = 0; i < entities.size(); i++){
            EntityGem gem = entities.get(i);
            if(EntityGem.sharesOwners(gem, this.holder)) {
                gem.focusLevel = 1;
            }
        }
    }

    @Override
    public MobEffectInstance effect() {
        return new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 1);
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.stern");
    }

    @Override
    public Class<LivingEntity>[] applicableEntities() {
        return new Class[]{
                EntityGem.class, Player.class
        };
    }
}
