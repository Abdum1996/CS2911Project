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
	 * Constructs a Sokoban Grid with all empty tiles given a width and height
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
		
		Map<Character, Tile> m = new HashMap<>();
		
		m.put('W', Tile.WALL);
		m.put('X', Tile.EMPTY);
		m.put('O', Tile.GOAL);
		m.put('F', Tile.FLOOR);
		
		String args[];
		String line;
		char c;
		Tile t;
		tiles = new ArrayList<>();
		while(sc.hasNext()) {
			line = sc.nextLine();
			
			if (line.equals("") || Character.isWhitespace(line.charAt(0))) 
				continue;
			
			args = line.split(" ");
			tiles.add(new ArrayList<Tile>());
			for (int x = 0; x < width; x++) {
				c = args[x].charAt(0);
				t = m.get(c);
				tiles.get(tiles.size()-1).add(t);
			}
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
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
	
	/**
	 * Attempts to move the player in a given direction
	 * @param dir the direction the players is moving in
	 * @return true if the move is valid, false otherwise
	 */
	public boolean movePlayer(Direction dir) {
		//TODO
		return false;
	}
	
	/**
	 * Checks if a move is valid
	 * @param dir the direction the player is moving in
	 * @return	true if the move is in a valid direction, false otherwise
	 */
	private boolean isValidMove(Direction dir) {
		// TODO
		return false;
	}
	
}
