package com;

/**
 * Class representing a player entity on a game board.
 */
public class Player implements Movable<Player> {
	private final Point position;
	
	/**
	 * Construct a player at the given x and y coordinates.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Player(int x, int y) {
		this(Point.at(x, y));
	}
	
	/**
	 * Construct a player at the given point.
	 * @param point - input point
	 */
	public Player(Point point) {
		position = point;
	}
	
	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public int getX() {
		return position.getX();
	}

	@Override
	public int getY() {
		return position.getY();
	}

	@Override
	public Player move(Direction dir) {
		return moveTo(position.move(dir));
	}

	@Override
	public Player moveTo(int x, int y) {
		return moveTo(Point.at(x, y));
	}

	@Override
	public Player moveTo(Point point) {
		return new Player(point);
	}
}
