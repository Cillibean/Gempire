package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.util.PaletteType;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ItemWhiteRejuvenator extends DestabBase {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ItemWhiteRejuvenator(Properties properties){
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -3F, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public void poofGem(LivingEntity pTarget) {
        if (pTarget.isAlive()) {
            if (pTarget instanceof EntityGem) {
                ((EntityGem) pTarget).setSkinColor(((EntityGem) pTarget).generatePaletteColor(PaletteType.SKIN));
                ((EntityGem) pTarget).setHairColor(((EntityGem) pTarget).generatePaletteColor(PaletteType.HAIR));
                ((EntityGem) pTarget).setGemColor(((EntityGem) pTarget).generatePaletteColor(PaletteType.GEM));
                if (((EntityGem) pTarget).hasMarkings())
                {
                    ((EntityGem) pTarget).setMarking2Color(((EntityGem) pTarget).generatePaletteColor(PaletteType.MARKINGS_2));
                }
                if (((EntityGem) pTarget).hasMarkings())
                {
                    ((EntityGem) pTarget).setMarkingColor(((EntityGem) pTarget).generatePaletteColor(PaletteType.MARKINGS));
                }
                ((EntityGem) pTarget).rebelPoints += 0.5F;
                pTarget.hurt(DamageSource.playerAttack(((EntityGem) pTarget).currentPlayer), pTarget.getMaxHealth() + 20);
                ((EntityGem) pTarget).setCracked(false);
            }
        }
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43383_) {
        return p_43383_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43383_);
    }
}
