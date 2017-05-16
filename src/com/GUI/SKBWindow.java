package com.GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.SokobanBoard;
import com.GameBoard;
import com.Action;

public class SKBWindow extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private JTextArea textArea1;
    private JButton btnStartGame;
    private JButton btnResetGame;

    private SKBCanvas canvas;
    private GameBoard board;
    private String currentMap;

    public SKBWindow(ImageManager imgMan) {
        currentMap = "./maps/map2.txt";
        board = SokobanBoard.readFile(currentMap);

        this.setTitle("Sokoban");
        this.setSize(800, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout());
        
        textArea1 = new JTextArea();
        textArea1.setText("Game in progress..");
        textArea1.setEditable(false);
        textArea1.setFocusable(false);

        JPanel jp1 = new JPanel();

        btnStartGame = new JButton("Start Game");
        btnStartGame.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Start game, do stuff");
        });

        btnResetGame = new JButton("Reset Game");
        btnResetGame.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
			resetGame();
        });

        //btnStartGame

        jp1.add(btnStartGame);
        jp1.add(btnResetGame);

        this.add(jp1);

        jp1.setLocation(0, 0);

        //c.gridx = 1;
        //c.gridy = 1;
        //c.weightx = 1.0;
        //c.weighty = 1.0;
        //c.fill = GridBagConstraints.BOTH;

        jp1.add(textArea1);
        //jp3.add(textArea1, c);

        this.canvas = new SKBCanvas(imgMan, board);
        this.add(canvas);

        //this.pack();
        this.setVisible(true);
        btnStartGame.setFocusable(false);
        btnResetGame.setFocusable(false);
        
        this.addKeyListener(this);
        this.pack();
        
    }

	@Override
	public void keyTyped(KeyEvent e) { 
		/**
		 * Do nothing
		 */
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				board.applyAction(Action.MOVE_RIGHT);
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				board.applyAction(Action.MOVE_LEFT);
				break;
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				board.applyAction(Action.MOVE_UP);
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				board.applyAction(Action.MOVE_DOWN);
				break;
			case KeyEvent.VK_R:
				// reset game
				System.out.println("Reset game, do stuff");
	        	this.board = SokobanBoard.readFile(currentMap);
	        	textArea1.setText("Game in progress..");
	        	canvas.replaceGrid(board);
	        	canvas.repaint();
	        	break;
			case KeyEvent.VK_1:
				currentMap = "./maps/map1.txt";
				System.out.println("Switch to map 1");
				resetGame();
	        	break;
			case KeyEvent.VK_2:
				currentMap = "./maps/map2.txt";
				System.out.println("Switch to map 2");
				resetGame();
	        	break;
			case KeyEvent.VK_3:
				currentMap = "./maps/map3.txt";
				System.out.println("Switch to map 3");
				resetGame();
	        	break;
			case KeyEvent.VK_4:
				currentMap = "./maps/map4.txt";
				System.out.println("Switch to map 4");
				resetGame();
	        	break;
			case KeyEvent.VK_5:
				currentMap = "./maps/map5.txt";
				System.out.println("Switch to map 5");
				resetGame();
	        	break;
			
		}
		
		if (board.gameWon()) {
			textArea1.setText("Game Won!");
		}
		canvas.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/**
		 * Do nothing.
		 */
	}
	
	public void resetGame() {
		System.out.println("reset game..");
		board =  SokobanBoard.readFile(currentMap);
    	textArea1.setText("Game in progress..");
    	canvas.replaceGrid(board);
    	canvas.repaint();
    	this.pack();
	}
}
