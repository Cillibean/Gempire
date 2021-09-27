package com.gempire.commands.impl;

import com.gempire.commands.CommandBase;
import com.gempire.entities.bases.EntityGem;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.structure.Structure;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.UUID;

public class CommandGempireFollow extends CommandBase {
    public static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("commands.gempire.nounderstand"));

    public CommandGempireFollow(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        this.builder = this.builder.executes(source -> execute(source.getSource()));
        return this.builder;
    }

    public int execute(CommandSource source) throws CommandSyntaxException {
        BlockPos pos = new BlockPos(source.getPos().x, source.getPos().y, source.getPos().z);
        AxisAlignedBB aabb = source.asPlayer().getBoundingBox().grow(12.0D);
        List<EntityGem> gems = source.getWorld().getEntitiesWithinAABB(EntityGem.class, aabb);
        for(EntityGem gem : gems){
            if(gem.isOwner(source.asPlayer())){
                gem.FOLLOW_ID = source.asPlayer().getUniqueID();
                gem.setMovementType((byte) 2);
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
