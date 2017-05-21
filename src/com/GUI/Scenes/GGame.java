package com.GUI.Scenes;

import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.Model.*;
import com.Model.Point;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Timer;

/**
 * Sokoban Canvas
 * custom canvas for drawing
 */
public class GGame extends GScene implements KeyListener, ActionListener {
    private static final long serialVersionUID = 1L;
    
    private ImageManager imgMan;
    
    private Timer timer = new Timer(15, this);
    
    private int xshift = 0;
    private int yshift = 0;
    
    private int nshifts = 11;
    private int currShifts = 0;
    
    private int w;
    
    private int h;

    private GameBoard board;
    
    private String map;
    
    private Action lastViableAction;
    
    private Direction newDirection;

	private Queue<Action> pendingActions = new LinkedList<Action>();

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

    /**
     * Applies the action specified to the board and processes animation
     * @param action - the action to be carried out
     */
    private void applyAction(Action action) {
    	ActionResult ar = board.getActionResult((action));
    	System.out.println(action);
        System.out.println(ar);
        
        // if there's an animation running
        if (timer.isRunning()) {
        	pendingActions.add(action);
			System.out.println("put into pending: " + action);
        	return;
        }
        
        if(ar == ActionResult.PLAYER_MOVE || ar == ActionResult.BOX_MOVE) {
	        File footstep = new File("./sound_files/walking.wav");
	        playSound(footstep);
	        lastViableAction = action;
	        newDirection = Direction.readAction(action);
	        timer.start();
        } else if (ar == ActionResult.CHANGE_ORIENTATION) {
        	board.applyAction(action);
        	repaint();
        	if (!pendingActions.isEmpty()) {
        		action = pendingActions.poll();
				this.applyAction(action);
				System.out.println("pulled from pending: " + action);
        	}
        }
    }
    
    /**
     * Checks whether the game displayed has been won
     * @return true if the game has been won, false otherwise
     */
    private boolean gameWon() {
        return this.board.gameWon();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed: " + e);
        // animation ain't done yet
        int kc = e.getKeyCode();
        if (kc == KeyEvent.VK_R) {
            reset();
        } 
        if (gameWon()) {
            System.out.println("Game Won!");
            File winSound = new File("./sound_files/goalplacement.wav");
            playSound(winSound);
        }
        
        applyAction(Action.readKeyEvent(e));     
        
        //repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(timer.isRunning()) {
        	paintChangingComponent(g);
        	return;
        }

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
    }
    
    public void paintChangingComponent(Graphics g) {
    	
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

        BufferedImage box = imgMan.getBoxImg(0);
        
        BufferedImage player = imgMan.getPlayerImg(newDirection);

        for (Box curr : board.getBoxes()) {
            Point pos = curr.getPosition();

            x = pos.getX() * box.getWidth();
            y = pos.getY() * box.getHeight();
            
            // This box is the one that's moving right now
            if(curr.getPosition().equals(board.getPlayer().getPosition().move(newDirection))) {
            	x = x + xshift;
            	y = y + yshift;
            }
            g.drawImage(imgMan.getBoxImg(curr.getId()), x, y, null);
        }

        Point playerPos = board.getPlayer().getPosition();
        x = playerPos.getX() * player.getWidth();
        y = playerPos.getY() * player.getHeight();

        g.drawImage(player, x+xshift, y+yshift, null);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Direction dir = Direction.readAction(lastViableAction);
		int magnitude = (currShifts+1)*GameConstants.IMAGE_DIMENSION/nshifts;
		switch (dir) {
			case UP:
				yshift = -magnitude;
				break;
			case DOWN:
				yshift = magnitude;
				break;
			case RIGHT:
				xshift = magnitude;
				break;
			case LEFT:
				xshift = -magnitude;
				break;
		}
		
		currShifts++;
		// terminate animation
		if (nshifts == currShifts) {
			currShifts = 0;
			timer.stop();
			yshift = 0;
			xshift = 0;
			board.applyAction(lastViableAction);
			if(!pendingActions.isEmpty()) {
				Action a = pendingActions.poll();
				this.applyAction(a);
				System.out.println("pulled from pending: " + a);
			}
		}
		
		repaint();
		
	}
}
