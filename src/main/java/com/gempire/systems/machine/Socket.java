package com.gempire.systems.machine;

public class Socket {
    MachineSide side;
    SocketType type;
    boolean IO;
    boolean chargeable;

    public Socket(MachineSide side, SocketType type, boolean IO, boolean chargeable){
        this.side = side;
        this.type = type;
        this.IO = IO;
        this.chargeable = chargeable;
    }

    public static Socket GENERIC(MachineSide side){
        return new Socket(side, SocketType.NONE, true, true);
    }

    public MachineSide getSide() {
        return side;
    }

    public SocketType getType() {
        return type;
    }

    public boolean isIO() {
        return IO;
    }

    public boolean isChargeable() {
        return chargeable;
    }
}
