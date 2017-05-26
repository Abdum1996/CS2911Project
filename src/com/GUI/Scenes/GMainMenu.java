package com.GUI.Scenes;

import com.GUI.ImageButton;
import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.Model.GameConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class GMainMenu extends GScene {
	/**
	 * Holds the button for the start game
	 */
    private JButton btnStartGame;
    
    /**
     * Holds the button to get the help screen
     */
    private JButton btnHelp;
    
    /**
     * holds the button that switches you to the credits screen
     */
    private JButton btnExit;
    
    /**
     * the background image of this menu
     */
    private BufferedImage bkgImg;

    public GMainMenu(SceneManager sceneManager, ImageManager imgMan) {
        super(sceneManager, imgMan);
        
        try {
			bkgImg = ImageIO.read(new File("./resources/menubackground.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

        this.setPreferredSize(new Dimension(bkgImg.getWidth(), bkgImg.getHeight() - GameConstants.IMAGE_DIMENSION));
        
        btnStartGame = new ImageButton("./resources/startbutton.png");
        btnStartGame.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Start game, do stuff");
            playSound(new File("./sound_files/gamestart.wav"));

            this.sceneManager.setScene(SceneManager.GAME_ID, new GGame(sceneManager, imgMan, "./maps/map5.txt"));
        });
        
        btnHelp = new ImageButton("./resources/helpbutton.png");
        btnHelp.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Help");
        });
        
        btnExit = new ImageButton("./resources/exitbutton.png");
        btnExit.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.exit(0);
        });
        btnStartGame.setAlignmentY(CENTER_ALIGNMENT);
        btnStartGame.setAlignmentX(CENTER_ALIGNMENT);
        
        add(btnStartGame);
        add(btnHelp);
        add(btnExit);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	g.drawImage(bkgImg, 0, 0, null);
    }
}
