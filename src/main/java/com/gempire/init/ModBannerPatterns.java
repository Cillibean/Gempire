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
}
