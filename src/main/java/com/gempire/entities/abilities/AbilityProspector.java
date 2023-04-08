package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.init.ModTags;
import com.gempire.util.Abilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class AbilityProspector extends Ability implements IIdleAbility {

    public AbilityProspector(){
        this.ability = Abilities.PROSPECTOR;
    }

    @Override
    public Component getName() {
        return Component.translatable("ability.gempire.prospector");
    }

    @Override
    public void execute() {
        Player player = holder.currentPlayer;
        boolean foundBlock = false;
        AABB aabb = this.holder.getBoundingBox().inflate(10D);
        List<BlockState> blocks = new ArrayList<>(this.holder.getLevel().getBlockStates(aabb).toList());
        System.out.println("list");
        if (!foundBlock) {
            System.out.println("found block false");
            for (BlockState block : blocks) {
                System.out.println("block check");
                if (isValuableBlock(block)); {
                    while (isValuableBlock(block)) {
                        System.out.println("found block");
                        if (player != null) {
                            if (!foundBlock) {
                                foundBlock = true;
                                System.out.println("found block bool false");
                                //player.sendSystemMessage(Component.literal("Found " + blockBelow.getName() + " at " + "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"));
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.PROSPECTOR_VALUABLES);
    }

}
