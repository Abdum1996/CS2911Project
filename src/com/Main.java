package com;

import com.GUI.ImageManager;
import com.GUI.SKBWindow;

public class Main {
    public static void main(String[] args) {

        // load the images
        ImageManager imgMan= new ImageManager();
        imgMan.loadTileImg(Tile.GOAL, "./resources/goal.png");
        imgMan.loadTileImg(Tile.FLOOR, "./resources/floor.png");
        imgMan.loadTileImg(Tile.WALL, "./resources/wall.png");
        imgMan.loadTileImg(Tile.EMPTY, "./resources/empty.png");

        imgMan.loadEntityImg(EntityTypes.BOX, "./resources/box.png");
        imgMan.loadEntityImg(EntityTypes.PLAYER, "./resources/player.png");

        SokobanGrid grid = new SokobanGrid("map1.txt");

        SKBWindow window = new SKBWindow(imgMan, grid);
    }
}
