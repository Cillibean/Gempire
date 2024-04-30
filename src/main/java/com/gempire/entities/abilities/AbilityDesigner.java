package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.ICraftingAbility;
import com.gempire.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

public class AbilityDesigner extends Ability implements ICraftingAbility {
    public AbilityDesigner(){
        super("designer", 5);
    }

    @Override
    public int recipeAmount() {
        return 1;
    }
    @Override
    public void setup() {
        input.add(Items.STONE);
        input2.add(Items.AIR);
        output.add(ModItems.PEDISTAL.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.RED_CHROMA.get());
        output.add(ModItems.RED_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.ORANGE_CHROMA.get());
        output.add(ModItems.ORANGE_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.YELLOW_CHROMA.get());
        output.add(ModItems.YELLOW_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.LIME_CHROMA.get());
        output.add(ModItems.LIME_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.GREEN_CHROMA.get());
        output.add(ModItems.GREEN_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.CYAN_CHROMA.get());
        output.add(ModItems.CYAN_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.LIGHT_BLUE_CHROMA.get());
        output.add(ModItems.LIGHT_BLUE_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.BLUE_CHROMA.get());
        output.add(ModItems.BLUE_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.PURPLE_CHROMA.get());
        output.add(ModItems.PURPLE_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.MAGENTA_CHROMA.get());
        output.add(ModItems.MAGENTA_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.PINK_CHROMA.get());
        output.add(ModItems.PINK_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.WHITE_CHROMA.get());
        output.add(ModItems.WHITE_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.LIGHT_GRAY_CHROMA.get());
        output.add(ModItems.LIGHT_GRAY_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.GRAY_CHROMA.get());
        output.add(ModItems.GRAY_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.BLACK_CHROMA.get());
        output.add(ModItems.BLACK_LATTICE.get());
        input.add(Items.IRON_BARS);
        input2.add(ModItems.BROWN_CHROMA.get());
        output.add(ModItems.BROWN_LATTICE.get());
        input.add(Items.GLASS);
        input2.add(ModItems.RED_CHROMA.get());
        output.add(ModItems.RED_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.ORANGE_CHROMA.get());
        output.add(ModItems.ORANGE_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.YELLOW_CHROMA.get());
        output.add(ModItems.YELLOW_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.LIME_CHROMA.get());
        output.add(ModItems.LIME_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.GREEN_CHROMA.get());
        output.add(ModItems.GREEN_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.CYAN_CHROMA.get());
        output.add(ModItems.CYAN_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.LIGHT_BLUE_CHROMA.get());
        output.add(ModItems.LIGHT_BLUE_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.BLUE_CHROMA.get());
        output.add(ModItems.BLUE_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.PURPLE_CHROMA.get());
        output.add(ModItems.PURPLE_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.MAGENTA_CHROMA.get());
        output.add(ModItems.MAGENTA_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.PINK_CHROMA.get());
        output.add(ModItems.PINK_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.WHITE_CHROMA.get());
        output.add(ModItems.WHITE_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.LIGHT_GRAY_CHROMA.get());
        output.add(ModItems.LIGHT_GRAY_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.GRAY_CHROMA.get());
        output.add(ModItems.GRAY_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.BLACK_CHROMA.get());
        output.add(ModItems.BLACK_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.BROWN_CHROMA.get());
        output.add(ModItems.BROWN_DIAMOND_GLASS.get());
        input.add(Items.GLASS);
        input2.add(ModItems.SPECIAL_CHROMA.get());
        output.add(ModItems.PRISMATIC_DIAMOND_GLASS.get());
        holder.inputList = input;
        holder.input2List = input2;
        holder.outputList = output;
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.designer");
    }
}
