package com.gempire.effect;

import com.gempire.aura.PlayerAuraProvider;
import com.gempire.init.ModMessages;
import com.gempire.networking.AuraDataSyncS2C;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class DaggerPullEffect extends MobEffect {

    public DaggerPullEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

}
