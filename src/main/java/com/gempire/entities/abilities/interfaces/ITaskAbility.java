package com.gempire.entities.abilities.interfaces;

import net.minecraft.entity.ai.goal.Goal;

public interface ITaskAbility {
    Goal goal();
    boolean targetTask();
}
