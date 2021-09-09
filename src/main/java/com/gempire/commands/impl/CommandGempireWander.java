package com.gempire.commands.impl;

import com.gempire.commands.CommandBase;
import com.gempire.entities.bases.EntityGem;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.List;

public class CommandGempireWander extends CommandBase {
    public static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("commands.gempire.nounderstand"));

    public CommandGempireWander(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        this.builder = this.builder.executes(source -> execute(source.getSource()));
        return this.builder;
    }

    public int execute(CommandSource source) throws CommandSyntaxException {
        BlockPos pos = new BlockPos(source.getPos().x, source.getPos().y, source.getPos().z);
        AxisAlignedBB aabb = source.asPlayer().getBoundingBox().grow(12.0D);
        List<EntityGem> gems = source.getWorld().getEntitiesWithinAABB(EntityGem.class, aabb);
        for(EntityGem gem : gems){
            if(gem.isOwner(source.asPlayer())){
                gem.FOLLOW_ID = source.asPlayer().getUUID(source.asPlayer().getGameProfile());
                gem.setMovementType((byte) 1);
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
