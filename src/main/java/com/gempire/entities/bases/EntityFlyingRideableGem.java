package com.gempire.entities.bases;

import com.gempire.entities.abilities.AbilityVehicle;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.util.FlightMoveController;
import com.gempire.entities.util.GemBodyController;
import com.gempire.keybindings.KeyBindings;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityFlyingRideableGem extends EntityGem implements FlyingAnimal, PlayerRideable {


    private boolean flying;
    private boolean nearGround;

    private final GroundPathNavigation groundNavigation;
    private final FlyingPathNavigation flyingNavigation;

    public EntityFlyingRideableGem(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        noCulling = true;

        moveControl = new FlightMoveController(this);

        flyingNavigation = new FlyingPathNavigation(this, level());
        groundNavigation = new GroundPathNavigation(this, level());

        flyingNavigation.setCanFloat(true);
        groundNavigation.setCanFloat(true);

        navigation = groundNavigation;
    }

    @Override
    @NotNull
    public BodyRotationControl createBodyControl()
    {
        return new GemBodyController(this);
    }

    public boolean shouldFly()
    {
        if (isFlying()) return !onGround(); // more natural landings
        return !isInWater() && !isNearGround();
    }

    @Override
    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying)
    {
        this.flying = flying;
    }

    public boolean isNearGround()
    {
        return nearGround;
    }

    public void setNavigation(boolean flying)
    {
        navigation = flying ?
                flyingNavigation :
                groundNavigation;
    }

    @Override
    public void tick() {
        super.tick();
        nearGround = onGround() || !level().noCollision(this, new AABB(getX(), getY(), getZ(), getX(), getY() - (3 * getScale()), getZ()));

        boolean flying = shouldFly();
        if (flying != isFlying())
        {
            setFlying(flying);

            // update pathfinding method
            if (!level().isClientSide) setNavigation(flying);
        }
    }

    @Override
    public void travel(Vec3 vec3)
    {
        boolean isFlying = isFlying();
        float speed = (float) getAttributeValue(isFlying? Attributes.FLYING_SPEED : Attributes.MOVEMENT_SPEED) * 0.225f;

        if (canBeControlledByRider()) // Were being controlled; override ai movement
        {
            if (getControllingPassenger() instanceof LivingEntity) {
                LivingEntity driver = (LivingEntity) getControllingPassenger();
                double moveSideways = vec3.x;
                double moveY = vec3.y;
                //noinspection ConstantConditions
                double moveForward = Math.min(Math.abs(driver.zza) + Math.abs(driver.xxa), 1);

                // rotate head to match driver.
                float yaw = driver.yHeadRot;
                if (moveForward > 0) // rotate in the direction of the drivers controls
                    yaw += (float) Mth.atan2(driver.zza, driver.xxa) * (180f / (float) Math.PI) - 90;
                yHeadRot = yaw;
                setXRot(driver.getXRot() * 0.68f);

                // rotate body towards the head
                setYRot(Mth.rotateIfNecessary(yHeadRot, getYRot(), 4));

                if (isControlledByLocalInstance()) // Client applies motion
                {
                    if (isFlying) {
                        moveForward = moveForward > 0 ? moveForward : 0;
                        moveY = 0;
                        //if (KeyBindings.FLIGHT_ASCENT_KEY.isDown()) moveY = 1;
                        if (KeyBindings.FLIGHT_DESCENT_KEY.isDown()) moveY = -1;
                        else if (moveForward > 0)
                            moveY = -driver.getXRot() * (Math.PI / 180);
                    }

                    vec3 = new Vec3(moveSideways, moveY, moveForward);
                    setSpeed(speed);
                } else if (driver instanceof Player) // other clients recieve animations
                {
                    //calculateEntityAnimation(this, true);
                    setDeltaMovement(Vec3.ZERO);
                    return;
                }
            }

            if (isFlying) {
                // Move relative to yaw - handled in the move controller or by driver
                moveRelative(speed, vec3);
                move(MoverType.SELF, getDeltaMovement());
                if (getDeltaMovement().lengthSqr() < 0.1) // we're not actually going anywhere, bob up and down.
                    setDeltaMovement(getDeltaMovement().add(0, Math.sin(tickCount / 4f) * 0.03, 0));
                setDeltaMovement(getDeltaMovement().scale(0.9f)); // smoothly slow down

            }
        }
        else super.travel(vec3);
    }

    @Override
    protected void tickDeath()
    {
        // unmount any riding entities
        ejectPassengers();

        // freeze at place
        setDeltaMovement(Vec3.ZERO);
        setYRot(yRotO);
        setYHeadRot(yHeadRotO);
        remove(RemovalReason.KILLED); // actually delete entity after the time is up
    }

    @Override
    public double getPassengersRidingOffset()
    {
        return getBbHeight() - 0.175;
    }

    @Override
    public boolean canBeControlledByRider()
    {
        return getControllingPassenger() != null;
    }

    @Override
    public LivingEntity getControllingPassenger()
    {
        List<Entity> list = getPassengers();
        return list.isEmpty()? null : (LivingEntity) list.get(0);
    }

    public void setRidingPlayer(Player player)
    {
        player.setYRot(getYRot());
        player.setXRot(getXRot());
        player.startRiding(this);
    }


    public ArrayList<Ability> definiteAbilities() {
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new AbilityVehicle());
        return arrayList;
    }


}
