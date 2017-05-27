package com.Model;

/**
 * Generic interface representing a game level.
 */
public interface GameLevel {
	public enum GameState { LOST, NOT_WON, WON }
	
	/**
	 * Reset the level to its starting state.
	 */
	public void reset();
	
	/**
	 * Determine what would happen if an action were applied to the board.
	 * @param action - action being checked
	 * @return move resulting from that action
	 */
	public Move getResultingMove(Action action);
	
	/**
	 * Apply an action to the game.
	 * @param action - action being performed
	 */
	public void applyAction(Action action);
	
	/**
	 * If possible, undo the last move executed by the player.
	 */
	public void undoLastMove();
	
	/**
	 * Determine if the game has been lost, won or neither.
	 * @return current state of the level
	 */
	public GameState getGameState();
	
	/**
	 * Determine if the game, given its current state, is solvable.
	 * @return true if there is exists set of actions to reach a goal state
	 */
	public boolean isSolvable();
	
	/**
	 * Get the game's difficulty level.
	 * @return difficulty level
	 */
	public Difficulty getDifficulty();
	
	/**
	 * Get the minimum number of pushes needed to solve the level.
	 * @return minimum pushes
	 */
	public int getMinPushes();
	
	/**
	 * Get the total number of pushes performed by the player.
	 * @return push count
	 */
	public int getPushCount();
	
	/**
	 * Get the total number of moves undone by the player.
	 * @return undo count
	 */
	public int getUndoCount();
	
	/**
	 * Get the tile at the given position in the level's board.
	 * @param pos - location of requested tile
	 * @return requested tile
	 */
	public Tile getTile(Point pos);
	
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
