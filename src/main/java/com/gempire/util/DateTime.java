package com.gempire.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    static DateTimeFormatter dtfMD = DateTimeFormatter.ofPattern("MM/dd");
    static DateTimeFormatter dtfM = DateTimeFormatter.ofPattern("MM");

    static LocalDateTime now = LocalDateTime.now();

    public static boolean aprilFools() {
        return dtfMD.format(now).equals("04/01");
    }

    public boolean prideMonth() {
        return dtfM.format(now).equals("06");
    }

    public static boolean halloween() {
        return dtfMD.format(now).equals("10/31");
    }

    public static boolean christmas() {
        return dtfMD.format(now).equals("12/24") || dtfMD.format(now).equals("12/25");
    }
}
