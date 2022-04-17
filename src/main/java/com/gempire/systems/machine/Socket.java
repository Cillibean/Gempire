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

    public Socket(MachineSide side, SocketType type, boolean chargeable){
        this.side = side;
        this.type = type;
        this.IO = true;
        this.chargeable = chargeable;
    }

    public static Socket GENERIC(MachineSide side){
        return new Socket(side, SocketType.NONE, true, false);
    }

    public static Socket POWER_IN(MachineSide side){
        return new Socket(side, SocketType.POWER, true);
    }
    public static Socket POWER_OUT(MachineSide side){
        return new Socket(side, SocketType.POWER, false, true);
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
