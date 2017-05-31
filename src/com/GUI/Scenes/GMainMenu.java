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
<<<<<<< HEAD
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

        JButton btnCredits = new ImageButton("./resources/creditbutton.png");
        btnCredits.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            sceneManager.setScene(new GCredits(sceneManager, imgMan));
        });
=======
>>>>>>> parent of 589af45... cleaned GGame a bit, added photos to main menu
        btnHelp = new JButton("Help");
        btnHelp.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            sceneManager.setScene(new GHelp(sceneManager, imgMan));
        });
<<<<<<< HEAD
        
        btnExit = new ImageButton("./resources/exitbutton.png");
        btnExit.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.exit(0);
        });
<<<<<<< HEAD
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
        add(btnCredits);
        add(btnExit);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight() );
    	g.drawImage(bkgImg, 0, 0, null);
=======
=======
>>>>>>> parent of 589af45... cleaned GGame a bit, added photos to main menu
        btnCredits = new JButton("Credits");
        btnCredits.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Credits");
        });

        this.add(btnStartGame);
        this.add(btnHelp);
        this.add(btnCredits);
>>>>>>> parent of 589af45... cleaned GGame a bit, added photos to main menu
    }
}
