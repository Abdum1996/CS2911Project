package com.Model;
/**
 * Enumeration to represent a tile in a grid.
 *
 */
public enum Tile {
	GOAL, FLOOR, WALL, EMPTY;
	
	/**
	 * Create a tile from a given symbol.
	 * @param symbol - letter on ASCII board
	 * @return new tile
	 */
	public static Tile parse(String symbol) {
		switch (symbol) {
			case "W":
				return WALL;
			case "X":
				return EMPTY;
			case "O":
				return GOAL;
			case "F":
				return FLOOR;
			case "P":
				return FLOOR;
			case "B":
				return FLOOR;
			default:
				return EMPTY;
		}		
	}
}
