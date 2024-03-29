package com.GUI.Scenes;

import com.GUI.ImageManager;
import com.GUI.SceneManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
    	Executor executor = Executors.newSingleThreadExecutor();
    	executor.execute(new Runnable() { 
    		public void run() { 
	    		try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(soundFile));
					FloatControl volControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					volControl.setValue(-16.0f);
					clip.start();
					Thread.sleep(clip.getMicrosecondLength());
				} catch (InterruptedException | LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
    		} 
    	});
    	
    }
}
