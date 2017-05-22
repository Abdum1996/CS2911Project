package com.Model;

import java.util.Iterator;
import java.util.Arrays;

/**
 * 2D coordinate grid of tiles. The top left of the grid is located at (0, 0).
 * As you move from left to right the grid's x coordinate increases, and the
 * y coordinate increases when moving down the grid.
 */
public class TileMap implements Grid<Tile> {
	private final Tile[] tiles;
	private final int width;
	private final int height;
	
	/**
	 * Construct a new tile map and add tiles to the map, in order of left to 
	 * right, top to bottom, from the input iterator.
	 * @param it     - iterator providing tiles
	 * @param width  - width of map in columns
	 * @param height - height of map in rows
	 */
	public TileMap(Iterator<Tile> it, int width, int height) {
		this(width, height);
		
		int index = 0;
		while (it.hasNext() && (index < tiles.length)) {
			tiles[index++] = it.next();
		}
	}
	
	/**
	 * Construct a new tile map.
	 * @param width  - width of map in columns
	 * @param height - height of map in rows
	 */
	public TileMap(int width, int height) {
		tiles = new Tile[width*height];
		this.width  = width;
		this.height = height;
		
		Arrays.fill(tiles, Tile.EMPTY);
	}
	
	/**
	 * Determine if an entity (box or player) can be placed at a given
	 * point in the map. The point is valid if it lies inside the grid
	 * and the corresponding tile is not empty or a wall of some sort.
	 * @param point - point being checked
	 * @return true if entities can be placed at that point
	 */
	public boolean isValidEntityPos(Point point) {
		if (!hasPoint(point)) return false;
		
		Tile tile = get(point);
		return !tile.equals(Tile.EMPTY) && !tile.equals(Tile.WALL);
	}
	
	@Override
	public void set(Tile value, Point point) {
		tiles[point.getX() + point.getY()*width] = value;
	}
	
	@Override
	public Tile get(Point point) {
		return tiles[point.getX() + point.getY()*width];
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean hasPoint(Point point) {
		if ((point.getX() < 0) || (point.getX() >= width)) return false;
		if ((point.getY() < 0) || (point.getY() >= height)) return false;
		return true;
	}

	@Override
	public Iterator<Tile> iterator() {
		return new Iterator<Tile>() {
			private int index = 0;
			
			@Override
			public boolean hasNext() {
				return (index < tiles.length);
			}

			@Override
			public Tile next() {
				return tiles[index++];
			}
		};
	}
}
