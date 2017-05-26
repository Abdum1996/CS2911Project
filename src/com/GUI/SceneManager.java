package com.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.GUI.Scenes.GMainMenu;
import com.GUI.Scenes.GScene;

/**
 * Manages the scenes on the main frame
 * @author Abdulrahman Alhomayany
 *
 */
@SuppressWarnings("serial")
public class SceneManager extends JFrame {

    private int currentScene;
    
    public static final int MAIN_MENU_ID = 0;
    public static final int GAME_ID = 1;
    public static final int PAUSE_ID = 2;
    public static final int CREDITS_ID = 3;
    public static final int HELP_ID = 4;
    public static final int NUM_SCENES = 5;
    
    
    private ArrayList<GScene> scenes;
    
    private ImageManager imgMan;

    public SceneManager(ImageManager imgMan) {
    	// set up null place holders for all scenes
    	scenes = new ArrayList<>();
    	for (int i = 0; i < NUM_SCENES; i++)
			scenes.add(null);
    	
    	this.imgMan = imgMan;
		
    	scenes.set(MAIN_MENU_ID, new GMainMenu(this, imgMan));
    	currentScene = MAIN_MENU_ID;

        this.setTitle("Warehouse Boss");
        this.setSize(640, 800);
        setBackground(Color.GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new GridLayout());
        this.setResizable(false);

        
        this.add(this.getCurrentScene());

        setVisible(true);
        setResizable(false);
        pack();
    }

    /**
     * Sets the scene/panel for this frame
     * @param sceneID - the id of the scene to be switched to 
     * @param scene - the panel component to be viewed/set
     */
    public void setScene (int sceneID, GScene scene) {
        if (getCurrentScene() != null) {
            remove(getCurrentScene());
        }
        
        if (sceneID != GAME_ID && sceneID != PAUSE_ID) {
        	this.setLayout(new BorderLayout());
        	this.setPreferredSize(new Dimension(10*32, 14*32));
        }
        System.out.println("Setting scene");

        currentScene = sceneID;
        scenes.set(currentScene, scene);
        // add scene to frame
        add(scene);
        setVisible(true);
        
        getCurrentScene().setFocusable(true);
        getCurrentScene().requestFocusInWindow();
        getCurrentScene().repaint();
        this.pack();
    }
    
    /**
     * Sets the scene/panel for this frame to an existing scene
     * @param sceneID - the scene id of the scene we want to switch to
     */
    public void switchScene(int sceneID) {
    	setScene(sceneID, scenes.get(sceneID));
    }
    
    /**
     * Gets the current scene being displayed
     * @return GScene in frame
     */
    public GScene getCurrentScene() {
        return scenes.get(currentScene);
    }
    
    /**
     * gets the scene given the scene ID
     * @param sceneID - the scene ID for the scene to be returned
     * @return The scene with scene id
     */
    public GScene getScene(int sceneID) {
    	return scenes.get(sceneID);
    }
   
}
