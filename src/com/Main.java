package com;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

        SKBWindow window = new SKBWindow(imgMan);
    }
    
    /**
	 * Attempts to move the player in a given direction
	 * @param dir the direction the players is moving in
	 * @return true if the move is valid, false otherwise
	 */
	public boolean movePlayer(Direction dir) {
		if (!isValidMove(dir))
			return false;
		
		playerPosition = playerPosition.move(dir);
		Box old = boxes.remove(playerPosition);
		
		if (old != null) 
			boxes.put(playerPosition.move(dir), old);
		return true;
	}
	
	/**
	 * Checks if a move is valid
	 * @param dir the direction the player is moving in
	 * @return	true if the move is in a valid direction, false otherwise
	 */
	private boolean isValidMove(Direction dir) {
		
	}
	
	public boolean isValidEntityPos(int x, int y) {
		// Check the position outside of the map grid
		if ((x < 0) || (x >= width) || (y < 0) || (y >= height)) return false;
		
		// Check if the position is inside a wall
		if (get(x, y).equals(Tile.EMPTY) || get(x, y).equals(Tile.WALL)) return false;
		
		return true;
	}
	
	
	/**
	 * checks if there's a box on specified position
	 */
	private boolean containsBox(int x, int y) {
		return boxes.containsKey(Point.at(x, y));
	}
	
	/**
	 * Whether or not the game has been won.
	 * @return true if all boxes are on goals else false
	 */
	public boolean gameWon() {
		for (Point boxPos : getBoxPositions()) {
			Tile currTile = get(boxPos.getX(), boxPos.getY());
			if (!currTile.equals(Tile.GOAL)) return false;
		}
		
		return true;
	}
	
	/**
	 * Constructs a Sokoban Grid with all empty tiles given a width and height. 
	 * Player is at (0,0) and there are no boxes
	 * @param width wanted width for this grid
	 * @param height wanted height for this grid
	 */
	public SokobanGrid(int width, int height) {
		playerPosition = Point.at(0, 0);
		tiles  = new Tile[height][width];i
		boxes  = new HashMap<>();
		
		this.width  = width;
		this.height = height;
		
		// initialize tiles to empty grid
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[y][x] = Tile.EMPTY;
			}
		}
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
