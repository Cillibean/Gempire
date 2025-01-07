package com.gempire.items;

import com.gempire.entities.bosses.base.EntityAlabasterEmpress;
import com.gempire.entities.bosses.base.EntityAmberHuntress;
import com.gempire.entities.bosses.base.EntityCobaltGuardian;
import com.gempire.entities.bosses.base.EntityFuchsiaPaladin;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModEntities;
import com.gempire.init.ModItems;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ItemBossGem extends Item {
    public ItemBossGem(Properties p_41383_) {
        super(p_41383_.stacksTo(1).fireResistant());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide) {
            /*
            if (level.getBlockState(context.getClickedPos()) == ModBlocks.PRISMATIC_PINK_ALTAR.get().defaultBlockState()) {
                if (context.getItemInHand().getItem() == ModItems.INACTIVE_PALADIN_GEM.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityIridescentPaladin paladin = new EntityIridescentPaladin(ModEntities.IRIDESCENT_PALADIN.get(), level);
                    paladin.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    paladin.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    level.addFreshEntity(paladin);
                    System.out.println("paladin attempt");
                    return InteractionResult.PASS;
                } else if (context.getItemInHand().getItem() == ModItems.PALADIN_FLOWER.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityFuchsiaPaladin paladin = new EntityFuchsiaPaladin(ModEntities.FUCHSIA_PALADIN.get(), level);
                    paladin.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    paladin.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    level.addFreshEntity(paladin);
                    System.out.println("paladin attempt");
                    return InteractionResult.PASS;
                }
            } else if (level.getBlockState(context.getClickedPos()) == ModBlocks.PRISMATIC_BLUE_ALTAR.get().defaultBlockState()) {
                if (context.getItemInHand().getItem() == ModItems.INACTIVE_GUARDIAN_GEM.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityMirroredGuardian guardian = new EntityMirroredGuardian(ModEntities.MIRRORED_GUARDIAN.get(), level);
                    guardian.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    guardian.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    level.addFreshEntity(guardian);
                    System.out.println("guardian attempt");
                    return InteractionResult.PASS;
                } else if (context.getItemInHand().getItem() == ModItems.GUARDIAN_TEAR.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityCobaltGuardian guardian = new EntityCobaltGuardian(ModEntities.COBALT_GUARDIAN.get(), level);
                    guardian.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    guardian.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    level.addFreshEntity(guardian);
                    System.out.println("guardian attempt");
                    return InteractionResult.PASS;
                }
            } else if (level.getBlockState(context.getClickedPos()) == ModBlocks.PRISMATIC_YELLOW_ALTAR.get().defaultBlockState()) {
                if (context.getItemInHand().getItem() == ModItems.INACTIVE_HUNTRESS_GEM.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityGildedHuntress huntress = new EntityGildedHuntress(ModEntities.GILDED_HUNTRESS.get(), level);
                    huntress.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    huntress.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    level.addFreshEntity(huntress);
                    System.out.println("huntress attempt");
                    return InteractionResult.PASS;
                } else if (context.getItemInHand().getItem() == ModItems.HUNTRESS_DAGGER.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityAmberHuntress huntress = new EntityAmberHuntress(ModEntities.AMBER_HUNTRESS.get(), level);
                    huntress.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    huntress.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    level.addFreshEntity(huntress);
                    System.out.println("huntress attempt");
                    return InteractionResult.PASS;
                }
            } else if (level.getBlockState(context.getClickedPos()) == ModBlocks.PRISMATIC_WHITE_ALTAR.get().defaultBlockState()) {
                if (context.getItemInHand().getItem() == ModItems.INACTIVE_EMPRESS_GEM.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityPrismaticEmpress empress = new EntityPrismaticEmpress(ModEntities.PRISMATIC_EMPRESS.get(), level);
                    empress.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    empress.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    level.addFreshEntity(empress);
                    System.out.println("empress attempt");
                    return InteractionResult.PASS;
                } else if (context.getItemInHand().getItem() == ModItems.EMPRESS_STAR.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityAlabasterEmpress empress = new EntityAlabasterEmpress(ModEntities.ALABASTER_EMPRESS.get(), level);
                    empress.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    empress.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    level.addFreshEntity(empress);
                    System.out.println("empress attempt");
                    return InteractionResult.PASS;
                }
            } else*/ if (level.getBlockState(context.getClickedPos()).getBlock() == ModBlocks.PINK_ALTAR.get()) {
                if (context.getItemInHand().getItem() == ModItems.PALADIN_FLOWER.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityFuchsiaPaladin paladin = new EntityFuchsiaPaladin(ModEntities.FUCHSIA_PALADIN.get(), level);
                    paladin.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    paladin.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    paladin.altarPos = context.getClickedPos();
                    level.addFreshEntity(paladin);
                    return InteractionResult.CONSUME;
                }
            } else if (level.getBlockState(context.getClickedPos()).getBlock() == ModBlocks.BLUE_ALTAR.get()) {
                if (context.getItemInHand().getItem() == ModItems.GUARDIAN_TEAR.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityCobaltGuardian guardian = new EntityCobaltGuardian(ModEntities.COBALT_GUARDIAN.get(), level);
                    guardian.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    guardian.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    guardian.altarPos = context.getClickedPos();
                    level.addFreshEntity(guardian);
                    return InteractionResult.CONSUME;
                }
            } else if (level.getBlockState(context.getClickedPos()).getBlock() == ModBlocks.YELLOW_ALTAR.get()) {
                if (context.getItemInHand().getItem() == ModItems.HUNTRESS_DAGGER.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityAmberHuntress huntress = new EntityAmberHuntress(ModEntities.AMBER_HUNTRESS.get(), level);
                    huntress.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    huntress.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    huntress.altarPos = context.getClickedPos();
                    level.addFreshEntity(huntress);
                    return InteractionResult.CONSUME;
                }
            } else if (level.getBlockState(context.getClickedPos()).getBlock() == ModBlocks.WHITE_ALTAR.get()) {
                if (context.getItemInHand().getItem() == ModItems.EMPRESS_STAR.get()) {
                    context.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
                    EntityAlabasterEmpress empress = new EntityAlabasterEmpress(ModEntities.ALABASTER_EMPRESS.get(), level);
                    empress.setPos(context.getClickedPos().getX(), context.getClickedPos().getY()+2, context.getClickedPos().getZ());
                    empress.moveTo(Vec3.atBottomCenterOf(context.getClickedPos().above(1)));
                    empress.altarPos = context.getClickedPos();
                    level.addFreshEntity(empress);
                    return InteractionResult.CONSUME;
                }
            }
        }
        return InteractionResult.FAIL;
    }
}
