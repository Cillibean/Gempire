package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IMeleeAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.items.ItemGem;
import com.gempire.util.Abilities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AbilityDisarming extends Ability{

    public AbilityDisarming() {
        this.ability = Abilities.DISARMING;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.disarming");
    }

}
