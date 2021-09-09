package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAreaAbility;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.util.Abilities;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AbilityAngler extends Ability implements IIdleAbility {

    public AbilityAngler(){
        this.ability = Abilities.ANGLER;
    }

    @Override
    public ITextComponent getName() {
        return new TranslationTextComponent("ability.gempire.angler");
    }

    @Override
    public void execute() {
        if (this.holder.ticksExisted % 40 == 0) {
            if (this.holder.isInWater()) {
                ItemStack rod = new ItemStack(Items.FISHING_ROD);
                rod.addEnchantment(Enchantments.LUCK_OF_THE_SEA, 4);
                LootTable loottable = this.holder.world.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
                List<ItemStack> items = loottable.generate((new LootContext.Builder((ServerWorld) this.holder.world))
                        .withLuck(this.holder.getLuck())
                        .withRandom(this.holder.getRNG())
                        .withParameter(LootParameters.TOOL, rod)
                        .withParameter(LootParameters.field_237457_g_, this.holder.getPositionVec())
                        .build(LootParameterSets.FISHING));
                for (ItemStack stack : items) {
                    this.holder.entityDropItem(stack);
                }
            }
        }
    }
}
