package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEmotionalAbility;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class AbilityPyrokinesis extends Ability implements IMeleeAbility, IEmotionalAbility, IViolentAbility {

    public AbilityPyrokinesis() {
        super(2, 1);
    }

    @Override
    public void outburst() {
        System.out.println("pyro outburst");
        for (int x = 0; x < 3; x++){
            for (int z = 0; z < 3; z++){
                for (int y = 0; y < 3; y++){
                    BlockPos pos = new BlockPos(x - 3, y - 3, z - 3).offset(this.holder.blockPosition());
                    System.out.println(pos);
                    if(this.holder.level.getBlockState(pos).getBlock() == Blocks.AIR) {
                        this.holder.level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
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
}
