package com.gempire.util;

import com.gempire.tileentities.PowerProviderTE;

public class Debug {

    public static void OnReceivePackage(PowerProviderTE te){
        System.out.println("[DEBUG]: Received an energy package at: " + te.getPos());
    }
}
