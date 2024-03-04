package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModPacketHandler;
import com.gempire.networking.C2SFusionStick;
import com.gempire.networking.C2SRequestHairChange;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;
import java.util.UUID;

public class ItemFusionStick extends Item {
    public ItemFusionStick(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        if (entity instanceof EntityGem) {
            //if (((EntityGem) entity).isOwner(player)) {
                if (player.isShiftKeyDown()) {
                    if (player.level().isClientSide()) {
                        System.out.println("got id");
                        stack.getOrCreateTag().putUUID("id", entity.getUUID());
                    }
                } else {
                    if (entity.level().isClientSide) {
                        System.out.println("fuse attempt");
                        ModPacketHandler.INSTANCE.sendToServer(new C2SFusionStick(entity.getId(), stack.getOrCreateTag().getUUID("id").hashCode()));
                    }
                }
            //}
        }
        return super.interactLivingEntity(stack, player, entity, hand);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        return super.use(p_41432_, p_41433_, p_41434_);
    }
}
