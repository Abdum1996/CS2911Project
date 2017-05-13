package com;

/**
 * Class representing a player entity on the game board.
 */
public class Player extends Entity {
	/**
	 * Construct a new player at the default position (0, 0).
	 */
	public Player() {
		super(0, 0);
	}
	
	/**
	 * Construct a new player at the given x and y coordinates.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Player(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Construct a new player at the given point.
	 * @param point - location of new box
	 */
	public Player(Point point) {
		super(point);
	}
}