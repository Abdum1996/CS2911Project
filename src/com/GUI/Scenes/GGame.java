package com.GUI.Scenes;

import com.GUI.ImageManager;
import com.GUI.SceneManager;
import com.Model.*;
import com.Model.Action;
import com.Model.Box;
import com.Model.GameLevel.GameState;
import com.Model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A GScene panel that displays the relevant game level
 */
@SuppressWarnings("serial")
public class GGame extends GScene implements KeyListener, ActionListener {
    /**
     * sound files
     */
    private File footstepSnd;
    private File lostgameSnd;
    
    /**
     * Image manager of this instance
     */
    private ImageManager imgMan;
    
    /**
     * timer to initiate animation sequence
     */
    private Timer timer = new Timer(15, this);
    
    /**
     * the initial x shift for the walking animation
     */
    private int xshift = 0;
    
    /**
     * the initial x shift for the walking animation
     */
    private int yshift = 0;
    
    /**
     * the number of different shifts of the sprite in one walk
     */
    private int nshifts = 11;
    
    /**
     * keeps track of the current shift (never exceeds number of shifts)
     */
    private int currShifts = 0;
    
    /**
     * width of this grid
     */
    private int w;
    
    /**
     * height of this grid
     */
    private int h;

    /**
     * the level containing the game data
     */
    private GameLevel level;
    
    /**
     * the last viable action to be used in between animations
     */
    private Action lastViableAction;
    
    private Direction newDirection;

    /**
     * a queue that stores the pending actions applied by the player in between animations
     */
	private Queue<Action> pendingActions = new LinkedList<>();
	
	private ControlPanel controlPanel;
    
    public GGame(SceneManager sceneManager, ImageManager imgMan, GameLevel level) {
        super(sceneManager, imgMan);

        initSounds();
        this.imgMan = imgMan;
        this.level = level;
        
        this.w = level.getMapWidth();
        this.h = level.getMapHeight();

        // panel for control
        controlPanel = new ControlPanel(false, this);

        sceneManager.getContentPane().setLayout(null);
        sceneManager.setPreferredSize(new Dimension(this.w * imgMan.getImgWidth() + 8,
                this.h * imgMan.getImgHeight() + 64));

        this.setPreferredSize(new Dimension(this.w * imgMan.getImgWidth(), this.h * imgMan.getImgHeight()));


        this.setBounds(0, 0, this.w * imgMan.getImgWidth(), this.h * imgMan.getImgHeight());
        System.out.println("listener");
        this.addKeyListener(this);

        // set the bounds for control panel
        controlPanel.setBounds(0, this.h * imgMan.getImgHeight(), this.h * imgMan.getImgWidth(), 28);
        controlPanel.setFocusable(false);
        sceneManager.add(controlPanel);

        this.setFocusable(true);
        this.requestFocus();
    }

    private void initSounds() {
        footstepSnd = new File("./sound_files/walking.wav");
        lostgameSnd = new File("./sound_files/Sad_Trombone-Joe_Lamb-665429450.wav");
    }

    /**
     * Pauses the game and displays a menu.
     */
    private void pauseGame() {
    	sceneManager.setLayout(new BorderLayout());
        sceneManager.setScene(new GPauseMenu(sceneManager, imgMan, level));
        sceneManager.remove(controlPanel);
        sceneManager.setVisible(true); // refresh at the level JFrame
    }

    public void reset() {
    	level.reset();
    	
        sceneManager.setScene(new GGame(sceneManager, imgMan, level));
    }

    /**
     * Applies the action specified to the board and processes animation
     * @param action - the action to be carried out
     */
    private void applyAction(Action action) {    	
    	Move move = level.getResultingMove(action);
    	if (!level.getGameState().equals(GameState.NOT_WON)) return;

    	System.out.println(action);
        System.out.println(move.getResult());
        
        // if there's an animation running
        if (timer.isRunning()) {
        	pendingActions.add(action);
			System.out.println("put into pending: " + action);
        	return;
        }
        
        if (!move.doesNothing()) {
        	//  play sound

	        playSound(footstepSnd);
	        
	        lastViableAction = action;
	        newDirection = Direction.readAction(action);
	        timer.start();
        } else {
        	if (!pendingActions.isEmpty()) {
        		Action lastAction = pendingActions.poll();
				applyAction(lastAction);
				System.out.println("pulled from pending: " + lastAction);
        	}
        }

        controlPanel.setCounter(level.getPushCount());

        if (!level.isSolvable()) {
            playSound(lostgameSnd);
        }
    }
    
