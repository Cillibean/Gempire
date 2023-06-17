package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.event.entity.living.LootingLevelEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbilityLootmaster extends Ability implements IViolentAbility, IMeleeAbility {

    public AbilityLootmaster() {
        this.ability = Abilities.LOOTMASTER;
    }

    @Override
    public void fight(LivingEntity entityIn, double damage) {
        if (entityIn.isDeadOrDying()) {
            if (entityIn.getLastHurtByMob() == this.holder) {
                if (this.holder.isPrimary())
                    new LootingLevelEvent(entityIn, DamageSource.mobAttack(this.holder), 4);
                else if (!this.holder.isDefective())
                    new LootingLevelEvent(entityIn, DamageSource.mobAttack(this.holder), 2);
            }
        }
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.lootmaster");
    }

}
