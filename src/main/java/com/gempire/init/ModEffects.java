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

    public static final RegistryObject<MobEffect> PALADINS_GRACE = MOB_EFFECTS.register("paladins_grace",
            () -> new PaladinArmorEffect(MobEffectCategory.BENEFICIAL, 16096720).addAttributeModifier(Attributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> HUNTRESS_WRATH = MOB_EFFECTS.register("huntress_wrath",
            () -> new HuntressArmorEffect(MobEffectCategory.BENEFICIAL, 16117149).addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.0D, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> EMPRESS_SPLENDOUR = MOB_EFFECTS.register("empress_splendour",
            () -> new EmpressArmorEffect(MobEffectCategory.BENEFICIAL, 16250871).addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> GUARDIANS_PROTECTION = MOB_EFFECTS.register("guardians_protection",
            () -> new GaurdianArmorEffect(MobEffectCategory.BENEFICIAL, 10343413));

    public static final RegistryObject<MobEffect> SHOCK_RESISTANCE = MOB_EFFECTS.register("shock_resistance",
            () -> new ShockResistanceEffect(MobEffectCategory.BENEFICIAL, 16117149));

    public static final RegistryObject<MobEffect> FLORAL_PROTECTION = MOB_EFFECTS.register("floral_protection",
            () -> new FloralProtectionEffect(MobEffectCategory.BENEFICIAL, 16760288));

    public static final RegistryObject<MobEffect> SHADE = MOB_EFFECTS.register("shade",
            () -> new ShadeEffect(MobEffectCategory.BENEFICIAL, 12566463));

    public static final RegistryObject<MobEffect> CALM = MOB_EFFECTS.register("calm",
            () -> new CalmEffect(MobEffectCategory.BENEFICIAL, 6407149));

    public static final RegistryObject<MobEffect> ENTANGLEMENT = MOB_EFFECTS.register("entanglement",
            () -> new EntangledEffect(MobEffectCategory.HARMFUL, 6923862).addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", -0.20000000298023224, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> ELECTROCUTION = MOB_EFFECTS.register("electrocution",
            () -> new ElectrocutionEffect(MobEffectCategory.HARMFUL, 16775275));

    public static final RegistryObject<MobEffect> BLINDING_LIGHT = MOB_EFFECTS.register("blinding_light",
            () -> new BlindingLightEffect(MobEffectCategory.HARMFUL, 16250871));

    public static final RegistryObject<MobEffect> DREAD = MOB_EFFECTS.register("dread",
            () -> new DreadEffect(MobEffectCategory.HARMFUL, 6407149).addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", -0.20000000298023224, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
