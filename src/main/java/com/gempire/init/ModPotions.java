package com.gempire.init;

import com.gempire.Gempire;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, Gempire.MODID);

    public static final RegistryObject<Potion> SHOCK_RESISTANCE_POTION = POTIONS.register("shock_resistance_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.SHOCK_RESISTANCE.get(), 1800, 0)));

    public static final RegistryObject<Potion> ELECTROCUTION_POTION = POTIONS.register("electrocution_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.ELECTROCUTION.get(), 100, 0)));

}
