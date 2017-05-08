package com.Graph;

/**
 * Heuristic strategy for a search algorithm.
 * @param <T> - type of action being performed in the search space
 */
public interface Heuristic<T> {
	/**
	 * Calculate the heuristic cost given a state.
	 * @param state - current state
	 * @return cost value
	 */
	public int hcost(State<T> state);
}
