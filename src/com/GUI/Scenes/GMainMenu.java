package com.GUI.Scenes;

import com.GUI.ImageButton;
import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.Model.Difficulty;
import com.Model.GameConstants;
import com.Model.SokobanLevel;

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

    private JPanel chooserPanel;

    public GMainMenu(SceneManager sceneManager, ImageManager imgMan) {
        super(sceneManager, imgMan);
        
        try {
			bkgImg = ImageIO.read(new File("./resources/menubackground.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

        this.setPreferredSize(new Dimension(bkgImg.getWidth(), bkgImg.getHeight() - GameConstants.IMAGE_DIMENSION));

        JButton btnEz = new ImageButton("./resources/easybutton.png");
        btnEz.addActionListener((ActionEvent e) -> {
            playSound(new File("./sound_files/gamestart.wav"));
            this.sceneManager.setScene(new GGame(sceneManager, imgMan, new SokobanLevel(Difficulty.EASY)));
        });
        JButton btnNorm = new ImageButton("./resources/normalbutton.png");
        btnNorm.addActionListener((ActionEvent e) -> {
            playSound(new File("./sound_files/gamestart.wav"));
            sceneManager.setScene(new GGame(sceneManager, imgMan, new SokobanLevel(Difficulty.NORMAL)));
        });
        JButton btnOP = new ImageButton("./resources/hardbutton.png");
        btnOP.addActionListener((ActionEvent e) -> {
            playSound(new File("./sound_files/gamestart.wav"));
            sceneManager.setScene(new GGame(sceneManager, imgMan, new SokobanLevel(Difficulty.HARD)));
        });
        
        btnHelp = new ImageButton("./resources/helpbutton.png");
        btnHelp.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Help");
        });
        
        btnExit = new ImageButton("./resources/exitbutton.png");
        btnExit.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.exit(0);
        });
        btnEz.setAlignmentY(CENTER_ALIGNMENT);
        btnEz.setAlignmentX(CENTER_ALIGNMENT);
        btnNorm.setAlignmentY(CENTER_ALIGNMENT);
        btnNorm.setAlignmentX(CENTER_ALIGNMENT);
        btnOP.setAlignmentY(CENTER_ALIGNMENT);
        btnOP.setAlignmentX(CENTER_ALIGNMENT);
        
        add(btnEz);
        add(btnNorm);
        add(btnOP);
        add(btnHelp);
        add(btnExit);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	g.drawImage(bkgImg, 0, 0, null);
    }
}
