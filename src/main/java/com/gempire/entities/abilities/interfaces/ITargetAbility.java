package com.gempire.entities.abilities.interfaces;

import net.minecraft.world.level.block.state.BlockState;

public interface ITargetAbility {
    public BlockState blockToSummon();
    public int chanceToSummon();
}
