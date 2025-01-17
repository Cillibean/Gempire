package com.gempire.init;

import com.gempire.Gempire;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModBannerPatterns {

    private ModBannerPatterns() {}

    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, Gempire.MODID);

    public static final RegistryObject<BannerPattern> DIAMOND = BANNER_PATTERNS.register("diamond", () -> new BannerPattern("di"));
    public static final RegistryObject<BannerPattern> PINK_DIAMOND = BANNER_PATTERNS.register("pink_diamond", () -> new BannerPattern("pd"));
    public static final RegistryObject<BannerPattern> BLUE_DIAMOND = BANNER_PATTERNS.register("blue_diamond", () -> new BannerPattern("bd"));
    public static final RegistryObject<BannerPattern> YELLOW_DIAMOND = BANNER_PATTERNS.register("yellow_diamond", () -> new BannerPattern("yd"));
    public static final RegistryObject<BannerPattern> WHITE_DIAMOND = BANNER_PATTERNS.register("white_diamond", () -> new BannerPattern("wd"));
}
