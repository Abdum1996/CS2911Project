package com.GUI.Scenes;

import javax.swing.*;

import com.GUI.ImageManager;
import com.GUI.SceneManager;

import java.awt.event.ActionEvent;

public class GCredits extends GScene {

	public GCredits(SceneManager sceneManager, ImageManager imgMan) {
		super(sceneManager, imgMan);
		
		JLabel licenses = new JLabel("info");
		
		licenses.setText("Textures and assets from Kenney.nl license: CC0 1.0 Universal (CC0 1.0) \n" +
				"\n" +
				"Sounds from http://www.gameburp.com/ license: http://www.gameburp.com/license-free/\n" +
				"\n" +
				"Sad trombone sound Recorded by Joe Lamb from  http://soundbible.com/1830-Sad-Trombone.html  license: Attribution 3.0");

		JLabel createdby = new JLabel("Created By:");
		JLabel name1 = new JLabel ("Abdul .");
		JLabel name2 = new JLabel ("Chris Miles");
		JLabel name3 = new JLabel ("Harry .");
		JLabel name4 = new JLabel ("Samir .");
		JLabel name5 = new JLabel ("Thomas .");
		JButton returnBtn = new JButton( "Return to menu");
		returnBtn.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
			sceneManager.setScene(new GMainMenu(sceneManager, imgMan));
		});

		this.add (createdby);
		this.add (name1);
		this.add (name2);
		this.add (name3);
		this.add (name4);
		this.add (name5);
		this.add(licenses);
		this.add(returnBtn);
	}
	
	

}
