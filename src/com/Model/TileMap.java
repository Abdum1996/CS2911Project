package com.Model;

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
	 * Builder class for constructing a tile map.
	 */
	public static class Builder implements Grid.Builder<Tile> {
		private final Tile[] tiles;
		private final int width;
		
		/**
		 * Construct a map builder of a given width and height.
		 * @param width  - width of map in columns
		 * @param height - height of map in rows
		 */
		public Builder(int width, int height) {
			tiles = new Tile[width*height];
			this.width = width;
			
			Arrays.fill(tiles, Tile.EMPTY);
		}
		
		@Override
		public void set(Tile value, Point point) {
			tiles[point.getX() + point.getY()*width] = value;
		}

		@Override
		public Grid<Tile> build() {
			int height = tiles.length/width;
			return new TileMap(tiles, width, height);
		}
	}
	
	/**
	 * Construct a tile map from a list of tiles.
	 * @param tiles  - input list of tiles
	 * @param width  - width of map in columns
	 * @param height - height of map in rows
	 */
	private TileMap(Tile[] tiles, int width, int height) {
		this.tiles  = tiles;
		this.width  = width;
		this.height = height;
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
	// A point is defined to be valid if it lies inside the grid
	// and it is not an empty tile or a wall of some sort
	public boolean isValidPoint(Point point) {
		if ((point.getX() < 0) || (point.getX() >= width)) return false;
		if ((point.getY() < 0) || (point.getY() >= height)) return false;
		
		Tile tile = get(point);
		return !tile.equals(Tile.EMPTY) && !tile.equals(Tile.WALL);
	}
}
