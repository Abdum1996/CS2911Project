package com;

import java.util.List;

/**
 * Interface for node in a search space.
 * @param <S> - state in search space
 * @param <A> - action which can be performed on a state
 */
public interface Node<S, A> {
	/**
	 * Get the node's state.
	 * @return state
	 */
	public S getState();
	
	/**
	 * Determine if a goal state has been reached or not.
	 * @return true if the node contains a goal state
	 */
	public boolean isGoalState();
	
	/**
	 *  Get the total cost of traveling to the node from the starting state.
	 * @return path cost
	 */
	public int getCost();
	
	
	/**
	 * Generate a list of successors to the node by applying set of all valid actions to it.
	 * @return list of successor nodes
	 */
	public List<Node<S, A>> getSuccessors();
	
	/**
	 * Reconstruct the sequence of actions needed to produce this given node.
	 * @return list of actions
	 */
	public List<A> getActionSequence();
}
