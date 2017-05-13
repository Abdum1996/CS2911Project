package com;

/**
 * Class representing a box entity on the game board.
 */
public class Box implements Movable<Box> {
	private final Point position;
	
	/**
	 * Construct a box at the given x and y coordinates.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Box(int x, int y) {
		this(Point.at(x, y));
	}
	
	/**
	 * Construct a box at the given point.
	 * @param point - input point
	 */
	public Box(Point point) {
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
	public Box move(Direction dir) {
		return moveTo(position.move(dir));
	}

	@Override
	public Box moveTo(int x, int y) {
		return moveTo(Point.at(x, y));
	}

	@Override
	public Box moveTo(Point point) {
		return new Box(point);
	}
}
