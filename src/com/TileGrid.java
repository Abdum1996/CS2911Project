package com;

import java.util.Iterator;
import java.util.Arrays;

/**
 * 2D coordinate grid of tiles. The top left of the grid is located at (0, 0).
 * As you move from left to right the grid's x coordinate increases, and the
 * y coordinate increases when moving down the grid.
 * @invariant tile values are never null
 */
public class TileGrid implements Grid<Tile> {
	private final Tile[] tiles;
	private final int width;
	private final int height;
	
	/**
	 * Construct a new tile grid.
	 * @param width  - number of columns in the grid
	 * @param height - number of rows in the grid
	 * @pre (width > 0) and (height > 0)
	 */
	public TileGrid(int width, int height) {
		tiles = new Tile[width*height];
		
		this.width  = width;
		this.height = height;
		
		Arrays.fill(tiles, Tile.EMPTY);
	}
	
	@Override
	public Tile get(int x, int y) {
		return tiles[x + y*height];
	}

	@Override
	public Tile get(Point point) {
		return get(point.getX(), point.getY());
	}

	@Override
	public void set(Tile value, int x, int y) {
		if (value != null)
			tiles[x + y*height] = value; 
	}

	@Override
	public void set(Tile value, Point point) {
		set(value, point.getX(), point.getY());
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public Iterator<Tile> iterator() {
		Iterator<Tile> it = new Iterator<Tile>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < tiles.length;
			}

			@Override
			public Tile next() {
				return tiles[index++];
			}
		};
		
		return it;
	}
}
