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
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A GScene panel that displays the relevant Gameboard
 */
@SuppressWarnings("serial")
public class GGame extends GScene implements KeyListener, ActionListener {
    
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
     * keeps track of the current shift (never exceeds nshifts)
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
     * the gameboard containing the game data
     */
    private GameBoard board;
    
    /**
     * the path to this map
     */
    private String map;
    
    /**
     * the last viable action to be used in between animations
     */
    private Action lastViableAction;
    
    
    private Direction newDirection;

    /**
     * a queue that stores the pending actions applied by the player in between animations
     */
	private Queue<Action> pendingActions = new LinkedList<Action>();
	
	/**
	 * the last actions made by the user, used for undoing actions
	 */
	private SizedStack<Action> recentActions = new SizedStack<>(3);
	
	/**
	 * the last action results made by the user, used for undoing
	 */
	private SizedStack<ActionResult> recentActionResults = new SizedStack<>(3);
<<<<<<< HEAD

	/**
	 * Checks whether the game is pause or not, default is unpaused
	 */
    private JPanel pauseMenu;
    private JLabel pauseScrLabel;
    private JButton pauseScrResumeBtn;
    private JButton pauseScrRQuitBtn;

=======
	
	private ControlPanel controlPanel;
>>>>>>> master
    /**
     * Constructs a GameScene with the puzzle loadeds
     * @param sceneManager - The sceneManager managing this GScene
     * @param imgMan - The ImageManager associated with this SceneManager
     * @param map - The map to be loaded on the GameBoard and displayed
     */
    public GGame(SceneManager sceneManager, ImageManager imgMan, String map) {
        super(sceneManager, imgMan);
        
        this.imgMan = imgMan;
        this.map = map;
        board = BoardGenerator.readMap(map);
<<<<<<< HEAD

=======
>>>>>>> master
        this.w = board.getMapWidth();
        this.h = board.getMapHeight();
        
        // panel for control
        controlPanel = new ControlPanel(false, this);
        
        sceneManager.getContentPane().setLayout(null);
        sceneManager.setPreferredSize(new Dimension(this.w * imgMan.getImgWidth() + 8,
        		this.h * imgMan.getImgHeight() + 64));
        
        this.setPreferredSize(new Dimension(this.w * imgMan.getImgWidth(), this.h * imgMan.getImgHeight()));
        
        
        this.setBounds(0, 0, this.w * imgMan.getImgWidth(), this.h * imgMan.getImgHeight());
        System.out.println("listener");
        this.addKeyListener(this);
        
<<<<<<< HEAD

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
            this.sceneManager.setScene(SceneManager.MAIN_MENU_ID, new GMainMenu(sceneManager, imgMan));
        });
        pauseScrRQuitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        pauseMenu.add(pauseScrLabel);
        pauseMenu.add(pauseScrResumeBtn);
        pauseMenu.add(pauseScrRQuitBtn);
        
//        for(Action a : board.solve())
//        	System.out.println(a);
    }

    /**
     * Resumes the game from a paused state
     */
    private void resumeGame() {
//        this.remove(pauseScrLabel);
//        this.remove(pauseScrResumeBtn);
//        this.remove(pauseScrRQuitBtn);
        remove(pauseMenu);
        repaint();
        sceneManager.setVisible(true); // refresh at the level JFrame
=======
        // set the bounds for cpanel
        controlPanel.setBounds(0, this.h * imgMan.getImgHeight(), this.h * imgMan.getImgWidth(), 28);
        controlPanel.setFocusable(false);
        sceneManager.add(controlPanel);
        
        this.setFocusable(true);
        this.requestFocus();
        
//        for(Action a : board.solve())
//        	System.out.println(a);
>>>>>>> master
    }
    
    
    /**
     * Pauses the game and displays a menu
     */
    private void pauseGame() {
<<<<<<< HEAD
//        this.add(pauseScrLabel);
//        this.add(pauseScrResumeBtn);
//        this.add(pauseScrRQuitBtn);
        sceneManager.setScene(SceneManager.PAUSE_ID, new GPauseMenu(sceneManager, imgMan, board));
//        repaint();
        sceneManager.setVisible(true); // refresh at the level JFrame

=======
    	sceneManager.setLayout(new BorderLayout());
        sceneManager.setScene(SceneManager.PAUSE_ID, new GPauseMenu(sceneManager, imgMan, this));
        sceneManager.remove(controlPanel);
        sceneManager.setVisible(true); // refresh at the level JFrame
>>>>>>> master
    }

    public void reset() {
        sceneManager.setScene(SceneManager.GAME_ID, new GGame(sceneManager, imgMan, map));
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
        } else if (ar == ActionResult.PLAYER_MOVE || ar == ActionResult.BOX_MOVE) {
        	// for undo
        	System.out.println("pushing...");
        	recentActions.push(action);
        	recentActionResults.push(ar);
//        	System.out.println(recentActions.pop());
//        	System.out.println(recentActions.peek());
        	
        	//  play sound
	        File footstep = new File("./sound_files/walking.wav");
	        playSound(footstep);
	        
	        // --------------
	        lastViableAction = action;
	        newDirection = Direction.readAction(action);
	        timer.start();
        } else if (ar == ActionResult.NONE) {
        	if (!pendingActions.isEmpty()) {
        		action = pendingActions.poll();
				this.applyAction(action);
				System.out.println("pulled from pending: " + action);
        	}
        }
        
        // no board action should be done after winning
    	if (gameWon()) {
            System.out.println("Game Won!");
            File winSound = new File("./sound_files/goalplacement.wav");
            playSound(winSound);
            reset();
            return;
        }
    }
    
    /**
     * Undoes the last action made by the user
     */
    public void undoLastMove() {
    	if (recentActions.empty()) 
    		return; //empty or too many undos
    	
    	// undo 
    	System.out.println("now reverting...");
    	board.revertAction(recentActions.pop(), recentActionResults.pop());
    	repaint();
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
<<<<<<< HEAD
        }  else if (kc == KeyEvent.VK_P) {
=======
        }  else if (kc == KeyEvent.VK_P || kc == KeyEvent.VK_ESCAPE)  {
>>>>>>> master
        	pauseGame();
        	return;
        }
        // ctrl z
        if ((kc == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
        	undoLastMove();
        	return;
        }
 /*       if (gameWon()) {
            System.out.println("Game Won!");
            File winSound = new File("./sound_files/goalplacement.wav");
            playSound(winSound);
        }
*/        
        applyAction(Action.readKeyEvent(e));        

    }

    @Override
    public void keyReleased(KeyEvent e) {}

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

        Iterator<Box> it = board.getBoxes();
        while (it.hasNext()) {
            Box curr = it.next();
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

                g.drawImage(imgMan.getTileImg(board.getTile(pos)), x, y, null);
                x += imgMan.getImgHeight();
            }
            y += imgMan.getImgWidth();
            x = 0;
        }

        BufferedImage box = imgMan.getBoxImg(0);
        
        BufferedImage player = imgMan.getPlayerImg(newDirection);
        Iterator<Box> it = board.getBoxes();
        
        while (it.hasNext()) {
        	Box curr = it.next();
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


	public ControlPanel getControlPanel() {
		return controlPanel;
	}
}
