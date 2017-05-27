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

public class ControlPanel extends JPanel {
	
	private ImageButton btnUndo;
	private ImageButton btnReset;
	private ImageButton btnNewPuzzle;
	private JLabel counter;
	private JLabel minSteps;
    private BufferedImage bkgImg;
	
	public ControlPanel(boolean isRandom, GGame context) {
		// initialize all components
		btnUndo = new ImageButton("./resources/undobutton.png");
		btnReset = new ImageButton("./resources/resetbutton.png");
		btnNewPuzzle = new ImageButton("./resources/newpuzzlebutton.png");
        try {
            bkgImg = ImageIO.read(new File("./resources/menubackground.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		counter = new JLabel("0 steps taken.");
		
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
		add (counter);

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
