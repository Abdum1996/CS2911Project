package com;
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
	private List<List<Tile>> tiles;
	
	private int width;
	
	private int height;
	
	private Player player;
	
	private List<Box> boxes;
	
	/**
	 * Constructs a Sokoban Grid with all empty tiles given a width and height. 
	 * Player is at (0,0) and there are no boxes
	 * @param width wanted width for this grid
	 * @param height wanted height for this grid
	 */
	public SokobanGrid(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new ArrayList<>();
		
		// initialize tiles to empty grid
		for (int y = 0; y < height; y++) {
			tiles.add(new ArrayList<Tile>());
			for (int x = 0; x < width; x++) {
				// add empty tile to last list added
				tiles.get(tiles.size()-1).add(Tile.EMPTY);
			}
		}
		
		player = new Player(0, 0);
		boxes = new ArrayList<>();
		
	}
	
	/**
	 * Construct a SokobanGrid given a txt file's name
	 * @param filename name of the txt file containing the map template
	 */
	public SokobanGrid(String filename) {
		//TODO construct a grid from a file
		// format for a file would be like this:
		// first line is width, second is height. Then space separated
		// tile letters or symbols just to make it easier to make puzzles
		// from ASCII art
		// UNDER DEVELOPMENT/Not yet tested
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		width = Integer.parseInt(sc.nextLine());
		height = Integer.parseInt(sc.nextLine());
		boxes = new ArrayList<>();
		
		Map<Character, Tile> m = new HashMap<>();
		
		m.put('W', Tile.WALL);
		m.put('X', Tile.EMPTY);
		m.put('O', Tile.GOAL);
		m.put('F', Tile.FLOOR);
		// for player and box
		m.put('P', Tile.FLOOR);
		m.put('B', Tile.FLOOR);
		
		String args[];
		String line;
		char c;
		Tile t;
		tiles = new ArrayList<>();
		int y = 0;
		while(sc.hasNext()) {
			line = sc.nextLine();
			
			// ignore all lines beginning with # or empty
			if (line.equals("") || Character.isWhitespace(line.charAt(0)) || line.charAt(0) == '#') 
				continue;
			
			args = line.split(" ");
			tiles.add(new ArrayList<Tile>());
			for (int x = 0; x < width; x++) {
				c = args[x].charAt(0);
				t = m.get(c);
				if (c == 'P') {
					player = new Player(x, y);
				} else if (c == 'B') {
					boxes.add(new Box(x, y));
				}
				tiles.get(tiles.size()-1).add(t);
			}
			y++;
		}
	}
	
	/**
	 * Get the Player object associated with this grid instance
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Get a list of all boxes in this grid
	 */
	public List<Box> getBoxes() {
		return boxes;
	}
	
	public Tile get(int x, int y) {
		return tiles.get(y).get(x);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public List<List<Tile>> getTiles() { return tiles; }
	
	/**
	 * Attempts to move the player in a given direction
	 * @param dir the direction the players is moving in
	 * @return true if the move is valid, false otherwise
	 */
	public boolean movePlayer(Direction dir) {
		if (!isValidMove(dir))
			return false;
		// perform move
		int x = player.x();
		int y = player.y();
		switch(dir) {
			case UP:
				if (containsBox(x, y-1))
					getBoxOn(x, y-1).move(dir);
				break;
			case DOWN:
				if (containsBox(x, y+1))
					getBoxOn(x, y+1).move(dir);
				break;
			case RIGHT:
				if (containsBox(x+1, y))
					getBoxOn(x+1, y).move(dir);
				break;
			case LEFT:
				if (containsBox(x-1, y))
					getBoxOn(x-1, y).move(dir);
				break;
		}
		
		player.move(dir);
		return true;
	}
	
	/**
	 * Checks if a move is valid
	 * @param dir the direction the player is moving in
	 * @return	true if the move is in a valid direction, false otherwise
	 */
	private boolean isValidMove(Direction dir) {
		boolean check = true;
		int x = player.x();
		int y = player.y();
		switch (dir) {
			case UP:
				// no space to go
				if (player.y() == 0) {
					check = false;
				} else if (y > 1 && containsBox(x, y - 1) && containsBox(x, y - 2)) { 
					// two boxes stacked after one another
					check = false;
				} else if(y == 1 && containsBox(x, 0)) { //box on map edge
					check = false;
				} else if (get(x, y - 1) == Tile.EMPTY || get(x, y - 1) == Tile.WALL) {// moving through a wall
					check = false;
				} else if (y > 1 && containsBox(x, y - 1) && (get(x, y - 2) == Tile.EMPTY || get(x, y - 2) == Tile.WALL)) {
					// moving a box through a wall
					check = false;
				}
				break;
			case DOWN:
				// no space to go
				if (player.y() == height - 1) {
					check = false;
				} else if (y < height - 2 && containsBox(x, y + 1) && containsBox(x, y + 2)) { 
					// two boxes stacked after one another
					check = false;
				} else if(y == height - 2 && containsBox(x, height - 1)) { //box on map edge
					check = false;
				} else if (get(x, y + 1) == Tile.EMPTY || get(x, y + 1) == Tile.WALL) {// moving through a wall
					check = false;
				} else if (y < height - 2 && containsBox(x, y + 1) && (get(x, y + 2) == Tile.EMPTY || get(x, y + 2) == Tile.WALL)) {
					// moving a box through a wall
					check = false;
				}
				break;
			case RIGHT:
				// no space to go
				if (player.x() == width - 1) {
					check = false;
				} else if (x < width - 2 && containsBox(x + 1, y) && containsBox(x + 2, y)) { 
					// two boxes stacked after one another
					check = false;
				} else if(x == width - 2 && containsBox(width - 1, y)) { //box on map edge
					check = false;
				} else if (get(x + 1, y) == Tile.EMPTY || get(x + 1, y) == Tile.WALL) {// moving through a wall
					check = false;
				} else if (x < width - 2 && containsBox(x + 1, y) && (get(x + 2, y) == Tile.EMPTY || get(x + 2, y) == Tile.WALL)) {
					// moving a box through a wall
					check = false;
				}
				break;
			case LEFT:
				// no space to go
				if (player.x() == 0) {
					check = false;
				} else if (x > 1 && containsBox(x - 1, y) && containsBox(x - 2, y)) { 
					// two boxes stacked after one another
					check = false;
				} else if(x == 1 && containsBox(0, y)) { //box on map edge
					check = false;
				} else if (get(x - 1, y) == Tile.EMPTY || get(x - 1, y) == Tile.WALL) {// moving through a wall
					check = false;
				} else if (x > 1 && containsBox(x - 1, y) && (get(x - 2, y) == Tile.EMPTY || get(x - 2, y) == Tile.WALL)) {
					// moving a box through a wall
					check = false;
				}
				break;
		}
		
		if (check == false) System.out.println("fail");
		return check;
	}
	
	/**
	 * checks if there's a box on specified position
	 */
	private boolean containsBox(int x, int y) {
		for (Box b : boxes) {
			if (x == b.x() && y == b.y()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Whether or not the game has been won.
	 * @return true if all boxes are on goals else false
	 */
	public boolean gameWon() {
		for (Box b : boxes) {
			if (get(b.x(), b.y()) != Tile.GOAL) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the box on the coordinates specified, if no box exists, return null
	 */
	private Box getBoxOn(int x, int y) {
		for (Box b : boxes) {
			if (x == b.x() && y == b.y()) {
				return b;
			}
		}
		return null;
	}
}
