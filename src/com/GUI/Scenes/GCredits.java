package com.GUI.Scenes;

import javax.swing.JLabel;

import com.GUI.ImageManager;
import com.GUI.SceneManager;

public class GCredits extends GScene {

	public GCredits(SceneManager sceneManager, ImageManager imgMan) {
		super(sceneManager, imgMan);
		
		JLabel licenses = new JLabel("info");
		
		licenses.setText("All licenses and attribution are included\n "
				+ "in the README.md file contained within this project\n\n"
				+ "Sad trombone sound by Joe Lamb\n"
				+ "All other assets used don't require crediting.");
		this.add(licenses);
	}
	
	

}
