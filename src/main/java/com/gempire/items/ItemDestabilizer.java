package com.gempire.items;


import com.gempire.entities.bases.EntityGem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.authlib.GameProfile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemDestabilizer extends Item {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ItemDestabilizer(Properties properties){
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.9F, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public void poofGem(LivingEntity pTarget, Player player) {
        if (pTarget.isAlive()){
            if (pTarget instanceof EntityGem) {
                pTarget.hurt(DamageSource.GENERIC, pTarget.getMaxHealth());
            }
        }
    }
    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity enemy, LivingEntity player) {
        float f = 1F;
        if (player instanceof Player)
        {
            f = ((Player) player).getAttackStrengthScale(0f);
        }
        if (f == 1) {
            poofGem(enemy, (Player) player);
        }
        if (player instanceof Player) {
            if (enemy instanceof EntityGem && !((Player) player).isCreative()) {
                itemStack.setDamageValue(getDamage(itemStack) + 1);
            }
        }
        return super.hurtEnemy(itemStack, enemy, player);
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43383_) {
        return p_43383_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43383_);
    }
}