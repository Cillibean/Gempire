package com.gempire.entities.gems.starter;

import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIMakePowerCrystal;
import com.gempire.entities.ai.EntityAIMakeShell;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityStarterGem;
import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class EntityNacre extends EntityStarterGem {
    public boolean hopperGoal = false;
    public int ticksDoingHopperGoal = 0;
    public int maxTicksDoingHopperGoal = 200;

    public EntityNacre(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 0);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.hopperGoal = compound.getBoolean("hopperGoal");
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("hopperGoal", this.hopperGoal);
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand){
        if(player.world.isRemote){
            return super.applyPlayerInteraction(player, vec, hand);
        }
        if(this.isOwner(player)){
            if(player.getHeldItem(hand).getItem() instanceof PickaxeItem){
                this.hopperGoal = true;
            }
        }
        return super.applyPlayerInteraction(player, vec, hand);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.hopperGoal){
            this.ticksDoingHopperGoal++;
            if(this.ticksDoingHopperGoal > this.maxTicksDoingHopperGoal){
                this.hopperGoal = false;
                this.ticksDoingHopperGoal = 0;
            }
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, CatEntity.class, 3.0F, 1.25D, 1.0D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, OcelotEntity.class, 3.0F, 1.25D, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(8, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(8, new EntityAIFollowOwner(this, 1.0D));
        this.goalSelector.addGoal(10, new EntityAIMakeShell(this, 1.0D));
    }

    @Override
    public GemPlacements[] getPlacements() {
        GemPlacements[] placement = new GemPlacements[]{
                GemPlacements.BACK_OF_HEAD, GemPlacements.LEFT_EYE, GemPlacements.RIGHT_EYE, GemPlacements.BACK, GemPlacements.CHEST,
                GemPlacements.LEFT_THIGH, GemPlacements.RIGHT_THIGH, GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.FOREHEAD
        };
        return placement;
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
                Abilities.NO_ABILITY
        };
    }
}
