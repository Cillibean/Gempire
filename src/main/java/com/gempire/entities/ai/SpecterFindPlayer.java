package com.gempire.entities.ai;

import com.gempire.entities.other.EntitySpecter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class SpecterFindPlayer extends Goal {

    EntitySpecter specter;
    Player player;

    public SpecterFindPlayer(EntitySpecter specter) {
        this.specter = specter;
    }

    @Override
    public boolean canUse() {
        this.player = specter.level().getNearestPlayer(specter, 20D);
        return player != null;
    }

    @Override
    public boolean canContinueToUse() {
        this.player = specter.level().getNearestPlayer(specter, 20D);
        return player != null;
    }

    @Override
    public void start() {
        specter.getNavigation().moveTo(player, 0.3D);
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
