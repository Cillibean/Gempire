package com.gempire.commands.impl;

import com.gempire.commands.CommandBase;
import com.gempire.entities.bases.EntityGem;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class CommandGempireLumberjack extends CommandBase {
    public static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(Component.translatable("commands.gempire.nounderstand"));

    public CommandGempireLumberjack(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> setExecution() {
        this.builder = this.builder.executes(source -> execute(source.getSource()));
        return this.builder;
    }

    public int execute(CommandSourceStack source) throws CommandSyntaxException {
        AABB aabb = source.getPlayerOrException().getBoundingBox().inflate(12.0D);
        EntityGem entityGem = null;
        List<EntityGem> gems = source.getLevel().getEntitiesOfClass(EntityGem.class, aabb);
        for (EntityGem gem : gems) {
            if (gem.isFarmer()) {
                if (gem.isOwner(source.getPlayerOrException())) {
                    entityGem = gem;
                }
            }
        }
        System.out.println("run build farm");
        if (entityGem != null) {
            entityGem.runLumberjackCommand(source.getPlayerOrException());
            return Command.SINGLE_SUCCESS;
        } else {
            source.getPlayerOrException().sendSystemMessage(Component.translatable("commands.gempire.nounderstand"));
            throw CommandGempireLumberjack.FAILED_EXCEPTION.create();
        }
    }

}
