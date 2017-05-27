package com.Model;

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
	 * Move the entity in a given direction.
	 * @param dir - move direction
	 * @return entity with an updated position
	 */
	public T move(Direction dir);
	
	/**
	 * Move the entity backwards in a given direction.
	 * @param dir - direction in which entity is moved backwards
	 * @return entity with an updated position
	 */
	public T moveBack(Direction dir);
	
	/**
	 * Get an instance of the entity in its original position.
	 * @return reset entity
	 */
	public T resetPosition();
}
