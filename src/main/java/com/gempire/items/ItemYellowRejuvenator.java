package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ItemYellowRejuvenator extends ItemDestabilizer {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ItemYellowRejuvenator(Properties properties){
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -3F, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public void poofGem(LivingEntity pTarget) {
        if (pTarget.isAlive()) {
            if (pTarget instanceof EntityGem) {
                ((EntityGem) pTarget).setHairVariant(((EntityGem) pTarget).generateHairVariant());
                ((EntityGem) pTarget).setOutfitVariant(((EntityGem) pTarget).generateOutfitVariant());
                ((EntityGem) pTarget).setInsigniaVariant(((EntityGem) pTarget).generateInsigniaVariant());
                pTarget.hurt(DamageSource.GENERIC, pTarget.getMaxHealth() * 2);
            }
        }
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43383_) {
        return p_43383_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43383_);
    }
}
