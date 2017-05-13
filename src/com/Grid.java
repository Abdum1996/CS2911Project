package com;

/**
 * Generic interface representing a grid of cells.
 */
public interface Grid<T> extends Iterable<T> {
	/**
	 * Get the cell at the given (x, y) coordinate.
	 * @param x - cell's x coordinate
	 * @param y - cell's y coordinate
	 * @return cell located at that coordinate
	 */
	public T get(int x, int y);
	
	/**
	 * Get the cell at the given point.
	 * @param point - point specifying cell's location
	 * @return cell located at that point
	 */
	public T get(Point point);
	
	/**
	 * Set the value of the cell at the given (x, y) coordinate.
	 * @param value - new value for cell
	 * @param x     - x coordinate
	 * @param y     - y coordinate
	 */
	public void set(T value, int x, int y);
	
	/**
	 * Set the value of the cell at the given point.
	 * @param value - new value for cell
	 * @param point - point specifying cell's location
	 */
	public void set(T value, Point point);
	
	/**
	 * Get the height of the grid.
	 * @return number of rows in the grid
	 */
	public int getHeight();
	
	/**
	 * Get the width of the grid.
	 * @return number of columns in the grid
	 */
	public int getWidth();
}
