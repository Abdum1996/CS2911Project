package com;

import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.HashMap;
import java.util.Scanner;

import com.GUI.ImageManager;
import com.GUI.SKBWindow;

public class Game {
	private GameBoard board;
	
	public Game(String filename) {
		
	}
	
    public static void main(String[] args) {

        // load the images
        ImageManager imgMan= new ImageManager();
        imgMan.loadTileImg(Tile.GOAL, "./resources/goal.png");
        imgMan.loadTileImg(Tile.FLOOR, "./resources/floor.png");
        imgMan.loadTileImg(Tile.WALL, "./resources/wall.png");
        imgMan.loadTileImg(Tile.EMPTY, "./resources/empty.png");

        imgMan.loadEntityImg(EntityTypes.BOX, "./resources/box.png");
        imgMan.loadEntityImg(EntityTypes.PLAYER, "./resources/player.png");

        SKBWindow window = new SKBWindow(imgMan);
    }
    
	
	/**
	 * Construct a SokobanGrid given a txt file's name
	 * @param filename name of the txt file containing the map template
	 */
	public SokobanGrid(String filename) {
		playerPosition = Point.at(0, 0);
		boxes = new HashMap<>();
		tiles = null;
		
		Scanner sc = null;
		
		try {
			sc = new Scanner(new FileReader(filename));
			
			width = Integer.parseInt(sc.nextLine());
			height = Integer.parseInt(sc.nextLine());
			tiles = new Tile[height][width];
			
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					String symbol = sc.next();
					tiles[y][x] = Tile.parse(symbol);
					
					if (symbol.equals("P")) {
						playerPosition = Point.at(x, y);
					} else if (symbol.equals("B")) {
						boxes.put(Point.at(x, y), new Box());
					}
				}
			}
			
		} catch (FileNotFoundException | NoSuchElementException e) {
			e.printStackTrace();
			
		} finally {
			if (sc != null) sc.close();
		}
	}
}
