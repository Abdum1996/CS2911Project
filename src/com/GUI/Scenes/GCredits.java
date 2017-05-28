package com.GUI.Scenes;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.GUI.ImageButton;
import com.GUI.ImageManager;
import com.GUI.SceneManager;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GCredits extends GScene {
	
	private BufferedImage bkgImg;

	public GCredits(SceneManager sceneManager, ImageManager imgMan) {
		super(sceneManager, imgMan);
		
		 try {
				bkgImg = ImageIO.read(new File("./resources/menubackground.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		JLabel licenses = new JLabel("info");
		
<<<<<<< HEAD
		licenses.setText("<html><font color='white'>Textures and assets from Kenney.nl <br>license: CC0 1.0 Universal (CC0 1.0) <br>" +
				"<br>Sounds from http://www.gameburp.com/ <br>license: http://www.gameburp.com/license-free/<br>" +
				"<br>Sad trombone sound Recorded by Joe Lamb <br>from  http://soundbible.com/1830-Sad-Trombone.html  "
				+ "<br>license: Attribution 3.0"
				+ "</font></html>");

		JLabel createdby = new JLabel();
		createdby.setText("<html><font color='white'>"
				+ "Created by:<br>"
				+ "Abdulrahman Alhomayany<br>"
				+ "Chris Miles<br>"
				+ "Thomas Daniell<br>"
				+ "Samir Mustavi<br>"
				+ "Harry Zhanga<br><br>"
				+ "</font></html>");
		JButton returnBtn = new ImageButton( "./resources/exitbutton.png");
=======
		licenses.setText("Textures and assets from Kenney.nl license: CC0 1.0 Universal (CC0 1.0) \n" +
				"\n" +
				"Sounds from http://www.gameburp.com/ license: http://www.gameburp.com/license-free/\n" +
				"\n" +
				"Sad trombone sound Recorded by Joe Lamb from  http://soundbible.com/1830-Sad-Trombone.html  license: Attribution 3.0" +
				"\n" +
				"Credit to the paper on https://larc.unt.edu/techreports/LARC-2011-01.pdf for providing ideas on board generation methods");

		JLabel createdby = new JLabel("Created By:");
		JLabel name1 = new JLabel ("Abdul Alhomayany");
		JLabel name2 = new JLabel ("Chris Miles");
		JLabel name3 = new JLabel ("Harry Zhanga");
		JLabel name4 = new JLabel ("Samir Md Mustavi");
		JLabel name5 = new JLabel ("Thomas Daniell");
		JButton returnBtn = new JButton( "Return to menu");
>>>>>>> master
		returnBtn.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
			sceneManager.setScene(new GMainMenu(sceneManager, imgMan));
		});

		this.add (createdby);
		this.add(licenses);
		this.add(returnBtn);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bkgImg, 0, 0, null);
	}
	
	

}
