package com;
/**
 * Enumeration to represent direction mainly used to indicate a player's move.
 *
 */
public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
	/**
	 * Create a direction from a given name.
	 * @param name - name of direction
	 * @return new direction
	 */
	public static Direction parse(String name) {
		switch (name) {
			case "UP":
				return UP;
			case "DOWN":
				return DOWN;
			case "LEFT":
				return LEFT;
			case "RIGHT":
				return RIGHT;
			default:
				return UP;
		}
	}
}
