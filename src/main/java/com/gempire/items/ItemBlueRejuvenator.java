package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityZircon;
import com.gempire.init.ModEnchants;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ItemBlueRejuvenator extends DestabBase {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ItemBlueRejuvenator(Properties properties){
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -3F, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public void poofGem(LivingEntity pTarget) {
        if (pTarget.isAlive()) {
            if (pTarget instanceof EntityGem) {
                ((EntityGem) pTarget).setAbilities(((EntityGem) pTarget).generateAbilities());
                if (pTarget instanceof EntityZircon)
                {
                    ((EntityZircon) pTarget).setEnchantPage(RandomSource.create().nextInt(ModEnchants.VANILLA_ENCHANTMENTS.size()));
                }
                pTarget.hurt(DamageSource.GENERIC, pTarget.getMaxHealth() * 2);
            }
        }
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43383_) {
        return p_43383_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43383_);
    }
}
