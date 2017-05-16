package com.GUI.Scenes;

import com.*;
import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.Point;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Sokoban Canvas
 * custom canvas for drawing
 */
public class GGame extends GScene implements KeyListener {
    private static final long serialVersionUID = 1L;
    private ImageManager imgMan;

    private int w;
    private int h;

    private GameBoard board;
    private String map;

    public GGame(SceneManager sceneManager, ImageManager imgMan, String map) {
        super(sceneManager, imgMan);

        this.imgMan = imgMan;
        this.map = map;
        board = SokobanBoard.readFile(map);

        this.w = board.getMapWidth();
        this.h = board.getMapHeight();
        this.setPreferredSize(new Dimension(this.w * imgMan.getImgWidth(), this.h * imgMan.getImgHeight()));
        this.setLayout(new GridLayout(w, h));

        System.out.println("listner");
        this.addKeyListener(this);

    }

    public void reset() {
        this.sceneManager.setScene(new GGame(this.sceneManager, this.imgMan, this.map));
    }

    private void applyAction(Action action) {
        this.board.applyAction(action);
    }

    private boolean gameWon() {
        return this.board.gameWon();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed: " + e);

        int kc = e.getKeyCode();
        if (kc == KeyEvent.VK_D || kc == KeyEvent.VK_RIGHT) {
            applyAction(Action.MOVE_RIGHT);
        } else if (kc == KeyEvent.VK_A || kc ==KeyEvent.VK_LEFT) {
            applyAction(Action.MOVE_LEFT);
        } else if (kc == KeyEvent.VK_W || kc ==KeyEvent.VK_UP) {
            applyAction(Action.MOVE_UP);
        } else if (kc ==KeyEvent.VK_S || kc == KeyEvent.VK_DOWN) {
            applyAction(Action.MOVE_DOWN);
        } else if (kc == KeyEvent.VK_R) {
            reset();
        }

        if (gameWon()) {
            System.out.println("Game Won!");
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

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

        BufferedImage box = imgMan.getEntityImg(EntityTypes.BOX);
        BufferedImage player = imgMan.getEntityImg(EntityTypes.PLAYER);

        for (Box curr : board.getBoxes()) {
            Point pos = curr.getPosition();

            x = pos.getX() * box.getWidth();
            y = pos.getY() * box.getHeight();
            g.drawImage(box, x, y, null);
        }

        Point playerPos = board.getPlayer().getPosition();
        x = playerPos.getX() * player.getWidth();
        y = playerPos.getY() * player.getHeight();

        g.drawImage(player, x, y, null);
    }
}
