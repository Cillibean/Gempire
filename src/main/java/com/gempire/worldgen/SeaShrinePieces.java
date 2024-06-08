package com.gempire.worldgen;

import com.gempire.init.ModStructures;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.structures.ShipwreckPieces;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.Iterator;
import java.util.Map;

public class SeaShrinePieces {
    static final BlockPos PIVOT = new BlockPos(4, 0, 15);
    private static final ResourceLocation STRUCTURE_LOCATION_OCEAN = new ResourceLocation("gempire:sea_shrine");

    public SeaShrinePieces() {
    }

    public static void addPieces(StructureTemplateManager p_229346_, BlockPos p_229347_, Rotation p_229348_, StructurePieceAccessor p_229349_, RandomSource p_229350_) {
        p_229349_.addPiece(new SeaShrinePieces.SeaShrinePiece(p_229346_, STRUCTURE_LOCATION_OCEAN, p_229347_, p_229348_));
    }

    public static class SeaShrinePiece extends TemplateStructurePiece {
        public SeaShrinePiece(StructureTemplateManager p_229354_, ResourceLocation p_229355_, BlockPos p_229356_, Rotation p_229357_) {
            super(ModStructures.SEA_SHRINE_PIECE.get(), 0, p_229354_, p_229355_, p_229355_.toString(), makeSettings(p_229357_), p_229356_);
        }

        public SeaShrinePiece(StructureTemplateManager p_229360_, CompoundTag p_229361_) {
            super(ModStructures.SEA_SHRINE_PIECE.get(), p_229361_, p_229360_, (p_229383_) -> {
                return makeSettings(Rotation.valueOf(p_229361_.getString("Rot")));
            });
        }

        protected void addAdditionalSaveData(StructurePieceSerializationContext p_229373_, CompoundTag p_229374_) {
            super.addAdditionalSaveData(p_229373_, p_229374_);
            p_229374_.putString("Rot", this.placeSettings.getRotation().name());
        }

        private static StructurePlaceSettings makeSettings(Rotation p_229371_) {
            return (new StructurePlaceSettings()).setRotation(p_229371_).setMirror(Mirror.NONE).setRotationPivot(SeaShrinePieces.PIVOT).addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
        }

        public void postProcess(WorldGenLevel p_229363_, StructureManager p_229364_, ChunkGenerator p_229365_, RandomSource p_229366_, BoundingBox p_229367_, ChunkPos p_229368_, BlockPos p_229369_) {
            int $$7 = p_229363_.getMaxBuildHeight();
            int $$8 = 0;
            Vec3i $$9 = this.template.getSize();
            Heightmap.Types $$10 = Heightmap.Types.OCEAN_FLOOR_WG;
            int $$11 = $$9.getX() * $$9.getZ();
            if ($$11 == 0) {
                $$8 = p_229363_.getHeight($$10, this.templatePosition.getX(), this.templatePosition.getZ());
            } else {
                BlockPos $$12 = this.templatePosition.offset($$9.getX() - 1, 0, $$9.getZ() - 1);

                int $$14;
                for(Iterator var14 = BlockPos.betweenClosed(this.templatePosition, $$12).iterator(); var14.hasNext(); $$7 = Math.min($$7, $$14)) {
                    BlockPos $$13 = (BlockPos)var14.next();
                    $$14 = p_229363_.getHeight($$10, $$13.getX(), $$13.getZ());
                    $$8 += $$14;
                }

                $$8 /= $$11;
            }

            int $$15 = $$8;
            this.templatePosition = new BlockPos(this.templatePosition.getX(), $$15, this.templatePosition.getZ());
            super.postProcess(p_229363_, p_229364_, p_229365_, p_229366_, p_229367_, p_229368_, p_229369_);
        }

        protected void handleDataMarker(String p_229376_, BlockPos p_229377_, ServerLevelAccessor p_229378_, RandomSource p_229379_, BoundingBox p_229380_) {

        }
    }
}
