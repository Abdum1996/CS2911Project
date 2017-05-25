package com.GUI.Scenes;

import com.GUI.ImageButton;
import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.Model.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GMainMenu extends GScene {
    private JButton btnStartGame;
    private JButton btnHelp;
    private JButton btnCredits;

    public GMainMenu(SceneManager sceneManager, ImageManager imgMan) {
        super(sceneManager, imgMan);

        this.setPreferredSize(new Dimension(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT));
        
        btnStartGame = new ImageButton("./resources/startbutton.png");
        btnStartGame.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Start game, do stuff");

            this.sceneManager.setScene(SceneManager.GAME_ID, new GGame(sceneManager, imgMan, "./maps/map1.txt"));
        });
        
        btnHelp = new ImageButton("./resources/helpbutton.png");
        btnHelp.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Help");
        });
        
        btnCredits = new ImageButton("./resources/exitbutton.png");
        btnCredits.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Credits");
        });
        
        this.add(btnStartGame);
        this.add(btnHelp);
        this.add(btnCredits);
    }
}
