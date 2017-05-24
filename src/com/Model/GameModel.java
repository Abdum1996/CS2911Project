package com.Model;

import java.util.Iterator;

public interface GameModel {
	/**
	 * Reset the game board such that the player and the boxes are in their
	 * original positions when the game first started.
	 */
	public void resetBoard();
	
	/**
	 * Procedurally generate a new board.
	 */
	public void generateBoard();
	
	/**
	 * Set the board to a hand generated one.
	 * @param name - name of map
	 */
	public void setBoard(String name);
	
	/**
	 * Apply an action to the board.
	 * @param action - game action
	 * @return true if the action was legal
	 */
	public boolean applyAction(Action action);
	
	/**
	 * Get an iterator providing access to the tiles in the board in order
	 * of left to right, bottom to down in the board.
	 * @return iterator for accessing tiles
	 */
	public Iterator<Tile> getTiles();
	
	/**
	 * Get an iterator providing access to the boxes in the map, primarily intended
	 * for allowing the boxes to be drawn by the game view.
	 * @return iterator for accessing the game boxes
	 */
	public Iterator<Box> getBoxes();
	
	/**
	 * Get a reference to the game's player for drawing them.
	 * @return reference to the game's player
	 */
	public Player getPlayer();
	
	/**
	 * Determine if the board has been solved.
	 * @return true if the game has been won
	 */
	public boolean hasWon();
	
	/**
	 * Get the width of the current map in columns.
	 * @return
	 */
	public int getMapWidth();
	
	/**
	 * Get the height of the current map in roes.
	 * @return
	 */
	public int getMapHeight();
}
