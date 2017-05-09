package com.GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.Direction;
import com.SokobanGrid;

public class SKBWindow extends JFrame implements KeyListener {
    private JTextArea textArea1;
    private JButton btnStartGame;
    private JButton btnResetGame;

    private ImageManager imgMan;
    private SKBCanvas canvas;

    private SokobanGrid grid;

    public SKBWindow(ImageManager imgMan) {
        this.imgMan = imgMan;
        grid = new SokobanGrid("./maps/map2.txt");

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
        	System.out.println("Reset game, do stuff");
        	this.grid = new SokobanGrid("./maps/map2.txt");
        	textArea1.setText("Game in progress..");
        	canvas.replaceGrid(grid);
        	canvas.repaint();
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

        this.canvas = new SKBCanvas(imgMan, grid);
        this.add(canvas);

        //this.pack();
        this.setVisible(true);
        btnStartGame.setFocusable(false);
        btnResetGame.setFocusable(false);
        
        this.addKeyListener(this);
        
    }

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				grid.movePlayer(Direction.RIGHT);
				break;
			case KeyEvent.VK_LEFT:
				grid.movePlayer(Direction.LEFT);
				break;
			case KeyEvent.VK_UP:
				grid.movePlayer(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
				grid.movePlayer(Direction.DOWN);
				break;
		}
		
		if (grid.gameWon()) {
			textArea1.setText("Game Won!");
		}
		canvas.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
