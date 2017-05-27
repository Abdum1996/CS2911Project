package com.Model;

/**
 * Class representing a box entity on a game board.
 */
public class Box implements Movable<Box> {
	private final Point initialPos;
	private final Point position;
	private final int id;
	
	/**
	 * Construct a new box at the given point.
	 * @param point - input point
	 * @param id - the id of this point
	 */
	public Box(Point point, int id) {
		initialPos = point;
		position = point;
		this.id = id;
	}
	
	/**
	 * Construct a box which has an updated position.
	 * @param original - box with original position
	 * @param point    - new position of box
	 */
	private Box(Box original, Point point) {
		initialPos = original.initialPos;
		id = original.id;
		
		this.position = point;
	}
	
	@Override
	public Point getInitialPosition() {
		return initialPos;
	}
	
	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public Box move(Direction dir) {
		return new Box(this, position.move(dir));
	}
	
	@Override
	public Box moveBack(Direction dir) {
		return move(dir);
	}

	/**
	 * Returns the integer id of this box
	 * @return id of (this)
	 */
	public int getId() {
		return id;
	}
}
