package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.util.GempireAbilities;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;

import java.util.List;

import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class AbilityAngler extends Ability implements IIdleAbility {

    public AbilityAngler(){
        super(27, 5);
        this.ability = GempireAbilities.ANGLER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.angler");
    }

    @Override
    public void execute() {
        if (this.holder.tickCount % 200 == 0) {
            if (this.holder.isInWater()) {
                ItemStack rod = new ItemStack(Items.FISHING_ROD);
                rod.enchant(Enchantments.FISHING_LUCK, 1);
                LootTable loottable = this.holder.level.getServer().getLootTables().get(BuiltInLootTables.FISHING);
                List<ItemStack> items = loottable.getRandomItems((new LootContext.Builder((ServerLevel) this.holder.level))
                        .withLuck(this.holder.getLuck())
                        .withRandom(this.holder.getRandom())
                        .withParameter(LootContextParams.TOOL, rod)
                        .withParameter(LootContextParams.ORIGIN, this.holder.position())
                        .create(LootContextParamSets.FISHING));
                for (ItemStack stack : items) {
                    this.holder.spawnAtLocation(stack);
                    this.holder.playSound(this.holder.getInstrument());
                }
            }
        }
    }
}
