package com.gempire.entities.abilities;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IIdleAbility;
import com.gempire.init.ModTags;
import com.gempire.util.GempireAbilities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class AbilityProspector extends Ability implements IIdleAbility {

    public AbilityProspector(){
        super(22, 5);
        this.ability = GempireAbilities.PROSPECTOR;
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
