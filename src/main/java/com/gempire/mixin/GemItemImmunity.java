package com.gempire.mixin;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*
@Mixin(ItemEntity.class)
public class TamableAnimalMixin {
    @Inject(
            method = "hurt()Lnet/minecraft/world/item/entity/ItemEntity;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/OwnableEntity;getOwnerUUID()Ljava/util/UUID;", shift = At.Shift.AFTER),
            cancellable = true
    )
    private void gempire_gemItemImmunity(CallbackInfoReturnable<ItemEntity> cir) {
        ItemEntity tamableAnimal = ((Item) this);
        UUID uuid = tamableAnimal.getOwnerUUID();
        if (uuid != null && !((Level) tamableAnimal.level()).isClientSide) {
            Entity owner = ((ServerLevel) tamableAnimal.level()).getEntity(uuid);
            if (owner instanceof EntityGem gem)
                cir.setReturnValue((OwnableEntity) gem);
        }
    }
}*/
