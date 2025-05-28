package com.nicolas.gdxbeispiel.lwjgl2;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nicolas.gdxbeispiel.BasicsGDX;
import com.nicolas.gdxbeispiel.BasicsModule;

/** Launches the desktop (LWJGL2) application. */
public class LauncherModule {
    public static void main(String[] args) {
        createApplication();
    }

    private static LwjglApplication createApplication() {
        return new LwjglApplication(new BasicsModule(), getDefaultConfiguration());
    }

    private static LwjglApplicationConfiguration getDefaultConfiguration() {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = "GDXBeispiel";
        configuration.width = 640;
        configuration.height = 480;
        //// This prevents a confusing error that would appear after exiting normally.
        configuration.forceExit = false;

        for (int size : new int[] { 128, 64, 32, 16 }) {
            configuration.addIcon("libgdx" + size + ".png", FileType.Internal);
        }
        return configuration;
    }
}
