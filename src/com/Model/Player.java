package com.Model;

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
	
	/**
	 * Construct a player at the given point facing the given direction
	 * @param point - input point
	 * @param dir the direction the player is initially facing
	 */
	public Player(Point point, Direction dir) {
		position = point;
		orientation = dir;
	}
	
	
	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public Player move(Direction dir) {
		return new Player(position.move(dir), dir);
	}

	@Override
	public Player moveTo(Point point) {
		return new Player(point, orientation);
	}

	/**
	 * What direction is the player facing
	 * @return The orientation of this player
	 */
	public Direction getOrientation() {
		return orientation;
	}
}
