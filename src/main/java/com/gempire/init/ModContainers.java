package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.container.*;
import com.gempire.systems.warping.WarpConfigMenu;
import com.gempire.systems.warping.WarpSelectionMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Gempire.MODID);

    //public static final RegistryObject<ContainerType<TestContainer>> TEST_CONTAINER = CONTAINERS.register("test_container", () -> IForgeContainerType.create(TestContainer::createContainerClientSide));

    public static final RegistryObject<MenuType<BoardContainer>> BOARD_CONTAINER = CONTAINERS.register("board_container", () -> IForgeMenuType.create(BoardContainer::new));
    public static final RegistryObject<MenuType<InjectorContainer>> INJECTOR_CONTAINER = CONTAINERS.register("injector_container", () -> IForgeMenuType.create(InjectorContainer::new));
    public static final RegistryObject<MenuType<GemUIContainer>> GEM_UI_CONTAINER = CONTAINERS.register("gem_ui_container", () -> IForgeMenuType.create(GemUIContainer::new));
    public static final RegistryObject<MenuType<ShellContainer>> SHELL_CONTAINER = CONTAINERS.register("shell_container", () -> IForgeMenuType.create(ShellContainer::new));
    public static final RegistryObject<MenuType<IncubatorContainer>> INCUBATOR_CONTAINER = CONTAINERS.register("incubator_container", () -> IForgeMenuType.create(IncubatorContainer::new));
    public static final RegistryObject<MenuType<PearlUIContainer>> PEARL_UI_CONTAINER = CONTAINERS.register("pearl_ui_container", () -> IForgeMenuType.create(PearlUIContainer::new));
    public static final RegistryObject<MenuType<PearlDefectiveUIContainer>> PEARL_DEFECTIVE_UI_CONTAINER = CONTAINERS.register("pearl_defective_ui_container", () -> IForgeMenuType.create(PearlDefectiveUIContainer::new));
    public static final RegistryObject<MenuType<ZirconUIContainer>> ZIRCON_UI_CONTAINER = CONTAINERS.register("zircon_ui_container", () -> IForgeMenuType.create(ZirconUIContainer::new));
    public static final RegistryObject<MenuType<WarpSelectionMenu>> WARP_SELECTION = CONTAINERS.register("warp_selection", () -> IForgeMenuType.create(WarpSelectionMenu::new));
    public static final RegistryObject<MenuType<WarpConfigMenu>> WARP_CONFIG = CONTAINERS.register("warp_config", () -> IForgeMenuType.create(WarpConfigMenu::new));
}
