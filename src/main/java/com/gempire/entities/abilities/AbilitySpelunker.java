package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class AbilitySpelunker extends Ability implements IIdleAbility {

    boolean foundBlock = false;

    public AbilitySpelunker(){
        super("spelunker", 4);
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.spelunker");
    }

    @Override
    public void execute() {
        if (holder.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PickaxeItem) {
            if (cooldown <= 0) {
                foundBlock = false;
                AABB aabb = this.holder.getBoundingBox().inflate(10D);
                List<BlockState> blocks = new ArrayList<>(holder.level().getBlockStates(aabb).toList());
                for (BlockState block : blocks) {
                    if (isValuableBlock(block)) {
                        if (!foundBlock && holder.getOwned()) {
                            cooldown = 250;
                            foundBlock = true;
                            ArrayList<Player> players = new ArrayList<>();
                            for (int i = 0; i < holder.OWNERS.size(); i++)
                                players.add(holder.level().getPlayerByUUID(holder.OWNERS.get(i)));
                            BlockPos blockPos = null;
                            boolean foundPos = false;
                            BlockPos pos = holder.getOnPosLegacy().offset(-10, -10, -10);
                            while (!foundPos) {
                                for (int x = 0; x < 20; x++) {
                                    for (int y = 0; y < 20; y++) {
                                        for (int z = 0; z < 20; z++) {
                                            BlockPos newPos = pos.offset(x, y, z);
                                            if (holder.level().getBlockState(newPos) == block) {
                                                blockPos = newPos;
                                                foundPos = true;
                                            }
                                        }
                                    }
                                }
                            }
                            for (Player player : players) {
                                player.sendSystemMessage(Component.literal("Found " + block.getBlock().getName() + " at " + "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"));
                            }
                            System.out.println("Found " + block.getBlock().getDescriptionId() + " at " + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ());
                        }
                    }
                }
            } else {
                cooldown--;
                System.out.println("cooldown: " + cooldown);
            }
        }
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.SPELUNKER_VALUABLES);
    }

}
