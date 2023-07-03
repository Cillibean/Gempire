package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.ICraftingAbility;
import com.gempire.entities.gems.starter.EntityShale;
import com.gempire.init.ModFluids;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

import javax.annotation.Nullable;

public class AbilityBrewEssence extends Ability implements ICraftingAbility {
    public AbilityBrewEssence(){
        this.ability = Abilities.ESSENCE_BREWER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.essence");
    }

    @Override
    public int recipeAmount() {
        return 1;
    }

    @Override
    public void setup() {
        input.add(ModItems.GEM_SLUSH_BUCKET.get());
        int n = random.nextInt(4);
        switch (n) {
            case 1 -> output.add(ModItems.PINK_ESSENCE_BUCKET.get());
            case 2 -> output.add(ModItems.BLUE_ESSENCE_BUCKET.get());
            case 3 -> output.add(ModItems.YELLOW_ESSENCE_BUCKET.get());
            default -> output.add(ModItems.WHITE_ESSENCE_BUCKET.get());
        }
        holder.inputList = input;
        holder.outputList = output;
    }
}