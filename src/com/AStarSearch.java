package com;

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.List;

// TODO - iterative deepening search algorithm (given in one of the research papers)

/**
 * AStar search algorithm class.
 * @param <T> - action type
 */
public class AStarSearch<T> {
	private final Heuristic<T> strategy;
	
	/**
	 * Creates a new A* search class with a given heuristic strategy.
	 * @param strategy  - heuristic evaluation strategy
	 * @param initState - initial state of the search space
	 */
	public AStarSearch(Heuristic<T> strategy, State<T> initState) {
		this.strategy  = strategy;
	} 
	
	/**
	 * Perform an A* search on the optimal set of possible actions to reach a goal state.
	 * @param initState - initial state in search space
	 * @return sequence of actions to reach goal state with the minimal cost
	 */
	public List<T> runAStarSearch(State<T> initState) {
		PriorityQueue<Node<T>> open = new PriorityQueue<>();
		HashMap<State<T>, Node<T>> closed = new HashMap<>();
		
		// What follows is basically the lecture slide A* algorithm translated to java code
		open.add(new Node<>(initState));
		while (!open.isEmpty()) {
			Node<T> curr = open.poll();
			closed.put(curr.getState(), curr);
		
			if (curr.isGoalState())
				return curr.getActionSequence();
			
			List<Node<T>> successors = curr.getSuccessors(strategy);
			if (successors == null) return null;
			
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
		
		return null;
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
}
