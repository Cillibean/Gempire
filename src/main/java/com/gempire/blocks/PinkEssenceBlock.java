package com.gempire.blocks;

import com.gempire.items.ItemGem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class PinkEssenceBlock extends LiquidBlock {
    public PinkEssenceBlock(RegistryObject<FlowingFluid> p_54694_, Properties p_54695_) {
        super(p_54694_, p_54695_);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity) {
            if ((((ItemEntity) entity).getItem()).getItem() instanceof ItemGem) {
                ItemGem itemGem = (ItemGem) (((ItemEntity) entity).getItem()).getItem();
                ItemStack stack = ((ItemEntity) entity).getItem();
                if ((itemGem).checkTags(stack)) {
                    if (stack.getTag().getBoolean("cracked")) {
                        stack.getTag().putBoolean("cracked", false);
                    }
                }
            }
        }
        super.entityInside(state, level, pos, entity);
    }
}
