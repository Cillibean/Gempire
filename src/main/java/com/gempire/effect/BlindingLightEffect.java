package com.gempire.effect;

import com.gempire.aura.PlayerAuraProvider;
import com.gempire.init.ModMessages;
import com.gempire.networking.AuraDataSyncS2C;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class BlindingLightEffect extends MobEffect {

    public BlindingLightEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        if (entity instanceof Player) {
            entity.getCapability(PlayerAuraProvider.PLAYER_AURA).ifPresent(aura -> {
                aura.setAura(5);
            });
            if (!entity.level().isClientSide) {
                ModMessages.sendToPlayer(new AuraDataSyncS2C(5), (ServerPlayer) entity);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
