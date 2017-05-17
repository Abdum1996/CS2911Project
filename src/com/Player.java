package com;

/**
 * Class representing a player entity on a game board.
 */
public class Player implements Movable<Player> {
	private final Point position;
	private Direction orientation;
	
	/**
	 * Construct a player at the given point.
	 * @param point - input point
	 */
	public Player(Point point) {
		position = point;
		orientation = Direction.DOWN;
	}
	
	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public Player move(Direction dir) {
		if(orientation != dir) {
			orientation = dir;
			return this;
		}
		return moveTo(position.move(dir));
	}

	@Override
	public Player moveTo(Point point) {
		return new Player(point);
	}
}
