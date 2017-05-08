package com;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Node<T> implements Comparable<Node<T>> {
	private final Node<T> parent;
	private final State<T> state;
	private final T action;
	
	private final int pcost;
	private final int hcost;
	
	public Node(State<T> initState) {
		parent = null;
		action = null;
		state  = initState;
		pcost  = 0;
		hcost  = 0;
	}
	
	private Node(Node<T> parent, T action, Heuristic<T> strategy) {
		this.parent = parent;
		this.action = action;
		
		state = parent.state.applyAction(action);
		pcost = parent.pcost + state.getStepCost(parent.state);
		hcost = strategy.hcost(state);
	}
	
	public State<T> getState() {
		return state;
	}

	public boolean isGoalState() {
		return state.isGoalState();
	}
	
	public int getCost() {
		return pcost + hcost;
	}
	
	public List<Node<T>> getSuccessor(Heuristic<T> strategy) {
		List<Node<T>> successors = new ArrayList<>();
		
		for (T curr : state.getValidActions())
			successors.add(new Node<>(this, curr, strategy));
		return successors;
	}
	
	public List<T> getActionSequence() {
		ArrayList<T> sequence = new ArrayList<>();
		Node<T> curr = this;
		
		while (curr.action != null) {
			sequence.add(curr.action);
			curr = curr.parent;
		}
		
		Collections.reverse(sequence);
		return sequence;
	}
	
	public int compareTo(Node<T> other) {
		
	}
}
