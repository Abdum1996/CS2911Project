package com.GUI.Scenes;


import com.GUI.ImageManager;
import com.GUI.SceneManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GHelp extends GScene  {
    public GHelp(SceneManager sceneManager, ImageManager imgMan) {
        super(sceneManager, imgMan);

        String intrtxt = "Java has upset the CSE gods and has gotten himself trapped in a dungeon full of misplaced boxes! To put it simply, he’s been wobkeked.\nTo return himself to the mortal plane, Java must journey through a set of challenges varying in difficulty involving the moving of boxes into marked goal locations. The handicap is that he can only push!\nJava has been sanDeeply lost in the Sokoban-esque levels for a considerable amount of time now and needs your help to get back.\nTo guide Java around the map, simply use the arrow keys of WASD equivalent.\nIf the challenge gets too tough, press the ESC or P key to pause for a breather. (Mashing keys is a lot of work after all.)\nGet back into the game by simply pressing the pause key again.\n\nGoodluck and have pun!";

        JLabel instr = new JLabel(intrtxt);
        JButton returnBtn = new JButton( "Return to menu");
        returnBtn.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            sceneManager.setScene(new GMainMenu(sceneManager, imgMan));
        });

        this.add(instr);
        this.add(returnBtn);
    }
}
