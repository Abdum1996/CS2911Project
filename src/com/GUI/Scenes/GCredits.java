package com.GUI.Scenes;

import javax.swing.JLabel;

import com.GUI.ImageManager;
import com.GUI.SceneManager;

public class GCredits extends GScene {

	public GCredits(SceneManager sceneManager, ImageManager imgMan) {
		super(sceneManager, imgMan);
		
		JLabel licenses = new JLabel("info");
		
		licenses.setText("All licenses and attribution are included in the README.md file contained within this project");
	}
	
	

}
