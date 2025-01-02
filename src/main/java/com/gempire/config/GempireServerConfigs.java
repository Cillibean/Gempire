package com.gempire.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GempireServerConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> GEMS_ATTACK_PLAYERS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> OUTBURSTS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> REBELLION;
    public static final ForgeConfigSpec.ConfigValue<Boolean> PYROKINESIS;

    static {
        BUILDER.push("Gempire Server Configuration");

        // HERE DEFINE YOUR CONFIGS

        GEMS_ATTACK_PLAYERS = BUILDER.comment("State if you would like gems to attack other players when provoked")
            .define("Gems Attack Players", true);
        OUTBURSTS = BUILDER.comment("State if you would like gems to have emotional outbursts")
                .define("Outbursts", true);
        REBELLION = BUILDER.comment("State if you would like gems to rebel")
                .define("Rebellion", true);
        PYROKINESIS = BUILDER.comment("State if you would like gems to have Pyrokinesis")
                .define("Pyrokinesis", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
