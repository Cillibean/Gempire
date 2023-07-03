package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.ICraftingAbility;
import com.gempire.init.ModItems;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

public class AbilityDesigner extends Ability implements ICraftingAbility {
    public AbilityDesigner(){
        this.ability = Abilities.DESIGNER;
    }

    @Override
    public int recipeAmount() {
        return 1;
    }
    @Override
    public void setup() {
        input.add(Items.STONE);
        output.add(ModItems.PEDISTAL.get());
        holder.inputList = input;
        holder.outputList = output;
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.designer");
    }
}
