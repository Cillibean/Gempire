package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.init.ModSounds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ItemWhistle extends Item {
    public ItemWhistle(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && (hand == InteractionHand.MAIN_HAND)) {
            AABB aabb = player.getBoundingBox().inflate(25.0D);
            List<EntityGem> gems = level.getEntitiesOfClass(EntityGem.class, aabb);
            for (EntityGem gem : gems) {
                if (!gem.getRebelled() && !(gem.getSludgeAmount() >= 5) && gem.isOwner(player.getUUID())) {
                    gem.setPos(player.getX(), player.getY(), player.getZ());
                    player.getCooldowns().addCooldown(this, 200);
                    player.playSound(ModSounds.WHISTLE.get());
                }
            }
        }
        return super.use(level, player, hand);
    }
}
