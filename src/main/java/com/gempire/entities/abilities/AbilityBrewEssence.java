package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAlchemyAbility;
import com.gempire.entities.abilities.interfaces.IAttributeAbility;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import javax.annotation.Nullable;
import java.util.UUID;

public class AbilityBrewEssence extends Ability implements IAlchemyAbility {
    public static final int EXPERIENCE = 40;

    public AbilityBrewEssence(){
        this.ability = Abilities.ESSENCE_BREWER;
    }

    @Override
    public Component getName() {
        return new TranslatableComponent("ability.gempire.essence");
    }

    @Override
    public Item input() {
        return ModItems.ESSENCE_BOTTLE.get();
    }

    @Override
    public Item output() {
        int i = this.holder.getRandom().nextInt(4);
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
    public boolean doSpecialActionOnInput(@Nullable Player player) {
        if(player.isCreative()) return true;
        boolean flagXP = false;
        if (player.totalExperience >= AbilityBrewEssence.EXPERIENCE) {
            EntityShale.decreaseExp(player, AbilityBrewEssence.EXPERIENCE);
            flagXP = true;
        }
        else{
            player.sendMessage(new TranslatableComponent("messages.gempire.entity.player_need_xp"), UUID.randomUUID());
        }
        return flagXP;
    }

    @Override
    public Item consume() {
        return ModItems.GILDED_LAPIS.get();
    }
}