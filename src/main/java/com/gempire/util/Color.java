package com.gempire.util;

import org.jline.utils.Colors;

import java.util.ArrayList;
import java.util.Random;

public class Color {

    public static int lerpHex(ArrayList<Integer> colors){
        Random random = new Random();
        if (colors.size() == 0) {
            return 0;
        }
        if (colors.size() == 1) {
            return colors.get(0);
        }

        int r = 0;
        int g = 0;
        int b = 0;
        float u = random.nextFloat();

        int bound = random.nextInt(colors.size() - 1);

        int b_r = (colors.get(bound) & 16711680) >> 16;
        int b_g = (colors.get(bound) & 65280) >> 8;
        int b_b = (colors.get(bound) & 255);
        int e_r = (colors.get(bound + 1) & 16711680) >> 16;
        int e_g = (colors.get(bound + 1) & 65280) >> 8;
        int e_b = (colors.get(bound + 1) & 255);

        r = (int) (u * b_r + (1f - u) * e_r);
        g = (int) (u * b_g + (1f - u) * e_g);
        b = (int) (u * b_b + (1f - u) * e_b);

        return (r << 16) + (g << 8) + b;
    }

    public static String getColorName(int c){
        Random random = new Random();
        if(c > 15 || c < 0){
            return Color.getColorName(random.nextInt(16));
        }
        return switch (c) {
            case 1 -> "orange";
            case 2 -> "magenta";
            case 3 -> "light_blue";
            case 4 -> "yellow";
            case 5 -> "lime";
            case 6 -> "pink";
            case 7 -> "gray";
            case 8 -> "light_gray";
            case 9 -> "cyan";
            case 10 -> "purple";
            case 11 -> "blue";
            case 12 -> "brown";
            case 13 -> "green";
            case 14 -> "red";
            case 15 -> "black";
            default -> "white";
        };
    }
}
