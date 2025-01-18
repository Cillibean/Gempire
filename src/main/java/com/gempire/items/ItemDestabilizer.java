package com.gempire.items;


import com.gempire.init.ModBlocks;
import com.gempire.init.ModItems;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;

import java.util.Objects;

public class ItemDestabilizer extends DestabBase {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ItemDestabilizer(Properties properties){
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.9F, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43383_) {
        return p_43383_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer().isShiftKeyDown() && !context.getLevel().isClientSide) {
            BlockPos pos = context.getClickedPos();
            if (context.getClickedFace() == Direction.UP) {
                pos = pos.above();
            } else if (context.getClickedFace() == Direction.DOWN) {
                pos = pos.below();
            } else if (context.getClickedFace() == Direction.NORTH) {
                pos = pos.offset(0, 0, -1);
                System.out.println("north "+pos);
            } else if (context.getClickedFace() == Direction.EAST) {
                pos = pos.offset(1, 0, 0);
                System.out.println("east "+pos);
            } else if (context.getClickedFace() == Direction.SOUTH) {
                pos = pos.offset(0, 0, 1);
                System.out.println("south "+pos);
            } else {
                pos = pos.offset(-1, 0, 0);
                System.out.println("west "+pos);
            }
            if (context.getItemInHand().getItem() == ModItems.YELLOW_DESTABILIZER.get()) {
                context.getLevel().setBlock(pos, Objects.requireNonNull(ModBlocks.YELLOW_DESTAB_WALL.get().getStateForPlacement(new BlockPlaceContext(context))), 0);
                if (context.getLevel().getBlockState(pos) == ModBlocks.YELLOW_DESTAB_WALL.get().defaultBlockState()) context.getItemInHand().hurtAndBreak(5, context.getPlayer(), (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            } else if (context.getItemInHand().getItem() == ModItems.PINK_DESTABILIZER.get()) {
                context.getLevel().setBlock(pos, Objects.requireNonNull(ModBlocks.PINK_DESTAB_WALL.get().getStateForPlacement(new BlockPlaceContext(context))), 0);
                if (context.getLevel().getBlockState(pos) == ModBlocks.PINK_DESTAB_WALL.get().defaultBlockState()) context.getItemInHand().hurtAndBreak(5, context.getPlayer(), (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            } else if (context.getItemInHand().getItem() == ModItems.BLUE_DESTABILIZER.get()) {
                context.getLevel().setBlock(pos, Objects.requireNonNull(ModBlocks.BLUE_DESTAB_WALL.get().getStateForPlacement(new BlockPlaceContext(context))), 0);
                if (context.getLevel().getBlockState(pos) == ModBlocks.BLUE_DESTAB_WALL.get().defaultBlockState()) context.getItemInHand().hurtAndBreak(5, context.getPlayer(), (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            } else if (context.getItemInHand().getItem() == ModItems.WHITE_DESTABILIZER.get()) {
                context.getLevel().setBlock(pos, Objects.requireNonNull(ModBlocks.WHITE_DESTAB_WALL.get().getStateForPlacement(new BlockPlaceContext(context))), 0);
                if (context.getLevel().getBlockState(pos) == ModBlocks.WHITE_DESTAB_WALL.get().defaultBlockState()) context.getItemInHand().hurtAndBreak(5, context.getPlayer(), (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
        }
        return super.useOn(context);
    }
}