package com;

/**
 * Abstract class representing a movable entity on a game board,
 * which keeps track of its own position and state.
 */
public abstract class Entity {
	private Point position;
	
	/**
	 * Constructs a new entity at the given x and y coordinates.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Entity(int x, int y) {
		position = Point.at(x, y);
	}
	
	/**
	 * Constructs a new entity at the given point.
	 * @param point - location of new entity
	 */
	public Entity(Point point) {
		position = point;
	}
	
	/**
	 * Get the entity's coordinate position.
	 * @return point representing entity's position. 
	 */
	public Point getPosition() {
		return position;
	}
	
	/**
	 * Get the x coordinate of this entity.
	 * @return x coordinate
	 */
	public int getX() {
		return position.getX();
	}
	
	/**
	 * Get the y coordinate of this entity.
	 * @return y coordinate
	 */
	public int getY() {
		return position.getY();
	}
	
	/**
	 * Move entity in the given direction.
	 * @param dir - move direction
	 */
	public void move(Direction dir) {
		position = position.move(dir);
	}
	
	/**
	 * Move the entity to the given x and y coordinates.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public void moveTo(int x, int y) {
		position = Point.at(x, y);
	}
	
	/**
	 * Move the entity to the given point.
	 * @param point - input point
	 */
	public void moveTo(Point point) {
		position = point;
	}
}
