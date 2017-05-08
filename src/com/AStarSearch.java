package com;

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.List;

public class AStarSearch<T> {
	private final Heuristic<T> strategy;
	
	public AStarSearch(Heuristic<T> strategy, State<T> initState) {
		this.strategy  = strategy;
	} 
	
	public List<T> runAStarSearch(State<T> initState) {
		PriorityQueue<Node<T>> open = new PriorityQueue<>();
		HashMap<State<T>, Node<T>> closed = new HashMap<>();
		
		open.add(new Node<>(initState));
		while (!open.isEmpty()) {
			Node<T> curr = open.poll();
			closed.put(curr.getState(), curr);
		
			if (curr.isGoalState()) {
				return curr.getActionSequence();
			}
			
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
	
	private Node<T> getFromQueue(PriorityQueue<Node<T>> open, Node<T> next) {
		for (Node<T> old : open) {
			if (next.getState().equals(old.getState())) 
				return old;
		}
		
		return null;
	}
}
