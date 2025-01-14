package com.gempire.init;

import com.gempire.Gempire;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {

    public static final WoodType DISTANT = WoodType.register(new WoodType(Gempire.MODID + ":distant", BlockSetType.OAK));
    public static final WoodType ASTER = WoodType.register(new WoodType(Gempire.MODID + ":aster", BlockSetType.OAK));
    public static final WoodType CRYSTAL = WoodType.register(new WoodType(Gempire.MODID + ":crystal", BlockSetType.OAK));
    public static final WoodType KALEIDOSCOPE = WoodType.register(new WoodType(Gempire.MODID + ":kaleidoscope", BlockSetType.OAK));
    public static final WoodType SHADED = WoodType.register(new WoodType(Gempire.MODID + ":shaded", BlockSetType.OAK));
    public static final WoodType VERDANT_PINE = WoodType.register(new WoodType(Gempire.MODID + ":verdant_pine", BlockSetType.OAK));
}
