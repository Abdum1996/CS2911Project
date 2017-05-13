package com;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing Sokoban board, which stores the player and the boxes in the 
 * tile grid, as well as the values of the tiles themselves.
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
	 * @pre map is in a 'valid format'
	 */
	public SokobanBoard(String filename) {
		
	}
	
}
