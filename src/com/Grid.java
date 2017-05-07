package com;
/**
 * Interface representing a Grid with tiles of type T
 *
 */
public interface Grid<T> {
	/**
	 * Returns the tile/component given a coordinate. (0,0) is top left, 
	 * y increases as it goes down and x increases as you go to the right
	 * @param x horizontal coordinate
	 * @param y vertical coordinate
	 * @return the tile object associated with position
	 */
	public T get(int x, int y);
	
	/**
	 * Returns the height of the grid.
	 */
	public int getHeight();
	
	/**
	 * Returns the with of the grid.
	 */
	public int getWidth();
}
