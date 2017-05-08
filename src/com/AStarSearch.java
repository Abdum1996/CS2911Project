package com;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashMap;

public class AStarSearch<S, A> {
	/**
	 * Perform an A* search on the set of all states.
	 * @return node satisfying goal state, at the end of the optimal route
	 */
	private Node<S, A> runAStarSearch() {
		PriorityQueue<Node<S, A>> open = new PriorityQueue<>();
		HashMap<S, Node<S, A>> closed = new HashMap<>();
		
		open.add();
		while (!open.isEmpty()) {
			Node curr = open.poll();
			closed.put(curr.getState(), curr);
		
			if (curr.isGoalState()) {
				System.out.println(closed.size() + " nodes expanded");
				return curr;
			}
			
			ArrayList<Node> successors = curr.getSuccessors(strategy);
			if (successors == null) return null;
			
			for (Node next : successors) {
				Node.State state = next.getState();
				
				if (closed.containsKey(state)) {
					Node old = closed.get(state);
					if (next.getFCost() < old.getFCost())
						closed.replace(state, old, next);
					continue;
				}
				
				Node old = getFromQueue(open, next);
				if (old != null) {
					if (next.getFCost() < old.getFCost()) {
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
}
