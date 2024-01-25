package com.gempire.commands.impl;

import com.gempire.commands.CommandBase;
import com.gempire.entities.bases.EntityGem;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandGempireGemOwner extends CommandBase {
    public static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(Component.translatable("commands.gempire.nounderstand"));

    public CommandGempireGemOwner(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> setExecution() {
        this.builder = this.builder.executes(source -> execute(source.getSource()));
        return this.builder;
    }

    public int execute(CommandSourceStack source) throws CommandSyntaxException {
        Player player = source.getPlayerOrException();
        AABB aabb = player.getBoundingBox().inflate(17.0D);
        List<EntityGem> gems = source.getLevel().getEntitiesOfClass(EntityGem.class, aabb);
        for(EntityGem gem : gems){
            if (gem.getRebelled()) {
                player.sendSystemMessage(Component.translatable(gem.getName().getString() + " " + gem.getFacetAndCut() + " " + ": Rebelled"));
            } else {
                if (!gem.getOwned()) {
                    player.sendSystemMessage(Component.translatable(gem.getName().getString() + " " + gem.getFacetAndCut() + " " + ": Unclaimed"));
                } else {
                    ArrayList<String> owners = new ArrayList<>();
                    for (UUID uuid : gem.OWNERS) {
                        if (!player.level().isClientSide) {
                            ServerLevel level = (ServerLevel) player.level();
                            owners.add(level.getPlayerByUUID(uuid).getDisplayName().getString());
                        }
                    }
                    StringBuilder ownerString = new StringBuilder();
                    for (String string : owners) {
                        if (!ownerString.toString().equals("")) {
                            ownerString.append(", ").append(string);
                        } else {
                            ownerString.append(string);
                        }
                    }
                    player.sendSystemMessage(Component.translatable(gem.getName().getString() + " " + gem.getFacetAndCut() + " " + ": " + ownerString));
                }
            }
        }
        return Command.SINGLE_SUCCESS;
    }

    public int executeWithPos(CommandSourceStack source) throws CommandSyntaxException {
        Player player = source.getPlayerOrException();
        AABB aabb = player.getBoundingBox().inflate(17.0D);
        List<EntityGem> gems = source.getLevel().getEntitiesOfClass(EntityGem.class, aabb);
        for(EntityGem gem : gems){
            if (gem.getRebelled()) {
                player.sendSystemMessage(Component.translatable(gem.getName().getString() + " " + gem.getFacetAndCut() + " " + ": Rebelled ", gem.getOnPos()));
            } else {
                if (!gem.getOwned()) {
                    player.sendSystemMessage(Component.translatable(gem.getName().getString() + " " + gem.getFacetAndCut() + " " + ": Unclaimed ", gem.getOnPos()));
                } else {
                    ArrayList<String> owners = new ArrayList<>();
                    for (UUID uuid : gem.OWNERS) {
                        if (!player.level().isClientSide) {
                            ServerLevel level = (ServerLevel) player.level();
                            owners.add(level.getPlayerByUUID(uuid).getDisplayName().getString());
                        }
                    }
                    StringBuilder ownerString = new StringBuilder();
                    for (String string : owners) {
                        if (!ownerString.toString().equals("")) {
                            ownerString.append(", ").append(string);
                        } else {
                            ownerString.append(string);
                        }
                    }
                    player.sendSystemMessage(Component.translatable(gem.getName().getString() + " " + gem.getFacetAndCut() + " " + ": " + ownerString + " " , gem.getOnPos()));
                }
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
