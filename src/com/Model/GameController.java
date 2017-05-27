package com.Model;

import java.util.Random;

import com.GUI.ImageManager;
import com.GUI.SceneManager;

/**
 * The driver class for the game, sets up the graphics and runs it
 */
public class GameController {
    public static void main(String[] args) {
        // load the images
        ImageManager imgMan = new ImageManager();
        imgMan.loadTileImg(Tile.GOAL, "./resources/goal.png");
        imgMan.loadTileImg(Tile.FLOOR, "./resources/floor.png");
        imgMan.loadTileImg(Tile.WALL, "./resources/wall.png");
        imgMan.loadTileImg(Tile.EMPTY, "./resources/empty.png");
        
        Random rand = new Random();
        for (int i = 0; i < GameConstants.BOX_LIMIT; i++) {
            int r = rand.nextInt(GameConstants.NUM_BOX_TEXTURES);
			imgMan.loadBoxImg(i, "./resources/box" + r + ".png");
		}
        
        @SuppressWarnings("unused")
        SceneManager window = new SceneManager(imgMan);
    }
}
