package com.gempire.entities.ai;

import com.gempire.entities.other.EntitySpecter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class SpecterRunWhenLookedAt extends Goal {
    EntitySpecter specter;

    public SpecterRunWhenLookedAt(EntitySpecter specter) {
        this.specter = specter;
    }

    @Override
    public boolean canUse() {
        AABB aabb = specter.getBoundingBox().inflate(20);
        List<Player> list = specter.level().getEntitiesOfClass(Player.class, aabb);
        for (Player player : list) {
            if (specter.isLookingAtMe(player)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        AABB aabb = specter.getBoundingBox().inflate(20);
        List<Player> list = specter.level().getEntitiesOfClass(Player.class, aabb);
        return !list.isEmpty();
    }

    @Override
    public void start() {
        this.specter.moveTo(getRunPos());
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Nullable
    protected Vec3 getRunPos() {
        RandomSource r = this.specter.getRandom();
        BlockPos pos = this.specter.blockPosition();

        for(int i = 0; i < 10; ++i) {
            BlockPos pos2 = pos.offset(r.nextInt(20) - 10, r.nextInt(6) - 3, r.nextInt(20) - 10);
            if (this.specter.getWalkTargetValue(pos2) < 0.0F) {
                return Vec3.atBottomCenterOf(pos2);
            }
        }

        return null;
    }
}
