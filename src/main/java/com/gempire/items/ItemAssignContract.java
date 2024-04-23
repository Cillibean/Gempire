package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemAssignContract extends Item {
    public ItemAssignContract(Properties properties) {
        super(properties);
    }

    public void execute(ItemStack itemStack, Player player, LivingEntity entity)
    {
        {
            if (checkTags(itemStack)) {
                if (((EntityGem) entity).ASSIGNED_ID == getGemUUID(itemStack)) {
                    ((EntityGem) entity).ASSIGNED_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
                    ((EntityGem) entity).setAssigned(false);
                    ((EntityGem) entity).setAssignedGem(null);
                    ((EntityGem) entity).setAssignedUtil(null, null, null);
                    player.sendSystemMessage(Component.translatable("Assigned ").append(entity.getName()).append(" to ").append(Component.literal((getGemName(itemStack)))));
                } else {
                    ((EntityGem) entity).ASSIGNED_ID = getGemUUID(itemStack);
                    ((EntityGem) entity).setAssigned(true);
                    ((EntityGem) entity).setAssignedUtil(getGemName(itemStack), getGemFacet(itemStack), getGemCut(itemStack));
                    player.sendSystemMessage(Component.translatable("Assigned ").append(entity.getName()).append(" to ").append(Component.literal(getGemName(itemStack))));
                }

            } else {
                player.sendSystemMessage(Component.translatable("No gem set as owner"));
            }
        }
    }

    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity entity, InteractionHand hand) {
        if ((entity instanceof EntityGem) && checkTags(itemStack) && itemStack.getTag().getUUID("gemUUID") != UUID.fromString("00000000-0000-0000-0000-000000000000")) {
            if (((EntityGem) entity).getOwned()) {
                if (!entity.level().isClientSide && entity.isAlive()) {
                    if (((EntityGem) entity).OWNERS.contains(player.getUUID())) {
                        execute(itemStack, player, entity);
                    } else {
                        player.sendSystemMessage(Component.translatable("Not Owner"));
                    }
                }
            }
            return InteractionResult.sidedSuccess(player.level().isClientSide);
        } else if ((entity instanceof EntityGem)) {
            if (!entity.level().isClientSide && entity.isAlive()) {
                player.setItemInHand(hand,setEntityTags(itemStack,entity.getName().getString(), ((EntityGem) entity).getFacet(), ((EntityGem) entity).getCut(), entity.getUUID()));
                player.sendSystemMessage(Component.translatable("Set ").append(entity.getName()).append(" as owner"));
            }
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
                    player.sendSystemMessage(Component.translatable("Cleared Gem"));
                }
            }
        }
        return super.use(world, player, hand);
    }

    public ItemStack setEntityTags(ItemStack item, String name, String facet, String cut, UUID uuid) {
        CompoundTag tag = item.getOrCreateTag();
        item.setTag(tag);
        tag.putString("gemName", name);
        tag.putString("gemFacet", facet);
        tag.putString("gemCut", cut);
        tag.putUUID("gemUUID", uuid);
        return item;
    }
    public boolean checkTags(ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getTag();
        return tag != null;
    }
    public String getGemName(ItemStack item)
    {
        return item.getTag().getString("gemName");
    }

    public String getGemFacet(ItemStack item)
    {
        return item.getTag().getString("gemFacet");
    }

    public String getGemCut(ItemStack item)
    {
        return item.getTag().getString("gemCut");
    }
    public UUID getGemUUID(ItemStack item)
    {
        return item.getTag().getUUID("gemUUID");
    }
    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return checkTags(p_41453_);
    }
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> p_40553_, TooltipFlag p_40554_) {
        if (checkTags(itemStack)) {
            p_40553_.add(Component.literal(this.getGemName(itemStack)).withStyle(ChatFormatting.GRAY));
        }
    }
}
