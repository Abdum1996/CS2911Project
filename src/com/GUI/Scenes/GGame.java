package com.GUI.Scenes;

import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.Model.*;
import com.Model.Action;
import com.Model.Box;
import com.Model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

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

    private boolean paused = false; // is the game paused?
    private JPanel pauseMenu;
    private JLabel pauseScrLabel;
    private JButton pauseScrResumeBtn;
    private JButton pauseScrRQuitBtn;

    public GGame(SceneManager sceneManager, ImageManager imgMan, String map) {
        super(sceneManager, imgMan);

        this.imgMan = imgMan;
        this.map = map;
        board = SokobanBoard.readFile(map);

        this.w = board.getMapWidth();
        this.h = board.getMapHeight();
        this.setPreferredSize(new Dimension(this.w * imgMan.getImgWidth(), this.h * imgMan.getImgHeight()));

        this.addKeyListener(this);

        pauseMenu = new JPanel();
        //pauseMenu.setBackground(Color.BLACK);
        pauseMenu.setLayout(new BoxLayout(pauseMenu, BoxLayout.PAGE_AXIS));
        pauseMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseScrLabel = new JLabel("Game Paused");
        pauseScrLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the stuff
        pauseScrResumeBtn = new JButton("Resume");
        pauseScrResumeBtn.addActionListener((ActionEvent ae) -> {
            resumeGame();
        });
        pauseScrResumeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseScrRQuitBtn = new JButton("Quit To Main Menu");
        pauseScrRQuitBtn.addActionListener((ActionEvent ae) -> {
            this.sceneManager.setScene(new GMainMenu(sceneManager, imgMan));
        });
        pauseScrRQuitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        pauseMenu.add(pauseScrLabel);
        pauseMenu.add(pauseScrResumeBtn);
        pauseMenu.add(pauseScrRQuitBtn);
    }

    private void resumeGame() {
        this.paused = false;
//        this.remove(pauseScrLabel);
//        this.remove(pauseScrResumeBtn);
//        this.remove(pauseScrRQuitBtn);
        this.remove(pauseMenu);
        this.repaint();
        this.sceneManager.setVisible(true); // refresh at the level JFrame
    }
    private void pauseGame() {
        this.paused = true;
//        this.add(pauseScrLabel);
//        this.add(pauseScrResumeBtn);
//        this.add(pauseScrRQuitBtn);
        this.add(pauseMenu);
        this.repaint();
        this.sceneManager.setVisible(true); // refresh at the level JFrame
    }

    public void reset() {
        this.sceneManager.setScene(new GGame(this.sceneManager, this.imgMan, this.map));
    }

    private boolean applyAction(Action action) {
        return this.board.applyAction(action);
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
        boolean validStep = false;

        // if paused check other keys
        if (!paused) {
            if (kc == KeyEvent.VK_D || kc == KeyEvent.VK_RIGHT) {
                validStep = applyAction(Action.MOVE_RIGHT);
            } else if (kc == KeyEvent.VK_A || kc ==KeyEvent.VK_LEFT) {
                validStep = applyAction(Action.MOVE_LEFT);
            } else if (kc == KeyEvent.VK_W || kc ==KeyEvent.VK_UP) {
                validStep = applyAction(Action.MOVE_UP);
            } else if (kc ==KeyEvent.VK_S || kc == KeyEvent.VK_DOWN) {
                validStep = applyAction(Action.MOVE_DOWN);
            } else if (kc == KeyEvent.VK_R) {
                reset();
            } else if (kc == KeyEvent.VK_P) {
                //pause
                this.pauseGame();
            }

            if(validStep) {
                File footstep = new File("./sound_files/walking.wav");
                playSound(footstep);
            }
            if (gameWon()) {
                System.out.println("Game Won!");
                File winSound = new File("./sound_files/goalplacement.wav");
                playSound(winSound);
            }
            repaint();
        }
        else { // hot keys for pause menu?
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!paused) {
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

            int idCount = 0;
            BufferedImage box = imgMan.getBoxImg(0);
            BufferedImage player = imgMan.getPlayerImg(board.getPlayer().getOrientation());

            for (Box curr : board.getBoxes()) {
                Point pos = curr.getPosition();

                x = pos.getX() * box.getWidth();
                y = pos.getY() * box.getHeight();
                g.drawImage(imgMan.getBoxImg(curr.getId()), x, y, null);
            }

            Point playerPos = board.getPlayer().getPosition();
            x = playerPos.getX() * player.getWidth();
            y = playerPos.getY() * player.getHeight();

            g.drawImage(player, x, y, null);
        } else {
        }
    }
}
