package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.ICraftingAbility;
import com.gempire.entities.abilities.interfaces.IEmotionalAbility;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.init.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class AbilityPyrokinesis extends Ability implements IMeleeAbility, IEmotionalAbility, IViolentAbility, ICraftingAbility {

    public AbilityPyrokinesis() {
        super("pyrokinesis", 1);
    }

    @Override
    public void outburst() {
        for (int x = 0; x < 3; x++){
            for (int z = 0; z < 3; z++){
                for (int y = 0; y < 3; y++){
                    BlockPos pos = new BlockPos(x - 1, y, z - 1).offset(this.holder.blockPosition());
                    if(this.holder.level().getBlockState(pos).getBlock() == Blocks.AIR && this.holder.level().getBlockState(pos.below()).getBlock() != Blocks.AIR && this.holder.level().getBlockState(pos.below()).getBlock() != Blocks.FIRE && this.holder.level().getBlockState(pos.below()).isSolid()) {
                        this.holder.level().setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
                    }
                }
            }
        }
    }

    @Override
    public void fight(LivingEntity entityIn, double damage) {
        entityIn.setSecondsOnFire(5);
    }
    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.pyrokinesis");
    }

    @Override
    public void setup() {
        input.add(ModItems.CONGEALED_PINK_ESSENCE_BLOCK.get());
        output.add(ModItems.CONGEALED_PINK_ESSENCE.get());
        input2.add(Items.AIR);
        input.add(ModItems.CONGEALED_BLUE_ESSENCE_BLOCK.get());
        output.add(ModItems.CONGEALED_BLUE_ESSENCE.get());
        input2.add(Items.AIR);
        input.add(ModItems.CONGEALED_YELLOW_ESSENCE_BLOCK.get());
        output.add(ModItems.CONGEALED_YELLOW_ESSENCE.get());
        input2.add(Items.AIR);
        input.add(ModItems.CONGEALED_WHITE_ESSENCE_BLOCK.get());
        output.add(ModItems.CONGEALED_WHITE_ESSENCE.get());
        input2.add(Items.AIR);
        holder.input2List = input2;
        holder.inputList = input;
        holder.outputList = output;
    }
}
