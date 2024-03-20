package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.other.*;
import com.gempire.init.ModItems;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DestabBase extends Item {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public DestabBase(Properties properties){
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.9F, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public void poofGem(LivingEntity pTarget) {
        if (pTarget.isAlive()){
            System.out.println("alive");
            if (pTarget instanceof EntityGem) {
                pTarget.hurt(pTarget.damageSources().magic(), pTarget.getMaxHealth()*20);
                ((EntityGem) pTarget).setCracked(false);
                System.out.println("poofed");
            } else if (pTarget instanceof EntityFuchsiaPaladin) {
                ItemStack stack = new ItemStack(ModItems.PALADIN_FLOWER.get());
                ItemEntity item = new ItemEntity(pTarget.level(), pTarget.getX(), pTarget.getY(), pTarget.getZ(), stack);
                pTarget.level().addFreshEntity(item);
                pTarget.remove(Entity.RemovalReason.DISCARDED);
            } else if (pTarget instanceof EntityCobaltGuardian) {
                ItemStack stack = new ItemStack(ModItems.GUARDIAN_TEAR.get());
                ItemEntity item = new ItemEntity(pTarget.level(), pTarget.getX(), pTarget.getY(), pTarget.getZ(), stack);
                pTarget.level().addFreshEntity(item);
                pTarget.remove(Entity.RemovalReason.DISCARDED);
            } else if (pTarget instanceof EntityAmberHuntress) {
                ItemStack stack = new ItemStack(ModItems.HUNTRESS_DAGGER.get());
                ItemEntity item = new ItemEntity(pTarget.level(), pTarget.getX(), pTarget.getY(), pTarget.getZ(), stack);
                pTarget.level().addFreshEntity(item);
                pTarget.remove(Entity.RemovalReason.DISCARDED);
            } else if (pTarget instanceof EntityAlabasterEmpress) {
                ItemStack stack = new ItemStack(ModItems.EMPRESS_STAR.get());
                ItemEntity item = new ItemEntity(pTarget.level(), pTarget.getX(), pTarget.getY(), pTarget.getZ(), stack);
                pTarget.level().addFreshEntity(item);
                pTarget.remove(Entity.RemovalReason.DISCARDED);
            } /*else if (pTarget instanceof EntityIridescentPaladin) {
                ItemStack stack = new ItemStack(ModItems.INACTIVE_PALADIN_GEM.get());
                ItemEntity item = new ItemEntity(pTarget.level(), pTarget.getX(), pTarget.getY(), pTarget.getZ(), stack);
                pTarget.level().addFreshEntity(item);
                pTarget.remove(Entity.RemovalReason.DISCARDED);
            } else if (pTarget instanceof EntityMirroredGuardian) {
                ItemStack stack = new ItemStack(ModItems.INACTIVE_GUARDIAN_GEM.get());
                ItemEntity item = new ItemEntity(pTarget.level(), pTarget.getX(), pTarget.getY(), pTarget.getZ(), stack);
                pTarget.level().addFreshEntity(item);
                pTarget.remove(Entity.RemovalReason.DISCARDED);
            } else if (pTarget instanceof EntityGildedHuntress) {
                ItemStack stack = new ItemStack(ModItems.INACTIVE_HUNTRESS_GEM.get());
                ItemEntity item = new ItemEntity(pTarget.level(), pTarget.getX(), pTarget.getY(), pTarget.getZ(), stack);
                pTarget.level().addFreshEntity(item);
                pTarget.remove(Entity.RemovalReason.DISCARDED);
            } else if (pTarget instanceof EntityPrismaticEmpress) {
                ItemStack stack = new ItemStack(ModItems.INACTIVE_EMPRESS_GEM.get());
                ItemEntity item = new ItemEntity(pTarget.level(), pTarget.getX(), pTarget.getY(), pTarget.getZ(), stack);
                pTarget.level().addFreshEntity(item);
                pTarget.remove(Entity.RemovalReason.DISCARDED);
            }*/
        }
    }
    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity enemy, LivingEntity player) {
        System.out.println("hurt enemy");
        float f = 1F;
        if (player instanceof Player)
        {
            f = ((Player) player).getAttackStrengthScale(0f);
        }
        if (f == 1) {
            System.out.println("f 1");
            System.out.println("begin poof");
            poofGem(enemy);
            itemStack.hurtAndBreak(1, player, (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }
        return super.hurtEnemy(itemStack, enemy, player);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43383_) {
        return p_43383_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43383_);
    }

    public boolean mineBlock(ItemStack p_43282_, Level p_43283_, BlockState p_43284_, BlockPos p_43285_, LivingEntity p_43286_) {
        if (p_43284_.getDestroySpeed(p_43283_, p_43285_) != 0.0F) {
            p_43282_.hurtAndBreak(2, p_43286_, (p_43276_) -> {
                p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_41134_, ItemStack p_41135_) {
        return p_41135_.is(ModItems.POWER_CRYSTAL_BLOCK_ITEM.get());
    }
}
