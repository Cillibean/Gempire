package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityPebble;
import com.gempire.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
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
        if(!context.getWorld().isRemote){
            if (context.getWorld().isBlockModifiable(context.getPlayer(), context.getPos()) && context.getPlayer().canPlayerEdit(context.getPos(), context.getFace(), context.getItem())) {
                boolean spawned = this.formGem(context.getWorld(), context.getPlayer(), context.getPos(), context.getItem());
                if (!context.getPlayer().isCreative() && spawned) {
                    context.getItem().shrink(1);
                }
            }
        }
        return super.onItemUse(context);
    }

    public boolean formGem(World world, PlayerEntity player, BlockPos pos, ItemStack stack) {
        if (!world.isRemote) {
            RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
            EntityGem gem = gemm.get().create(world);
            String namee = this.getName().getString().replaceAll("(?i)item", "").replaceAll("(?i)gem", "").replaceAll("_", "").replaceAll(" ", "");
            try {
                gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(namee.toUpperCase()).get(null);
                gem = gemm.get().create(world);
            } catch(Exception e){
                e.printStackTrace();
            }
            try {
                gem.read(stack.getTag());
            } catch (Exception e){
                gem.onInitialSpawn(world.getServer().func_241755_D_(), world.getDifficultyForLocation(player.getPosition()), SpawnReason.TRIGGERED, null, null);
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
