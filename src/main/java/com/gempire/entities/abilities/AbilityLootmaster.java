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
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;

public class AbilityLootmaster extends Ability implements IViolentAbility, IMeleeAbility {

    public AbilityLootmaster() {
        this.ability = Abilities.LOOTMASTER;
    }

    @Override
    public void fight(LivingEntity entityIn, double damage) {
        /*ResourceLocation lootTable = entityIn.getLootTable();
        LootContext.Builder lootContextBuilder = new LootContext.Builder((ServerLevel) this.holder.getLevel());
        lootContextBuilder.withParameter(LootContextParams.DAMAGE_SOURCE, DamageSource.MAGIC);
        lootContextBuilder.withOptionalParameter(LootContextParams.DIRECT_KILLER_ENTITY, this.holder);
        lootContextBuilder.withOptionalParameter(LootContextParams.KILLER_ENTITY, this.holder);
        lootContextBuilder.withParameter(LootContextParams.THIS_ENTITY, entityIn);
        LootTable loottable = this.holder.getServer().getLootTables().get(lootTable);
        List<ItemStack> list = loottable.getRandomItems(lootContextBuilder.create(LootContextParamSets.ENTITY));
        ServerLevel serverlevel = (ServerLevel) this.holder.getLevel();
        list.forEach((itemStack) -> {
            ItemEntity itementity = new ItemEntity(serverlevel, this.holder.getX(), this.holder.getY(), this.holder.getZ(), itemStack.copy());
            itementity.setDefaultPickUpDelay();
            serverlevel.addFreshEntity(itementity);
        });*/
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.lootmaster");
    }

}
