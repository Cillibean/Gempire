package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAlchemyAbility;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.UUID;

public class AbilityAmphibian extends Ability implements IEffectAbility, IAlchemyAbility {

    public AbilityAmphibian(){
        this.ability = Abilities.AMPHIBIAN;
    }

    @Override
    public EffectInstance[] effects() {
        return new EffectInstance[]{
                new EffectInstance(Effects.DOLPHINS_GRACE, 400, 4), new EffectInstance(Effects.WATER_BREATHING, 400, 4)
        };
    }

    @Override
    public boolean hasMultipleEffects() {
        return true;
    }

    @Override
    public EffectInstance effect() {
        return null;
    }

    @Override
    public boolean playerOnly() {
        return true;
    }

    @Override
    public boolean gemAndPlayerOnly() {
        return false;
    }

    @Override
    public ITextComponent getName() {
        return new TranslationTextComponent("ability.gempire.amphibian");
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
    public boolean doSpecialActionOnInput(@Nullable PlayerEntity player) {
        boolean flagHunger = false;
        boolean flagHealth = false;
        boolean flagHeart = player.isCreative();
        for(int i = 0; i < this.holder.NUMBER_OF_SLOTS - 6; i++){
            if(this.holder.getStackInSlot(i + 36).getItem() == Items.HEART_OF_THE_SEA ){
                flagHeart = true;
            }
        }
        if(flagHeart) {
            if (this.holder.getHealth() <= this.holder.getMaxHealth() / 2 && !player.isCreative()) {
                player.sendMessage(new TranslationTextComponent("messages.gempire.entity.spodumene_sore"), UUID.randomUUID());
                return false;
            } else {
                flagHealth = true;
                if (!player.isCreative())
                    this.holder.damageEntity(DamageSource.GENERIC, this.holder.getMaxHealth() / 2);
            }
            if (player.getFoodStats().getFoodLevel() < 10 && !player.isCreative()) {
                player.sendMessage(new TranslationTextComponent("messages.gempire.entity.player_hungry"), UUID.randomUUID());
            } else {
                flagHunger = true;
                if (!player.isCreative()) player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() - 10);
            }
        }
        return flagHealth && flagHunger && flagHeart;
    }
}
