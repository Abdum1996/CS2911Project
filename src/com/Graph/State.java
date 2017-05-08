package com.Graph;

import java.util.List;

/**
 * Generic class representing a state in a search space.
 * @param <T> - type of action which can be applied to a state
 */
public interface State<T> {
	/**
	 * Get the cost of traveling from the previous state to the current state.
	 * @param prev - previous state
	 * @return step cost
	 */
	public int getStepCost(State<T> prev);
	
	/**
	 * Apply action to a state to get a new state.
	 * @param action - action applied
	 * @return new state
	 */
	public State<T> applyAction(T action);
	
	/**
	 * Get a list of all the legal actions which can be performed on this state.
	 * @return list of valid actions
	 */
	public List<T> getValidActions();
	
	/**
	 * Determine if the current state is a goal state.
	 * @return true if the state is a goal state
	 */
	public boolean isGoalState();
}
