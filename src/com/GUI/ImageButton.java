package com.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * Class to that extends JButton but only supports an image background
 * @author Abdulrahman Alhomayany
 */
@SuppressWarnings("serial")
public class ImageButton extends JButton {
	
	private BufferedImage img;
	
	public ImageButton (String imgPath) {
		try {
			img = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
	
	
}
