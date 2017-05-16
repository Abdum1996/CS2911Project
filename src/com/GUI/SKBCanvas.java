package com.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.EntityTypes;
import com.GameBoard;
import com.Point;
import com.Box;

/**
 * Sokoban Canvas
 * custom canvas for drawing
 */
public class SKBCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private ImageManager imgMan;
    private GameBoard board;

    private int w;
    private int h;

    public SKBCanvas(ImageManager imgMan, GameBoard board) {
        this.imgMan = imgMan;
        this.board = board;

        this.w = board.getMapWidth();
        this.h = board.getMapHeight();
        this.setPreferredSize(new Dimension(this.w*imgMan.getImgWidth(), this.h*imgMan.getImgHeight()));
        this.setLayout(new GridLayout(w, h));
    }
    
    public void replaceGrid(GameBoard newBoard) {
    	this.board = newBoard;

        this.w = board.getMapWidth();
        this.h = board.getMapHeight();
        this.setPreferredSize(new Dimension(this.w*imgMan.getImgWidth(), this.h*imgMan.getImgHeight()));
        this.setLayout(new GridLayout(w, h));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("Grid", 0, 0);

        int x = 0;
        int y = 0;

        // paint all tiles
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
            	Point pos = Point.at(j, i);
            	
                g.drawImage(imgMan.getTileImg(board.getTile(pos)), x, y, null);
                x += imgMan.getImgHeight();
            }
            y += imgMan.getImgWidth();
            x = 0;
        }

        BufferedImage box    = imgMan.getEntityImg(EntityTypes.BOX);
        BufferedImage player = imgMan.getEntityImg(EntityTypes.PLAYER);
        
        for (Box curr : board.getBoxes()) {
        	Point pos = curr.getPosition();
        	
        	x = pos.getX()*box.getWidth();
        	y = pos.getY()*box.getHeight();
        	g.drawImage(box, x, y, null);
        }
        
        Point playerPos = board.getPlayer().getPosition();
        x = playerPos.getX()*player.getWidth();
        y = playerPos.getY()*player.getHeight();
        
        g.drawImage(player, x, y, null);
    }
}
