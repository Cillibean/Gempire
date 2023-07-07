package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class AbilityDisarming extends Ability implements IViolentAbility, IMeleeAbility {

    public AbilityDisarming() {
        super(36, 3);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.disarming");
    }

    @Override
    public void fight(LivingEntity entityIn, double damage) {
        if (!(entityIn instanceof Player)) {
            if (!entityIn.level.isClientSide) {
                if (holder.getRandom().nextFloat() < 0.25f) {
                    ItemStack item = entityIn.getItemBySlot(EquipmentSlot.MAINHAND);
                    if (item.isDamageableItem()) {
                        item.setDamageValue(item.getMaxDamage() - holder.getRandom().nextInt(1 + holder.getRandom().nextInt(Math.max(item.getMaxDamage() - 3, 1))));
                    }
                    ItemEntity dropitem = new ItemEntity(entityIn.level, entityIn.getX(), entityIn.getY(), entityIn.getZ(), item);
                    entityIn.level.addFreshEntity(dropitem);
                    entityIn.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                }
            }
        } else {
            if (!entityIn.level.isClientSide) {
                if (holder.getRandom().nextFloat() < 0.25f) {
                    ItemStack item = entityIn.getItemBySlot(EquipmentSlot.MAINHAND);
                    if (item.isDamageableItem()) {
                        item.setDamageValue(item.getMaxDamage() - holder.getRandom().nextInt(1 + holder.getRandom().nextInt(Math.max(item.getMaxDamage() - 3, 1))));
                    }
                    ItemEntity dropitem = new ItemEntity(entityIn.level, entityIn.getX(), entityIn.getY(), entityIn.getZ(), item);
                    entityIn.level.addFreshEntity(dropitem);
                    entityIn.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                }
            }
        }
    }
}
