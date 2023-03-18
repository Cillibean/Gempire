package com.gempire.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import net.minecraft.world.item.Item.Properties;

public class ItemIceShard extends Item  {

    public ItemIceShard(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        boolean spawned = false;
        if(!worldIn.isClientSide) {
            ItemStack itemstack = playerIn.getItemInHand(handIn);
            HitResult raytraceresult = this.getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.NONE);
            if (raytraceresult.getType() == HitResult.Type.MISS) {
                return super.use(worldIn, playerIn, handIn);
            } else {
                if (raytraceresult.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockraytraceresult = (BlockHitResult) raytraceresult;
                    BlockPos blockpos = blockraytraceresult.getBlockPos();
                    Direction direction = blockraytraceresult.getDirection();
                    if (!worldIn.mayInteract(playerIn, blockpos) || !playerIn.mayUseItemAt(blockpos.relative(direction), direction, itemstack)) {
                        return super.use(worldIn, playerIn, handIn);
                    }

                    if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {
                    }
                }
                //Problem with the claiming of gems ??
                if (!playerIn.isCreative() && spawned) {
                    playerIn.getMainHandItem().shrink(1);
                }
            }
        }
        return super.use(worldIn, playerIn, handIn);
    }
}
