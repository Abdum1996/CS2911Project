package com;

import java.util.Iterator;
import java.util.Arrays;

/**
 * 2D coordinate grid of tiles. The top left of the grid is located at (0, 0).
 * As you move from left to right the grid's x coordinate increases, and the
 * y coordinate increases when moving down the grid.
 */
public class TileGrid implements Grid<Tile> {
	private final Tile[] tiles;
	private final int height;
	private final int width;
	
	public TileGrid(int height, int width) {
		tiles = new Tile[height*width];
		
		this.height = height;
		this.width  = width;
		
		Arrays.fill(tiles, Tile.EMPTY);
	}
	
	@Override
	public Tile get(int x, int y) {
		return tiles[height*y + x];
	}

	@Override
	public Tile get(Point point) {
		return get(point.getX(), point.getY());
	}

	@Override
	public void set(Tile value, int x, int y) {
		tiles[height*y + x] = value; 
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
