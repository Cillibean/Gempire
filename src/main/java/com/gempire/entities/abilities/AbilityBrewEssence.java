package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAlchemyAbility;
import com.gempire.entities.abilities.interfaces.IAttributeAbility;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class AbilityBrewEssence extends Ability implements IAlchemyAbility {
    public static final int EXPERIENCE = 40;

    public AbilityBrewEssence(){
        this.ability = Abilities.ESSENCE_BREWER;
    }

    @Override
    public ITextComponent getName() {
        return new TranslationTextComponent("ability.gempire.essence");
    }

    @Override
    public Item input() {
        return ModItems.ESSENCE_BOTTLE.get();
    }

    @Override
    public Item output() {
        int i = this.holder.getRNG().nextInt(4);
        Item essence = ModItems.ESSENCE_BOTTLE.get();
        switch (i){
            case 1:
                essence = ModItems.YELLOW_ESSENCE.get();
                break;
            case 2:
                essence = ModItems.BLUE_ESSENCE.get();
                break;
            case 3:
                essence = ModItems.PINK_ESSENCE.get();
                break;
            default:
                essence = ModItems.WHITE_ESSENCE.get();
                break;
        }
        return essence;
    }

    @Override
    public boolean doSpecialActionOnInput(@Nullable PlayerEntity player) {
        if(player.isCreative()) return true;
        boolean flagXP = false;
        boolean flagLapis = this.holder.consumeItemCheck(ModItems.GILDED_LAPIS.get());
        if(flagLapis){
            if (player.experienceTotal >= AbilityBrewEssence.EXPERIENCE) {
                EntityShale.decreaseExp(player, AbilityBrewEssence.EXPERIENCE);
                flagXP = true;
            }
        }
        return flagLapis && flagXP;
    }
}