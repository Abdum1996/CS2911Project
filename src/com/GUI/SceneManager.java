package com.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.GUI.Scenes.GMainMenu;
import com.GUI.Scenes.GPauseMenu;
import com.GUI.Scenes.GScene;

/**
 * Manages the scenes on the main frame
 */
@SuppressWarnings("serial")
public class SceneManager extends JFrame {

    private GScene currentScene;
    
    private ImageManager imgMan;

    public SceneManager(ImageManager imgMan) {
    	
    	this.imgMan = imgMan;

        this.setTitle("Warehouse Boss");
        this.setSize(640, 800);
        setBackground(Color.GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new GridLayout());
        this.setResizable(false);

        
        this.setScene(new GMainMenu(this, imgMan));

        setVisible(true);
        setResizable(false);
        pack();
    }

    /**
     * Sets the scene/panel for this frame
     * @param scene - the panel component to be viewed/set
     */
    public void setScene (GScene scene) {
        if (currentScene != null) {
            System.out.println("Removing previous scene");
            remove(currentScene);
        }
        
       if (scene instanceof GMainMenu || scene instanceof GPauseMenu) {
       	this.setLayout(new BorderLayout());
       	this.setPreferredSize(new Dimension(480, 600));
       }
        System.out.println("Setting scene");

        currentScene = scene;
        // add scene to frame
        add(scene);
        setVisible(true);

        currentScene.setFocusable(true);
        currentScene.requestFocusInWindow();
        currentScene.repaint();
        this.pack();
    }
    
    /**
     * Sets the scene/panel for this frame to an existing scene
     * @param sceneID - the scene id of the scene we want to switch to
     */
//    public void switchScene(int sceneID) {
//    	setScene(sceneID, scenes.get(sceneID));
//    }
    
    /**
     * Gets the current scene being displayed
     * @return GScene in frame
     */
    public GScene getCurrentScene() {
        return this.currentScene;
    }
    
    /**
     * gets the scene given the scene ID
     * @param sceneID - the scene ID for the scene to be returned
     * @return The scene with scene id
     */
//    public GScene getScene(int sceneID) {
//    	return scenes.get(sceneID);
//    }
   
}
