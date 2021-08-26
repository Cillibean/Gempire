package com.gempire.init;

import com.gempire.commands.CommandBase;
import com.gempire.commands.impl.CommandGempireLocate;
import com.gempire.commands.impl.CommandGempireLocateBiome;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;

import java.util.ArrayList;

public class ModCommands {
    public static final ArrayList<CommandBase> COMMANDS = new ArrayList<>();

    public static void registerCommands(final RegisterCommandsEvent event){
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();

        COMMANDS.add(new CommandGempireLocate("scoutlocate", 0, true));
        COMMANDS.add(new CommandGempireLocateBiome("scoutlocatebiome", 0, true));

        COMMANDS.forEach(command -> {
            if(command.isEnabled() && command.setExecution() != null){
                dispatcher.register(command.getBuilder());
            }
        });
    }
}
