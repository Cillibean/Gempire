package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Gempire.MODID);

    public static final RegistryObject<MobEffect> PARALYSIS = MOB_EFFECTS.register("paralysis",
            () -> new ParalysisEffect(MobEffectCategory.HARMFUL, 2354151));

    public static final RegistryObject<MobEffect> PINK_AURA = MOB_EFFECTS.register("pink_aura",
            () -> new PinkAuraEffect(MobEffectCategory.HARMFUL, 16096720));

    public static final RegistryObject<MobEffect> YELLOW_AURA = MOB_EFFECTS.register("yellow_aura",
            () -> new YellowAuraEffect(MobEffectCategory.HARMFUL, 16117149));

    public static final RegistryObject<MobEffect> WHITE_AURA = MOB_EFFECTS.register("white_aura",
            () -> new WhiteAuraEffect(MobEffectCategory.HARMFUL, 16250871));

    public static final RegistryObject<MobEffect> BLUE_AURA = MOB_EFFECTS.register("blue_aura",
            () -> new BlueAuraEffect(MobEffectCategory.HARMFUL, 10343413));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
