package com.gempire.entities.util;

public interface ICustomMoveController {
    void up(boolean up);

    void down(boolean down);

    void attack(boolean attack);

    void strike(boolean strike);

    void dismount(boolean dismount);

    void setControlState(byte state);

    byte getControlState();
}
