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
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.ArrayList;

public class EntityShale extends EntityStarterGem {

    public EntityShale(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 0);
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand){
        if(player.world.isRemote){
            return super.applyPlayerInteraction(player, vec, hand);
        }
        if(this.isOwner(player)) {
            if (player.getHeldItem(hand).getItem() == ModItems.ESSENCE_BOTTLE.get()) {
                if (player.experienceTotal >= 40) {
                    EntityShale.decreaseExp(player, 40);
                    player.getHeldItem(hand).shrink(1);
                    int rand = this.rand.nextInt(4);
                    if (rand == 0) {
                        player.addItemStackToInventory(new ItemStack(ModItems.PINK_ESSENCE.get()));
                    } else if (rand == 1) {
                        player.addItemStackToInventory(new ItemStack(ModItems.BLUE_ESSENCE.get()));
                    } else if (rand == 2) {
                        player.addItemStackToInventory(new ItemStack(ModItems.YELLOW_ESSENCE.get()));
                    } else {
                        player.addItemStackToInventory(new ItemStack(ModItems.WHITE_ESSENCE.get()));
                    }
                }
            }
        }
        return super.applyPlayerInteraction(player, vec, hand);
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

    public static void decreaseExp(PlayerEntity player, float amount) {
        if (player.experienceTotal - amount <= 0)
        {
            player.experienceLevel = 0;
            player.experience = 0;
            player.experienceTotal = 0;
            return;
        }
        player.experienceTotal -= amount;
        if (player.experience * (float)player.xpBarCap() <= amount)
        {
            amount -= player.experience * (float)player.xpBarCap();
            player.experience = 1.0f;
            player.experienceLevel--;
        }
        while (player.xpBarCap() < amount)
        {
            amount -= player.xpBarCap();
            player.experienceLevel--;
        }
        player.experience -= amount / (float)player.xpBarCap();
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
}
