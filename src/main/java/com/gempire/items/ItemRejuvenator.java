package com.gempire.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.Item.Properties;

public class ItemRejuvenator extends PoweredItem {

    public ItemRejuvenator(Properties properties){
        super(properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        use(stack);
        return super.hurtEnemy(stack, target, attacker);
    }
}
