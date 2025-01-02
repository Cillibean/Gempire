package com.gempire.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GempireCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;


    static {
        BUILDER.push("Gempire Common Configuration");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
