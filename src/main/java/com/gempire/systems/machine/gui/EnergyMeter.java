package com.gempire.systems.machine.gui;

import com.gempire.Gempire;
import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.interfaces.IPowerProvider;
import com.gempire.tileentities.PowerProviderTE;
import com.gempire.util.GUIUtilities;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;

public class EnergyMeter {
    public static final ResourceLocation POWER_METER_NORMAL = new ResourceLocation("gempire:textures/gui/power_meter.png");
    public static final ResourceLocation POWER_METER_INJECTOR = new ResourceLocation("gempire:textures/gui/power_meter_small.png");

    public static final ResourceLocation POWER_METER_FILL_NORMAL = new ResourceLocation("gempire:textures/gui/power_meter_fill.png");
    public static final ResourceLocation POWER_METER_FILL_INJECTOR = new ResourceLocation("gempire:textures/gui/power_meter_fill_small.png");

    public static void RenderBattery(AbstractContainerScreen screen, PoseStack stack, BlockEntity tileEntity, IPowerProvider powerProvider, int x, int y, MeterSize size){

        GUIUtilities.setup(getMeterLocationFromSize(size));
        screen.blit(stack, x, y, getMeterUV_X_FromSize(size), getMeterUV_Y_FromSize(size), 0, 0, getMeterUV_X_FromSize(size), getMeterUV_Y_FromSize(size), getMeterUV_X_FromSize(size), getMeterUV_Y_FromSize(size));

        GUIUtilities.setup(getMeterFillLocationFromSize(size));
        Battery battery = powerProvider.getBattery();
        int powerStored = (int) Math.floor((getMeterUV_X_FromSize(size) - 2) * battery.getCharge() / battery.getMaxCapacity());
        screen.blit(stack, x + 1, y + 1, powerStored, getMeterUV_Y_FromSize(size) - 2, 0, 0,
                powerStored, getMeterUV_Y_FromSize(size) - 2, getMeterUV_X_FromSize(size) - 2, getMeterUV_Y_FromSize(size) - 2);
    }

    public static ResourceLocation getMeterLocationFromSize(MeterSize size){
        switch (size){
            case INJECTOR:
                return POWER_METER_INJECTOR;
            default:
                return POWER_METER_NORMAL;
        }
    }

    public static ResourceLocation getMeterFillLocationFromSize(MeterSize size){
        switch (size){
            case INJECTOR:
                return POWER_METER_FILL_INJECTOR;
            default:
                return POWER_METER_FILL_NORMAL;
        }
    }

    public static int getMeterUV_X_FromSize(MeterSize size){
        switch (size){
            case INJECTOR:
                return 42;
            default:
                return 162;
        }
    }

    public static int getMeterUV_Y_FromSize(MeterSize size){
        return 6;
    }
}
