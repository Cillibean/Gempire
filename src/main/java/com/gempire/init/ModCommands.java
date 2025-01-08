package com.gempire.init;

import com.gempire.commands.CommandBase;
import com.gempire.commands.impl.*;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;

import java.util.ArrayList;

public class ModCommands {
    public static final ArrayList<CommandBase> COMMANDS = new ArrayList<>();

    public static void registerCommands(final RegisterCommandsEvent event){
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        COMMANDS.add(new CommandGempireLocate("scoutlocate", 0, true));
        COMMANDS.add(new CommandGempireLocateBiome("scoutlocatebiome", 0, true));
        COMMANDS.add(new CommandGempireRecall("recall", 0, true));
        COMMANDS.add(new CommandGempireBuildFarm("buildfarm", 0, true));
        COMMANDS.add(new CommandGempireTerraform("terraform", 0, true));
        //COMMANDS.add(new CommandGempireLumberjack("lumberjack", 0, true));
        COMMANDS.add(new CommandGempireFollow("massfollow", 0, true));
        COMMANDS.add(new CommandGempireGemOwner("gemowners", 1, true));
        COMMANDS.add(new CommandGempireRelease("massrelease", 0, true));
        COMMANDS.add(new CommandGempireStay("massstay", 0, true));
        COMMANDS.add(new CommandGempireWander("masswander", 0, true));
        COMMANDS.add(new CommandGempirePoof("masspoof", 1, true));

        COMMANDS.forEach(command -> {
            if(command.isEnabled() && command.setExecution() != null){
                dispatcher.register(command.getBuilder());
            }
        });
    }
}
