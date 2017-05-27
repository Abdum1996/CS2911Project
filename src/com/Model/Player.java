package com.Model;

/**
 * Class representing a player entity on a game board.
 */
public class Player implements Movable<Player> {
	private final Direction orientation;
	private final Point initialPos;
	private final Point position;
	
	/**
	 * Construct a new player at the given point.
	 * @param point - input point
	 */
	public Player(Point point) {
		orientation = Direction.DOWN;
		
		initialPos = point;
		position = point;
	}
	
	/**
	 * Construct a player with an updated orientation and position.
	 * @param original - player with the original position
	 * @param point    - new position of player
	 * @param dir      - new player orientation
	 */
	private Player(Player original, Point point, Direction dir) {
		initialPos = original.initialPos;
		
		this.orientation = dir;
		this.position = point;
	}
	
	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public Player move(Direction dir) {
		return new Player(this, position.move(dir), dir);

	}
	
	@Override
	public Player moveBack(Direction dir) {
		return new Player(this, position.move(dir), 
				Direction.oppositeDirection(dir));
	}

	/**
	 * What direction is the player facing
	 * @return The orientation of this player
	 */
	public Direction getOrientation() {
		return orientation;
	}

	@Override
	public Player resetPosition() {
		return new Player(initialPos);
	}
}
