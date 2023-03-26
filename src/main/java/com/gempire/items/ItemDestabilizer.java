package com.gempire.items;


import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntitySpinel;
import com.gempire.entities.gems.EntitySpodumene;
import com.gempire.entities.gems.EntityTopaz;
import com.gempire.entities.gems.EntityTourmaline;
import com.gempire.networking.C2SRequestPoof;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ItemDestabilizer extends Item {
    public ItemDestabilizer(Properties properties){
        super(properties);
    }

    public void poofGem(LivingEntity pTarget, LivingEntity pAttacker) {
        if (pTarget.isAlive()){
            if (pTarget instanceof EntityGem) {
                pTarget.hurt(DamageSource.GENERIC, pTarget.getMaxHealth());
            }
        }
    }
    @Override
    public InteractionResult interactLivingEntity(ItemStack item, Player player, LivingEntity entity, InteractionHand hand) {
        if (!player.getCooldowns().isOnCooldown(this)) {
            poofGem(entity, player);
            player.getCooldowns().addCooldown(this, 100);
        }
        return super.interactLivingEntity(item, player, entity, hand);
    }
}
