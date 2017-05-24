package com.Model;

/**
 * Generic interface representing a fixed 2D coordinate grid of cells.
 * @param <T> - type of cell stored in the grid
 */
public interface Grid<T> {
	/**
	 * Builder interface for setting up a new grid.
	 * @param <T> - type of cell stored in the grid
	 */
	public interface Builder<T> {
		/**
		 * Set the cell at the given point to a new value.
		 * @param value - new value of cell
		 * @param point - location of cell
		 */
		public void set(T value, Point point);
		
		/**
		 * Generate grid from its builder object.
		 * @return newly generated grid
		 */
		public Grid<T> build();
	}
	
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
	 * Determine if a point on the grid is 'valid'. (implementation defined)
	 * @param point - specified point
	 * @return true if the point is 'valid'
	 */
	public boolean isValidPoint(Point point);
}
