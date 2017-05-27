package com.GUI.Scenes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.GUI.ImageButton;
import com.Model.Difficulty;
import com.Model.SokobanLevel;

public class ControlPanel extends JPanel {
	
	private ImageButton btnUndo;
	private ImageButton btnReset;
	private ImageButton btnNewPuzzle;
	private JLabel counter;
	private JLabel minSteps;
    private BufferedImage bkgImg;
    private ImageButton btnHard;
    private ImageButton btnNormal;
    private ImageButton btnEasy;
    
	
	public ControlPanel(boolean isRandom, GGame context) {
		// initialize all components
		btnUndo = new ImageButton("./resources/undobutton.png");
		btnReset = new ImageButton("./resources/resetbutton.png");
		btnNewPuzzle = new ImageButton("./resources/newpuzzlebutton.png");
		
		btnHard = new ImageButton("./resources/hardbutton_small.png");
		btnNormal = new ImageButton("./resources/normalbutton_small.png");
		btnEasy = new ImageButton("./resources/easybutton_small.png");
		
		
		
        try {
            bkgImg = ImageIO.read(new File("./resources/menubackground.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		counter = new JLabel("0 steps taken.");
		
		minSteps = new JLabel("Min box pushes to solve: " + context.getMinPushes() + ".");
		
		// add action listeners for all buttons
		btnUndo.addActionListener((ActionEvent ae) -> {
			context.undoLastMove();
			context.repaint();
		});
		
		btnNewPuzzle.addActionListener((ActionEvent ae) -> {
			context.genNewPuzzle();
			context.repaint();
		});
		
		btnReset.addActionListener((ActionEvent ae) -> {
			context.reset();
			context.repaint();
		});
		
		btnHard.addActionListener((ActionEvent ae) -> {
			context.playSound(new File("./sound_files/gamestart.wav"));
            context.sceneManager.setScene(new GGame(context.sceneManager, context.imgMan, new SokobanLevel(Difficulty.HARD)));
		});
		
		btnNormal.addActionListener((ActionEvent ae) -> {
			context.playSound(new File("./sound_files/gamestart.wav"));
            context.sceneManager.setScene(new GGame(context.sceneManager, context.imgMan, new SokobanLevel(Difficulty.NORMAL)));
		});
		
		btnEasy.addActionListener((ActionEvent ae) -> {
			context.playSound(new File("./sound_files/gamestart.wav"));
            context.sceneManager.setScene(new GGame(context.sceneManager, context.imgMan, new SokobanLevel(Difficulty.EASY)));
		});
		
		// make all buttons non-focusable
		btnNewPuzzle.setFocusable(false);
		btnReset.setFocusable(false);
		btnUndo.setFocusable(false);
		
		// configure label counter
		//counter.setText(context.getMinMoves());
		
		
		
		// add all buttons
		add(btnEasy);
		add(btnNormal);
		add(btnHard);
		
		add(btnUndo);
		add(btnReset);
		
		btnEasy.setFocusable(false);
		btnNormal.setFocusable(false);
		btnHard.setFocusable(false);
		btnUndo.setFocusable(false);
		btnReset.setFocusable(false);
		
		
		// only add new puzzle thing if it's random
		if (isRandom)
			add(btnNewPuzzle);
		add (counter);
		add (minSteps);
		

	}

	public void setCounter(int c) {
		this.counter.setText(Integer.toString(c) + " steps taken.");
		this.repaint();
	}

	@Override
    public void paintComponent(Graphics g) {
	    super.paintComponent(g);
        g.drawImage(bkgImg, 0, 0, null);
    }

}
