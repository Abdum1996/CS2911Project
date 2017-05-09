package com;

import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SokobanGrid implements Grid<Tile> {
	/**
	 * List of all tiles. Top left tile is (0,0).
	 * As you go right, x increases and as you go down y increases.
	 */
	
	private final Map<Point, Box> boxes;
	private Point playerPosition;
	
	private Tile[][] tiles;
	private int height;
	private int width;
	
	/**
	 * Constructs a Sokoban Grid with all empty tiles given a width and height. 
	 * Player is at (0,0) and there are no boxes
	 * @param width wanted width for this grid
	 * @param height wanted height for this grid
	 */
	public SokobanGrid(int width, int height) {
		playerPosition = Point.at(0, 0);
		tiles  = new Tile[height][width];
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
	
	/**
	 * Get the Player object associated with this grid instance
	 */
	public Point getPlayerPos() {
		return playerPosition;
	}
	
	/**
	 * Get a list of all boxes in this grid
	 */
	public List<Point> getBoxPositions() {
		return new ArrayList<>(boxes.keySet());
	}
	
	public Tile get(int x, int y) {
		return tiles[y][x];
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Tile[][] getTiles() { 
		return tiles; 
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
		Point next1 = playerPosition.move(dir);
		Point next2 = playerPosition.move(dir);
		int x1 = next1.getX(), y1 = next1.getY();
		int x2 = next2.getX(), y2 = next2.getY();

		// Ensure the target position is not outside the map or a wall
		if (!isValidEntityPos(x1, y1)) return false;
		
		// Handle case where the adjacent object is a box
		if (containsBox(x1, y1)) {
			if (!isValidEntityPos(x2, y2)) return false;
			if (containsBox(x2, y2)) return false;
		}
			
		return true;
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
}
