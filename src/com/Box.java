package com;

/**
 * Class representing a box entity on a game board.
 */
public class Box implements Movable<Box> {
	private final Point position;
	
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
	public Box move(Direction dir) {
		return moveTo(position.move(dir));
	}

	@Override
	public Box moveTo(Point point) {
		return new Box(point);
	}
}
