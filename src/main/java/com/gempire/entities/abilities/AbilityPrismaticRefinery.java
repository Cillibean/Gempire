package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.ICraftingAbility;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;

public class AbilityPrismaticRefinery extends Ability implements ICraftingAbility {
    public AbilityPrismaticRefinery(){
        this.ability = Abilities.REFINERY;
    }

    @Override
    public int recipeAmount() {
        return 1;
    }
    @Override
    public void setup() {
        input.add(ModItems.GEM_ALLOY.get());
        output.add(ModItems.PRISMATIC_INGOT.get());
        holder.inputList = input;
        holder.outputList = output;
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.refinery");
    }
}