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
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                g.drawImage(imgMan.getTileImg(grid.get(i, j)), x, y, null);
                x += this.imgMan.getImgWidth();
            }
            y += this.imgMan.getImgHeight();
            x = 0;
        }

        // paint entities over the tiles

        //JLabel picLabel = new JLabel();
        //add(picLabel);
    }
}
