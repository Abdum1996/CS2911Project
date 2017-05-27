package com.Graph;

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.List;

/**
 * AStar search algorithm class.
 * @param <T> - action type
 */
public class AStarSearch<T> {
	private Node<T> endNode;
	
	/**
	 * Creates a new A* search class with a given heuristic strategy,
	 * and perform an A* search on the optimal set of actions to reach
	 * a goal state.
	 * @param strategy  - heuristic evaluation strategy
	 * @param initState - initial state in the search space
	 */
	public AStarSearch(Heuristic<T> strategy, State<T> initState) {
		PriorityQueue<Node<T>> open = new PriorityQueue<>();
		HashMap<State<T>, Node<T>> closed = new HashMap<>();
		endNode = null;
		
		open.add(new Node<>(initState));
		while (!open.isEmpty()) {
			Node<T> curr = open.poll();
			closed.put(curr.getState(), curr);
		
			if (curr.isGoalState()) {
				endNode = curr;
				return;
			}
			
			List<Node<T>> successors = curr.getSuccessors(strategy);
			if (successors == null) return;
			
			for (Node<T> next : successors) {
				State<T> state = next.getState();
				
				if (closed.containsKey(state)) {
					Node<T> old = closed.get(state);
					if (next.getCost() < old.getCost())
						closed.replace(state, old, next);
					continue;
				}
				
				Node<T> old = getFromQueue(open, next);
				if (old != null) {
					if (next.getCost() < old.getCost()) {
						open.remove(old);
						open.add(next);
					}
				} else {
					open.add(next);
				}
			}
		}
	}
	
	/**
	 * Helper function for finding a node in the priority queue.
	 * @param open - frontier of nodes being explored
	 * @param next - next node being considered
	 * @return old node with same state as next node or null if not found
	 */
	private Node<T> getFromQueue(PriorityQueue<Node<T>> open, Node<T> next) {
		for (Node<T> old : open) {
			if (next.getState().equals(old.getState())) 
				return old;
		}
		
		return null;
	}
	
	/**
	 * Determine whether the search space was solvable.
	 * @return true if it was solvable
	 */
	public boolean isSolvable() {
		return (endNode != null);
	}
	
	/**
	 * Get the sequence of optimal actions needed to reach goal state.
	 * @return ordered list of optimal actions
	 */
	public List<T> getActionSequence() {
		return endNode.getActionSequence();
	}
	
	/**
	 * Get the minimal number of actions needed to reach goal state.
	 * @return action count
	 */
	public int getActionCount() {
		return endNode.getState().getActionCount();
	}
}
