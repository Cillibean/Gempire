package com.gempire.entities.abilities;

import com.gempire.util.Abilities;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class AbilityPyrokinesis extends Ability{

    public AbilityPyrokinesis(CreatureEntity holder) {
        super(holder);
        AbilityPyrokinesis.ABILITY = Abilities.PYROKINESIS;
    }

    @Override
    public void Fight(Entity entityIn, double damage) {
        entityIn.setFire(5);
    }

    @Override
    public void EmotionalOutburst() {
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
}
