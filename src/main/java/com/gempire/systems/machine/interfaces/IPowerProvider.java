package com.gempire.systems.machine.interfaces;

public interface IPowerProvider extends ISixWayInterface {
    float getVoltage();
    void combineVoltage(float inVoltage);
    void setVoltage(float inVoltage);
    boolean isSource();
}
