package com.nicolas.gdxbeispiel.lwjgl2;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {

    public static final boolean DRAW_DEBUG_OUTLINE = false;

    public static final String RAW_ASSETS_PATH = "lwjgl2/assets-raw";
    public static final String ASSETS_PATH = "assets";


    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.debug = DRAW_DEBUG_OUTLINE;
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;

        TexturePacker.process(settings,
            RAW_ASSETS_PATH + "/images",
            ASSETS_PATH + "/images",
            "atlasSample");
    }
}
