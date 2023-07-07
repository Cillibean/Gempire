package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.ICraftingAbility;
import com.gempire.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

public class AbilityHydration extends Ability implements ICraftingAbility {
    public AbilityHydration(){
        super(38, 5);
    }

    @Override
    public int recipeAmount() {
        return 2;
    }
    @Override
    public void setup() {
        input.add(Items.BUCKET);
        output.add(Items.WATER_BUCKET);
        input.add(Items.GLASS_BOTTLE);
        output.add(Items.POTION);
        holder.inputList = input;
        holder.outputList = output;
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.hydration");
    }
}
