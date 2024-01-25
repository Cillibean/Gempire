package com.gempire.enchants;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.UUID;

public class ShatterEnchant extends Enchantment {
    public ShatterEnchant(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot... p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        if (!attacker.level().isClientSide) {
            if (target instanceof EntityGem) {
                if (((EntityGem) target).getMaxHealth() >= 15) {
                    if (((EntityGem) target).getHealth() <= ((EntityGem) target).getMaxHealth()/2) {
                        if (((EntityGem) target).getRandom().nextInt(12 - (level * 2)) == 1) {
                            ((EntityGem) target).setShatter(true);
                            target.hurt(target.damageSources().magic(), ((EntityGem) target).getMaxHealth() * 20);
                        }
                    }
                }
            }
        }
        super.doPostAttack(attacker, target, level);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
