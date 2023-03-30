package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.entities.bases.EntityGem;
import com.gempire.util.Abilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class AbilityBeastmaster extends Ability implements IIdleAbility {
    List<Wolf> tamed_wolves = new ArrayList<>();

    public AbilityBeastmaster(){
        this.ability = Abilities.BEASTMASTER;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.beastmaster");
    }

    @Override
    public void execute() {
        int timer = 100;
        if (holder.getOwned()) {
        Player player = this.holder.currentPlayer;
        BlockPos pos = this.holder.getOnPos();
        AABB aabb = this.holder.getBoundingBox().inflate(12.0D);
        List<Wolf> wolves = this.holder.getLevel().getEntitiesOfClass(Wolf.class, aabb);
            if (timer >= 0) {
                for(Wolf wolf : wolves){
                    System.out.println("tame attempt");
                    wolf.tame(player);
                    tamed_wolves.add(wolf);
                    timer = 100;
                }
            } else {
                timer--;
            }
        }
    }
}
