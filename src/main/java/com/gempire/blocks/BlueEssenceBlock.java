package com.gempire.blocks;

import com.gempire.entities.bases.EntityGem;
import com.gempire.events.GemFormEvent;
import com.gempire.init.ModEntities;
import com.gempire.items.ItemGem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.RegistryObject;

public class BlueEssenceBlock extends LiquidBlock {
    public BlueEssenceBlock(RegistryObject<FlowingFluid> p_54694_, Properties p_54695_) {
        super(p_54694_, p_54695_);
    }

    int timer = 0;

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity) {
            if ((((ItemEntity) entity).getItem()).getItem() == Items.STONE_BUTTON) {
                if (!level.isClientSide) {
                    if (timer == 60) {
                        EntityGem gem = ModEntities.SHALE.get().create(level);
                        gem.finalizeSpawn((ServerLevelAccessor) level, level.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.TRIGGERED, null, null);
                        gem.setMovementType((byte) 2);
                        gem.setPos(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
                        gem.setHealth(gem.getMaxHealth());
                        gem.GUARD_POS = gem.getOnPos().above();
                        gem.clearFire();
                        gem.removeAllEffects();
                        gem.setDeltaMovement(0, 0, 0);
                        gem.fallDistance = 0;
                        GemFormEvent event = new GemFormEvent(gem, gem.blockPosition());
                        MinecraftForge.EVENT_BUS.post(event);
                        level.addFreshEntity(gem);
                        System.out.println(gem.getGemPlacementE());
                        System.out.println(gem.getOutfitVariant() + " and " + gem.getInsigniaVariant());
                        entity.remove(Entity.RemovalReason.DISCARDED);
                        timer = 0;
                    } else {
                        timer++;
                    }
                }
            }
            /*
            if ((((ItemEntity) entity).getItem()).getItem() instanceof ItemGem) {
                ItemGem itemGem = (ItemGem) (((ItemEntity) entity).getItem()).getItem();
                ItemStack stack = ((ItemEntity) entity).getItem();
                if ((itemGem).checkTags(stack)) {
                    if (stack.getTag().getBoolean("cracked")) {
                        stack.getTag().putBoolean("cracked", false);
                    }
                }
            }*/
        }
        super.entityInside(state, level, pos, entity);
    }
}
