package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAreaAbility;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.world.NoteBlockEvent;
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
        ArrayList<EntityGem> entities = new ArrayList<>(this.holder.world.getEntitiesWithinAABB(EntityGem.class,
                new AxisAlignedBB(this.holder.getPosX(), this.holder.getPosY(), this.holder.getPosZ(), this.holder.getPosX() + 1, this.holder.getPosY() + 1 , this.holder.getPosZ() + 1)
                .grow(16, this.holder.world.getHeight(), 16)));
        for(int i = 0; i < entities.size(); i++){
            EntityGem gem = entities.get(i);
            if(EntityGem.sharesOwners(gem, this.holder)) {
                gem.focusLevel = 1;
            }
        }
    }

    @Override
    public EffectInstance effect() {
        return new EffectInstance(Effects.NIGHT_VISION, 400, 1);
    }
    @Override
    public ITextComponent getName() {
        return new TranslationTextComponent("ability.gempire.stern");
    }

    @Override
    public Class<LivingEntity>[] applicableEntities() {
        return new Class[]{
                EntityGem.class, PlayerEntity.class
        };
    }
}
