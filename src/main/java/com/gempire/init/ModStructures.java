package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.worldgen.DungeonStructures;
import com.gempire.worldgen.SeaShrinePieces;
import com.gempire.worldgen.SeaShrineStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

public class ModStructures {

    public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(Registries.STRUCTURE_TYPE, Gempire.MODID);

    /**
     * Registers the base structure itself and sets what its path is. In this case,
     * this base structure will have the resourcelocation of structure_tutorial:sky_structures.
     */
    public static final RegistryObject<StructureType<DungeonStructures>> DUNGEON_STRUCTURES = DEFERRED_REGISTRY_STRUCTURE.register("dungeon_structures", () -> explicitStructureTypeTyping(DungeonStructures.CODEC));

    public static final RegistryObject<StructureType<SeaShrineStructure>> SEA_SHRINE_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("sea_shrine_structure", () -> explicitStructureTypeTyping(SeaShrineStructure.CODEC));

    /**
     * Originally, I had a double lambda ()->()-> for the RegistryObject line above, but it turns out that
     * some IDEs cannot resolve the typing correctly. This method explicitly states what the return type
     * is so that the IDE can put it into the DeferredRegistry properly.
     */
    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPE_DEFERRED_REGISTER = DeferredRegister.create(Registries.STRUCTURE_PIECE, Gempire.MODID);


    public static final RegistryObject<StructurePieceType> SEA_SHRINE_PIECE = STRUCTURE_PIECE_TYPE_DEFERRED_REGISTER.register("sea_shrine_piece", () -> setTemplatePieceId(SeaShrinePieces.SeaShrinePiece::new, "SeaShrine"));


    private static StructurePieceType setFullContextPieceId(StructurePieceType p_210159_, String p_210160_) {
        return (StructurePieceType)Registry.register(BuiltInRegistries.STRUCTURE_PIECE, p_210160_.toLowerCase(Locale.ROOT), p_210159_);
    }

    private static StructurePieceType setTemplatePieceId(StructurePieceType.StructureTemplateType p_210156_, String p_210157_) {
        return setFullContextPieceId(p_210156_, p_210157_);
    }
}
