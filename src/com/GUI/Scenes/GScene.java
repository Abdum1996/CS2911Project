package com.GUI.Scenes;

import com.GUI.ImageManager;
import com.GUI.SceneManager;

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
}
