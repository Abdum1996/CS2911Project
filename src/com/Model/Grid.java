package com.Model;

/**
 * Generic interface representing a 2D coordinate grid of cells.
 * @param <T> - type of cell stored in the grid
 */
public interface Grid<T> extends Iterable<T> {	
	/**
	 * Set the cell at the given point to a new value.
	 * @param value - new value of cell
	 * @param point - location of cell
	 */
	public void set(T value, Point point);
	
	/**
	 * Get the cell at the given point.
	 * @param point - point specifying cell's location
	 * @return cell located at that point
	 */
	public T get(Point point);
	
	/**
	 * Get the width of the grid in columns.
	 * @return number of columns in the grid
	 */
	public int getWidth();
	
	/**
	 * Get the height of the grid in rows.
	 * @return number of rows in the grid
	 */
	public int getHeight();
	
	/**
	 * Determine if a point is contained on the grid.
	 * @param point - specified point
	 * @return true if the point within the grid's range
	 */
	public boolean hasPoint(Point point);
}
