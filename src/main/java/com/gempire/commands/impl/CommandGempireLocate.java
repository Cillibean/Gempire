package com.gempire.commands.impl;

import com.gempire.commands.CommandBase;
import com.gempire.entities.bases.EntityGem;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
//import net.minecraft.commands.arguments.ResourceOrTagKeyArgument;
import net.minecraft.commands.arguments.ResourceOrTagKeyArgument;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class CommandGempireLocate extends CommandBase {
    public static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(Component.translatable("commands.gempire.nounderstand"));

    private static final DynamicCommandExceptionType ERROR_STRUCTURE_INVALID = new DynamicCommandExceptionType((p_207534_) -> {
        return Component.translatable("commands.gempire.nounderstand", p_207534_);
    });

    public CommandGempireLocate(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> setExecution() {
        this.builder = this.builder.then(Commands.argument("structure", ResourceOrTagKeyArgument.resourceOrTagKey(Registries.STRUCTURE)).executes(source -> execute(source.getSource(), ResourceOrTagKeyArgument.getResourceOrTagKey((source), "structure", Registries.STRUCTURE, ERROR_STRUCTURE_INVALID))));
        return this.builder;
    }

    public int execute(CommandSourceStack source, ResourceOrTagKeyArgument.Result<Structure> structure) throws CommandSyntaxException {
        System.out.println("execute");
        //BlockPos pos = new BlockPos(source.getPosition().x, source.getPosition().y, source.getPosition().z);
        AABB aabb = source.getPlayerOrException().getBoundingBox().inflate(12.0D);
        EntityGem nephrite = null;
        List<EntityGem> gems = source.getLevel().getEntitiesOfClass(EntityGem.class, aabb);
        for(EntityGem gem : gems){
            System.out.println("gem check");
            if(gem.canLocateStructures()){
                System.out.println("can locate");
                if(gem.isOwner(source.getPlayerOrException())){
                    System.out.println("is owner");
                    if(!gem.isOnStructureCooldown()){
                        System.out.println("is on cooldown");
                        nephrite = gem;
                        System.out.println(nephrite);
                    } else {
                        source.getPlayerOrException().sendSystemMessage(Component.translatable("She's too tired!"));
                    }
                }
            }
        }
        if(nephrite != null){
            nephrite.runFindCommand(source, source.getPlayerOrException(), structure, null, false);
            return Command.SINGLE_SUCCESS;
        }
        else{
            source.getPlayerOrException().sendSystemMessage(Component.translatable("commands.gempire.nounderstand"));
            throw CommandGempireLocate.FAILED_EXCEPTION.create();
        }
    }
}
