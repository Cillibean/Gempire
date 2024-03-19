package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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

    public static final RegistryObject<MobEffect> PALADINS_PROTECTION = MOB_EFFECTS.register("paladins_protection",
            () -> new PaladinArmorEffect(MobEffectCategory.BENEFICIAL, 16096720).addAttributeModifier(Attributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> HUNTRESS = MOB_EFFECTS.register("huntress",
            () -> new HuntressArmorEffect(MobEffectCategory.BENEFICIAL, 16117149).addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.0D, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> EMPRESS = MOB_EFFECTS.register("empress",
            () -> new EmpressArmorEffect(MobEffectCategory.BENEFICIAL, 16250871));

    public static final RegistryObject<MobEffect> GUARDIANS_GRACE = MOB_EFFECTS.register("guardians_grace",
            () -> new GaurdianArmorEffect(MobEffectCategory.BENEFICIAL, 10343413));

    public static final RegistryObject<MobEffect> SHOCK_RESISTANCE = MOB_EFFECTS.register("shock_resistance",
            () -> new ShockResistanceEffect(MobEffectCategory.BENEFICIAL, 16117149));

    public static final RegistryObject<MobEffect> ELECTROCUTION = MOB_EFFECTS.register("electrocution",
            () -> new ElectrocutionEffect(MobEffectCategory.HARMFUL, 16775275));

    public static final RegistryObject<MobEffect> BLINDING_LIGHT = MOB_EFFECTS.register("blinding_light",
            () -> new BlindingLightEffect(MobEffectCategory.HARMFUL, 16250871));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
