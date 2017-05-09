package com.GUI;

import com.Grid;
import com.SokobanGrid;
import com.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Sokoban Canvas
 * custom canvas for drawing
 */
public class SKBCanvas extends JPanel {
    private ImageManager imgMan;
    private SokobanGrid grid;

    private int w;
    private int h;

    public SKBCanvas(ImageManager imgMan, SokobanGrid grid) {
        this.imgMan = imgMan;
        this.grid = grid;

        this.w = grid.getWidth();
        this.h = grid.getHeight();
        this.setPreferredSize(new Dimension(this.w*imgMan.getImgWidth(), this.h*imgMan.getImgHeight()));
        this.setLayout(new GridLayout(w, h));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("Grid", 0, 0);

        int x=0;
        int y=0;

        // paint all tiles
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                g.drawImage(imgMan.getTileImg(grid.get(j, i)), x, y, null);
                x += this.imgMan.getImgHeight();
            }
            y += this.imgMan.getImgWidth();
            x = 0;
        }

        BufferedImage box    = imgMan.getEntityImg(com.EntityTypes.BOX);
        BufferedImage player = imgMan.getEntityImg(com.EntityTypes.PLAYER);
        
        for (com.Box curr : grid.getBoxes()) {
        	x = curr.getCoordinates().getX()*box.getWidth();
        	y = curr.getCoordinates().getY()*box.getHeight();
        	
        	g.drawImage(box, x, y, null);
        }
        
        x = grid.getPlayer().x()*player.getWidth();
        y = grid.getPlayer().y()*player.getHeight();
        
        g.drawImage(player, x, y, null);
        //JLabel picLabel = new JLabel();
        //add(picLabel);
    }
}
