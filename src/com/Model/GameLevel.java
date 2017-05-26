package com.Model;

import java.util.LinkedList;
import java.util.Deque;

/**
 * Class representing a game 'level', which consists of a Sokoban board and all
 * the information about the current game pertaining to that board.
 */
public class GameLevel {
	private final Difficulty difficulty;
	private final SokobanBoard board;
	private final int minMoves;
	
	// The below type can be used as both a stack and a queue :o
	private final Deque<ActionResult> pastActionResults;
	private final Deque<Action> pastActions;
	
	private int undoCount = 0;
	private int moveCount = 0;
	private int score = 0;
	
	/**
	 * Construct a new game level with a given board and difficulty.
	 * @param difficulty - difficulty level for this game
	 * @param board - board to be used in the game
	 */
	public GameLevel(Difficulty difficulty, SokobanBoard board) {
		this.board = board;
		
		minMoves = board.getMinMoves();
		this.difficulty = difficulty;
		
		pastActionResults = new LinkedList<>();
		pastActions = new LinkedList<>();
	}	

	/**
	 * Revert the Sokoban board and level to their starting states.
	 */
	public void reset() {
		board.reset();
		
		pastActionResults.clear();
		pastActions.clear();
		
		undoCount = 0;
		moveCount = 0;
		score = 0;
	}
	
	/**
	 * Determine what would have occurred if a player performed a given action.
	 * @param action - action being checked
	 * @return result of that action
	 */
	public ActionResult getActionResult(Action action) {
		return board.getActionResult(action);
	}
	
	/**
	 * Apply an action to the board.
	 * @param action - specified action
	 */
	public void applyAction(Action action) {		
		ActionResult result = getActionResult(action);
		
		pastActionResults.push(result);
		pastActions.push(action);
		
		if (pastActions.size() > difficulty.getUndoDepth()) {
			pastActionResults.removeLast();
			pastActions.removeLast();
		}
		
		board.applyAction(action);
		moveCount++;
		
		// add some metric for score counting in here
	}
	
	/**
	 * If possible, undo the last move executed by the player.
	 */
	public void undoLastMove() {
		if (undoCount >= difficulty.getMaxUndos() ||
				pastActions.isEmpty()) return;
		
		ActionResult result = pastActionResults.pop();
		Action action = pastActions.pop();
		board.revertAction(action, result);
		
		undoCount++;
	}
	
	/**
	 * Get the minimum moves needed to solve the board.
	 * @return minimum moves
	 */
	public int getMinMoves() {
		return minMoves;
	}
	
	/**
	 * Get the total number of undo actions performed by the player.
	 * @return undo count
	 */
	public int getUndoCount() {
		return undoCount;
	}

	/**
	 * Get the total number of moves (actions) performed by the player.
	 * @return move count
	 */
	public int getMoveCount() {
		return moveCount;
	}
	
	/**
	 * Get the player's current score.
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Determine if the game has been won.
	 * @return true if all the boxes are in their goals
	 */
	public boolean hasWon() {
		return !hasLost() && board.gameWon();
	}
	
	/**
	 * Determine if the game has been lost.
	 * @return true if the maximum move count has been exceeded
	 */
	public boolean hasLost() {
		if (!difficulty.equals(Difficulty.HARD)) return false;
		return moveCount > minMoves*2;
	}
	
	/**
	 * Get read-only access to the tiles in the game board.
	 * @return iterable reference to such tiles
	 */
	public Iterable<Tile> getTiles() {
		return board.getTiles();
	}
	
	/**
	 * Get read-only access to the boxes on the game board.
	 * @return iterable reference to such boxes
	 */
	public Iterable<Box> getBoxes() {
		return board.getBoxes();
	}
	
	/**
	 * Get the current state of the player in the game.
	 * @return player
	 */
	public Player getPlayer() {
		return board.getPlayer();
	}
	
	/**
	 * Get the width of the board's map in columns.
	 * @return map width
	 */
	public int getMapWidth() {
		return board.getMapHeight();
	}

	/**
	 * Get the height of the board's map in rows.
	 * @return map height
	 */
	public int getMapHeight() {
		return board.getMapHeight();
	}	
}
