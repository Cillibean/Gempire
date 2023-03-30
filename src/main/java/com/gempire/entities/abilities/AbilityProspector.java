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
        BlockPos pos = this.holder.getOnPos();
        Player player = holder.currentPlayer;
        boolean foundBlock = false;
        if (!foundBlock) {
            for (int i = 0; i <= pos.getY() + 9; i++) {
                for (int i1 = 0; i1 <= pos.getY() - 9; i1++) {
                    for (int i2 = 0; i2 <= pos.getX() + 9; i2++) {
                        for (int i3 = 0; i3 <= pos.getX() - 9; i3++) {
                            for (int i4 = 0; i4 <= pos.getZ() + 9; i4++) {
                                for (int i5 = 0; i5 <= pos.getZ() - 9; i5++) {
                                    BlockState blockPosi = holder.getLevel().getBlockState(pos.offset(i2, i1, i4));
                                    BlockState blockNeg = holder.getLevel().getBlockState(pos.offset(i3, i, i5));

                                    if (isValuableBlock(blockPosi)) {
                                        outputValuableCoordinates(pos.offset(i2, i1, i4), player, blockPosi.getBlock());
                                        foundBlock = true;
                                        if (!isValuableBlock(blockPosi)) {
                                            break;
                                        }
                                    } else if (isValuableBlock(blockNeg)) {
                                        outputValuableCoordinates(pos.offset(i3, i, i5), player, blockNeg.getBlock());
                                        foundBlock = true;
                                        if (!isValuableBlock(blockPosi)) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendSystemMessage(Component.literal("Found " + blockBelow.getName() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.PROSPECTOR_VALUABLES);
    }

}
