package com.GUI.Scenes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.GUI.ImageButton;
import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.Model.Box;
import com.Model.GameBoard;
import com.Model.Point;

@SuppressWarnings("serial")
public class GPauseMenu extends GScene implements KeyListener {
    
    /**
     * the background image of this menu
     */
    private BufferedImage bkgImg;
    
    private GGame context;

	public GPauseMenu(SceneManager sceneManager, ImageManager imgMan, GGame context) {
		super(sceneManager, imgMan);
		this.context = context;
		
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
	        this.sceneManager.switchScene(SceneManager.MAIN_MENU_ID);
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
	
//	public BufferedImage setAlpha(byte alpha, BufferedImage obj_img) {       
//	    alpha %= 0xff; 
//	    for (int cx=0;cx<obj_img.getWidth();cx++) {          
//	        for (int cy=0;cy<obj_img.getHeight();cy++) {
//	            int color = obj_img.getRGB(cx, cy);
//
//	            int mc = (alpha << 24) | 0x00ffffff;
//	            int newcolor = color & mc;
//	            obj_img.setRGB(cx, cy, newcolor);            
//
//	        }
//
//	    }
//	    return obj_img;
//	}
//	
//	private static BufferedImage copy(BufferedImage bi) {
//		 ColorModel cm = bi.getColorModel();
//		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
//		WritableRaster raster = bi.copyData(null);
//		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
//	}
	
	public void resumeGame() {
		System.out.println("inside resume game");
		sceneManager.add(context.getControlPanel());
		sceneManager.switchScene(SceneManager.GAME_ID);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		System.out.println("in keypressed");
		if (kc == KeyEvent.VK_P) {
			System.out.println("now resuming...");
			resumeGame();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
