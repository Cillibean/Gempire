package com.gempire.items.tools;

import com.gempire.entities.projectiles.ElectrokinesisLightning;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.Vec3;

public class ItemHuntressSword extends SwordItem {
    public ItemHuntressSword(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity player) {
        if (player.getRandom().nextInt(5) == 1) {
                ElectrokinesisLightning lightning = new ElectrokinesisLightning(player.level(), player, target);
                lightning.moveTo(Vec3.atBottomCenterOf(target.getOnPos()));
                target.level().addFreshEntity(lightning);
                target.hurt(player.damageSources().lightningBolt(), 5);
                stack.hurtAndBreak(2, player, (player1) -> {
                    player1.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                });
        }
        return super.hurtEnemy(stack, target, player);
    }
}
