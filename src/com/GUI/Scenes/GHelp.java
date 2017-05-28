package com.GUI.Scenes;


import com.GUI.ImageButton;
import com.GUI.ImageManager;
import com.GUI.SceneManager;

import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GHelp extends GScene  {
	
	private BufferedImage bkgImg;
	
    public GHelp(SceneManager sceneManager, ImageManager imgMan) {
        super(sceneManager, imgMan);
        
        try {
			bkgImg = ImageIO.read(new File("./resources/menubackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

        String intrtxt = "<html> <font color='white'>Java has upset the CSE gods <br>"
        		+ "and has gotten himself<br>"
        		+ " trapped in a dungeon full of misplaced boxes!<br>"
        		+ " To put it simply,"
        		+ "he’s been wobkeked.To return himself to <br>"
        		+ "the mortal plane, Java must journey <br>"
        		+ "through a set of challenges varying in <br>"
        		+ "difficulty involving the moving of <br>"
        		+ "boxes into marked goal locations. <br>"
        		+ "The handicap is that he can only push!<br>"
        		+ "Java has been sanDeeply lost in the <br>"
        		+ "Sokoban-esque levels for a considerable<br>"
        		+ " amount of time now and needs your help to get back.<br>"
        		+ "To guide Java around the map, simply use the <br>"
        		+ "arrow keys of WASD equivalent.<br>"
        		+ "If the challenge gets too tough, press the <br>"
        		+ "ESC or P key to pause for a breather. <br>"
        		+ "(Mashing keys is a lot of work after all.)<br>"
        		+ "Get back into the game by simply pressing the pause key again.<br>"
        		+ "Goodluck and have pun!</font></html>";

        JLabel instr = new JLabel(intrtxt);
        JButton returnBtn = new ImageButton( "./resources/exitbutton.png");
        returnBtn.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            sceneManager.setScene(new GMainMenu(sceneManager, imgMan));
        });
        

        this.add(instr);
        this.add(returnBtn);
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(bkgImg, 0, 0, null);
    }
}
