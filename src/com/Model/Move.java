package com.Model;

/**
 * Representation of a game move, which consists of an action
 * and the result of performing that action.
 */
public class Move {
	private final Action.Result result;
	private final Action action;
	
	/**
	 * Construct a move which does nothing.
	 * @return move with no action or result
	 */
	public static Move none() {
		return new Move(Action.NO_MOVE, Action.Result.NONE);
	}
	
	/**
	 * Factory method for constructing a new game move.
	 * @param action - action that was applied
	 * @param result - result of that action
	 * @return new move
	 */
	public static Move of(Action action, Action.Result result) {
		return new Move(action, result);
	}
	
	/**
	 * Construct a new game move.
	 * @param action - action that was applied
	 * @param result - result of that action
	 */
	private Move(Action action, Action.Result result) {
		this.action = action;
		this.result = result;
	}
	
	/**
	 * Get the action performed in the move.
	 * @return action performed
	 */
	public Action getAction() {
		return action;
	}
	
	/**
	 * Determine if the move is a box push.
	 * @return true if this is the case
	 */
	public boolean isPush() {
		return result.equals(Action.Result.BOX_MOVE);
	}
	
	/**
	 * Determine if a given move has no result.
	 * @return true if this is the case
	 */
	public boolean doesNothing() {
		return result.equals(Action.Result.NONE);
	}
}
