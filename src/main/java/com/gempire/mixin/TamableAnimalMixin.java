package com.gempire.mixin;

import com.gempire.entities.bases.EntityGem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(TamableAnimal.class)
public class TamableAnimalMixin {
    @Inject(
            method = "getOwner()Lnet/minecraft/world/entity/LivingEntity;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/TamableAnimal;getOwnerUUID()Ljava/util/UUID;", shift = At.Shift.AFTER),
            cancellable = true
    )
    private void gempire_tamableAnimalMixin(CallbackInfoReturnable<LivingEntity> cir) {
        TamableAnimal tamableAnimal = ((TamableAnimal) (Object) this);
        UUID uuid = tamableAnimal.getOwnerUUID();
        if (uuid != null && !tamableAnimal.level.isClientSide) {
            Entity owner = ((ServerLevel) tamableAnimal.getLevel()).getEntity(uuid);
            if (owner instanceof EntityGem gem)
                cir.setReturnValue(gem);
        }
    }
}
