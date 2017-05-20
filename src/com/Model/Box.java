package com.Model;

/**
 * Class representing a box entity on a game board.
 */
public class Box implements Movable<Box> {
	private final Point position;
	
	/**
	 * the id of this box to allow for randomized textures
	 */
	private final int id;
	
	/**
	 * Construct a box at the given point.
	 * @param point - input point
	 */
	public Box(Point point) {
		id = 0;
		position = point;
	}
	
	/**
	 * Construct a box at the given point.
	 * @param point - input point
	 * @param id - the id of this point
	 */
	public Box(Point point, int id) {
		this.id = id;
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
		return new Box(point, id);
	}

	public int getId() {
		return id;
	}
}
