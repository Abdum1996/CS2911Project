package com.Model;

/**
 * Generic interface representing a game level. Note that player
 * movement is not considered to be a game move.
 */
public interface GameLevel {
	public enum State { UNSOLVABLE, SOLVABLE, WON }
	
	/**
	 * Reset the level to its starting state.
	 */
	public void reset();
	
	/**
	 * Determine if a given action is valid.
	 * @param action - action to be applied to the game
	 * @return true if the action is legal
	 */
	public boolean isValidAction(Action action);
	
	/**
	 * Apply an action to the game.
	 * @param action - action to be performed
	 */
	public void applyAction(Action action);
	
	/**
	 * If possible, undo the last move executed by the player.
	 */
	public void undoLastMove();
	
	/**
	 * Get the game's difficulty level.
	 * @return difficulty level
	 */
	public Difficulty getDifficulty();
	
	/**
	 * Get the minimum number of moves needed to solve the level.
	 * @return minimum moves
	 */
	public int getMinMoves();
	
	/**
	 * Get the total number of moves undone by the player.
	 * @return undo count
	 */
	public int getUndoCount();
	
	/**
	 * Get the total number of moves performed by the player.
	 * @return move count
	 */
	public int getMoveCount();
	
	/**
	 * Determine if the game is unsolvable, solvable or is in a winning state.
	 * @return current state of the level
	 */
	public State getGameState();
	
	/**
	 * Get read-only access to the tiles in the game board.
	 * @return iterable reference to such tiles
	 */
	public Iterable<Tile> getTiles();
	
	/**
	 * Get read-only access to the boxes on the game board.
	 * @return iterable reference to such boxes
	 */
	public Iterable<Box> getBoxes();
	
	/**
	 * Get the current representation of a player in the game.
	 * @return player
	 */
	public Player getPlayer();
	
	/**
	 * Get the width of the game's map in columns.
	 * @return map width
	 */
	public int getMapWidth();
	
	/**
	 * Get the height of the game map's in rows.
	 * @return map height
	 */
	public int getMapHeight();
}
