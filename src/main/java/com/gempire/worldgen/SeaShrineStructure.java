package com.gempire.worldgen;

import com.gempire.init.ModStructures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Optional;

public class SeaShrineStructure extends Structure {
    // A custom codec that changes the size limit for our code_structure_sky_fan.json's config to not be capped at 7.
    // With this, we can have a structure with a size limit up to 30 if we want to have extremely long branches of pieces in the structure.
    public static final Codec<SeaShrineStructure> CODEC = RecordCodecBuilder.<SeaShrineStructure>mapCodec(instance ->
            instance.group(SeaShrineStructure.settingsCodec(instance),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight)
            ).apply(instance, SeaShrineStructure::new)).codec();

    private final HeightProvider startHeight;
    public SeaShrineStructure(StructureSettings config,
                              HeightProvider startHeight)
    {
        super(config);
        this.startHeight = startHeight;
    }

    /*
     * This is where extra checks can be done to determine if the structure can spawn here.
     * This only needs to be overridden if you're adding additional spawn conditions.
     *
     * Fun fact, if you set your structure separation/spacing to be 0/1, you can use
     * extraSpawningChecks to return true only if certain chunk coordinates are passed in
     * which allows you to spawn structures only at certain coordinates in the world.
     *
     * Basically, this method is used for determining if the land is at a suitable height,
     * if certain other structures are too close or not, or some other restrictive condition.
     *
     * For example, Pillager Outposts added a check to make sure it cannot spawn within 10 chunk of a Village.
     * (Bedrock Edition seems to not have the same check)
     *
     * If you are doing Nether structures, you'll probably want to spawn your structure on top of ledges.
     * Best way to do that is to use getBaseColumn to grab a column of blocks at the structure's x/z position.
     * Then loop through it and look for land with air above it and set blockpos's Y value to it.
     * Make sure to set the final boolean in JigsawPlacement.addPieces to false so
     * that the structure spawns at blockpos's y value instead of placing the structure on the Bedrock roof!
     *
     * Also, please for the love of god, do not do dimension checking here.
     * If you do and another mod's dimension is trying to spawn your structure,
     * the locate command will make minecraft hang forever and break the game.
     * Use the biome tags for where to spawn the structure and users can datapack
     * it to spawn in specific biomes that aren't in the dimension they don't like if they wish.
     */
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext p_229391_) {
        Heightmap.Types $$1 = Heightmap.Types.OCEAN_FLOOR_WG;
        return onTopOfChunkCenter(p_229391_, $$1, (p_229394_) -> {
            this.generatePieces(p_229394_, p_229391_);
        });
    }

    private void generatePieces(StructurePiecesBuilder p_229396_, Structure.GenerationContext p_229397_) {
        Rotation $$2 = Rotation.getRandom(p_229397_.random());
        BlockPos $$3 = new BlockPos(p_229397_.chunkPos().getMinBlockX(), 90, p_229397_.chunkPos().getMinBlockZ());
        SeaShrinePieces.addPieces(p_229397_.structureTemplateManager(), $$3, $$2, p_229396_, p_229397_.random());
    }

    public StructureType<?> type() {
        return ModStructures.SEA_SHRINE_STRUCTURE.get();
    }
}
