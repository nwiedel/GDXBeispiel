package com.nicolas.gdxbeispiel.lwjgl2;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.nicolas.gdxbeispiel.common.BeispielFactory;
import com.nicolas.gdxbeispiel.common.BeispielInfos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GDXBeispielLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int CELL_WIDTH = 200;
    private static final int CANVAS_WIDTH = WIDTH - CELL_WIDTH;

    private LwjglAWTCanvas lwjglAWTCanvas;
    private JList beispielList;
    private JPanel controlPanel;

    public GDXBeispielLauncher() throws HeadlessException {
        init();
    }

    private void init(){
        createControlPanel();

        Container container = getContentPane();
        container.add(controlPanel, BorderLayout.WEST);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (lwjglAWTCanvas != null){
                    lwjglAWTCanvas.stop();
                }
            }
        });

        setTitle(GDXBeispielLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void createControlPanel(){
        controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 1;

        beispielList = new JList(
            BeispielInfos.getBeispielNames().toArray());


        beispielList.setFixedCellWidth(CELL_WIDTH);
        beispielList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        beispielList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    launchSelectedBeispiel();
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(beispielList);
        controlPanel.add(scrollPane, c);

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;

        JButton launchButton = new JButton("Starte LibGDX Beispiel");
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchSelectedBeispiel();
            }
        });

        controlPanel.add(launchButton, c);
    }

    private void launchSelectedBeispiel(){
        String beispielName = (String) beispielList.getSelectedValue();

        if (beispielName == null || beispielName.isEmpty()){
            System.out.println("Kein Beispiel gewählt!");
            return;
        }
        launchBeispiel(beispielName);
    }

    private void launchBeispiel(String name){
        System.out.println("Starte Beispiel mit dem Namen: " + name);

        Container container = getContentPane();

        if(lwjglAWTCanvas != null){
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        ApplicationListener beispiel = BeispielFactory.newBeispiel(name);

        lwjglAWTCanvas = new LwjglAWTCanvas(beispiel);
        lwjglAWTCanvas.getCanvas().setSize(CANVAS_WIDTH, HEIGHT);
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
