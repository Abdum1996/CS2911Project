package com;

import com.GUI.ImageManager;
import com.GUI.SceneManager;

public class GameController {
	
    public static void main(String[] args) {

        // load the images
        ImageManager imgMan = new ImageManager();
        imgMan.loadTileImg(Tile.GOAL, "./resources/goal.png");
        imgMan.loadTileImg(Tile.FLOOR, "./resources/floor.png");
        imgMan.loadTileImg(Tile.WALL, "./resources/wall.png");
        imgMan.loadTileImg(Tile.EMPTY, "./resources/empty.png");

        imgMan.loadPlayerImg(Direction.RIGHT, "./resources/playerR.png");
        imgMan.loadPlayerImg(Direction.LEFT, "./resources/playerL.png");
        imgMan.loadPlayerImg(Direction.UP, "./resources/playerU.png");
        imgMan.loadPlayerImg(Direction.DOWN, "./resources/playerD.png");
        
        imgMan.loadBoxImg("./resources/box.png");

        SceneManager window = new SceneManager(imgMan);
        System.out.print(window.getHeight()); // get rid of annoying unused warning
    }
}
