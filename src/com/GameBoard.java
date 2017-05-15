package com;

import java.util.List;

/**
 * Generic interface representing a Sokoban game board consisting
 * of a grid of tiles. This interface is intended to be used by the
 * board solving algorithm and the game's front-end.
 */
public interface GameBoard {
	/**
	 * Builder interface for setting up a new game board.
	 */
	public interface Builder {
		/**
		 * Set the tile at the given point.
		 * @param value - value of the tile
		 * @param point - tile's location
		 */
		public void setTile(Tile value, Point point);
		
		/**
		 * Add a box to the given position in the board.
		 * @param point - location of new box
		 */
		public void addBox(Point point);
		
		/**
		 * Set the position of the player to a given point.
		 * @param point - new location of the player
		 */
		public void setPlayerPos(Point point);
		
		/**
		 * Construct finalized game board from the builder.
		 * @return new game board
		 */
		public GameBoard build();
	}
	
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
	public List<Box> getBoxes();
	
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
	 * @return true if the action is legal
	 */
	public boolean applyAction(Action action);
	
	/**
	 * Create a copy of the board with the applied action
	 * @param action - action applied to board after it is copied
	 * @return new board with action applied to it
	 */
	public GameBoard genSuccessor(Action action);
	
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
}
