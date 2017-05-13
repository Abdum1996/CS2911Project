package com;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class representing Sokoban board, which stores the player and the boxes in the 
 * tile grid, as well as the values of the tiles themselves. It is responsible for
 * validating 
 */
public class SokobanBoard {
	private final TileGrid  tiles;
	private final List<Box> boxes;
	private final Player player;
	
	/**
	 * Construct a Sokoban board filled with empty tiles. The player is located at
	 * (0, 0) and there are no boxes in the grid.
	 * @param width  - width of the tile grid
	 * @param height - height of the tile grid
	 * @pre (width > 0) and (height > 0)
	 */
	public SokobanBoard(int width, int height) {
		tiles = new TileGrid(width, height);
		boxes = new ArrayList<>();
		player = new Player();
	}
	
	/**
	 * Given an input map file, construct a new Sokoban grid.
	 * @param filename - name of file containing template map
	 * @param width    - width of the map
	 * @param height   - height of the map
	 * @pre (width > 0) and (height > 0)
	 * @pre map is in a 'valid format'
	 */
	public SokobanBoard(String filename, int width, int height) {
		this(width, height);
		
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(filename));
			
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					String symbol = sc.next();
					tiles.set(Tile.parse(symbol), x, y);
					
					if (symbol.equals("P")) {
						player.moveTo(x, y);
					} else if (symbol.equals("B")) {
						boxes.add(new Box(x, y));
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
	 * Get the player stored on this board.
	 * @return stored player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Get a list of boxes on this board.
	 * @return list of boxes
	 */
	public List<Box> getBoxes() {
		return new ArrayList<>(boxes);
	}
}
