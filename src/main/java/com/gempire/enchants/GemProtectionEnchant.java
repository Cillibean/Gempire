package com.gempire.enchants;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;

public class GemProtectionEnchant extends Enchantment {

    public GemProtectionEnchant(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot[] p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }

    public int getDamageProtection(int p_45133_, DamageSource p_45134_) {
        if (p_45134_.isBypassInvul()) {
            return 0;
        } else {
            return !p_45134_.isMagic()  && !p_45134_.isBypassEnchantments() ? p_45133_ * 2 : 0;
        }
    }

    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}
