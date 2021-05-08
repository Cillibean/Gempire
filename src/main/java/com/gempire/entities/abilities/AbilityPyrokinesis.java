package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IEmotionalAbility;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.util.Abilities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class AbilityPyrokinesis extends Ability implements IMeleeAbility, IEmotionalAbility, IViolentAbility {

    public AbilityPyrokinesis() {
        this.ability = Abilities.PYROKINESIS;
    }

    @Override
    public void outburst() {
        for (int x = 0; x < 6; x++){
            for (int z = 0; z < 6; z++){
                for (int y = 0; y < 6; y++){
                    BlockPos pos = new BlockPos(x - 3, y - 3, z - 3).add(this.holder.getPosition());
                    if(this.holder.world.getBlockState(pos).getBlock() == Blocks.AIR) {
                        this.holder.world.setBlockState(pos, Blocks.FIRE.getDefaultState());
                    }
                }
            }
        }
    }

    @Override
    public void fight(Entity entityIn, double damage) {
        entityIn.setFire(5);
    }
}
