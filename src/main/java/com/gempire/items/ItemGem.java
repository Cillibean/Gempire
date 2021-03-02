package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;

public class ItemGem extends Item {

    public ItemGem(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        return ActionResultType.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        boolean spawned = false;
        if(!worldIn.isRemote) {
            ItemStack itemstack = playerIn.getHeldItem(handIn);
            RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.NONE);
            if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
                return super.onItemRightClick(worldIn, playerIn, handIn);
            } else {
                if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
                    BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
                    BlockPos blockpos = blockraytraceresult.getPos();
                    Direction direction = blockraytraceresult.getFace();
                    if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(direction), direction, itemstack)) {
                        return super.onItemRightClick(worldIn, playerIn, handIn);
                    }

                    if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemstack)) {
                        spawned = this.formGem(worldIn, playerIn, blockpos, itemstack);
                    }
                }
                //Problem with the claiming of gems ??
                if (!playerIn.isCreative() && spawned) {
                    playerIn.getHeldItemMainhand().shrink(1);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    //TODO: A lot needs fixing here

    public boolean formGem(World world, PlayerEntity player, BlockPos pos, ItemStack stack) {
        if (!world.isRemote) {
            RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
            EntityGem gem = gemm.get().create(world);
            String namee = this.getRegistryName().toString().replaceAll("(?i)item", "").replaceAll("gempire", "").replaceAll("(?i)gem", "").replaceAll("_", "").replaceAll(":", "").replaceAll(" ", "");
            try {
                gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(namee.toUpperCase()).get(null);
                gem = gemm.get().create(world);
                gem.setUniqueId(MathHelper.getRandomUUID(world.rand));
            } catch(Exception e){
                e.printStackTrace();
            }
            try {
                gem.read(stack.getTag());
            } catch (Exception e){
                gem.onInitialSpawn(world.getServer().func_241755_D_(), world.getDifficultyForLocation(player.getPosition()), SpawnReason.TRIGGERED, null, null);
                gem.setOwned(true, PlayerEntity.getUUID(player.getGameProfile()));
            }
            gem.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            gem.setHealth(gem.getMaxHealth());
            gem.extinguish();
            gem.clearActivePotions();
            world.addEntity(gem);
            return true;
        }
        return false;
    }

    /*@Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        entity.setNoDespawn();
        entity.extinguish();
        return true;
    }*/

    public void setData(EntityGem host, ItemStack stack) {
        stack.setTag(host.writeWithoutTypeId(new CompoundNBT()));
        stack.getTag().putString("name", host.getName().getString());
    }

    public void clearData(ItemStack stack) {
        stack.setTag(new CompoundNBT());
    }
}
