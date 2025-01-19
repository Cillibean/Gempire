package com.gempire.init;

import com.gempire.Gempire;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Gempire.MODID);

    public static final RegistryObject<SoundEvent> INJECT = SOUNDS.register("inject", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "inject"), 16));
    public static final RegistryObject<SoundEvent> POOF = SOUNDS.register("poof", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "poof"), 16));
    public static final RegistryObject<SoundEvent> WHISTLE = SOUNDS.register("whistle", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "whistle"), 16));
    public static final RegistryObject<SoundEvent> AMBIENT_WHITE = SOUNDS.register("ambient.desolate.white", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "ambient.desolate.white"), 16));
    public static final RegistryObject<SoundEvent> AMBIENT_YELLOW = SOUNDS.register("ambient.desolate.yellow", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "ambient.desolate.yellow"), 16));
    public static final RegistryObject<SoundEvent> AMBIENT_BLUE = SOUNDS.register("ambient.desolate.blue", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "ambient.desolate.blue"), 16));
    public static final RegistryObject<SoundEvent> AMBIENT_PINK = SOUNDS.register("ambient.desolate.pink", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "ambient.desolate.pink"), 16));
    public static final RegistryObject<SoundEvent> AMBIENT_DESOLATE = SOUNDS.register("ambient.desolate.general", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "ambient.desolate.general"), 16));
    public static final RegistryObject<SoundEvent> REMNANTS = SOUNDS.register("remnants", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Gempire.MODID, "remnants")));
    public static final RegistryObject<SoundEvent> FRAGMENTS = SOUNDS.register("fragments", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Gempire.MODID, "fragments")));

    public static final RegistryObject<SoundEvent> CRAWLER_HURT = SOUNDS.register("crawler_hurt", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "crawler_hurt"), 16));

    public static final RegistryObject<SoundEvent> CRAWLER_ATTACK = SOUNDS.register("crawler_attack", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "crawler_attack"), 16));

    public static final RegistryObject<SoundEvent> CRAWLER_DEATH = SOUNDS.register("crawler_death", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "crawler_death"), 16));

    public static final RegistryObject<SoundEvent> CRAWLER_LIVING = SOUNDS.register("crawler_living", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "crawler_living"), 16));
    public static final RegistryObject<SoundEvent> SHAMBLER_HURT = SOUNDS.register("shambler_hurt", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "shambler_hurt"), 16));

    public static final RegistryObject<SoundEvent> SHAMBLER_ATTACK = SOUNDS.register("shambler_attack", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "shambler_attack"), 16));

    public static final RegistryObject<SoundEvent> SHAMBLER_DEATH = SOUNDS.register("shambler_death", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "shambler_death"), 16));

    public static final RegistryObject<SoundEvent> SHAMBLER_LIVING = SOUNDS.register("shambler_living", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "shambler_living"), 16));
    public static final RegistryObject<SoundEvent> ABOMINATION_HURT = SOUNDS.register("abomination_hurt", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "abomination_hurt"), 16));

    public static final RegistryObject<SoundEvent> ABOMINATION_ATTACK = SOUNDS.register("abomination_attack", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "abomination_attack"), 16));

    public static final RegistryObject<SoundEvent> ABOMINATION_DEATH = SOUNDS.register("abomination_death", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "abomination_death"), 16));

    public static final RegistryObject<SoundEvent> ABOMINATION_LIVING = SOUNDS.register("abomination_living", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "abomination_living"), 16));

    public static final RegistryObject<SoundEvent> SPECTER_HURT = SOUNDS.register("specter_hurt", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "specter_hurt"), 16));

    public static final RegistryObject<SoundEvent> SPECTER_CLOSE = SOUNDS.register("specter_close", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "specter_close"), 16));

    public static final RegistryObject<SoundEvent> SPECTER_RUNNING = SOUNDS.register("specter_running", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "specter_running"), 16));

    public static final RegistryObject<SoundEvent> SPECTER_DEATH1 = SOUNDS.register("specter_death1", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "specter_death1"), 16));

    public static final RegistryObject<SoundEvent> SPECTER_DEATH2 = SOUNDS.register("specter_death2", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "specter_death2"), 16));

    public static final RegistryObject<SoundEvent> SPECTER_AMBIENT = SOUNDS.register("specter_ambient", () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(Gempire.MODID, "specter_ambient"), 16));


}
