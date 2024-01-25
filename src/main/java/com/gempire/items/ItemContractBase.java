package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemContractBase extends Item {

    public ItemContractBase(Properties properties) {
        super(properties);
    }

    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity entity, InteractionHand hand) {
        if ((entity instanceof EntityGem)) {
            if (((EntityGem) entity).getOwned()) {
                if (!entity.level().isClientSide && entity.isAlive()) {
                    if (player.getUUID().equals(((EntityGem) entity).MASTER_OWNER)) {
                        this.contractExecute(itemStack, player, entity);
                    } else {
                        player.sendSystemMessage(Component.translatable("Not Master Owner"));
                    }
                }
            }
            return InteractionResult.sidedSuccess(player.level().isClientSide);
        }
        else if ((entity instanceof Player)) {
            if (!entity.level().isClientSide && entity.isAlive()) {
                player.setItemInHand(hand,setEntityTags(itemStack,entity.getName().getString(),entity.getUUID()));
                player.sendSystemMessage(Component.translatable("Set Player"));}
            return InteractionResult.sidedSuccess(player.level().isClientSide);
        }
        else {
            return InteractionResult.PASS;
        }
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            if (player.isCrouching()) {
                ItemStack itemStack = player.getItemInHand(hand);
                if (checkTags(itemStack)) {
                    player.setItemInHand(hand, new ItemStack(this.asItem()));
                    player.sendSystemMessage(Component.translatable("Cleared Player"));
                }
            }
        }
        return super.use(world, player, hand);
    }
    public void contractExecute(ItemStack itemStack, Player player, LivingEntity entity)
    {

    }
    public ItemStack setEntityTags(ItemStack item, String name, UUID uuid) {
        CompoundTag tag = item.getOrCreateTag();
        item.setTag(tag);
        tag.putString("playerName", name);
        tag.putUUID("playerUUID", uuid);
        return item;
    }
    public boolean checkTags(ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getTag();
        return tag != null;
    }
    public String getPlayerName(ItemStack item)
    {
        return item.getTag().getString("playerName");
    }
    public UUID getPlayerUUID(ItemStack item)
    {
        return item.getTag().getUUID("playerUUID");
    }
    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return checkTags(p_41453_);
    }
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> p_40553_, TooltipFlag p_40554_) {
        if (checkTags(itemStack)) {
            p_40553_.add(Component.literal(this.getPlayerName(itemStack)).withStyle(ChatFormatting.GRAY));
        }
    }
}
