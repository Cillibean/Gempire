package com.gempire.entities.bases;

import com.gempire.init.ModMessages;
import com.gempire.networking.C2SFlightEntityDash;
import com.gempire.networking.C2SFlightEntityMovement;
import com.gempire.util.ElytraCalculation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public abstract class EntityFlyingVehicleGem extends EntityGem implements PlayerRideable, PlayerRideableJumping {

    public boolean flying = false, jumping = false, descend = false, elytra = false, moving = false, hasMoved = false;

    public EntityFlyingVehicleGem(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public void isMovingCheck() {
        Vec2 groundmovement = new Vec2((float)this.getDeltaMovement().x, (float)this.getDeltaMovement().z);
        groundmovement = groundmovement.normalized();

        boolean moving2 = (Mth.abs(groundmovement.x) > 0 || Mth.abs(groundmovement.y) > 0);
        if (!isElytraFlying()) {

            if (this.level().isClientSide) {
                if (moving2 == true && hasMoved == false) {
                    moving = true; hasMoved = true;
                    ModMessages.sendToServer(new C2SFlightEntityMovement(true, this.getId(), this.getSharedFlag(7)));

                } else if (moving2 == false && hasMoved == true) {
                    this.moving = false; hasMoved = false;
                    ModMessages.sendToServer(new C2SFlightEntityMovement(false, this.getId(), this.getSharedFlag(7)));
                }
            }
        }
    }

    @Override
    public void travel(Vec3 vec3) {
        super.travel(vec3);
    }

    @Override
    public void tickRidden(Player pPlayer, Vec3 travelVec) {
        super.tickRidden(pPlayer, travelVec);
        if (this.level().isClientSide()) {
            // Set the mob to look at where the player is and rotate the body too.
            Vec2 riderLookVec = new Vec2(pPlayer.getXRot(), pPlayer.getYRot());
            this.setRot(riderLookVec.y, riderLookVec.x);
            this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();

            AttributeInstance gravity = this.getAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
            double gravityValue = gravity.getValue();

            // If there's no gravity, add the gravity back. This is used to workaround the "Kicked for flying a vehicle" check
            if (this.isNoGravity() && !this.isElytraFlying()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -gravityValue, 0.0D));
            }


            double strafex = pPlayer.xxa * 0.5f;
            double yascend = pPlayer.yya;
            double forwardz = pPlayer.zza;

            // make backward movement twice as slow.
            if (forwardz <= 0.0f) {
                forwardz *= 0.5f;
            }

            if (this.isControlledByLocalInstance()) {

                Vec3 jvec = this.getDeltaMovement();
                // Launch off the ground with more power
                if (this.jumping && this.onGround() && !this.isElytraFlying()) {
                    this.setDeltaMovement(jvec.x, 1.8, jvec.z);
                    this.jumping = false;
                }
                // Launch in the air with less power
                if (this.flying && this.jumping && !this.isElytraFlying()) {
                    this.setDeltaMovement(jvec.x, jvec.y + 1.5, jvec.z);
                    this.jumping = false;
                }
                // Launch lapis forward in the air with elytra gliding, similar to a minecraft rocket.
                if (this.flying && this.jumping && this.isElytraFlying()) {
                    ModMessages.sendToServer(new C2SFlightEntityDash());
                    this.jumping = false;
                }
                // Descend lapis if the Descend key is called
                if (this.flying && this.descend && !this.isElytraFlying()) {
                    this.moveRelative(0.1F, new Vec3(strafex, -20, forwardz));
                }

                if (this.isElytraFlying()) {
                    ElytraCalculation.calculateGlide(this, this.getLookAngle());
                }
            }
        }

        if (onGround()) {
            this.flying = false;
            this.jumping = false;
            this.descend = false;
            this.setElytraFlying(false);

        }

        if (this.level().isClientSide) {
            if (this.isControlledByLocalInstance()) {
                if (!this.flying) {
                    this.isMovingCheck();
                }
            }
        }
    }

    @Override
    protected Vec3 getRiddenInput(Player pPlayer, Vec3 travelVec) {
        double strafex = pPlayer.xxa * 0.5f;
        double yascend = pPlayer.yya;
        double forwardz = pPlayer.zza;

        // make backward movement twice as slow.
        if (forwardz <= 0.0f) {
            forwardz *= 0.5f;
        }

        // While flying, move towards where the rider is facing.
        if (flying && !isElytraFlying()) {
            this.moveRelative(0.1F,new Vec3(strafex, yascend, forwardz));
        }


        return new Vec3(strafex, yascend, forwardz);
    }

    @Override
    public void onPlayerJump(int pJumpPower) {
        if (this.level().dimension() != Level.NETHER) {
                this.flying = true;
                this.jumping = true;
            }
    }

    @Override
    public boolean canJump() {
        return true;
    }

    @Override
    public void handleStartJump(int pJumpPower) {

    }

    @Override
    public void handleStopJump() {

    }


    public boolean isFlying() {
        return this.flying;
    }

    public void setDescend(boolean descend) {
        this.descend = descend;
    }


    public void setElytraFlying(boolean elytra) {
        this.elytra = elytra;
        this.setSharedFlag(7, elytra);
    }

    public boolean isElytraFlying() {
        return this.elytra;
    }


    @Override
    public LivingEntity getControllingPassenger() {
        List<Entity> list = getPassengers();
        if (list.isEmpty()) {
            return null;
        } else {
            return (LivingEntity)list.get(0);
        }
    }

    public void setRidingPlayer(Player player) {
        player.setYRot(getYRot());
        player.setXRot(getXRot());
        player.startRiding(this);
    }

    public boolean canBeControlledByRider() {
        return getControllingPassenger() instanceof LivingEntity ;
    }

    @Override
    public void positionRider(Entity passenger, Entity.MoveFunction pCallback) {
        super.positionRider(passenger, pCallback);

        Entity rider = getControllingPassenger();
        if (rider != null) {
            // Set the position of the rider to the current lapis position
            // Try to put the rider on 1.45 Y height
            double yOffset = this.getBbHeight() - 1.45;
            passenger.setPos(this.getX(), this.getY() + (this.getBbHeight() - yOffset),
                    this.getZ());

            // Face the rider the same direction the lapis is facing.
            if (rider instanceof LivingEntity) {
                ((LivingEntity) rider).yBodyRot = this.yBodyRot;
            }
        }

    }
}
