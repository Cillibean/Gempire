package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemShatterer extends Item {

    public ItemShatterer(Properties properties){
        super(properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity enemy, LivingEntity player) {
        if (enemy instanceof EntityGem) {
            ((EntityGem) enemy).setShatter(true);
            enemy.hurt(enemy.damageSources().magic(), enemy.getMaxHealth()*20);
            itemStack.hurtAndBreak(1, player, (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }
        return super.hurtEnemy(itemStack, enemy, player);
    }
}
