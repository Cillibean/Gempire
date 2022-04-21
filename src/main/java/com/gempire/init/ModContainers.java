package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.container.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Gempire.MODID);

    //public static final RegistryObject<ContainerType<TestContainer>> TEST_CONTAINER = CONTAINERS.register("test_container", () -> IForgeContainerType.create(TestContainer::createContainerClientSide));
    public static final RegistryObject<MenuType<TankContainer>> TANK_CONTAINER = CONTAINERS.register("tank_container", () -> IForgeContainerType.create(TankContainer::new));
    public static final RegistryObject<MenuType<InjectorContainer>> INJECTOR_CONTAINER = CONTAINERS.register("injector_container", () -> IForgeContainerType.create(InjectorContainer::new));
    public static final RegistryObject<MenuType<GemUIContainer>> GEM_UI_CONTAINER = CONTAINERS.register("gem_ui_container", () -> IForgeContainerType.create(GemUIContainer::new));
    public static final RegistryObject<MenuType<ShellContainer>> SHELL_CONTAINER = CONTAINERS.register("shell_container", () -> IForgeContainerType.create(ShellContainer::new));
    public static final RegistryObject<MenuType<PearlUIContainer>> PEARL_UI_CONTAINER = CONTAINERS.register("pearl_ui_container", () -> IForgeContainerType.create(PearlUIContainer::new));
    public static final RegistryObject<MenuType<ZirconUIContainer>> ZIRCON_UI_CONTAINER = CONTAINERS.register("zircon_ui_container", () -> IForgeContainerType.create(ZirconUIContainer::new));
}
