package com.gempire.systems.machine.interfaces;

import com.gempire.blocks.markers.IPowerMarker;

import java.util.ArrayList;

public interface IPowerConsumer extends IPowerConductor {

    ArrayList<IPowerGenerator> getGenerators();
}
