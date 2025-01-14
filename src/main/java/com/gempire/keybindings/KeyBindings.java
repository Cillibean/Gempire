package com.gempire.keybindings;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final String KEY_CATEGORIES_GEMPIRE = "key.categories.gempire";
    public static final String KEY_WARP = "key.gempire.warp";
    public static final String KEY_DESCEND = "key.gempire.descend";
    public static final String KEY_GLIDE = "key.gempire.glide";


    public static final KeyMapping WARP_KEY = new KeyMapping(KEY_WARP, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_PERIOD, KEY_CATEGORIES_GEMPIRE);

    public static final KeyMapping DESCENDING_KEY = new KeyMapping(KEY_DESCEND, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORIES_GEMPIRE);

    public static final KeyMapping GLIDE_KEY = new KeyMapping(KEY_GLIDE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORIES_GEMPIRE);


}
