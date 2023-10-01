package com.gempire.entities.util;

import com.gempire.entities.bases.EntityFlyingRideableGem;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.NodeEvaluator;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class FlightManager {
    private final EntityFlyingRideableGem gem;
    private Vec3 target;
    private Vec3 startAttackVec;
    private Vec3 startPreyVec;
    private boolean hasStartedToScorch = false;
    private LivingEntity prevAttackTarget = null;

    public FlightManager(EntityFlyingRideableGem gem) {
        this.gem = gem;
    }

    public static float approach(float number, float max, float min) {
        min = Math.abs(min);
        return number < max ? Mth.clamp(number + min, number, max) : Mth.clamp(number - min, max, number);
    }

    public static class PlayerFlightMoveHelper<T extends Mob & IFlyingMount> extends MoveControl {

        private final T gem;

        public PlayerFlightMoveHelper(T gem) {
            super(gem);
            this.gem = gem;
        }

        @Override
        public void tick() {
            if (gem instanceof EntityFlyingRideableGem theDragon && theDragon.getControllingPassenger() != null) {
                // New ride system doesn't need move controller
                // The flight move control is disabled here, the walking move controller will stay Operation.WAIT so nothing will happen too
                return;
            }

            double flySpeed = speedModifier * 2 * 3;
            Vec3 gemVec = gem.position();
            Vec3 moveVec = new Vec3(wantedX, wantedY, wantedZ);
            Vec3 normalized = moveVec.subtract(gemVec).normalize();
            double dist = gemVec.distanceTo(moveVec);
            gem.setDeltaMovement(normalized.x * flySpeed, normalized.y * flySpeed, normalized.z * flySpeed);
            if (dist > 2.5E-7) {
                float yaw = (float) Math.toDegrees(Math.PI * 2 - Math.atan2(normalized.x, normalized.y));
                gem.setYRot(rotlerp(gem.getYRot(), yaw, 5));
                gem.setSpeed((float) (speedModifier));
            }
            gem.move(MoverType.SELF, gem.getDeltaMovement());
        }
    }
}
