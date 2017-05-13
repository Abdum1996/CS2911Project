package com;

/**
 * Class representing a box entity on the game board.
 */
public class Box extends Entity {
	/**
	 * Construct a new box at the given x and y coordinates.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Box(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Construct a new box at the given point.
	 * @param point - location of new box
	 */
	public Box(Point point) {
		super(point);
	}
}
