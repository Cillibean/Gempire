package com.gempire.util;

import com.gempire.tileentities.PowerProviderTE;
import net.minecraft.tileentity.TileEntity;

public class Debug {

    public static void OnReceivePackage(TileEntity te){
        System.out.println("[DEBUG]: Received an energy package at: " + te.getPos());
    }

    public static void OnRejectPackage(TileEntity te){
        System.out.println("[DEBUG]: Rejected an energy package at: " + te.getPos());
    }
}
