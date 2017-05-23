package com.Model;

import java.util.Iterator;

/**
 * 2D fixed coordinate grid of tiles. The top left of the grid is located at (0, 0).
 * As you move from left to right, the grid's x coordinate increases, and the
 * y coordinate increases when moving down the grid.
 */
public class TileMap implements Iterable<Tile> {
	private final Tile[] tiles;
	private final int width;
	private final int height;
	
	/**
	 * Construct a new tile map and add tiles to the map, in order of left to 
	 * right, top to bottom, from the input iterator. If the map still has
	 * empty spots left then these spots are filled with empty tiles.
	 * @param it     - iterator providing tiles
	 * @param width  - width of map in columns
	 * @param height - height of map in rows
	 */
	public TileMap(Iterator<Tile> it, int width, int height) {
		tiles = new Tile[width*height];
		this.width  = width;
		this.height = height;
		
		int index = 0;
		while (it.hasNext() && (index < tiles.length)) {
			tiles[index++] = it.next();
		}
		
		while (index < tiles.length) {
			tiles[index++] = Tile.EMPTY;
		}
	}
	
	/**
	 * Get the tile at the specified location.
	 * @pre grid actually contains the input point
	 * @param point - point representing tiles location
	 * @return tile at the given location
	 */
	public Tile get(Point point) {
		return tiles[point.getX() + point.getY()*width];
	}
	
	/**
	 * Get the width of the map in columns.
	 * @return width of map
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Get the height of the map in rows.
	 * @return height of map
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Determine if a point is within the bounds of the map.
	 * @param point - point being checked
	 * @return true if the map contains the point
	 */
	public boolean hasPoint(Point point) {
		if ((point.getX() < 0) || (point.getX() >= width)) return false;
		if ((point.getY() < 0) || (point.getY() >= height)) return false;
		return true;
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
