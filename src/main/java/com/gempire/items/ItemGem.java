package com.gempire.items;

import com.gempire.Gempire;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityZircon;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.events.GemFormEvent;
import com.gempire.init.AddonHandler;
import com.gempire.init.ModEnchants;
import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
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
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Attr;

public class ItemGem extends Item {
    public String ID;

    private static final String TAG_CRACKED = "Cracked";
    int coundownMax = 600;
    int countdown = 600;

    boolean livingEntityHit;
    Random rand = new Random();
    public boolean doEffect = false;

    public ItemGem(Properties properties, String id) {
        super(properties);
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
                        stack.getOrCreateTag().putUUID("assignedID", UUID.randomUUID());
                        player.sendSystemMessage(Component.translatable("This Gem is no longer assigned to "+ entity.getName().getString() + ", " +((EntityGem) entity).getFacetAndCut()));
                        livingEntityHit = true;
                        /*System.out.println("gem interact");
                        gemToAssign = null;
                        isAssigned = true;*/
                    } else {
                        stack.getOrCreateTag().putUUID("assignedID", entity.getUUID());
                        player.sendSystemMessage(Component.translatable("This Gem was assigned to "+ entity.getName().getString() + ", " +((EntityGem) entity).getFacetAndCut()));
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
        System.out.println("checking");
        if (checkTags(stack)) {
            System.out.println("checked");
            System.out.println(stack.getOrCreateTag().getBoolean("cracked"));
            return stack.getOrCreateTag().getBoolean("cracked");
        } else {
            System.out.println("check failed");
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
                    namee = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this)).toString().replaceAll(this.ID, "").replaceAll("gem", "").replaceAll(":", "").replaceAll(" ", "");
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
                        gemm = (RegistryObject<EntityType<EntityPebble>>) AddonHandler.ADDON_ENTITY_REGISTRIES.get(this.ID).getField(namee.toUpperCase()).get(null);
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
                        System.out.println("try");
                        gem.setDefective(stack.getTag().getBoolean("defective"));
                        gem.setPrimary(stack.getTag().getBoolean("prime"));
                        if (item != null) {
                            System.out.println("item not null");
                            gem.spawnGem = item;
                            System.out.println(gem.spawnGem);
                            gem.load(stack.getTag());
                        }
                        if (player != null) {
                            gem.load(stack.getTag());
                        }
                        System.out.println(stack.getTag());
                        System.out.println(gem.getFacetAndCut());
                        System.out.println("stack loaded");
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
                                    gem.setPrimary(false);
                                    gem.setDefective(false);
                                }
                                case 1 -> {
                                    gem.setDefective(true);
                                    System.out.println("defective");
                                }
                                case 2 -> {
                                    gem.setPrimary(true);
                                    System.out.println("prime");
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
                                gem.setPrimary(false);
                                gem.setDefective(false);
                            }
                            case 0, 1 -> gem.setDefective(true);
                            case 3 -> gem.setPrimary(true);
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
                //gem.setAssignedId(stack.getOrCreateTag().getUUID("assignedID"));
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
                        if (itemStack.getTag().getString("facet") != " ") {
                            p_40553_.add(Component.translatable(itemStack.getTag().getString("facet")).withStyle(ChatFormatting.GRAY));
                        }
                        if (itemStack.getTag().getString("cut") != " ") {
                            p_40553_.add(Component.translatable(itemStack.getTag().getString("cut")).withStyle(ChatFormatting.GRAY));
                        }
                        if (itemStack.getTag().getBoolean("rebel")) {
                            p_40553_.add(Component.translatable("Rebel").withStyle(ChatFormatting.RED));
                        }
                        if (itemStack.getTag().getBoolean("cracked")) {
                            p_40553_.add(Component.translatable("Cracked").withStyle(ChatFormatting.RED));
                        }
                        if (itemStack.getTag().getInt("sludgeAmount") >= 5) {
                            p_40553_.add(Component.translatable("Sludged").withStyle(ChatFormatting.RED));
                        }
                        /*if (!itemStack.getTag().getUUID("assignedID").equals(UUID.randomUUID())) {
                            p_40553_.add(Component.translatable("Assigned")); //to " + assigned_gem.getName().getString() + " " + assigned_gem.getFacetAndCut()));
                        }*/
                        if (itemStack.getTag().getBoolean("prime")) {
                            p_40553_.add(Component.translatable("Perfect").withStyle(ChatFormatting.LIGHT_PURPLE));
                        }
                        if (itemStack.getTag().getBoolean("defective")) {
                            p_40553_.add(Component.translatable("Off Colour").withStyle(ChatFormatting.LIGHT_PURPLE));
                        }
                    } else {
                        if (itemStack.getTag().getString("name") != " ") {
                            p_40553_.add(Component.translatable(itemStack.getTag().getString("name")).withStyle(ChatFormatting.GRAY));
                        }
                        if (itemStack.getTag().getBoolean("cracked")) {
                            p_40553_.add(Component.translatable("Cracked").withStyle(ChatFormatting.RED));
                        }
                        if (itemStack.getTag().getInt("sludgeAmount") >= 5) {
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
        tag.putString("name", gem.getName().getString());
        tag.putBoolean("cracked", gem.getCracked());
        tag.putBoolean("prime", gem.isPrimary());
        tag.putBoolean("defective", gem.isDefective());
        tag.putString("facet", gem.getFacet());
        tag.putString("cut", gem.getCut());
        tag.putInt("abilitySlots", gem.getAbilitySlots());
        gem.writeOwners(tag);
        tag.putInt("hardness", gem.getHardness());
        tag.putString("abilities", gem.getAbilities());
        tag.putBoolean("emotional", gem.isEmotional());
        tag.putUUID("followID", gem.FOLLOW_ID);
        tag.putUUID("masterID", gem.MASTER_OWNER);
        tag.putUUID("assignedID", gem.ASSIGNED_ID);
        tag.putByte("movementType", gem.getMovementType());
        tag.putInt("skinColorVariant", gem.getSkinColorVariant());
        tag.putInt("skinColor", gem.getSkinColor());
        tag.putInt("hairColor", gem.getHairColor());
        tag.putInt("skinVariant", gem.getSkinVariant());
        tag.putInt("hairVariant", gem.getHairVariant());
        tag.putInt("CraftTicks", gem.ticking);
        tag.putInt("abilityTicks", gem.abilityTicks);
        tag.putBoolean("isCrafting", gem.isCrafting);
        tag.putFloat("xscale", gem.getXScale());
        tag.putFloat("yscale", gem.getYScale());
        tag.putFloat("zscale", gem.getZScale());
        tag.putInt("gemPlacement", gem.getGemPlacement());
        tag.putInt("gemColor", gem.getGemColor());
        tag.putInt("outfitColor", gem.getOutfitColor());
        tag.putInt("outfitVariant", gem.getOutfitVariant());
        tag.putInt("insigniaColor", gem.getInsigniaColor());
        tag.putInt("insigniaVariant", gem.getInsigniaVariant());
        tag.putBoolean("usesAreaAbility", gem.usesAreaAbilities());
        tag.putBoolean("rebel", gem.getRebelled());
        tag.putBoolean("isHostile", gem.getHostile());
        tag.putInt("focusLevel", gem.focusLevel);
        tag.putByte("emotionMeter", gem.emotionMeter);
        tag.putInt("markingVariant", gem.getMarkingVariant());
        tag.putInt("markingColor", gem.getMarkingColor());
        tag.putInt("marking2Variant", gem.getMarking2Variant());
        tag.putInt("marking2Color", gem.getMarking2Color());
        tag.putInt("structureTime", gem.structureTime);
        tag.putFloat("rebelPoints", gem.rebelPoints);
        tag.putInt("rebelTicks", gem.rebelTicks);
        tag.putInt("rebelHairVariant", gem.getRebelHairVariant());
        tag.putInt("rebelOutfitColor", gem.getRebelOutfitColor());
        tag.putInt("rebelOutfitVariant", gem.getRebelOutfitVariant());
        tag.putInt("rebelInsigniaColor", gem.getRebelInsigniaColor());
        tag.putInt("rebelInsigniaVariant", gem.getRebelInsigniaVariant());
        tag.putInt("crackAmount", gem.getCrackAmount());
        tag.putInt("sludgeAmount", gem.getSludgeAmount());
        tag.putBoolean("assigned", gem.getAssigned());
        gem.writeStructures(tag);
        ContainerHelper.saveAllItems(tag, gem.items);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        this.Countdown(stack, entity);
        return super.onEntityItemUpdate(stack, entity);
    }

    public void clearData(ItemStack stack) {
        stack.setTag(new CompoundTag());
    }

    public void Countdown(ItemStack stack, ItemEntity entity) {
        if (!getCracked(stack)) {
            if (this.countdown > 0) {
                if (this.countdown < (int) Math.floor(this.coundownMax / 6)) {
                    this.doEffect = true;
                    float f = (this.rand.nextFloat() - 0.5F) * 2.0F;
                    float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F;
                    float f2 = (this.rand.nextFloat() - 0.5F) * 2.0F;
                    entity.level.addParticle(ParticleTypes.EXPLOSION, entity.getX() + (double) f, entity.getY() + 2.0D + (double) f1, entity.getZ() + (double) f2, 0.0D, 0.0D, 0.0D);
                }
                this.countdown--;
            } else {
                this.formGem(entity.level, null, entity.blockPosition(), stack, entity);
                this.countdown = this.coundownMax;
            }
        }
    }
}
