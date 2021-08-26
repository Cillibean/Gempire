package com.gempire.commands.impl;

import com.gempire.commands.CommandBase;
import com.gempire.entities.bases.EntityGem;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.ResourceLocationArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.List;
import java.util.UUID;

public class CommandGempireLocate extends CommandBase {
    public static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("commands.gempire.nounderstand"));

    public CommandGempireLocate(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        for (Structure<?> structureFeature : net.minecraftforge.registries.ForgeRegistries.STRUCTURE_FEATURES) {
            String name = structureFeature.getRegistryName().toString().replace("minecraft:", "");
            this.builder = this.builder.then(Commands.literal(name)
                    .executes(source -> execute(source.getSource(), structureFeature)));
        }
        return this.builder;
    }

    public int execute(CommandSource source, Structure<?> structure) throws CommandSyntaxException {
        BlockPos pos = new BlockPos(source.getPos().x, source.getPos().y, source.getPos().z);
        AxisAlignedBB aabb = source.asPlayer().getBoundingBox().grow(12.0D);
        EntityGem nephrite = null;
        List<EntityGem> gems = source.getWorld().getEntitiesWithinAABB(EntityGem.class, aabb);
        for(EntityGem gem : gems){
            if(gem.canLocateStructures()){
                if(gem.isOwner(source.asPlayer())){
                    if(!gem.isOnStructureCooldown()){
                        nephrite = gem;
                    }
                }
            }
        }
        if(nephrite != null){
            nephrite.runFindCommand(source.asPlayer(), structure, null, false);
            return Command.SINGLE_SUCCESS;
        }
        else{
            source.asPlayer().sendMessage(new TranslationTextComponent("commands.gempire.nounderstand"), UUID.randomUUID());
            throw CommandGempireLocate.FAILED_EXCEPTION.create();
        }
    }
}
