package com.GUI.Scenes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import com.GUI.ImageButton;
import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.Model.GameLevel;

@SuppressWarnings("serial")
public class GPauseMenu extends GScene implements KeyListener {
    
    /**
     * the background image of this menu
     */
    private BufferedImage bkgImg;
    
    private GameLevel level;

	public GPauseMenu(SceneManager sceneManager, ImageManager imgMan, GameLevel level) {
		super(sceneManager, imgMan);
		this.level = level;
		
//		setOpaque(false);
		try {
			bkgImg = ImageIO.read(new File("./resources/menubackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		setPreferredSize(new Dimension(bkgImg.getWidth(), bkgImg.getHeight()));
		
		
		JButton pauseScrResumeBtn = new ImageButton("./resources/resumebutton.png");
		pauseScrResumeBtn.setAlignmentY(CENTER_ALIGNMENT);
	    pauseScrResumeBtn.addActionListener((ActionEvent ae) -> {
	        this.resumeGame();
	    });
	    add(pauseScrResumeBtn);
		
	    JButton pauseScrQuitBtn = new ImageButton("./resources/exitbutton.png");

		pauseScrQuitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		pauseScrQuitBtn.setAlignmentY(CENTER_ALIGNMENT);
	    pauseScrQuitBtn.addActionListener((ActionEvent ae) -> {
	        this.sceneManager.setScene(new GMainMenu(sceneManager, imgMan));
	    });

	    add(pauseScrQuitBtn);
	    
	    addKeyListener(this);
	    setFocusable(true);
	    requestFocus();
	    repaint();
	}
	
	@Override
    public void paintComponent(Graphics g) {
		// first paint normal grid
        super.paintComponent(g);
        
        g.drawImage(bkgImg, 0, 0, null);
        
    }
	
	public void resumeGame() {
		System.out.println("inside resume game");
		sceneManager.setScene(new GGame(sceneManager, imgMan, level));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		System.out.println("in keypressed");
		if (kc == KeyEvent.VK_P || kc == KeyEvent.VK_ESCAPE) {
			System.out.println("now resuming...");
			resumeGame();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
