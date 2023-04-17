package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ItemTransferContract extends ItemContractBase{
    public ItemTransferContract(Properties properties) {
        super(properties);
    }

    @Override
    public void contractExecute(ItemStack itemStack, Player player, LivingEntity entity)
    {
        if (checkTags(itemStack)) {
            ((EntityGem) entity).OWNERS.remove(player.getUUID());
            ((EntityGem) entity).MASTER_OWNER = getPlayerUUID(itemStack);
            ((EntityGem) entity).OWNERS.add(getPlayerUUID(itemStack));
            player.sendSystemMessage(Component.translatable("Transferred ").append(entity.getName()).append(" to ").append(Component.literal((getPlayerName(itemStack)))));
        }
        else {
            player.sendSystemMessage(Component.translatable("No Player set to Contract"));
        }
    }
}
