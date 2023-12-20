package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.ICraftingAbility;
import com.gempire.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

public class AbilitySalvaging extends Ability implements ICraftingAbility {

    public AbilitySalvaging(){
        super("salvaging", 4);
    }

    @Override
    public int recipeAmount() {
        return 1;
    }

    @Override
    public void setup() {
        input.add(ModItems.SLUDGE_GLOB.get());
        int n = random.nextInt(16);
        int m = random.nextInt(4);
        switch (m) {
            case 1,2,3 -> {
                switch (n) {
                    case 1 -> output.add(ModItems.RED_CHROMA.get());
                    case 2 -> output.add(ModItems.ORANGE_CHROMA.get());
                    case 3 -> output.add(ModItems.YELLOW_CHROMA.get());
                    case 4 -> output.add(ModItems.LIME_CHROMA.get());
                    case 5 -> output.add(ModItems.GREEN_CHROMA.get());
                    case 6 -> output.add(ModItems.CYAN_CHROMA.get());
                    case 7 -> output.add(ModItems.LIGHT_BLUE_CHROMA.get());
                    case 8 -> output.add(ModItems.BLUE_CHROMA.get());
                    case 9 -> output.add(ModItems.PURPLE_CHROMA.get());
                    case 10 -> output.add(ModItems.MAGENTA_CHROMA.get());
                    case 11 -> output.add(ModItems.PINK_CHROMA.get());
                    case 12 -> output.add(ModItems.BROWN_CHROMA.get());
                    case 13 -> output.add(ModItems.WHITE_CHROMA.get());
                    case 14 -> output.add(ModItems.LIGHT_GRAY_CHROMA.get());
                    case 15 -> output.add(ModItems.GRAY_CHROMA.get());
                    default -> output.add(ModItems.BLACK_CHROMA.get());
                }
            }
            default -> {
                output.add(Items.AIR);
            }
        }
        input2.add(Items.AIR);
        holder.input2List = input2;
        holder.inputList = input;
        holder.outputList = output;
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.salvaging");
    }
}