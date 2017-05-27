package com.Model;

/**
 * Representation of a game move, which consists of an action
 * and the result of performing that action.
 */
public class Move {
	private final ActionResult result;
	private final Action action;
	
	/**
	 * Construct a new game move.
	 * @param action - action that was applied
	 * @param result - result of that action
	 */
	public Move(Action action, ActionResult result) {
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
	 * Get the result of performing the move.
	 * @return result of move
	 */
	public ActionResult getActionResult() {
		return result;
	}
	
	/**
	 * Determine if the move is a box push.
	 * @return true if this is the case
	 */
	public boolean isPush() {
		return result.equals(ActionResult.BOX_MOVE);
	}
}
