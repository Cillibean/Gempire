package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAlchemyAbility;
import com.gempire.entities.abilities.interfaces.IAttributeAbility;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class AbilityBrewEssence extends Ability implements IAlchemyAbility {

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
}
