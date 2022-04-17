package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAlchemyAbility;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import javax.annotation.Nullable;
import java.util.UUID;

public class AbilityAmphibian extends Ability implements IEffectAbility, IAlchemyAbility {

    public AbilityAmphibian(){
        this.ability = Abilities.AMPHIBIAN;
    }

    @Override
    public MobEffectInstance[] effects() {
        return new MobEffectInstance[]{
                new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 400, 4), new MobEffectInstance(MobEffects.WATER_BREATHING, 400, 4)
        };
    }

    @Override
    public boolean hasMultipleEffects() {
        return true;
    }

    @Override
    public MobEffectInstance effect() {
        return null;
    }

    @Override
    public Class<LivingEntity>[] applicableEntities() {
        return new Class[]{
                Player.class
        };
    }

    @Override
    public Component getName() {
        return new TranslatableComponent("ability.gempire.amphibian");
    }

    @Override
    public Item input() {
        return Items.SLIME_BALL;
    }

    @Override
    public Item output() {
        return ModItems.SPODUMENE_PIECE.get();
    }

    @Override
    public boolean doSpecialActionOnInput(@Nullable Player player) {
        boolean flagHunger = false;
        boolean flagHealth = false;
        boolean flagHeart = player.isCreative();
        for(int i = 0; i < this.holder.NUMBER_OF_SLOTS - 6; i++){
            if(this.holder.getItem(i + 36).getItem() == Items.HEART_OF_THE_SEA ){
                flagHeart = true;
            }
        }
        if(flagHeart) {
            if (this.holder.getHealth() <= this.holder.getMaxHealth() / 2 && !player.isCreative()) {
                player.sendMessage(new TranslatableComponent("messages.gempire.entity.spodumene_sore"), UUID.randomUUID());
                return false;
            } else {
                flagHealth = true;
                if (!player.isCreative())
                    this.holder.actuallyHurt(DamageSource.GENERIC, this.holder.getMaxHealth() / 2);
            }
            if (player.getFoodData().getFoodLevel() < 10 && !player.isCreative()) {
                player.sendMessage(new TranslatableComponent("messages.gempire.entity.player_hungry"), UUID.randomUUID());
            } else {
                flagHunger = true;
                if (!player.isCreative()) player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 10);
            }
        }
        return flagHealth && flagHunger && flagHeart;
    }
}
