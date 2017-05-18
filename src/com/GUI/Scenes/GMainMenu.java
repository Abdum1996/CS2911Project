package com.GUI.Scenes;

import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GMainMenu extends GScene {
    private JButton btnStartGame;
    private JButton btnHelp;
    private JButton btnCredits;

    public GMainMenu(SceneManager sceneManager, ImageManager imgMan) {
        super(sceneManager, imgMan);

        this.setPreferredSize(new Dimension(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT));

        btnStartGame = new JButton("Start Game");
        btnStartGame.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Start game, do stuff");

            this.sceneManager.setScene(new GGame(sceneManager, imgMan, "./maps/map1.txt"));
        });
        btnHelp = new JButton("Help");
        btnHelp.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Help");
        });
        btnCredits = new JButton("Credits");
        btnCredits.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Credits");
        });

        this.add(btnStartGame);
        this.add(btnHelp);
        this.add(btnCredits);
    }
}
