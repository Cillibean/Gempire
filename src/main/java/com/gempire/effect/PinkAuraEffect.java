package com.gempire.effect;

import com.gempire.aura.PlayerAura;
import com.gempire.aura.PlayerAuraProvider;
import com.gempire.init.ModMessages;
import com.gempire.networking.AuraDataSyncS2C;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Arrays;

public class PinkAuraEffect extends MobEffect {

    public PinkAuraEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        if (entity instanceof Player) {
            entity.getCapability(PlayerAuraProvider.PLAYER_AURA).ifPresent(aura -> {
                aura.setAura(1);
            });
            if (!entity.level().isClientSide) {
                ModMessages.sendToPlayer(new AuraDataSyncS2C(1), (ServerPlayer) entity);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {

        return true;
    }

}
