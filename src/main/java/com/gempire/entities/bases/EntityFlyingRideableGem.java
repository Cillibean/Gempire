package com.gempire.entities.bases;

import com.gempire.entities.abilities.AbilityVehicle;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.util.FlightManager;
import com.gempire.entities.util.ICustomMoveController;
import com.gempire.entities.util.IFlyingMount;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class EntityFlyingRideableGem extends EntityGem implements FlyingAnimal, ICustomMoveController, IFlyingMount {

    private static final EntityDataAccessor<Byte> CONTROL_STATE = SynchedEntityData.defineId(EntityFlyingRideableGem.class, EntityDataSerializers.BYTE);

    public EntityFlyingRideableGem(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public ArrayList<Ability> definiteAbilities() {
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new AbilityVehicle());
        return arrayList;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CONTROL_STATE, (byte) 0);
    }

    @Override
    public boolean isGoingUp() {
        return (entityData.get(CONTROL_STATE) & 1) == 1;
    }

    @Override
    public boolean isGoingDown() {
        return (entityData.get(CONTROL_STATE) >> 1 & 1) == 1;
    }

    public boolean isAttacking() {
        return (entityData.get(CONTROL_STATE) >> 2 & 1) == 1;
    }

    public boolean isStriking() {
        return (entityData.get(CONTROL_STATE) >> 3 & 1) == 1;
    }

    public boolean isDismounting() {
        return (entityData.get(CONTROL_STATE) >> 4 & 1) == 1;
    }

    @Override
    public void up(boolean up) {
        setStateField(0, up);
    }

    @Override
    public void down(boolean down) {
        setStateField(1, down);
    }

    @Override
    public void attack(boolean attack) {
        setStateField(2, attack);
    }

    @Override
    public void strike(boolean strike) {
        setStateField(3, strike);
    }

    @Override
    public void dismount(boolean dismount) {
        setStateField(4, dismount);
    }

    private void setStateField(int i, boolean newState) {
        byte prevState = entityData.get(CONTROL_STATE);
        if (newState) {
            entityData.set(CONTROL_STATE, (byte) (prevState | (1 << i)));
        } else {
            entityData.set(CONTROL_STATE, (byte) (prevState & ~(1 << i)));
        }
    }

    @Override
    public byte getControlState() {
        return entityData.get(CONTROL_STATE);
    }

    @Override
    public void setControlState(byte state) {
        entityData.set(CONTROL_STATE, state);
    }

    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        for (Entity passenger : this.getPassengers()) {
            if (passenger instanceof Player player && this.getTarget() != passenger) {
                for (int i = 0; i < this.OWNERS.size(); i++) {
                    if (this.OWNERS.get(i) != null && this.OWNERS.get(i).equals(player.getUUID())) {
                        return player;
                    }
                }
            }
        }
        return null;
    }

    public boolean isRidingPlayer(Player player) {
        return getRidingPlayer() != null && player != null && getRidingPlayer().getUUID().equals(player.getUUID());
    }

    @Override
    @Nullable
    public Player getRidingPlayer() {
        if (this.getControllingPassenger() instanceof Player) {
            return (Player) this.getControllingPassenger();
        }
        return null;
    }

    @Override
    public boolean shouldRiderSit() {
        return this.getControllingPassenger() != null;
    }

    @Override
    public void positionRider(@NotNull Entity passenger) {
        super.positionRider(passenger);
        if (this.hasPassenger(passenger)) {
                this.setYRot(passenger.getYRot());
                this.setYHeadRot(passenger.getYHeadRot());
                this.setXRot(passenger.getXRot());

                Vec3 riderPos = this.getRiderPosition();
                passenger.setPos(riderPos.x, riderPos.y + passenger.getBbHeight(), riderPos.z);
        }
    }

    @Override
    public void rideTick() {
        Entity entity = this.getVehicle();
        if (this.isPassenger() && !entity.isAlive()) {
            this.stopRiding();
        } else {
            this.setDeltaMovement(0, 0, 0);
            this.tick();
            if (this.isPassenger()) {
                this.updateRiding(entity);
            }
        }
    }

    public void updateRiding(Entity riding) {
        if (riding != null && riding.hasPassenger(this) && riding instanceof Player) {
            final int i = riding.getPassengers().indexOf(this);
            final float radius = (i == 2 ? -0.2F : 0.5F) + (((Player) riding).isFallFlying() ? 2 : 0);
            final float angle = (0.01745329251F * ((Player) riding).yBodyRot) + (i == 1 ? 90 : i == 0 ? -90 : 0);
            final double extraX = radius * Mth.sin((float) (Math.PI + angle));
            final double extraZ = radius * Mth.cos(angle);
            final double extraY = (riding.isShiftKeyDown() ? 1.2D : 1.4D) + (i == 2 ? 0.4D : 0D);
            this.yHeadRot = ((Player) riding).yHeadRot;
            this.setYRot(((Player) riding).yHeadRot);
            this.setPos(riding.getX() + extraX, riding.getY() + extraY, riding.getZ() + extraZ);
            if ((this.getControlState() == 1 << 4 || ((Player) riding).isFallFlying()) && !riding.isPassenger()) {
                this.stopRiding();
                if (level.isClientSide) {
                    //TODO: send messsage to server
                }

            }

        }
    }

    @Override
    public boolean isControlledByLocalInstance() {
        return super.isControlledByLocalInstance();
    }

    @Override
    public boolean canBeControlledByRider() {
        return true;
    }

    int speed = 10;

    @Override
    public void travel(@NotNull Vec3 pTravelVector) {
        if (!this.isVehicle()) {
            if (this.getNavigation().getPath() != null) {
                this.getNavigation().stop();
            }
            pTravelVector = new Vec3(0, 0, 0);
        }
        // Player riding controls
        // Note: when motion is handled by the client no server side setDeltaMovement() should be called
        // otherwise the movement will halt
        // Todo: move wrongly fix
        if (this.getControllingPassenger() != null && canBeControlledByRider()) {
            LivingEntity rider = (LivingEntity) this.getControllingPassenger();
            if (rider == null) {
                super.travel(pTravelVector);
                return;
            }

            // Flying control, include flying through waterfalls
            if (isHovering() || isFlying()) {
                double forward = rider.zza;
                double strafing = rider.xxa;
                double vertical = 0;
                // Set flag for logic and animation
                // Rider controlled tackling
                boolean gliding = rider.isSprinting();
                if (!gliding) {
                    // Mouse controlled yaw
                    // Slower on going astern
                    forward *= rider.zza > 0 ? 1.0f : 0.5f;
                    // Slower on going sideways
                    strafing *= 0.4f;
                    if (isGoingUp() && !isGoingDown()) {
                        vertical = 1f;
                    } else if (isGoingDown() && !isGoingUp()) {
                        vertical = -1f;
                    }
                    else if (isControlledByLocalInstance()) {
//                        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0f, 0.8f, 1.0f));
                    }
                } else {
                    // Mouse controlled yaw and pitch
                    strafing *= 0.1f;
                    // Diving is faster
                    // Todo: a new and better algorithm much like elytra flying
                    // Try to match the moving vector to the rider's look vector
                    forward = Mth.abs(Mth.cos(this.getXRot() * ((float) Math.PI / 180F)));
                    vertical = Mth.abs(Mth.sin(this.getXRot() * ((float) Math.PI / 180F)));
                    // Pitch is still responsive to spacebar and x key
                    if (isGoingUp() && !isGoingDown()) {
                        vertical = Math.max(vertical, 0.5);
                    } else if (isGoingDown() && !isGoingUp()) {
                        vertical = Math.min(vertical, -0.5);
                    } else if (isGoingUp() && isGoingDown()) {
                        vertical = 0;
                    }
                    // X rotation takes minus on looking upward
                    else if (this.getXRot() < 0) {
                        vertical *= 1;
                    } else if (this.getXRot() > 0) {
                        vertical *= -1;
                    } else if (isControlledByLocalInstance()) {
//                        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0f, 0.8f, 1.0f));
                    }
                }
                // Speed bonus damping

                if (this.isControlledByLocalInstance()) {
                    // Vanilla friction on Y axis is smaller, which will influence terminal speed for climbing and diving
                    // use same friction coefficient on all axis simplifies how travel vector is computed
                    this.setSpeed(speed);

                    this.moveRelative(1, new Vec3(strafing, vertical, forward));
                    this.move(MoverType.SELF, this.getDeltaMovement());
                    this.setDeltaMovement(this.getDeltaMovement().multiply(new Vec3(0.9, 0.9, 0.9)));

                    Vec3 currentMotion = this.getDeltaMovement();
                    if (this.horizontalCollision) {
                        currentMotion = new Vec3(currentMotion.x, 0.1D, currentMotion.z);
                    }
                    this.setDeltaMovement(currentMotion);
                } else {
                    this.setDeltaMovement(Vec3.ZERO);
                }
                this.tryCheckInsideBlocks();
                return;
            }
            // In water move control, for those that can't swim
            else if (isInWater() || isInLava()) {
                double forward = rider.zza;
                double strafing = rider.xxa;
                double vertical = 0;
                if (isGoingUp() && !isGoingDown()) {
                    vertical = 0.5f;
                } else if (isGoingDown() && !isGoingUp()) {
                    vertical = -0.5f;
                }
                // Float in water for those can't swim is done in LivingEntity#aiStep on server side
                // Leave this handled by both side before we have a better solution
                this.setSpeed(speed);
                // Overwrite the zza in setSpeed
                this.setZza((float) forward);
                // Vanilla in water behavior includes float on water and moving very slow
                // in lava behavior includes moving slow and sink
                super.travel(pTravelVector.add(strafing, vertical, forward));

                return;
            }
            // Walking control
            else {
                double forward = rider.zza;
                double strafing = rider.xxa;
                // Inherit y motion for dropping
                double vertical = pTravelVector.y;

                float groundSpeedModifier = (float) (1.8F * this.getFlightSpeedModifier());
                speed *= groundSpeedModifier;
                // Try to match the original riding speed
                forward *= speed;
                // Faster sprint
                forward *= rider.isSprinting() ? 1.2f : 1.0f;
                // Slower going back
                forward *= rider.zza > 0 ? 1.0f : 0.2f;
                // Slower going sideway
                strafing *= 0.05f;

                if (this.isControlledByLocalInstance()) {
                    this.setSpeed(speed);

                    // Vanilla walking behavior includes going up steps
                    super.travel(new Vec3(strafing, vertical, forward));
                } else {
                    this.setDeltaMovement(Vec3.ZERO);
                }
                this.tryCheckInsideBlocks();
                return;
            }
        }
        // No rider move control
        else {
            super.travel(pTravelVector);
        }
    }

    int spacebarTicks = 0;

    public void updateRider() {
        Entity controllingPassenger = this.getControllingPassenger();

        if (controllingPassenger instanceof Player rider) {
            int ticksStill = 0;
            int hoverTicks = 0;
            int flyTicks = 0;

            if (this.isGoingUp()) {
                if (!this.isFlying() && !this.isHovering()) {
                    // Update spacebar tick for take off
                    spacebarTicks += 2;
                }
            }
            // Update spacebar ticks and take off
            if (this.spacebarTicks > 0) {
                this.spacebarTicks--;
            }
            // Hold spacebar 1 sec to take off
            for (int i = 0; i < OWNERS.size(); i++) {
                if (this.spacebarTicks > 20 && this.getPassengers().contains(OWNERS.get(i)) && !this.isFlying() && !this.isHovering()) {
                    if (!this.isInWater()) {
                        this.spacebarTicks = 0;
                    }
                }
            }

            // Shift key to dismount
            if (this.getControllingPassenger().isShiftKeyDown()) {
                if (this.getControllingPassenger() != null) this.getControllingPassenger().stopRiding();
            }
        }
    }

    @Override
    public void setDeltaMovement(Vec3 pMotion) {
        super.setDeltaMovement(pMotion);
    }

    @Override
    public void move(@NotNull MoverType pType, @NotNull Vec3 pPos) {
        if (this.isVehicle()) {
            // When riding, the server side movement check is performed in ServerGamePacketListenerImpl#handleMoveVehicle
            if (isControlledByLocalInstance()) {
                // it's done by server, however client does not fire server side events, so breakBlock() here won't work
                if (horizontalCollision) {
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.6F, 1, 0.6F));
                }
                super.move(pType, pPos);
            } else {
                // Use noPhysics tag to disable server side collision check
                this.noPhysics = true;
                super.move(pType, pPos);
            }
            // Set no gravity flag to prevent getting kicked by flight disabled servers
            if (this.isHovering() || this.isFlying()) {
                this.setNoGravity(true);
            } else {
                this.setNoGravity(false);
            }

        } else {
            this.noPhysics = false;
            // The flight mgr is not ready for noGravity
            this.setNoGravity(false);
            super.move(pType, pPos);
        }

    }


    public void updateCheckPlayer() {
        final double checkLength = this.getBoundingBox().getSize() * 3;
        final Player player = level.getNearestPlayer(this, checkLength);
        if (this.isSleeping()) {
            if (player != null && !player.isCreative()) {
                this.setTarget(player);
            }
        }
    }


    protected float riderWalkingExtraY = 0;

    public Vec3 getRiderPosition() {
        // The old position is seems to be given by a series compute of magic numbers
        // So I replace the number with an even more magical yet better one I tuned
        // The rider's hitbox and pov is now closer to its model, and less model clipping in first person

        final float headPosX = (float) (getX()* Mth.cos((float) ((getYRot() + 90) * Math.PI / 180)));
//        final float headPosY = (float) (getY() + (0.7F + sitProg + hoverProg + deadProg + sleepProg + flyProg + pitchY) * getRenderSize() * 0.3F + this.getScale() * 0.2F);
        final float headPosY = (float) (getY());
        final float headPosZ = (float) (getZ() * Mth.sin((float) ((getYRot() + 90) * Math.PI / 180)));
        return new Vec3(headPosX, headPosY, headPosZ);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity pPassenger) {
        return this.getRiderPosition().add(0, pPassenger.getBbHeight(), 0);
    }

    @Override
    public boolean save(@NotNull CompoundTag compound) {
        return this.saveAsPassenger(compound);
    }

    @Override
    public @NotNull Vec3 handleRelativeFrictionAndCalculateMovement(@NotNull Vec3 pDeltaMovement, float pFriction) {
        if (this.moveControl instanceof FlightManager.PlayerFlightMoveHelper)
            return pDeltaMovement;
        return super.handleRelativeFrictionAndCalculateMovement(pDeltaMovement, pFriction);
    }
}
