package com;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic node in a search space.
 * @param <T> - type of action performed (likely an enumeration)
 */
public class Node<T> implements Comparable<Node<T>> {
	private final Node<T> parent;
	private final State<T> state;
	private final T action;
	
	private final int pcost;
	private final int hcost;
	
	/**
	 * Construct a node from an initial state in the search space.
	 * @param initState - initial state
	 */
	public Node(State<T> initState) {
		parent = null;
		action = null;
		state  = initState;
		pcost  = 0;
		hcost  = 0;
	}
	
	/**
	 * Private constructor that produces a successor to a node.
	 * @param parent   - node to which an action is applied
	 * @param action   - the action applied to the parent node
	 * @param strategy - method for calculating the heuristic cost
	 */
	private Node(Node<T> parent, T action, Heuristic<T> strategy) {
		this.parent = parent;
		this.action = action;
		
		state = parent.state.applyAction(action);
		pcost = parent.pcost + state.getStepCost(parent.state);
		hcost = strategy.hcost(state);
	}
	
	/**
	 * Get the state encapsulated by the node.
	 * @return current state
	 */
	public State<T> getState() {
		return state;
	}

	/**
	 * Determines if the current node is at goal state.
	 * @return true if it is at a goal state
	 */
	public boolean isGoalState() {
		return state.isGoalState();
	}
	
	/**
	 * Apply the cost function to the node.
	 * @return node path cost plus heuristic cost
	 */
	public int getCost() {
		return pcost + hcost;
	}
	
	/**
	 * Get the set of all successors to a given node.
	 * @param strategy - method for calculating the heuristic cost
	 * @return list of successor nodes
	 */
	public List<Node<T>> getSuccessors(Heuristic<T> strategy) {
		List<Node<T>> successors = new ArrayList<>();
		
		for (T curr : state.getValidActions())
			successors.add(new Node<>(this, curr, strategy));
		return successors;
	}
	
	/**
	 * Reconstruct the sequence of actions needed to get to the current node.
	 * @return list of actions
	 */
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
	
	/**
	 * Compare the total cost of this node with another.
	 */
	public int compareTo(Node<T> other) {
		return Integer.compare(getCost(), other.getCost());
	}
}
