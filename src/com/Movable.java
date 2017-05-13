package com;

/**
 * Interface representing movable entities which keep track
 * of their own position on a 2D coordinate plane.
 * @param <T> - type implementing this interface
 */
public interface Movable<T> {
	/**
	 * Get the entity's coordinate position.
	 * @return point representing entity's position. 
	 */
	public Point getPosition();
	
	/**
	 * Get the x coordinate of this entity.
	 * @return x coordinate
	 */
	public int getX();
	
	/**
	 * Get the y coordinate of this entity.
	 * @return y coordinate
	 */
	public int getY();
	
	/**
	 * Move the entity in a given direction.
	 * @param dir - move direction
	 * @return entity with an updated position
	 */
	public T move(Direction dir);
	
	/**
	 * Move the entity to a new set of coordinates.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return entity with an updated position
	 */
	public T moveTo(int x, int y);
	
	/**
	 * Move the entity to a new point.
	 * @param point - input point
	 * @return entity with an updated position
	 */
	public T moveTo(Point point);
}
