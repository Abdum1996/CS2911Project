package com.GUI;

import javax.swing.JFrame;

import com.GUI.Scenes.GMainMenu;
import com.GUI.Scenes.GScene;
import com.GameBoard;

public class SceneManager extends JFrame {
    private static final long serialVersionUID = 1L;

    private GScene currentScene;

    public SceneManager(ImageManager imgMan) {
        this.setScene(new GMainMenu(this, imgMan));

        this.setTitle("Warehouse Boss");
        this.setSize(800, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new GridLayout());


        this.add(this.getScene());

        this.setVisible(true);

        this.pack();
    }

    public void setScene (GScene scene) {
        if (this.currentScene != null) {
            this.remove(this.currentScene);
        }

        this.currentScene = scene;
        this.add(this.currentScene);
        //setVisible(true);
        this.currentScene.setFocusable(true);
        this.currentScene.requestFocusInWindow();

        this.pack();
    }
    public GScene getScene () {
        return this.currentScene;
    }
}
