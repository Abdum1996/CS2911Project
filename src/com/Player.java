package com;

/**
 * Class to represent a player and encapsulates its location
 *
 */
public class Player {
	private Point position;
	
	/** 
	 * Constructs a player given specific coordinates (x, y)
	 * @param x
	 * @param y
	 */
	public Player(int x, int y) {
		position = Point.at(x, y);
	}
	
	/**
	 * Constructs a player given a Point object and uses its coordinates
	 * @param p
	 */
	public Player(Point p) {
		position = p;
	}

	/**
	 * Gives the coordinates of this player
	 * @return Point object encapsulating position
	 */
	public Point getCoordinates() {
		return position;
	}
	
	/**
	 * Get x coordinate of this player
	 */
	public int getX() {
		return position.getX();
	}
	
	/**
	 * Get y coordinate of this player
	 */
	public int getY() {
		return position.getY();
	}
	
	/**
	 * Moves the player in one of the major 4 direction
	 * @param dir the direction the player is to move in
	 */
	public void move(Direction dir) {
		position = position.move(dir);
	}
}