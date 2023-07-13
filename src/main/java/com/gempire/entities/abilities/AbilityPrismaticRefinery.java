package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.ICraftingAbility;
import com.gempire.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

public class AbilityPrismaticRefinery extends Ability implements ICraftingAbility {
    public AbilityPrismaticRefinery(){
        super(44, 4);
    }

    @Override
    public int recipeAmount() {
        return 1;
    }
    @Override
    public void setup() {
        input.add(ModItems.GEM_ALLOY.get());
        output.add(ModItems.PRISMATIC_INGOT.get());
        input2.add(Items.AIR);
        holder.input2List = input2;
        holder.inputList = input;
        holder.outputList = output;
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.refinery");
    }
}