    /**
     * Undoes the last action made by the user
     */
    public void undoLastMove() {
    	System.out.println("now reverting...");
    	level.undoLastMove();
    	repaint();
    }
    
    /**
     * Checks whether the game displayed has been won
     * @return true if the game has been won, false otherwise
     */
    private boolean gameWon() {
        return level.getGameState().equals(GameState.WON);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    	// do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	System.out.println("keyPressed: " + e);
        // animation ain't done yet
        int kc = e.getKeyCode();
        if (kc == KeyEvent.VK_R) {
            reset();
        }  else if (kc == KeyEvent.VK_P || kc == KeyEvent.VK_ESCAPE)  {
        	pauseGame();
        	return;
        }
        
        // control z
        if ((kc == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
        	undoLastMove();
        	return;
        }
        if (gameWon()) {
            System.out.println("Game Won!");
            File winSound = new File("./sound_files/goalplacement.wav");
            playSound(winSound);
        }
        
        applyAction(Action.readKeyEvent(e));        

    }

    @Override
    public void keyReleased(KeyEvent e) {
    	// do nothing
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

                g.drawImage(imgMan.getTileImg(level.getTile(pos)), x, y, null);
                x += imgMan.getImgHeight();
            }
            y += imgMan.getImgWidth();
            x = 0;
        }

        BufferedImage box = imgMan.getBoxImg(0);
        BufferedImage player = imgMan.getPlayerImg(level.getPlayer().getOrientation());

        for (Box curr : level.getBoxes()) {
            Point pos = curr.getPosition();

            x = pos.getX() * box.getWidth();
            y = pos.getY() * box.getHeight();
            g.drawImage(imgMan.getBoxImg(curr.getId()), x, y, null);
        }

        Point playerPos = level.getPlayer().getPosition();
        x = playerPos.getX() * player.getWidth();
        y = playerPos.getY() * player.getHeight();

        g.drawImage(player, x, y, null);
    }
    
    /**
     * Paints the board while an object is moving on the screen
     * @param g
     */
    public void paintChangingComponent(Graphics g) {
    	
        g.drawString("Grid", 0, 0);

        int x = 0;
        int y = 0;

        // paint all tiles
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Point pos = Point.at(j, i);

                g.drawImage(imgMan.getTileImg(level.getTile(pos)), x, y, null);
                x += imgMan.getImgHeight();
            }
            y += imgMan.getImgWidth();
            x = 0;
        }

        BufferedImage box = imgMan.getBoxImg(0);
        
        BufferedImage player = imgMan.getPlayerImg(newDirection);
        for (Box curr : level.getBoxes()) {
            Point pos = curr.getPosition();

            x = pos.getX() * box.getWidth();
            y = pos.getY() * box.getHeight();
            
            // This box is the one that's moving right now
            if(curr.getPosition().equals(level.getPlayer().getPosition().move(newDirection))) {
            	x = x + xshift;
            	y = y + yshift;
            }
            g.drawImage(imgMan.getBoxImg(curr.getId()), x, y, null);
        }

        Point playerPos = level.getPlayer().getPosition();
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
			level.applyAction(lastViableAction);
			if(!pendingActions.isEmpty()) {
				Action a = pendingActions.poll();
				this.applyAction(a);
				System.out.println("pulled from pending: " + a);
			}
		}
		
		repaint();
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

    public void genNewPuzzle() {
        this.sceneManager.setScene(new GGame(sceneManager, imgMan, new SokobanLevel(level.getDifficulty())));
    }
}
