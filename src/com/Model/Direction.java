package com.Model;

import java.util.Arrays;

/**
 * Enumeration to represent direction mainly used to indicate a player's move.
 */
public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
	/**
	 * Get a collection of all the directions.
	 * @return collection of directions
	 */
	public static Iterable<Direction> allDirections() {
		return Arrays.asList(Direction.values());
	}
	
	/**
	 * Determine the direction of movement from an action.
	 * @param action - input action
	 * @return direction in which action is applied
	 */
	public static Direction readAction(Action action) {
		switch (action) {
			case MOVE_UP:
				return UP;
			case MOVE_DOWN:
				return DOWN;
			case MOVE_LEFT:
				return LEFT;
			case MOVE_RIGHT:
				return RIGHT;
			default:
				return null;
		}
	}
	
	/**
	 * Returns the opposite direction of the direction specified
	 * @param dir - the direction you want the opposite of 
	 * @return the opposite direction of dir
	 */
	public static Direction oppositeDirection(Direction dir) {
		switch (dir) { // this function is used mainly in the undo process
			case UP:
				return DOWN;
			case DOWN:
				return UP;
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
			default:
				return null;
		}
	}
}
