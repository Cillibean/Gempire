package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.container.InjectorContainer;
import com.gempire.container.TankContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Gempire.MODID);

    //public static final RegistryObject<ContainerType<TestContainer>> TEST_CONTAINER = CONTAINERS.register("test_container", () -> IForgeContainerType.create(TestContainer::createContainerClientSide));
    public static final RegistryObject<ContainerType<TankContainer>> TANK_CONTAINER = CONTAINERS.register("tank_container", () -> IForgeContainerType.create(TankContainer::new));
    public static final RegistryObject<ContainerType<InjectorContainer>> INJECTOR_CONTAINER = CONTAINERS.register("injector_container", () -> IForgeContainerType.create(InjectorContainer::new));
}
