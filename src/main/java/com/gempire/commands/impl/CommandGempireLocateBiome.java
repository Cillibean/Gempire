package com.gempire.commands.impl;

import com.gempire.commands.CommandBase;
import com.gempire.entities.bases.EntityGem;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;

import java.util.List;
import java.util.UUID;

public class CommandGempireLocateBiome /*extends CommandBase*/ {
    /*public static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(Component.translatable("commands.gempire.nounderstand"));

    public CommandGempireLocateBiome(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> setExecution() {
        return this.builder.then(Commands.argument("biome", ResourceLocationArgument.id()).suggests(SuggestionProviders.AVAILABLE_BIOMES).executes((source) -> {
            return execute(source.getSource(), source.getArgument("biome", ResourceLocation.class));
        }));
    }

    public int execute(CommandSourceStack source, ResourceLocation biome) throws CommandSyntaxException {
        BlockPos pos = new BlockPos(source.getPosition().x, source.getPosition().y, source.getPosition().z);
        AABB aabb = source.getPlayerOrException().getBoundingBox().inflate(12.0D);
        EntityGem nephrite = null;
        List<EntityGem> gems = source.getLevel().getEntitiesOfClass(EntityGem.class, aabb);
        for(EntityGem gem : gems){
            if(gem.canLocateStructures()){
                if(gem.isOwner(source.getPlayerOrException())){
                    if(!gem.isOnStructureCooldown()){
                        nephrite = gem;
                    }
                }
            }
        }
        if(nephrite != null){
            nephrite.runFindCommand(source.getPlayerOrException(), null, biome, true);
            return Command.SINGLE_SUCCESS;
        }
        else{
            source.getPlayerOrException().sendMessage(new TranslatableComponent("commands.gempire.nounderstand"), UUID.randomUUID());
            throw CommandGempireLocateBiome.FAILED_EXCEPTION.create();
        }
    }*/
}
