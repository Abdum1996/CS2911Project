package com.GUI.Scenes;

import com.GUI.ImageManager;
import com.GUI.SceneManager;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

/**
 * a game scene
 */
public class GScene extends JPanel {
    protected ImageManager imgMan;
    protected SceneManager sceneManager;

    public GScene(SceneManager sceneManager, ImageManager imgMan) {
        this.imgMan = imgMan;
        this.sceneManager = sceneManager;

        //this.setPreferredSize(new Dimension(width, height));
    }
    
    public void playSound(File soundFile) {
    	Thread soundThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(soundFile));
					FloatControl volControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					volControl.setValue(-16.0f);
					clip.start();
					Thread.sleep(clip.getMicrosecondLength()/1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
    		
    	});
    	soundThread.start();
    	
    }
}
