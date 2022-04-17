package com.gempire.util;

import com.gempire.tileentities.PowerProviderTE;
import net.minecraft.world.level.block.entity.BlockEntity;

public class Debug {

    public static void OnReceivePackage(BlockEntity te){
        System.out.println("[DEBUG]: Received an energy package at: " + te.getBlockPos());
    }

    public static void OnRejectPackage(BlockEntity te){
        System.out.println("[DEBUG]: Rejected an energy package at: " + te.getBlockPos());
    }
}
