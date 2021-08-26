package com.gempire.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CommandBase {
    protected LiteralArgumentBuilder<CommandSource> builder;
    boolean enabled;

    public CommandBase(String name, int permissionLevel, boolean enabled){
        this.builder = Commands.literal(name).requires(source -> source.hasPermissionLevel(permissionLevel));
        this.enabled = enabled;
    }

    public LiteralArgumentBuilder<CommandSource> getBuilder(){
        return this.builder;
    }

    public boolean isEnabled(){
        return this.enabled;
    }

    public LiteralArgumentBuilder<CommandSource> setExecution(){
        return null;
    }
}
