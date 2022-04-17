package com.gempire.entities.gems.starter;

import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityStarterGem;
import com.gempire.entities.gems.EntityRuby;
import com.gempire.init.ModItems;
import com.gempire.systems.injection.Crux;
import com.gempire.util.Abilities;
import com.gempire.util.Color;
import com.gempire.util.CruxType;
import com.gempire.util.GemPlacements;
import net.minecraft.block.Blocks;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

public class EntityShale extends EntityStarterGem {

    public EntityShale(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Cat.class, 3.0F, 1.25D, 1.0D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Ocelot.class, 3.0F, 1.25D, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(8, new EntityAIFollowOwner(this, 1.0D));
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

    public static void decreaseExp(Player player, float amount) {
        if (player.totalExperience - amount <= 0)
        {
            player.experienceLevel = 0;
            player.experienceProgress = 0;
            player.totalExperience = 0;
            return;
        }
        player.totalExperience -= amount;
        if (player.experienceProgress * (float)player.getXpNeededForNextLevel() <= amount)
        {
            amount -= player.experienceProgress * (float)player.getXpNeededForNextLevel();
            player.experienceProgress = 1.0f;
            player.experienceLevel--;
        }
        while (player.getXpNeededForNextLevel() < amount)
        {
            amount -= player.getXpNeededForNextLevel();
            player.experienceLevel--;
        }
        player.experienceProgress -= amount / (float)player.getXpNeededForNextLevel();
    }

    @Override
    public int generateAbilitySlots() {
        return 1;
    }

    @Override
    public Abilities[] definiteAbilities() {
        return new Abilities[]{
                Abilities.ESSENCE_BREWER
        };
    }

    @Override
    public boolean canOpenInventoryByDefault() {
        return true;
    }
}
