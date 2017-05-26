package com.GUI.Scenes;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.GUI.ImageButton;

public class ControlPanel extends JPanel {
	
	private ImageButton btnUndo;
	private ImageButton btnReset;
	private ImageButton btnNewPuzzle;
	private JLabel counter;
	private JLabel minSteps;
	
	public ControlPanel(boolean isRandom, GGame context) {
		// initialize all components
		btnUndo = new ImageButton("./resources/undobutton.png");
		btnReset = new ImageButton("./resources/resetbutton.png");
		btnNewPuzzle = new ImageButton("./resources/newpuzzlebutton.png");
		
		counter = new JLabel();
		minSteps = new JLabel();
		
		// add action listeners for all buttons
		btnUndo.addActionListener((ActionEvent ae) -> {
			context.undoLastMove();
			context.repaint();
		});
		
		btnNewPuzzle.addActionListener((ActionEvent ae) -> {
			//context.generateNewPuzzle();
			context.repaint();
		});
		
		btnReset.addActionListener((ActionEvent ae) -> {
			context.reset();
			context.repaint();
		});
		
		// make all buttons non-focusable
		btnNewPuzzle.setFocusable(false);
		btnReset.setFocusable(false);
		btnUndo.setFocusable(false);
		
		btnNewPuzzle.setAlignmentX(CENTER_ALIGNMENT);
		// configure label counter
		//counter.setText(context.getMinMoves());
		
		
		
		// add all buttons
		add(btnUndo);
		add(btnReset);
		
		// only add new puzzle thing if it's random
		if (isRandom)
			add(btnNewPuzzle);

	}
	
	public void incrementCounter() {
		
	}
	
	public void decrementCounter() {
		
	}

}
