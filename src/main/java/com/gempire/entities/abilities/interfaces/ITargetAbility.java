package com.gempire.entities.abilities.interfaces;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public interface ITargetAbility {
    public BlockState blockToSummon();
    public int chanceToSummon();
}
