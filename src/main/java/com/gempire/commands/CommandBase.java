package com.gempire.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandBase {
    protected LiteralArgumentBuilder<CommandSourceStack> builder;
    boolean enabled;

    public CommandBase(String name, int permissionLevel, boolean enabled){
        this.builder = Commands.literal(name).requires(source -> source.hasPermission(permissionLevel));
        this.enabled = enabled;
    }

    public LiteralArgumentBuilder<CommandSourceStack> getBuilder(){
        return this.builder;
    }

    public boolean isEnabled(){
        return this.enabled;
    }

    public LiteralArgumentBuilder<CommandSourceStack> setExecution(){
        return null;
    }
}
