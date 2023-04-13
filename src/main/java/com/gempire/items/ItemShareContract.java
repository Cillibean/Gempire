package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ItemShareContract extends ItemContractBase{
    public ItemShareContract(Properties properties) {
        super(properties);
    }

    @Override
    public void contractExecute(ItemStack itemStack, Player player, LivingEntity entity)
    {
        {
            if (checkTags(itemStack)) {
                if (((EntityGem) entity).OWNERS.contains(getPlayerUUID(itemStack))) {
                    ((EntityGem) entity).OWNERS.remove(getPlayerUUID(itemStack));
                    player.sendSystemMessage(Component.translatable("Stopped sharing ").append(entity.getName()).append(" with ").append(Component.literal((getPlayerName(itemStack)))));
                } else {
                    ((EntityGem) entity).OWNERS.add(getPlayerUUID(itemStack));
                    player.sendSystemMessage(Component.translatable("Shared ").append(entity.getName()).append(" with ").append(Component.literal(getPlayerName(itemStack))));
                }

            } else {
                player.sendSystemMessage(Component.translatable("No Player set to Contract"));
            }
        }
    }
}
