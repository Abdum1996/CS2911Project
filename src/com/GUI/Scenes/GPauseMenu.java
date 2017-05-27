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
<<<<<<< HEAD
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;

=======
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.GUI.ImageButton;
>>>>>>> master
import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.Model.Box;
import com.Model.GameBoard;
import com.Model.Point;

@SuppressWarnings("serial")
public class GPauseMenu extends GScene implements KeyListener {
<<<<<<< HEAD
	
	private GameBoard context;
	
	/**
     * width of this grid
     */
    private int w;
    
    /**
     * height of this grid
     */
    private int h;
    
    private final byte alpha = (byte) 190;

	public GPauseMenu(SceneManager sceneManager, ImageManager imgMan, GameBoard context) {
		super(sceneManager, imgMan);
		this.w = context.getMapWidth();
		this.h = context.getMapHeight();
		this.context = context;
		
		
//		setOpaque(false);
		setBackground(new Color(0, 0, 0));
		setPreferredSize(new Dimension(this.w * imgMan.getImgWidth(), this.h * imgMan.getImgHeight()));
		
		
		JButton pauseScrResumeBtn = new JButton("Resume");
=======
    
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
>>>>>>> master
		pauseScrResumeBtn.setAlignmentY(CENTER_ALIGNMENT);
	    pauseScrResumeBtn.addActionListener((ActionEvent ae) -> {
	        this.resumeGame();
	    });
	    add(pauseScrResumeBtn);
		
<<<<<<< HEAD
	    JButton pauseScrQuitBtn = new JButton("Quit To Main Menu");
=======
	    JButton pauseScrQuitBtn = new ImageButton("./resources/exitbutton.png");
>>>>>>> master

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
        
<<<<<<< HEAD
        g.drawString("Grid", 0, 0);

        int x = 0;
        int y = 0;

        // paint all tiles
	    for (int i = 0; i < h; i++) {
	        for (int j = 0; j < w; j++) {
	            Point pos = Point.at(j, i);
	
	            g.drawImage(imgMan.getTileImg(context.getTile(pos)), x, y, null);
	            x += imgMan.getImgHeight();
	        }
	        y += imgMan.getImgWidth();
	        x = 0;
	    }
	
	    BufferedImage box = imgMan.getBoxImg(0);
	    BufferedImage player = imgMan.getPlayerImg(context.getPlayer().getOrientation());
	
	    Iterator<Box> it = context.getBoxes();
	    while (it.hasNext()) {
	    	Box curr = it.next();
	        Point pos = curr.getPosition();
	
	        x = pos.getX() * box.getWidth();
	        y = pos.getY() * box.getHeight();
	        g.drawImage(imgMan.getBoxImg(curr.getId()), x, y, null);
	    }
	
	    Point playerPos = context.getPlayer().getPosition();
	    x = playerPos.getX() * player.getWidth();
	    y = playerPos.getY() * player.getHeight();
	
	    g.drawImage(player, x, y, null);
=======
        g.drawImage(bkgImg, 0, 0, null);
>>>>>>> master
        
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
<<<<<<< HEAD
=======
		sceneManager.add(context.getControlPanel());
		context.getControlPanel().repaintAll();
>>>>>>> master
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
<<<<<<< HEAD
		if (kc == KeyEvent.VK_P) {
=======
		if (kc == KeyEvent.VK_P || kc == KeyEvent.VK_ESCAPE) {
>>>>>>> master
			System.out.println("now resuming...");
			resumeGame();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
