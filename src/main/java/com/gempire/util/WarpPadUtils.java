package com.gempire.util;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class WarpPadUtils {
    public static final Random RANDOM = new Random();
    public static float getRandom() {
        return RANDOM.nextFloat();
    }
    public static Vec3 getTopCenter(BlockPos pos) {
        return new Vec3(pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F);
    }
    public static float getDirectionX(float angle) {
        return (float)Math.sin(2 * Math.PI * angle);
    }
    public static float getDirectionZ(float angle) {
        return (float)Math.cos(2 * Math.PI * angle);
    }
    public static Vec3 getDirection(float angle) {
        float x = getDirectionX(angle);
        float z = getDirectionZ(angle);
        return new Vec3(x, 0, z);
    }
    public static Vec3 getDirection() {
        return getDirection(getRandom());
    }
    public static Vec3 getPositionOnSquare(Vec3 pos, float apothem) {
        float radius = Mth.sqrt(2 * Mth.square(apothem));
        Vec3 dir = getDirection().scale(radius);
        double x = Mth.clamp(dir.x, -apothem, apothem);
        double z = Mth.clamp(dir.z, -apothem, apothem);
        return pos.add(x, 0, z);
    }
    public static AABB getBoxAbovePosition(Vec3 pos, float width, float height) {
        Vec3 bottom = pos.add(-width / 2, 0, -width / 2);
        Vec3 top = bottom.add(width, height, width);
        return new AABB(bottom, top);
    }
    public static float[] brightenColor(float[] color, float delta) {
        float r = Math.min(1, color[0] + delta);
        float g = Math.min(1, color[1] + delta);
        float b = Math.min(1, color[2] + delta);
        return new float[]{r, g, b};
    }
}
