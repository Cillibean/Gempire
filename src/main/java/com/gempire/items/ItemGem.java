package com.gempire.items;

import com.gempire.Gempire;
import com.gempire.entities.bases.EntityFusion;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityStarterGem;
import com.gempire.entities.gems.EntityPearl;
import com.gempire.entities.gems.EntityZircon;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.events.GemFormEvent;
import com.gempire.init.AddonHandler;
import com.gempire.init.ModEnchants;
import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.apache.logging.log4j.core.jmx.Server;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Attr;

public class ItemGem extends Item {
    public final String ID;
    int coundownMax = 600;
    int countdown = 600;

    boolean livingEntityHit;
    Random rand = new Random();
    public boolean doEffect = false;

    public ItemGem(Properties properties, String id) {
        super(properties.stacksTo(1).fireResistant());
        this.ID = id;
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
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        System.out.println("entity interact");
        if (entity instanceof EntityGem) {
                if (((EntityGem) entity).isOwner(player)) {
                    if (player.isCrouching()) {
                        stack.getOrCreateTag().putUUID("assignedID", UUID.fromString("00000000-0000-0000-0000-000000000000"));
                        //player.sendSystemMessage(Component.translatable("This Gem is no longer assigned to "+ entity.getName().getString() + ", " +((EntityGem) entity).getFacetAndCut()));
                        livingEntityHit = true;
                        /*System.out.println("gem interact");
                        gemToAssign = null;
                        isAssigned = true;*/
                    } else {
                        stack.getOrCreateTag().putUUID("assignedID", entity.getUUID());
                        System.out.println("entity uuid "+entity.getUUID());
                        System.out.println("assigned id "+stack.getOrCreateTag().getUUID("assignedID"));
                        //player.sendSystemMessage(Component.translatable("This Gem was assigned to "+ entity.getName().getString() + ", " +((EntityGem) entity).getFacetAndCut()));
                        livingEntityHit = true;
                        /*System.out.println("gem interact");
                        gemToAssign = (EntityGem) entity;
                        isAssigned = false;*/
                    }
                }
        }
        return super.interactLivingEntity(stack, player, entity, hand);
    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level worldIn, @NotNull Player playerIn, @NotNull InteractionHand handIn) {
        boolean spawned = false;
        ItemStack stack = playerIn.getItemInHand(handIn);
        if (!worldIn.isClientSide && (handIn == InteractionHand.MAIN_HAND)) {
            if (!livingEntityHit) {
                if (stack.getOrCreateTag().contains("assignedID")) {
                    System.out.println("assigned id " + stack.getOrCreateTag().getUUID("assignedID"));
                }
                System.out.println("check tags");
                if (!getCracked(stack)) {
                    if (!getSludged(stack)) {
                        System.out.println("through");
                        ItemStack itemstack = playerIn.getItemInHand(handIn);
                        BlockHitResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.NONE);
                        if (raytraceresult.getType() == HitResult.Type.MISS) {
                            return super.use(worldIn, playerIn, handIn);
                        } else {
                            if (raytraceresult.getType() == HitResult.Type.BLOCK) {
                                BlockPos blockpos = raytraceresult.getBlockPos();
                                System.out.println(blockpos);
                                Direction direction = raytraceresult.getDirection();
                                System.out.println(direction.getName());
                                if (!worldIn.mayInteract(playerIn, blockpos) || !playerIn.mayUseItemAt(blockpos.relative(direction), direction, itemstack)) {
                                    return super.use(worldIn, playerIn, handIn);
                                }

                                if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos.relative(direction), direction, itemstack) /*&& worldIn.getBlockState(blockpos.above()) == Blocks.AIR.defaultBlockState()*/) {
                                    spawned = this.formGem(worldIn, playerIn, blockpos.relative(direction).below(), itemstack, null);
                                }
                            }
                            //Problem with the claiming of gems ??
                            if (!playerIn.isCreative() && spawned) {
                                playerIn.getMainHandItem().shrink(1);
                            }
                        }
                    }
                }
            } else {
                livingEntityHit = false;
            }
        }
        return super.use(worldIn, playerIn, handIn);
    }
    public boolean checkTags(ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getTag();
        return tag != null;
    }
    //(?i) means case sensitive

    public boolean getCracked(ItemStack stack) {
        if (checkTags(stack)) {
            return stack.getOrCreateTag().getBoolean("cracked");
        } else {
            return false;
        }
    }

    public boolean getSludged(ItemStack stack) {
        if (checkTags(stack)) {
            return stack.getOrCreateTag().getInt("sludgeAmount") >= 5;
        } else {
            return false;
        }
    }
    public boolean formGem(Level world, @Nullable Player player, BlockPos pos, ItemStack stack, @Nullable ItemEntity item) {
        if (!world.isClientSide) {
            System.out.println("form event");
            RegistryObject<EntityType<EntityPebble>> gemm = ModEntities.PEBBLE;
            String skinColorVariant = "";
            EntityGem gem = gemm.get().create(world);
            String namee = "";
            boolean dying = false;
            List<EntityGem> list;
            if (player == null) {
                dying = false;
            } else {
                list = player.level.getEntitiesOfClass(EntityGem.class, player.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));
                for (EntityGem gemmy : list) {
                    if (gemmy.isDeadOrDying()) dying = true;
                    System.out.println(gemmy.getUUID());
                }
            }
            if (!dying) {
                if (Objects.equals(this.ID, Gempire.MODID)) {
                    namee = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this)).toString().replaceAll("gempire", "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
                } else {
                    namee = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this)).toString().replaceAll(this.ID, "").replaceAll("gempire", "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
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
                if (nullFlag) ainmneacha = ArrayUtils.remove(ainmneacha, idx);
                namee = ainmneacha[0];
                if (ainmneacha.length > 1) skinColorVariant = ainmneacha[1];
                for (String s : ainmneacha) {
                    System.out.println(s);
                }
                //End of check and set
                try {
                    if (Objects.equals(this.ID, Gempire.MODID)) {
                        gemm = (RegistryObject<EntityType<EntityPebble>>) ModEntities.class.getField(namee.toUpperCase()).get(null);
                    } else {
                        gemm = (RegistryObject<EntityType<EntityPebble>>) AddonHandler.ENTITY_ADDON_ENTITY_REGISTRIES.get(namee).getField(namee.toUpperCase()).get(null);
                        //gemm = (RegistryObject<EntityType<EntityPebble>>) AddonHandler.ADDON_ENTITY_REGISTRIES.get(this.ID).getField(namee.toUpperCase()).get(null);
                    }
                    gem = gemm.get().create(world);
                    System.out.println("gem "+gem);
                    assert gem != null;
                    gem.setUUID(Mth.createInsecureUUID(world.random));
                    System.out.println(gem.getUUID());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (checkTags(stack)) {
                    if (stack.getTag().getString("abilities") != "") {
                        assert gem != null;
                        if (stack.getTag().contains("assignedID")) {
                            if (stack.getOrCreateTag().getUUID("assignedID") != UUID.fromString("00000000-0000-0000-0000-000000000000")) {
                                gem.setAssignedId(stack.getOrCreateTag().getUUID("assignedID"));
                                System.out.println("assigned id " + stack.getOrCreateTag().getUUID("assignedID"));
                            }
                        }
                        System.out.println("try");
                        gem.setQuality(stack.getTag().getInt("quality"));
                        if (item != null) {
                            System.out.println("item not null");
                            gem.spawnGem = item;
                            System.out.println(gem.spawnGem);
                            gem.load(stack.getTag());
                        }
                        if (player != null) {
                            gem.load(stack.getTag());
                        }
                        System.out.println("gem assigned id "+gem.ASSIGNED_ID);
                        System.out.println("gem assigned "+gem.getAssignedGem());
                        System.out.println(stack.getTag());
                        System.out.println(gem.getFacetAndCut());
                        System.out.println("stack loaded");
                        System.out.println("assigned id "+gem.ASSIGNED_ID);
                    } else {
                        assert gem != null;
                        if (ainmneacha.length > 1) {
                            gem.setSkinVariantOnInitialSpawn = false;
                            gem.initalSkinVariant = Integer.parseInt(skinColorVariant);
                        }
                        if (stack.getTag().contains("assignedID")) {
                            if (stack.getOrCreateTag().getUUID("assignedID") != UUID.fromString("00000000-0000-0000-0000-000000000000")) {
                                gem.setAssignedId(stack.getOrCreateTag().getUUID("assignedID"));
                                System.out.println("assigned id " + stack.getOrCreateTag().getUUID("assignedID"));
                            }
                        }
                        System.out.println("player " + player);
                        if (player != null) {
                            assert gem != null;
                            System.out.println("finalize spawn");
                            if (!(gem instanceof EntityStarterGem)) {
                                switch (this.rand.nextInt(10)) {
                                    default -> {
                                        gem.setQuality(1);
                                    }
                                    case 1 -> {
                                        gem.setQuality(0);
                                        System.out.println("defective");
                                    }
                                    case 2 -> {
                                        gem.setQuality(2);
                                        System.out.println("prime");
                                    }
                                }
                            }
                            gem.finalizeSpawn((ServerLevelAccessor) world, world.getCurrentDifficultyAt(player.blockPosition()), MobSpawnType.TRIGGERED, null, null);
                            gem.addOwner(player.getUUID());
                            if (gem.MASTER_OWNER == null) {
                                gem.addMasterOwner(player.getUUID());
                            }
                            gem.FOLLOW_ID = player.getUUID();
                            gem.setMovementType((byte) 2);
                        } else {
                            if (item != null) {
                                gem.spawnGem = item;
                            }
                            assert gem != null;
                            assert item != null;
                            gem.finalizeSpawn((ServerLevelAccessor) world, world.getCurrentDifficultyAt(item.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                        }
                    }
                } else {
                    if (ainmneacha.length > 1) {
                        assert gem != null;
                        gem.setSkinVariantOnInitialSpawn = false;
                        gem.initalSkinVariant = Integer.parseInt(skinColorVariant);
                    }
                    System.out.println("player " + player);
                    if (player != null) {
                        assert gem != null;
                        System.out.println("finalize spawn");
                        switch (this.rand.nextInt(10)) {
                            default -> {
                                gem.setQuality(1);
                            }
                            case 0, 1 -> gem.setQuality(0);
                            case 3 -> gem.setQuality(2);
                        }
                        gem.finalizeSpawn((ServerLevelAccessor) world, world.getCurrentDifficultyAt(player.blockPosition()), MobSpawnType.TRIGGERED, null, null);
                        gem.addOwner(player.getUUID());
                        if (gem.MASTER_OWNER == null) {
                            gem.addMasterOwner(player.getUUID());
                        }
                        gem.FOLLOW_ID = player.getUUID();
                        gem.setMovementType((byte) 2);
                    } else {
                        if (item != null) {
                            gem.spawnGem = item;
                        }
                        assert gem != null;
                        assert item != null;
                        gem.finalizeSpawn((ServerLevelAccessor) world, world.getCurrentDifficultyAt(item.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                    }
                }
                /*try {
                    if (stack.getTag().getString("abilities") != "") {
                        assert gem != null;
                        System.out.println("try");
                        if (item != null) {
                            System.out.println("item not null");
                            System.out.println(gem != null);
                            gem.spawnGem = item;
                            System.out.println(gem.spawnGem);
                        }
                        gem.load(stack.getTag());
                        System.out.println(stack.getTag());
                        System.out.println(gem.getFacetAndCut());
                        System.out.println("stack loaded");
                    }
                } catch (Exception e) {
                    if (ainmneacha.length > 1) {
                        assert gem != null;
                        gem.setSkinVariantOnInitialSpawn = false;
                        gem.initalSkinVariant = Integer.parseInt(skinColorVariant);
                    }
                    System.out.println("player " +player);
                    if (player != null) {
                        assert gem != null;
                        System.out.println("finalize spawn");
                        gem.finalizeSpawn((ServerLevelAccessor) world, world.getCurrentDifficultyAt(player.blockPosition()), MobSpawnType.TRIGGERED, null, null);
                        gem.addOwner(player.getUUID());
                        if (gem.MASTER_OWNER == null) {
                            gem.addMasterOwner(player.getUUID());
                        }
                        gem.FOLLOW_ID = player.getUUID();
                        gem.setMovementType((byte) 2);
                    } else {
                        if (item != null) {
                            gem.spawnGem = item;
                        }
                        assert gem != null;
                        assert item != null;
                        gem.finalizeSpawn((ServerLevelAccessor) world, world.getCurrentDifficultyAt(item.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                    }
                }*/
                if (gem instanceof EntityZircon) {
                    if (!((EntityZircon) gem).getEnchantPageDefined()) {
                        if (gem.isPrimary()) {
                            ((EntityZircon) gem).setEnchantPage(RandomSource.create().nextInt(ModEnchants.GEMPIRE_ENCHANTMENTS.size()));
                        } else {
                            ((EntityZircon) gem).setEnchantPage(RandomSource.create().nextInt(ModEnchants.VANILLA_ENCHANTMENTS.size()));
                        }
                        ((EntityZircon) gem).setEnchantPageDefined(true);
                    }
                }
                if (gem.MASTER_OWNER == null) {
                    gem.MASTER_OWNER = player.getUUID();
                }
                if (gem.getXScale() == 0) {
                    gem.generateXScale();
                }
                if (gem.getYScale() == 0) {
                    gem.generateYScale();
                }
                if (gem.getZScale() == 0) {
                    gem.generateZScale();
                }
                gem.setPos(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
                gem.setHealth(gem.getMaxHealth());
                gem.GUARD_POS = gem.getOnPos().above();
                gem.clearFire();
                gem.removeAllEffects();
                gem.setDeltaMovement(0, 0, 0);
                gem.fallDistance = 0;
                GemFormEvent event = new GemFormEvent(gem, gem.blockPosition());
                MinecraftForge.EVENT_BUS.post(event);
                world.addFreshEntity(gem);
                System.out.println(gem.getGemPlacementE());
                System.out.println(gem.getOutfitVariant() + " and " + gem.getInsigniaVariant());
                System.out.println("assigned id "+gem.ASSIGNED_ID);
                return true;
            }
        }
        return false;
    }

    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> p_40553_, TooltipFlag p_40554_) {
        if(level != null) {
        if (level.isClientSide) {
            if (checkTags(itemStack)) {
                if (itemStack.getTag().getString("abilities") != "") {
                    if (Screen.hasShiftDown()) {
                        if (itemStack.getTag().getString("name") != " ") {
                            p_40553_.add(Component.translatable(itemStack.getTag().getString("name")).withStyle(ChatFormatting.GRAY));
                        }
                        if (itemStack.getTag().getString("facetCut") != " ") {
                            String[] string = itemStack.getTag().getString("facetCut").split(",");
                            p_40553_.add(Component.translatable(string[0]).withStyle(ChatFormatting.GRAY));
                        }
                        if (itemStack.getTag().getString("facetCut") != " ") {
                            String[] string = itemStack.getTag().getString("facetCut").split(",");
                            p_40553_.add(Component.translatable(string[1]).withStyle(ChatFormatting.GRAY));
                        }
                        String[] util = itemStack.getTag().getString("util").split(",");
                        if (Boolean.parseBoolean(util[2])) {
                            p_40553_.add(Component.translatable("Rebel").withStyle(ChatFormatting.RED));
                        }
                        String[] crackShatter = itemStack.getTag().getString("crackShatter").split(",");
                        if (Boolean.parseBoolean(crackShatter[0])) {
                            p_40553_.add(Component.translatable("Cracked").withStyle(ChatFormatting.RED));
                        }
                        if (Integer.parseInt(crackShatter[3]) >= 5) {
                            p_40553_.add(Component.translatable("Sludged").withStyle(ChatFormatting.RED));
                        }
                        if (itemStack.getTag().contains("assignedID")) {
                            //.add(Component.translatable("Assigned" + itemStack.getTag().getUUID("assignedID"))); //to " + assigned_gem.getName().getString() + " " + assigned_gem.getFacetAndCut()));
                        }
                        if (Integer.parseInt(util[3]) == 2) {
                            p_40553_.add(Component.translatable("Perfect").withStyle(ChatFormatting.LIGHT_PURPLE));
                        }
                        if (Integer.parseInt(util[3]) == 0) {
                            p_40553_.add(Component.translatable("Off Colour").withStyle(ChatFormatting.LIGHT_PURPLE));
                        }
                    } else {
                        if (itemStack.getTag().getString("name") != " ") {
                            p_40553_.add(Component.translatable(itemStack.getTag().getString("name")).withStyle(ChatFormatting.GRAY));
                        }
                        String[] crackShatter = itemStack.getTag().getString("crackShatter").split(",");
                        if (Boolean.parseBoolean(crackShatter[0])) {
                            p_40553_.add(Component.translatable("Cracked").withStyle(ChatFormatting.RED));
                        }
                        if (Integer.parseInt(crackShatter[3]) >= 5) {
                            p_40553_.add(Component.translatable("Sludged").withStyle(ChatFormatting.RED));
                        }
                        p_40553_.add(Component.translatable("Hold Shift for more info").withStyle(ChatFormatting.GOLD));
                    }
                }
            }
        }}
    }

    public static void saveData(ItemStack stack, EntityGem gem) {
        CompoundTag tag = stack.getOrCreateTag();
        if (gem instanceof EntityPearl) {
            ListTag list1 = new ListTag();
            for (int i = 0; i < ((EntityPearl) gem).items1.size(); i++) {
                list1.add(i, ((EntityPearl) gem).items1.get(i).save(new CompoundTag()));
            }
            ListTag list2 = new ListTag();
            for (int i = 0; i < ((EntityPearl) gem).items2.size(); i++) {
                list2.add(i, ((EntityPearl) gem).items2.get(i).save(new CompoundTag()));
            }
            tag.put("Items1", list1);
            tag.put("Items2", list2);
        }
        if (gem instanceof EntityZircon) {
            tag.putInt("page", ((EntityZircon) gem).getEnchantPage());
            tag.putInt("min", ((EntityZircon) gem).getEnchantMin());
            tag.putBoolean("pagedefined", ((EntityZircon) gem).getEnchantPageDefined());
            ContainerHelper.saveAllItems(tag, ((EntityZircon) gem).zirconItems);
        }
        tag.putString("abilities", gem.getAbilities());
        tag.putString("name", gem.getName().getString());
        gem.writeCrackShatter(tag);
        gem.writeOwners(tag);
        gem.writeIDs(tag);
        gem.writeCraft(tag);
        gem.writeScale(tag);
        gem.writeColour(tag);
        gem.writeVariant(tag);
        gem.writeRebelColour(tag);
        gem.writeRebelVariant(tag);
        gem.writeFacetCut(tag);
        gem.writeAbilityUtil(tag);
        gem.writeUtil(tag);
        ContainerHelper.saveAllItems(tag, gem.items);
    }

    public static void saveFusionData(ItemStack stack, EntityFusion gem) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("abilities", gem.getAbilities());
        tag.putString("name", gem.getName().getString());
        gem.writeCrackShatter(tag);
        gem.writeOwners(tag);
        gem.writeIDs(tag);
        gem.writeCraft(tag);
        gem.writeScale(tag);
        gem.writeColour(tag);
        gem.writeVariant(tag);
        gem.writeRebelColour(tag);
        gem.writeRebelVariant(tag);
        gem.writeFacetCut(tag);
        gem.writeAbilityUtil(tag);
        gem.writeUtil(tag);
        ContainerHelper.saveAllItems(tag, gem.items);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        this.Countdown(stack, entity);
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
        return 10000000;
    }

    public void clearData(ItemStack stack) {
        stack.setTag(new CompoundTag());
    }

    public void Countdown(ItemStack stack, ItemEntity entity) {
            if (!getCracked(stack) && !getSludged(stack)) {
                if (stack.getOrCreateTag().contains("assignedID")) {
                    System.out.println("assigned id " + stack.getOrCreateTag().getUUID("assignedID"));
                }
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
}
