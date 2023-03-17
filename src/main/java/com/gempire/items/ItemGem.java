package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.events.GemFormEvent;
import com.gempire.init.AddonHandler;
import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

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
        super(properties.durability(100).setNoRepair());
        this.ID = ID;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return super.isFoil(stack) || stack.getItem() == ModItems.NACRE_GEM.get();
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
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
                        spawned = this.formGem(worldIn, playerIn, blockpos, itemstack, null);
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

    //TODO: A lot needs fixing here

    //(?i) means case sensitive
    public boolean formGem(Level world, @Nullable Player player, BlockPos pos, ItemStack stack, @Nullable ItemEntity item) {
        if (!world.isClientSide) {
            RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
            String skinColorVariant = "";
            EntityGem gem = gemm.get().create(world);
            String namee = "pebble";

            if (this.ID == "") {
                namee = ForgeRegistries.ITEMS.getKey(this).toString().replaceAll("gempire", "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
            }
            else{
                namee = ForgeRegistries.ITEMS.getKey(this).toString().replaceAll(this.ID, "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
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
                gem.setUUID(Mth.createInsecureUUID(world.random));
            } catch(Exception e){
                e.printStackTrace();
            }
            try {
                if(item != null){
                    gem.spawnGem = item;
                }
                gem.load(stack.getTag());
            } catch (Exception e){
                if(ainmneacha.length > 1) {
                    gem.setSkinVariantOnInitialSpawn = false;
                    gem.initalSkinVariant = Integer.valueOf(skinColorVariant);
                }
                if(player != null) {
                    gem.finalizeSpawn((ServerLevelAccessor) world, world.getCurrentDifficultyAt(player.blockPosition()), MobSpawnType.TRIGGERED, null, null);
                    gem.addOwner(player.getUUID());
                    gem.FOLLOW_ID = player.getUUID();
                    gem.setMovementType((byte) 2);
                }
                else{
                    if(item != null){
                        gem.spawnGem = item;
                    }
                    gem.finalizeSpawn((ServerLevelAccessor) world, world.getCurrentDifficultyAt(item.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                }
            }
            gem.setPos(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            gem.setHealth(gem.getMaxHealth());
            gem.clearFire();
            gem.removeAllEffects();
            gem.setDeltaMovement(0, 0 ,0);
            gem.fallDistance = 0;
            GemFormEvent event = new GemFormEvent(gem, gem.blockPosition());
            MinecraftForge.EVENT_BUS.post(event);
            world.addFreshEntity(gem);
            System.out.println(gem.getGemPlacementE());
            return true;
        }
        return false;
    }

    public void setData(EntityGem host, ItemStack stack) {
        stack.setTag(host.saveWithoutId(new CompoundTag()));
        stack.getTag().putString("name", host.getName().getString());
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        this.Countdown(stack, entity);
        return super.onEntityItemUpdate(stack, entity);
    }

    public void clearData(ItemStack stack) {
        stack.setTag(new CompoundTag());
    }

    public void Countdown(ItemStack stack, ItemEntity entity){
        if(this.countdown > 0){
            if(this.countdown < (int)Math.floor(this.coundownMax / 6)){
                this.doEffect = true;
                float f = (this.rand.nextFloat() - 0.5F) * 2.0F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F;
                float f2 = (this.rand.nextFloat() - 0.5F) * 2.0F;
                entity.level.addParticle(ParticleTypes.EXPLOSION, entity.getX() + (double)f, entity.getY() + 2.0D + (double)f1, entity.getZ() + (double)f2, 0.0D, 0.0D, 0.0D);
            }
            this.countdown--;
        }
        else{
            this.formGem(entity.level, null, entity.blockPosition(), stack, entity);
            this.countdown = this.coundownMax;
        }
    }
}
