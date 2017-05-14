package com;
/**
 * Enumeration to represent direction mainly used to indicate a player's move.
 *
 */
public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
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
				return UP;
		}
	}
}
