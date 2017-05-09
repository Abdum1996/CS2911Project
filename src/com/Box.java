package com;

/**
 * Class representing a box to be used in a game grid
 *
 */
public class Box {
	
	private Point position;
	
	/** 
	 * Constructs a box given specific coordinates (x, y)
	 * @param x
	 * @param y
	 */
	public Box(int x, int y) {
		position = Point.at(x, y);
	}
	
	/**
	 * Constructs a box given a Point object and uses its coordinates
	 * @param p
	 */
	public Box(Point p) {
		position = p;
	}
	
	/**
	 * Gives the coordinates of this box
	 * @return Point object encapsulating position
	 */
	public Point getCoordinates() {
		return position;
	}
	
	/**
	 * Get x coordinate of this box
	 */
	public int x() {
		return position.getX();
	}
	
	/**
	 * Get y coordinate of this box
	 */
	public int y() {
		return position.getY();
	}
	
	/**
	 * Moves the box in one of the major 4 direction
	 * @param dir the direction the box is to be moved in
	 */
	public void move(Direction dir) {
		position.move(dir);
	}
}
