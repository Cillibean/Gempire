package com.gempire.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRejuvenator extends PoweredItem {

    public ItemRejuvenator(Properties properties){
        super(properties);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        use(stack);
        return super.hitEntity(stack, target, attacker);
    }
}
