package com.Model;

import java.util.Iterator;
import java.util.List;

/**
 * Generic interface representing a Sokoban game board consisting
 * of a grid of tiles. This interface is intended to be used by the
 * board solving algorithm and the game's front-end.
 */
public interface GameBoard {
	/**
	 * Get the tile at the given point.
	 * @param point - location of tile in the map
	 * @return tile located at that point
	 */
	public Tile getTile(Point point);
	
	/**
	 * Get all the boxes in the game map.
	 * @return list of boxes in the map
	 */
	public Iterator<Box> getBoxes();
	
	/**
	 * Get the current state of the player.
	 * @return player in the map
	 */
	public Player getPlayer();
	
	/**
	 * Get the width of the game map in columns.
	 * @return map width
	 */
	public int getMapWidth();
	
	/**
	 * Get the height of the game map in rows.
	 * @return map height
	 */
	public int getMapHeight();
	
	/**
	 * Determine if a given action is valid.
	 * @param action - action to be applied to the board
	 * @return true if the action is legal
	 */
	public boolean isValidAction(Action action);
	
	/**
	 * Get player to apply an action to the board.
	 * @param action - action applied to the board
	 * @return the ActionResult of action
	 */
	public ActionResult applyAction(Action action);
	
	/**
	 * Get the ActionResult that would follow 
	 * from action without applying it
	 * @param action the action to checked
	 * @return the ActionResult resulting from action
	 */
	public ActionResult getActionResult(Action action);
	
	/**
	 * Determine if the game is in a winning state.
	 * @return true if the player has won the game
	 */
	public boolean gameWon();
	
	/**
	 * Create optimal list of actions needed to solve the board.
	 * @return list of actions to solve the board
	 */
	public List<Action> solve();
	
	/**
	 * Determine if the board is solvable.
	 */
	public boolean isSolvable();
	
	/**
	 * Revert the action given the action and the result of that action
	 * @return whether or not this revert was successful
	 */
	public boolean revertAction(Action action, ActionResult ar);
}
