package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class AbilitySpelunker extends Ability implements IIdleAbility {

    //TODO: FIX SPELUNKER
    // make it not lag
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
        Player player = holder.currentPlayer;
        AABB aabb = this.holder.getBoundingBox().inflate(10D);
        List<BlockState> blocks = new ArrayList<>(this.holder.getLevel().getBlockStates(aabb).toList());
        System.out.println("list");
        if (cooldown <= 0) {
            if (!foundBlock) {
                System.out.println("found block false");
                for (BlockState block : blocks) {
                    cooldown = 500;
                    System.out.println("block check");
                    if (isValuableBlock(block)); {
                        while (isValuableBlock(block)) {
                            //if (player != null) {
                                if (!foundBlock) {
                                    foundBlock = true;
                                    System.out.println("found block bool false");
                                    //player.sendSystemMessage(Component.literal("Found " + blockBelow.getName() + " at " + "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"));
                                }
                            //}
                        }
                    }
                }
            }
        } else {
            cooldown -- ;
            foundBlock = false;
        }
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendSystemMessage(Component.literal("Found " + blockBelow.getName() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.SPELUNKER_VALUABLES);
    }

}
