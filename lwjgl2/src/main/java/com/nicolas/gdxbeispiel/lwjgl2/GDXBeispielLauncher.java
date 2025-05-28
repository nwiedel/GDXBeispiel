package com.nicolas.gdxbeispiel.lwjgl2;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.utils.reflect.ClassReflection;

import javax.swing.*;
import java.awt.*;

public class GDXBeispielLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private LwjglAWTCanvas lwjglAWTCanvas;

    public GDXBeispielLauncher() throws HeadlessException {
        setTitle(GDXBeispielLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        launchBeispiel("com.nicolas.gdxbeispiel.BasicsInputPolling");
    }

    private void launchBeispiel(String name){
        System.out.println("Starte Beispiel mit dem Namen: " + name);

        Container container = getContentPane();

        if(lwjglAWTCanvas != null){
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        ApplicationListener beispiel;
        try{
            Class<ApplicationListener> clazz = ClassReflection.forName(name);

            beispiel = ClassReflection.newInstance(clazz);
        } catch (Exception e){
            throw new RuntimeException("Kann das Beispiel mit Namen: " + name + " nicht starten!");
        }
        lwjglAWTCanvas = new LwjglAWTCanvas(beispiel);
        lwjglAWTCanvas.getCanvas().setSize(WIDTH, HEIGHT);
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GDXBeispielLauncher();
            }
        });
    }
}
