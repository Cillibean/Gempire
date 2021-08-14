package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.events.GemFormEvent;
import com.gempire.init.AddonHandler;
import com.gempire.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ItemGem extends Item {
    public String ID = "";
    int coundownMax = 600;
    int countdown = 600;
    Random rand = new Random();
    public boolean doEffect = false;

    public ItemGem(Properties properties) {
        super(properties);
    }

    public ItemGem(Properties properties, String ID) {
        super(properties);
        this.ID = ID;
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
                        spawned = this.formGem(worldIn, playerIn, blockpos, itemstack, null);
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

    //(?i) means case sensitive
    public boolean formGem(World world, @Nullable PlayerEntity player, BlockPos pos, ItemStack stack, @Nullable ItemEntity item) {
        if (!world.isRemote) {
            RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
            String skinColorVariant = "";
            EntityGem gem = gemm.get().create(world);
            String namee = "pebble";

            if (this.ID == "") {
                namee = this.getRegistryName().toString().replaceAll("gempire", "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
            }
            else{
                namee = this.getRegistryName().toString().replaceAll(this.ID, "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
            }

            //This whole section here checks for variations in color so it can spawn the correct type of gem

            String[] ainmneacha = namee.split("_");
            boolean nullFlag = false;
            int idx = 0;
            for (int i = 0; i < ainmneacha.length; i++) {
                if (ainmneacha[i].isEmpty()) {
                    nullFlag = true;
                    idx = i;
                }
            }
            if(nullFlag) ainmneacha = ArrayUtils.remove(ainmneacha, idx);
            namee = ainmneacha[0];
            if(ainmneacha.length > 1) skinColorVariant = ainmneacha[1];
            for (String s : ainmneacha) {
                System.out.println(s);
            }

            //End of check and set

            try {
                gemm = ModEntities.PEBBLE;
                if(this.ID == ""){
                    gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(namee.toUpperCase()).get(null);
                }
                else{
                    gemm = (RegistryObject<EntityType<EntityPebble>>) AddonHandler.ADDON_ENTITY_REGISTRIES.get(this.ID).getField(namee.toUpperCase()).get(null);
                }
                gem = gemm.get().create(world);
                gem.setUniqueId(MathHelper.getRandomUUID(world.rand));
            } catch(Exception e){
                e.printStackTrace();
            }
            try {
                gem.read(stack.getTag());
            } catch (Exception e){
                if(ainmneacha.length > 1) {
                    gem.setSkinVariantOnInitialSpawn = false;
                    gem.initalSkinVariant = Integer.valueOf(skinColorVariant);
                }
                if(player != null) {
                    gem.onInitialSpawn((IServerWorld) world, world.getDifficultyForLocation(player.getPosition()), SpawnReason.TRIGGERED, null, null);
                    gem.setOwned(true, PlayerEntity.getUUID(player.getGameProfile()));
                }
                else{
                    gem.onInitialSpawn((IServerWorld) world, world.getDifficultyForLocation(item.getPosition()), SpawnReason.TRIGGERED, null, null);
                }
            }
            gem.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            gem.setHealth(gem.getMaxHealth());
            gem.extinguish();
            gem.clearActivePotions();
            gem.setVelocity(0, 0 ,0);
            GemFormEvent event = new GemFormEvent(gem, gem.getPosition());
            MinecraftForge.EVENT_BUS.post(event);
            world.addEntity(gem);
            System.out.println(gem.getGemPlacementE());
            return true;
        }
        return false;
    }



    public void setData(EntityGem host, ItemStack stack) {
        stack.setTag(host.writeWithoutTypeId(new CompoundNBT()));
        stack.getTag().putString("name", host.getName().getString());
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        this.Countdown(stack, entity);
        return super.onEntityItemUpdate(stack, entity);
    }

    public void clearData(ItemStack stack) {
        stack.setTag(new CompoundNBT());
    }

    public void Countdown(ItemStack stack, ItemEntity entity){
        if(this.countdown > 0){
            if(this.countdown < (int)Math.floor(this.coundownMax / 6)){
                this.doEffect = true;
                float f = (this.rand.nextFloat() - 0.5F) * 2.0F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F;
                float f2 = (this.rand.nextFloat() - 0.5F) * 2.0F;
                entity.world.addParticle(ParticleTypes.EXPLOSION, entity.getPosX() + (double)f, entity.getPosY() + 2.0D + (double)f1, entity.getPosZ() + (double)f2, 0.0D, 0.0D, 0.0D);
            }
            this.countdown--;
        }
        else{
            this.formGem(entity.world, null, entity.getPosition(), stack, entity);
            entity.remove();
            this.countdown = this.coundownMax;
        }
    }
}
