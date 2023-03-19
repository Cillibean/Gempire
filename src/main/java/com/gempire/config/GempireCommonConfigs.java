package com.gempire.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GempireCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> INJECT_NYI_GEMS;

    static {
        BUILDER.push("Configs for Gempire");

        INJECT_NYI_GEMS = BUILDER.comment("State if you would like NYI gems to emerge from injecting!")
                .define("Inject NYI Gems", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
