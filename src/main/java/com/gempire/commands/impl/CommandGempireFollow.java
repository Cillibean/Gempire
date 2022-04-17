package com.gempire.commands.impl;

import com.gempire.commands.CommandBase;
import com.gempire.entities.bases.EntityGem;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.command.Commands;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.gen.feature.structure.Structure;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.UUID;

public class CommandGempireFollow extends CommandBase {
    public static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableComponent("commands.gempire.nounderstand"));

    public CommandGempireFollow(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> setExecution() {
        this.builder = this.builder.executes(source -> execute(source.getSource()));
        return this.builder;
    }

    public int execute(CommandSourceStack source) throws CommandSyntaxException {
        BlockPos pos = new BlockPos(source.getPosition().x, source.getPosition().y, source.getPosition().z);
        AABB aabb = source.getPlayerOrException().getBoundingBox().inflate(12.0D);
        List<EntityGem> gems = source.getLevel().getEntitiesOfClass(EntityGem.class, aabb);
        for(EntityGem gem : gems){
            if(gem.isOwner(source.getPlayerOrException())){
                gem.FOLLOW_ID = source.getPlayerOrException().getUUID();
                gem.setMovementType((byte) 2);
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
