package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.init.ModFluids;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

import javax.annotation.Nullable;

public class AbilityBrewEssence extends Ability {
    public AbilityBrewEssence(){
        this.ability = Abilities.ESSENCE_BREWER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.essence");
    }
}